/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.service.imple;

import java.util.ArrayList;
import java.util.Date;
import com.model.SaleProduct;
import com.repository.SaleProductRepository;
import com.service.SaleProductService;

/**
 *
 * @author TgNam
 */
public class SaleProductImple implements SaleProductService{
    SaleProductRepository spr = new SaleProductRepository();

    @Override
    public ArrayList<SaleProduct> getList() {
        return spr.getAll();
    }

    @Override
    public String add(SaleProduct sp) {
        if (spr.add(sp)) {
            return "Thêm thành công!";
        }
        else{
            return "Thêm thất bại!";
        }
    }

    @Override
    public String delete_ID_created_at(Date created_at) {
        if (spr.delete_ID_created_at(created_at)) {
            return "Xóa thành công!";
        }
        else{
            return "Xóa thất bại!";
        }
    }

    @Override
    public String delete_ID(String id) {
        if (spr.delete_ID(id)) {
            return "Xóa thành công!";
        }
        else{
            return "Xóa thất bại!";
        }
    }

    @Override
    public String update(SaleProduct sp, String id) {
        if (spr.Update(sp, id)) {
            return "Update thành công!";
        }
        else{
            return "Update thất bại!";
        }
    }
}
