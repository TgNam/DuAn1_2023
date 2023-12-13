/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.repository;

import com.model.ProductCategory;

/**
 *
 * @author thiet
 */
public class Product_CategoryRepository {
    public boolean Insert(ProductCategory pc){
        try {
            String sql = "insert into db_levents.product_category(category_id, product_id) values(?,?);";
            JDBCHelped.excuteUpdate(sql, pc.getCategoryId().getId(),pc.getProductId().getId());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean Delete(ProductCategory pc){
        try {
            String sql = "delete from db_levents.product_category where category_id = ? and product_id = ?;";
            JDBCHelped.excuteUpdate(sql, pc.getCategoryId().getId(),pc.getProductId().getId());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
