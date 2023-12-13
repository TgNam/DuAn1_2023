/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.service;

import java.util.ArrayList;
import com.model.Bill;
import java.math.BigDecimal;

/**
 *
 * @author TgNam
 */
public interface BillService {
    ArrayList<Bill> getListBill_0();
    ArrayList<Bill> getBill_status(String status1,String status2);
    String add_bill(Bill b);
    //them thang getall
    ArrayList<Bill> getAll();
    //Xóa bill thông qua id
    boolean delete_bill_id(Bill b);
    // link
    Bill getById(Long id);
    boolean updateStatusById(String id, int status);
    boolean updateVoucherByIdBill(String voucher_id, String id);
    boolean updatemoneyByIdBill(BigDecimal into_money, BigDecimal total_cost, String id);
    void printerBill(Long id);
    boolean update_Status(String status,String idBill);
    boolean update_address(String address_id,String idBill);
}
