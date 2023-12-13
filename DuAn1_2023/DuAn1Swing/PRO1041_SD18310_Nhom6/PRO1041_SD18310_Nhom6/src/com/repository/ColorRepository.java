/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.repository;

import java.sql.*;
import java.util.ArrayList;
import com.model.Color;
/**
 *
 * @author thiet
 */
public class ColorRepository {

     public ArrayList<Color> getListCL() {
        ArrayList<Color> List = new ArrayList<>();
        try {
            String sql = "select db_levents.color.id, db_levents.color.name_color, db_levents.color.created_at, db_levents.color.updated_at  from db_levents.color;";
            ResultSet rs =  JDBCHelped.executeQuery(sql);
            while(rs.next()){
                Color cl;
                String id = rs.getString(1);
                String name = rs.getString(2);
                Date created_at = rs.getDate(3);
                Date  updated_at = rs.getDate(4);
                cl = new Color( created_at, id, updated_at, name);
                List.add(cl);
            }
            return List;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }
    
     public boolean  Insert(Color cl){
        try {
            String sql = "INSERT INTO db_levents.color (created_at, updated_at, name_color, statuss) VALUES (curdate(),curdate(),?,'1');";
            JDBCHelped.excuteUpdate(sql, cl.getNameColor());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean Update(String id, Color cl) {
        try {
            String sql = "UPDATE `db_levents`.`color` SET `updated_at` = curdate(), `name_color` = ? WHERE (`id` = ?);";
            JDBCHelped.excuteUpdate(sql, cl.getNameColor(), id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
     //them vao 1/12
    public boolean Delete(String id) {
        try {
            String sql = "update db_levents.color set db_levents.color.statuss = '0' where db_levents.color.id = ?;";
            JDBCHelped.excuteUpdate(sql,  id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public ArrayList<Color> getColor_sell(int min, int max) {
        ArrayList<Color> List = new ArrayList<>();
        try {
            String sql = """
                         select * from (select 
                         db_levents.color.id, 
                         db_levents.color.name_color, 
                         db_levents.color.created_at, 
                         db_levents.color.updated_at , 
                         statuss,ROW_NUMBER() OVER (ORDER BY color.id) AS rownum 
                         from db_levents.color where db_levents.color.statuss = '1') 
                         AS temp WHERE rownum BETWEEN ? AND ?;
                         """;
            ResultSet rs =  JDBCHelped.executeQuery(sql, min,max);
            while(rs.next()){
                Color cl;
                String id = rs.getString(1);
                String name = rs.getString(2);
                Date created_at = rs.getDate(3);
                Date  updated_at = rs.getDate(4);
                cl = new Color( created_at, id, updated_at, name);
                List.add(cl);
            }
            return List;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }
    
    //them vao 12/12
     public ArrayList<Color> getCBB() {
        ArrayList<Color> List = new ArrayList<>();
        try {
            String sql = "select db_levents.color.id, db_levents.color.name_color, db_levents.color.created_at, db_levents.color.updated_at  from db_levents.color where statuss = '1';";
            ResultSet rs =  JDBCHelped.executeQuery(sql);
            while(rs.next()){
                Color cl;
                String id = rs.getString(1);
                String name = rs.getString(2);
                Date created_at = rs.getDate(3);
                Date  updated_at = rs.getDate(4);
                cl = new Color( created_at, id, updated_at, name);
                List.add(cl);
            }
            return List;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }
     
     public ArrayList<Color> getColor_Stop(int min, int max) {
        ArrayList<Color> List = new ArrayList<>();
        try {
            String sql = """
                         select * from (select 
                         db_levents.color.id, 
                         db_levents.color.name_color, 
                         db_levents.color.created_at, 
                         db_levents.color.updated_at , 
                         statuss,ROW_NUMBER() OVER (ORDER BY color.id) AS rownum 
                         from db_levents.color where db_levents.color.statuss = '0') 
                         AS temp WHERE rownum BETWEEN ? AND ?;
                         """;
            ResultSet rs =  JDBCHelped.executeQuery(sql, min,max);
            while(rs.next()){
                Color cl;
                String id = rs.getString(1);
                String name = rs.getString(2);
                Date created_at = rs.getDate(3);
                Date  updated_at = rs.getDate(4);
                cl = new Color( created_at, id, updated_at, name);
                List.add(cl);
            }
            return List;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }
     
      public boolean KhoiPhuc(String id) {
        try {
            String sql = "update db_levents.color set db_levents.color.statuss = '1' where db_levents.color.id = ?;";
            JDBCHelped.excuteUpdate(sql,  id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
}
