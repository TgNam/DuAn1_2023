package com.nhom_6.duan_1.serviceImp;

import com.nhom_6.duan_1.model.entity.*;
import com.nhom_6.duan_1.model.req.CartReq;
import com.nhom_6.duan_1.model.req.ProductReq;
import com.nhom_6.duan_1.repository.BillDetailsResponsitory;
import com.nhom_6.duan_1.repository.BillResponsitory;
import com.nhom_6.duan_1.repository.UserResponsitory;
import com.nhom_6.duan_1.repository.VoucherResponsitory;
import com.nhom_6.duan_1.service.BillService;
import com.nhom_6.duan_1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BillServiceImp implements BillService {

    @Autowired
    BillResponsitory billResponsitory;

    @Autowired
    UserServiceImp userService;

    @Autowired
    UserResponsitory userResponsitory;

    @Autowired
    BillDetailsResponsitory billDetailsResponsitory;

    @Autowired
    ProductDetailsServiceImp productDetailsServiceImp;

    @Autowired
    ProductService productService;

    @Autowired
    VoucherResponsitory voucherResponsitory;

    public Bill getCartByUserId(Long id) {
        return  billResponsitory.getCartByUserId(id)
                .orElse(null);
    }
    public Bill getById(Long id) {
        return billResponsitory.findById(id)
                .orElse(null);
    }

    public void updateCart(CartReq entity) throws Exception {
        Bill  cart = getById(entity.getCartId());
        if(cart == null )
            throw new Exception("Cart not found");
        billDetailsResponsitory.deleteAll(cart.getBillDetails());
        List<BillDetails> cartDetails = new ArrayList<>();
        Double total = 0.0;
        for(int i = 0; i < entity.getData().size(); i++){
            ProductReq prd = entity.getData().get(i);
            ProductDetail productDetail = productDetailsServiceImp.getById(prd.getId());
            BillDetails billDetails = new BillDetails();
            billDetails.setProductDetail(productDetail);
            billDetails.setQuantityUrchased(prd.getQuantity());
            billDetails.setPriceNow(productDetail.getProduct().getProductPrice());
            billDetails.setBill(cart);
            billDetailsResponsitory.save(billDetails);
            cartDetails.add(billDetails);
            total +=(billDetails.getPriceNow() * Double.parseDouble(billDetails.getQuantityUrchased()));
        }
        cart.setTotalCost(total);
        if(cart.getVoucher() != null){
            cart.setIntoMoney(cart.getTotalCost() - (cart.getTotalCost() * (cart.getVoucher().getSaleOf()/100)) );
        }else{
            cart.setIntoMoney(cart.getTotalCost());
        }
        cart.setBillDetails(cartDetails);
        billResponsitory.save(cart);
    }
    public void addCart(CartReq cartReq,Long userId,Long size,Long color) {
        Bill cart = getCartByUserId(userId);
        ProductReq prd = cartReq.getData().get(0);
        User u = userService.getById(userId);
        if (cart == null){
            cart = new Bill();
            cart.setUser(u);
            cart.setStatus("-1");
            cart.setBillDetails(new ArrayList<>());
            cart.setTotalCost(0.0);
            cart.setIntoMoney(0.0);
            billResponsitory.save(cart);
        }
        Product product= productService.getById(prd.getId());
        ProductDetail productDetail = new ProductDetail();
        for (ProductDetail pd : product.getProductDetails()){
            if(pd.getColor().getId() == color && pd.getSize().getId() == size){
                productDetail = pd;
                break;
            }
        }
        for(int i = 0; i < cart.getBillDetails().size(); i++) {
            BillDetails billDetails = cart.getBillDetails().get(i);
            if(billDetails.getProductDetail().getId() == prd.getId()) {
                billDetails.setQuantityUrchased(prd.getQuantity());
                billDetailsResponsitory.save(billDetails);
                return;
            }
        }
        BillDetails bD = new BillDetails();
        bD.setProductDetail(productDetail);
        bD.setQuantityUrchased(prd.getQuantity());
        bD.setPriceNow(productDetail.getProduct().getProductPrice());
        bD.setBill(cart);

        billDetailsResponsitory.save(bD);

        u.getBills().add(cart);
        userResponsitory.save(u);

        cart.getBillDetails().add(bD);
        cart.setTotalCost(cart.getTotalCost() + productDetail.getProduct().getProductPrice());

        if(cart.getVoucher() != null){
            cart.setIntoMoney(cart.getTotalCost() - (cart.getTotalCost() * (cart.getVoucher().getSaleOf()/100)) );
        }else{
            cart.setIntoMoney(cart.getTotalCost());
        }

        billResponsitory.save(cart);
    }

    @Override
    public Bill getCart(Long id) {
        return null;
    }

    public void addVoucher(String code,Long userId) throws Exception {
        Bill cart = getCartByUserId(userId);
        Long codeLong =0L;
        try {
            codeLong = Long.parseLong(code);
        }catch (Exception e){
            throw new Exception("Code Không Đúng");
        }

        Voucher voucher = voucherResponsitory.findById(codeLong)
                .orElseThrow(()->{
                        try {
                            throw new Exception("Code Không Đúng");
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                });
        Date date1 = new Date();
        Date date2 = voucher.getEndAt();
        if (date1.after(date2)) {
            throw new Exception("Code Hết Hạn");
        }

        cart.setVoucher(voucher);
        cart.setIntoMoney(cart.getTotalCost() - (cart.getTotalCost() * (cart.getVoucher().getSaleOf()/100)));
        billResponsitory.save(cart);
        voucher.getBills().add(cart);
        voucherResponsitory.save(voucher);

    }
}
