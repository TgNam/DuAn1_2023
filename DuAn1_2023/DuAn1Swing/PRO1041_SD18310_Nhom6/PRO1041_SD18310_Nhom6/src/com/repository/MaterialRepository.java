/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.repository;


import java.util.ArrayList;
import com.model.Material;
import java.sql.*;

/**
 *
 * @author thiet
 */
public class MaterialRepository {

    public ArrayList<Material> getListNL() {
        ArrayList<Material> List = new ArrayList<>();
        try {
            String sql = "select id, name_material, created_at, updated_at, status from material;";
            ResultSet rs =  JDBCHelped.executeQuery(sql);
            while(rs.next()){
                Material m;
                String id = rs.getString(1);
                String name = rs.getString(2);
                Date created_at = rs.getDate(3);
                Date  updated_at = rs.getDate(4);
                boolean status = rs.getBoolean(5);
                m = new Material(status, created_at, id, updated_at, name);
                List.add(m);
            }
            return List;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }

     public boolean Insert(Material mr){
        try {
            String sql = "INSERT INTO db_levents.material (created_at, updated_at, name_material, status) VALUES (curdate(), curdate(), ?,1);";
            JDBCHelped.excuteUpdate(sql, mr.getNameMaterial());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean Update(String id, Material mr){
        try {
            String sql = "UPDATE `db_levents`.`material` SET `updated_at` = curdate(), `name_material` = ? WHERE (`id` = ?);";
            JDBCHelped.excuteUpdate(sql, mr.getNameMaterial(),id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
     //them vao 1/12
    public boolean Delete(String id) {
        try {
            String sql = "update db_levents.material set db_levents.material.status = 0 where db_levents.material.id = ?;";
            JDBCHelped.excuteUpdate(sql,  id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
     public ArrayList<Material> getMaterial_Sell(int min, int max) {
        ArrayList<Material> List = new ArrayList<>();
        try {
            String sql = """
                         select * from (select 
                         id, 
                         name_material, 
                         created_at, 
                         updated_at, 
                         status, 
                         ROW_NUMBER() OVER (ORDER BY material.id) AS rownum 
                         from material where status = 1) 
                         AS temp WHERE rownum BETWEEN ? AND ?;   
                         """;
            ResultSet rs =  JDBCHelped.executeQuery(sql,min,max);
            while(rs.next()){
                Material m;
                String id = rs.getString(1);
                String name = rs.getString(2);
                Date created_at = rs.getDate(3);
                Date  updated_at = rs.getDate(4);
                boolean status = rs.getBoolean(5);
                m = new Material(status, created_at, id, updated_at, name);
                List.add(m);
            }
            return List;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }
    
     //them vao 12/12
     public ArrayList<Material> getCBB() {
        ArrayList<Material> List = new ArrayList<>();
        try {
            String sql = "select id, name_material, created_at, updated_at, status from material where status = 1;";
            ResultSet rs =  JDBCHelped.executeQuery(sql);
            while(rs.next()){
                Material m;
                String id = rs.getString(1);
                String name = rs.getString(2);
                Date created_at = rs.getDate(3);
                Date  updated_at = rs.getDate(4);
                boolean status = rs.getBoolean(5);
                m = new Material(status, created_at, id, updated_at, name);
                List.add(m);
            }
            return List;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }
     
      public boolean KhoiPhuc(String id) {
        try {
            String sql = "update db_levents.material set db_levents.material.status = 1 where db_levents.material.id = ?;";
            JDBCHelped.excuteUpdate(sql,  id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
     public ArrayList<Material> getMaterial_Stop(int min, int max) {
        ArrayList<Material> List = new ArrayList<>();
        try {
            String sql = """
                         select * from (select 
                         id, 
                         name_material, 
                         created_at, 
                         updated_at, 
                         status, 
                         ROW_NUMBER() OVER (ORDER BY material.id) AS rownum 
                         from material where status = 0) 
                         AS temp WHERE rownum BETWEEN ? AND ?;   
                         """;
            ResultSet rs =  JDBCHelped.executeQuery(sql,min,max);
            while(rs.next()){
                Material m;
                String id = rs.getString(1);
                String name = rs.getString(2);
                Date created_at = rs.getDate(3);
                Date  updated_at = rs.getDate(4);
                boolean status = rs.getBoolean(5);
                m = new Material(status, created_at, id, updated_at, name);
                List.add(m);
            }
            return List;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }
}
