/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.service;

import java.util.ArrayList;
import java.util.Date;
import com.model.Product;

/**
 *
 * @author TgNam
 */
public interface ProductService {
    ArrayList<Product> getList_sale();
    String updateSale_ID_created_at(Date created_at, String id);
    void updateSale_ID(String id);
    //them ngay 23/11
    ArrayList<Product> getNext(String tensp,int min, int max);
    ArrayList<Product> getProduct_sell();
    ArrayList<Product> getProduct_Stop_selling();
    public boolean them(Product prd);
    public boolean sua(String id, Product prd);
    public boolean xoa(String name);
    //them cai nay ngay25/11
    
    ArrayList<Product> getNext_Product_Stop_selling(int min, int max);
    public boolean KhoiPhuc(String id);
    boolean delete_product_bill(String id);
    Product getProcuct(String id);
}
