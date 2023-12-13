/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.service.imple;

import com.model.ReturnBill;
import com.repository.Return_Bill_Repository;
import com.service.ReturnBillService;

/**
 *
 * @author lenovo
 */
public class ReturnBillImple implements ReturnBillService {

    private Return_Bill_Repository returnBillRepository = new Return_Bill_Repository();

    @Override
    public boolean insert(ReturnBill returnBill) {
        return returnBillRepository.insert(returnBill);
    }

    @Override
    public ReturnBill getByIdBill(String idBill) {
        ReturnBill returnBill = returnBillRepository.getByIdBill(idBill);
        if (returnBill!=null) {
            return returnBill;
        }else{
            return null;
        }     
    }

    @Override
    public ReturnBill getById(String id) {
        return returnBillRepository.getById(id);
    }

    @Override
    public ReturnBill getBy_IdBill(String idBill) {
        ReturnBill returnBill = returnBillRepository.getBy_IdBill(idBill);
        if (returnBill!=null) {
            return returnBill;
        }else{
            return null;
        }     
    }

    @Override
    public boolean update_status(ReturnBill returnBill) {
        if (returnBillRepository.update_status(returnBill)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete_returnBill(String id) {
        if(returnBillRepository.delete_returnBill(id)){
            return true;
        }else{
            return false;
        }
    }
}
