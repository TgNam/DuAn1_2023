/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.service.imple;

import java.util.ArrayList;
import com.model.Bill;
import com.model.BillDetail;
import com.model.response.BillRes;
import com.model.response.ProductRes;
import com.model.system.Word;
import com.repository.BillRepository;
import com.service.BillService;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author TgNam
 */
public class BillImple implements BillService {

    BillRepository br = new BillRepository();
    BillDetailImple billDetailService = new BillDetailImple();

    @Override
    public ArrayList<Bill> getListBill_0() {
        return br.getBill_0();
    }

    @Override
    public String add_bill(Bill b) {
        if (br.add_bill(b)) {
            return "Thêm thành công!";
        } else {
            return "Thêm thất bại!";
        }
    }

    //lk vs inter
    @Override
    public ArrayList<Bill> getAll() {
        return br.getBil_All();
    }

    //Xóa bill thông qua id
    @Override
    public boolean delete_bill_id(Bill b) {
        if (br.delete_bill_id(b)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Bill getById(Long id) {
        return br.getById(id);
    }

    @Override
    public ArrayList<Bill> getBill_status(String status1, String status2) {
        return br.getBill_status(status1, status2);
    }

    @Override
    public boolean updateStatusById(String id, int status) {
        return br.updateStatusById(id, status);
    }

    @Override
    public boolean updateVoucherByIdBill(String voucher_id, String id) {
        if (br.updateVoucherByIdBill(voucher_id, id)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updatemoneyByIdBill(BigDecimal into_money, BigDecimal total_cost, String id) {
        if (br.updatemoneyByIdBill(into_money, total_cost, id)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void printerBill(Long id) {

        Bill bill = getById(id);
        List<BillDetail> billDetails = billDetailService.getBill_idBill(id.toString());

        BillRes billRes = new BillRes();

        billRes.setCode(bill.getId());
        billRes.setCustomeName(bill.getUserId().getFullName());
        billRes.setCustomeAddress(bill.getAddressId().getAddressDetail());
        billRes.setCustomePhone(bill.getUserId().getNumberPhone());
        billRes.setTotal(bill.getTotalCost());
        BigDecimal total = BigDecimal.ZERO;
        List<ProductRes> list = new ArrayList<>();

        for (BillDetail e : billDetails) {
            String code = e.getProductDetailId().getId();
            String name = e.getProductDetailId().getProductId().getName_product();
            Integer quantity = Integer.valueOf(e.getQuantityPurchased());
            BigDecimal unitPriceBigDecimal = e.getProductDetailId().getProductId().getProduct_price();

            BigDecimal quantityBigDecimal = BigDecimal.valueOf(quantity);
            BigDecimal totalProduct = unitPriceBigDecimal.multiply(quantityBigDecimal);

            list.add(new ProductRes(
                    code,
                    name,
                    quantity,
                    unitPriceBigDecimal,
                    totalProduct
            ));
            total = total.add(totalProduct);
        }

        BigDecimal saleOf = BigDecimal.ZERO;
        if (bill.getVoucherId() != null) {
            saleOf = BigDecimal.valueOf(bill.getVoucherId().getSaleOf());
        }

        BigDecimal discountRate = saleOf.divide(BigDecimal.valueOf(100)); // Tính tỷ lệ giảm dựa trên voucher

        BigDecimal giam = total.multiply(discountRate);

        total = total.subtract(giam);
        billRes.setSaleOfMoney(giam);
        billRes.setTotal(total);
        billRes.setProducts(list);

        Word.createFile(billRes);

    }

    @Override
    public boolean update_Status(String status, String idBill) {
        if (br.update_Status(status, idBill)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean update_address(String address_id, String idBill) {
        if (br.update_address(address_id, idBill)) {
            return true;
        } else {
            return false;
        }
    }
}
