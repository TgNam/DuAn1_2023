/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.service;

import com.model.ReturnBill;
import java.util.List;
import com.model.ReturnBillDetail;

/**
 *
 * @author lenovo
 */
public interface ReturnBillDetailService {
    boolean insert(com.model.ReturnBillDetail returnBillDetail);
    public List<ReturnBillDetail> getAll();
    public List<ReturnBillDetail> getByIdBill(String idBill);
    boolean delete_returnBillDetal(ReturnBill returnBill);
}
