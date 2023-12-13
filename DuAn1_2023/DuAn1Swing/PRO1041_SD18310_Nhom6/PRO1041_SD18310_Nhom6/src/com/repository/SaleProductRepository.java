/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.repository;
import java.sql.*;
import java.util.ArrayList;
import com.model.SaleProduct;
import java.util.Date;
/**
 *
 * @author TgNam
 */
public class SaleProductRepository {
     public ArrayList<SaleProduct> getAll() {
        ArrayList<SaleProduct> list = new ArrayList<>();
        try {
            String sql = "SELECT id, created_at, updated_at, start_at, end_at, sale, status FROM sale_product;";
            ResultSet rs = JDBCHelped.executeQuery(sql);
            while (rs.next()) {
                list.add(new  SaleProduct(rs.getString(1), rs.getDate(2), rs.getDate(3), rs.getDate(4), rs.getDate(5), rs.getDouble(6), rs.getString(7))            
            );
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
     public boolean add(SaleProduct sp){
        try {
            String sql = "INSERT INTO sale_product (created_at, updated_at, start_at, sale, end_at, status)\n" +
                            "VALUES (?, ?, ?, ?, ?, ?)";
        JDBCHelped.excuteUpdate(sql, sp.getCreated_at(),sp.getUpdated_at(), sp.getStart_at(), sp.getSale() ,sp.getEnd_at(), sp.getStatus());
        return true;
        } catch (Exception e) {
            return false;
        }
    }
     public boolean delete_ID_created_at(Date created_at){
        try {
            String sql = "DELETE FROM db_levents.sale_product WHERE id = (SELECT id FROM (SELECT id FROM db_levents.sale_product WHERE created_at = ?) AS temp);";
        JDBCHelped.excuteUpdate(sql, created_at);
        return true;
        } catch (Exception e) {
            return false;
        }
    }
     public boolean delete_ID(String id){
        try {
            String sql = "DELETE FROM db_levents.sale_product WHERE id = ?;";
        JDBCHelped.excuteUpdate(sql, id);
        return true;
        } catch (Exception e) {
            return false;
        }
    }
     public boolean Update(SaleProduct sp, String id){
        try {
            String sql = "update db_levents.sale_product set updated_at = ?, sale = ?,  start_at = ?, end_at = ? , status = ? where id = ?;";
        JDBCHelped.excuteUpdate(sql, sp.getUpdated_at(), sp.getSale(), sp.getStart_at(), sp.getEnd_at(),sp.getStatus(), id);
        return true;
        } catch (Exception e) {
            return false;
        }
    } 
}
