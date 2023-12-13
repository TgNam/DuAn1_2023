/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.service;

import java.util.ArrayList;
import com.model.Bill;
import com.model.BillDetail;
import java.math.BigDecimal;

/**
 *
 * @author TgNam
 */
public interface BillDetailService {

    ArrayList<BillDetail> getBill_idBill(String id);

    boolean add_bill_datail(BillDetail bd, String bill_id, String pd_id);

    boolean Update_bill_datail(int quantity_urchased, String billDetail_id);

    boolean delete_bill_datail_ShoppingCart(BillDetail bd);

    String delete_product_datail(String bill_id, String pd_id);
    //them cai nay 22-11 2:45sa

    ArrayList<BillDetail> getbill_all(String id);

     //Xóa billdetail của phần giỏ hàng thông qua id của bill
    boolean delete_bill_datail(Bill b);
    boolean updateprice_nowByIdBillDetail(BigDecimal price_now, String id);
    ArrayList<BillDetail> getBill_idBill_0(String id);
}
