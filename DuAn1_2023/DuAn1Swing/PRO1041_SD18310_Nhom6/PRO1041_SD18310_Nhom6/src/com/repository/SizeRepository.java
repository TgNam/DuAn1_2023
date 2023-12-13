/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.model.Color;
import com.model.Size;

/**
 *
 * @author TgNam
 */
public class SizeRepository {
    public ArrayList<Size> getListSize() {
        ArrayList<Size> List = new ArrayList<>();
        try {
            String sql = "select db_levents.size.id, db_levents.size.name_size, db_levents.size.created_at, db_levents.size.updated_at from db_levents.size;";
            ResultSet rs =  JDBCHelped.executeQuery(sql);
            while(rs.next()){
                Size s;
                String id = rs.getString(1);
                String name = rs.getString(2);
                Date created_at = rs.getDate(3);
                Date  updated_at = rs.getDate(4);
                s = new Size(created_at, id, updated_at, name);
                List.add(s);
            }
            return List;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }
    
    public boolean Insert(Size s){
        try {
            String sql = "INSERT INTO db_levents.size (created_at,updated_at, name_size, statuss) VALUES (curdate(),curdate(),?,'1');";
            JDBCHelped.excuteUpdate(sql, s.getNameSize());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean Update(String id,Size s){
        try {
            String sql = "UPDATE `db_levents`.`size` SET `updated_at` = curdate(), `name_size` = ? WHERE (`id` = ?);";
            JDBCHelped.excuteUpdate(sql, s.getNameSize(), id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
     //them vao 1/12
    public boolean Delete(String id){
        try {
            String sql = "update db_levents.size set db_levents.size.statuss = '0' where db_levents.size.id = ?;";
            JDBCHelped.excuteUpdate(sql,id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public ArrayList<Size> getSize_Sell(int min, int max) {
        ArrayList<Size> List = new ArrayList<>();
        try {
            String sql = """
                         select * from (select 
                         db_levents.size.id, 
                         db_levents.size.name_size, 
                         db_levents.size.created_at, 
                         db_levents.size.updated_at, 
                         statuss, 
                         ROW_NUMBER() OVER (ORDER BY size.id) AS rownum 
                         from db_levents.size where statuss = '1') 
                         AS temp WHERE rownum BETWEEN ? AND ?;   
                         """;
            ResultSet rs =  JDBCHelped.executeQuery(sql, min, max);
            while(rs.next()){
                Size s;
                String id = rs.getString(1);
                String name = rs.getString(2);
                Date created_at = rs.getDate(3);
                Date  updated_at = rs.getDate(4);
                s = new Size(created_at, id, updated_at, name);
                List.add(s);
            }
            return List;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }
    
    //them vao 12/12
    public ArrayList<Size> getCBB() {
        ArrayList<Size> List = new ArrayList<>();
        try {
            String sql = "select db_levents.size.id, db_levents.size.name_size, db_levents.size.created_at, db_levents.size.updated_at from db_levents.size WHERE statuss = '1';";
            ResultSet rs =  JDBCHelped.executeQuery(sql);
            while(rs.next()){
                Size s;
                String id = rs.getString(1);
                String name = rs.getString(2);
                Date created_at = rs.getDate(3);
                Date  updated_at = rs.getDate(4);
                s = new Size(created_at, id, updated_at, name);
                List.add(s);
            }
            return List;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }
    
    public ArrayList<Size> getSize_Stop_Sell(int min, int max) {
        ArrayList<Size> List = new ArrayList<>();
        try {
            String sql = """
                         select * from (select 
                         db_levents.size.id, 
                         db_levents.size.name_size, 
                         db_levents.size.created_at, 
                         db_levents.size.updated_at, 
                         statuss, 
                         ROW_NUMBER() OVER (ORDER BY size.id) AS rownum 
                         from db_levents.size where statuss = '0') 
                         AS temp WHERE rownum BETWEEN ? AND ?;   
                         """;
            ResultSet rs =  JDBCHelped.executeQuery(sql, min, max);
            while(rs.next()){
                Size s;
                String id = rs.getString(1);
                String name = rs.getString(2);
                Date created_at = rs.getDate(3);
                Date  updated_at = rs.getDate(4);
                s = new Size(created_at, id, updated_at, name);
                List.add(s);
            }
            return List;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }
    
    public boolean KhoiPhuc(String id){
        try {
            String sql = "update db_levents.size set db_levents.size.statuss = '1' where db_levents.size.id = ?;";
            JDBCHelped.excuteUpdate(sql,id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
