/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.repository;

import java.util.ArrayList;
import java.sql.*;
import com.model.Thickness;

/**
 *
 * @author thiet
 */
public class ThicknessRepository {
   
     public ArrayList<Thickness> getListThikness() {
        ArrayList<Thickness> List = new ArrayList<>();
        try {
            String sql = "select id, gsm, created_at, updated_at , status from thickness;";
            ResultSet rs =  JDBCHelped.executeQuery(sql);
            while(rs.next()){
               Thickness t;
                String id = rs.getString(1);
                int gsm = rs.getInt(2);
                Date created_at = rs.getDate(3);
                Date  updated_at = rs.getDate(4);
                boolean status = rs.getBoolean(5);
                t = new Thickness(gsm, status, created_at, id, updated_at);
                List.add(t);
            }
            return List;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }
    
     public boolean Insert(Thickness tk){
        try {
            String sql = "INSERT INTO `db_levents`.`thickness` (`gsm`, `status`, `created_at`, `updated_at`) VALUES (?, 1, curdate(), curdate());";
            JDBCHelped.excuteUpdate(sql, tk.getGsm());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean Update(String id, Thickness tk){
        try {
            String sql = "UPDATE `db_levents`.`thickness` SET `updated_at` = curdate(), `gsm` = ? WHERE (`id` = ?);";
            JDBCHelped.excuteUpdate(sql, tk.getGsm(), id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
     //them vao 1/12
    public boolean Delete(String id) {
        try {
            String sql = "update db_levents.thickness set db_levents.thickness.status = 0 where db_levents.thickness.id = ?;";
            JDBCHelped.excuteUpdate(sql,  id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public ArrayList<Thickness> getThickness_sell(int min, int max) {
        ArrayList<Thickness> List = new ArrayList<>();
        try {
            String sql = """
                         select * from 
                         (select id, gsm, created_at, updated_at , status,ROW_NUMBER() OVER (ORDER BY thickness.id) AS rownum  
                         from thickness where  status = 1) AS temp WHERE rownum BETWEEN ? AND ?;   
                         """;
            ResultSet rs =  JDBCHelped.executeQuery(sql, min,max);
            while(rs.next()){
               Thickness t;
                String id = rs.getString(1);
                int gsm = rs.getInt(2);
                Date created_at = rs.getDate(3);
                Date  updated_at = rs.getDate(4);
                boolean status = rs.getBoolean(5);
                t = new Thickness(gsm, status, created_at, id, updated_at);
                List.add(t);
            }
            return List;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }
    //them vao 12/12
    
    public ArrayList<Thickness> getCBB() {
        ArrayList<Thickness> List = new ArrayList<>();
        try {
            String sql = "select id, gsm, created_at, updated_at , status from thickness where status = 1;";
            ResultSet rs =  JDBCHelped.executeQuery(sql);
            while(rs.next()){
               Thickness t;
                String id = rs.getString(1);
                int gsm = rs.getInt(2);
                Date created_at = rs.getDate(3);
                Date  updated_at = rs.getDate(4);
                boolean status = rs.getBoolean(5);
                t = new Thickness(gsm, status, created_at, id, updated_at);
                List.add(t);
            }
            return List;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }
    
    public boolean KhoiPhuc(String id) {
        try {
            String sql = "update db_levents.thickness set db_levents.thickness.status = 1 where db_levents.thickness.id = ?;";
            JDBCHelped.excuteUpdate(sql,  id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public ArrayList<Thickness> getThickness_Stop(int min, int max) {
        ArrayList<Thickness> List = new ArrayList<>();
        try {
            String sql = """
                         select * from 
                         (select id, gsm, created_at, updated_at , status,ROW_NUMBER() OVER (ORDER BY thickness.id) AS rownum  
                         from thickness where  status = 0) AS temp WHERE rownum BETWEEN ? AND ?;   
                         """;
            ResultSet rs =  JDBCHelped.executeQuery(sql, min,max);
            while(rs.next()){
               Thickness t;
                String id = rs.getString(1);
                int gsm = rs.getInt(2);
                Date created_at = rs.getDate(3);
                Date  updated_at = rs.getDate(4);
                boolean status = rs.getBoolean(5);
                t = new Thickness(gsm, status, created_at, id, updated_at);
                List.add(t);
            }
            return List;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }
    
}
