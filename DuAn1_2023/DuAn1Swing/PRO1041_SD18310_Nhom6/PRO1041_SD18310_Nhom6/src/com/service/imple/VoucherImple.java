/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.service.imple;

import java.util.List;
import com.model.Voucher;
import com.repository.VoucherResponsitory;
import com.service.VoucherService;

/**
 *
 * @author lenovo
 */
public class VoucherImple implements VoucherService {

    private VoucherResponsitory voucherResponsitory = new VoucherResponsitory();

    @Override
    public Voucher getById(String id) {
        return voucherResponsitory.getById(id);
    }

    @Override
    public boolean insert(Voucher vc) {
        return voucherResponsitory.create(vc);
    }

    @Override
    public boolean update(Voucher vc) {
        return voucherResponsitory.update(vc);
    }

    @Override
    public boolean delete(String id) {
        return voucherResponsitory.remove(id);
    }

    @Override
    public List<Voucher> getAll() {
        return voucherResponsitory.getAll();
    }

    @Override
    public boolean resetStatus() {
        return voucherResponsitory.resetStatus();
    }

}
