/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.service.imple;

import java.util.ArrayList;
import com.model.Bill;
import com.model.BillDetail;
import com.repository.BillDetailRepository;
import com.service.BillDetailService;
import java.math.BigDecimal;

/**
 *
 * @author TgNam
 */
public class BillDetailImple implements BillDetailService {

    private BillDetailRepository bdr = new BillDetailRepository();

    @Override
    public ArrayList<BillDetail> getBill_idBill(String id) {
        return bdr.getBill_idBill(id);
    }

    @Override
    public boolean add_bill_datail(BillDetail bd, String bill_id, String pd_id) {
        if (bdr.add_bill_datail(bd, bill_id, pd_id)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String delete_product_datail(String bill_id, String pd_id) {
        if (bdr.delete_product_datail(bill_id, pd_id)) {
            return "Xóa thành công!";
        } else {
            return "Xóa thất bại!";
        }
    }

    //them cai nay 22-11 2:45sa
    @Override
    public ArrayList<BillDetail> getbill_all(String id) {
        return this.bdr.getBill_all(id);
    }

    @Override
    public boolean delete_bill_datail_ShoppingCart(BillDetail bd) {
        if (bdr.delete_bill_datail_ShoppingCart(bd)) {
            return true;
        } else {
            return false;
        }
    }
    //Xóa billdetail của phần giỏ hàng thông qua id của bill
    @Override
    public boolean delete_bill_datail(Bill b) {
        if (bdr.delete_bill_datail(b)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean Update_bill_datail(int quantity_urchased, String billDetail_id) {
        if (bdr.Update_bill_datail(quantity_urchased, billDetail_id)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ArrayList<BillDetail> getBill_idBill_0(String id) {
        return bdr.getBill_idBill_0(id);
    }

    @Override
    public boolean updateprice_nowByIdBillDetail(BigDecimal price_now, String id) {
        if (bdr.updateprice_nowByIdBillDetail(price_now, id)) {
            return true;
        } else {
            return false;
        }
    }
}
