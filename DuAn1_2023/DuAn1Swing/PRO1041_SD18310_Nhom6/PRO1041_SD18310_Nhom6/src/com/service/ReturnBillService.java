package com.service;

import com.model.ReturnBill;

/**
 *
 * @author lenovo
 */
public interface ReturnBillService {
    boolean insert(ReturnBill id);
    ReturnBill getByIdBill(String idBill);
    ReturnBill getById(String id);
    ReturnBill getBy_IdBill(String idBill);
    boolean update_status(ReturnBill returnBill);
    boolean delete_returnBill(String id);
}
