/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.service.imple;

import java.util.ArrayList;
import java.util.Date;
import com.model.Product;
import com.repository.ProductRepository;
import com.service.ProductService;

/**
 *
 * @author TgNam
 */
public class ProductImple implements ProductService {

    ProductRepository pr = new ProductRepository();

    @Override
    public ArrayList<Product> getList_sale() {
        return pr.getAll_sale();
    }

    @Override
    public String updateSale_ID_created_at(Date created_at, String id) {
        if (pr.updateSale_ID_created_at(created_at, id)) {
            return "Thêm sản phẩm sale thành công!";
        } else {
            return "Thêm sản phẩm sale thất bại!";
        }
    }

    @Override
    public void updateSale_ID(String id) {
        pr.updateSale_ID(id);
    }
//thewm phan nay

    @Override
    public ArrayList<Product> getNext(String tensp, int min, int max) {
        return pr.getNext(tensp,min, max);
    }

    @Override
    public ArrayList<Product> getProduct_sell() {
        return pr.getListSP_Sell();
    }

    @Override
    public ArrayList<Product> getProduct_Stop_selling() {
        return pr.getListSP_Stop_Selling();
    }

    @Override
    public boolean them(Product prd) {
        if (pr.Insert(prd)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean sua(String id, Product prd) {
        if (pr.Update(id, prd)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean xoa(String id) {
        if (pr.Delete(id)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ArrayList<Product> getNext_Product_Stop_selling(int min, int max) {
        return pr.getNext_Product_stop_selling(min, max);
    }

    @Override
    public boolean KhoiPhuc(String id) {
        return pr.Restore(id);
    }

    @Override
    public boolean delete_product_bill(String id) {
        if (pr.delete_product_bill(id)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Product getProcuct(String id) {
        return pr.getProcuct(id);
    }
}
