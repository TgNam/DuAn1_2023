/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.repository;

import java.sql.*;
import java.util.ArrayList;
import com.model.Custom;

/**
 *
 * @author thiet
 */
public class CustomRepository {

    public ArrayList<Custom> getListCustom() {
        ArrayList<Custom> List = new ArrayList<>();
        try {
            String sql = "select id, name_custom, created_at, updated_at , status from custom;";
            ResultSet rs = JDBCHelped.executeQuery(sql);
            while (rs.next()) {
                Custom c;
                String id = rs.getString(1);
                String name = rs.getString(2);
                Date created_at = rs.getDate(3);
                Date updated_at = rs.getDate(4);
                boolean status = rs.getBoolean(5);
                c = new Custom(status, created_at, id, updated_at, name);
                List.add(c);
            }
            return List;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean Insert(Custom c) {
        try {
            String sql = "INSERT INTO `db_levents`.`custom` (`status`, `created_at`, `updated_at`, `name_custom`) VALUES (1, curdate(), curdate(), ?);";
            JDBCHelped.excuteUpdate(sql, c.getNameCustom());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean Update(String id, Custom c) {
        try {
            String sql = "UPDATE `db_levents`.`custom` SET `updated_at` = curdate(), `name_custom` = ? WHERE (`id` = ?);";
            JDBCHelped.excuteUpdate(sql, c.getNameCustom(), id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //them vao 1/12
    public boolean Delete(String id) {
        try {
            String sql = "update db_levents.custom set db_levents.custom.status = 0 where db_levents.custom.id = ?;";
            JDBCHelped.excuteUpdate(sql, id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Custom> getCustom_Sell(int min, int max) {
        ArrayList<Custom> List = new ArrayList<>();
        try {
            String sql = """
                         select * from (select 
                         id, 
                         name_custom, 
                         created_at, 
                         updated_at , 
                         status, 
                         ROW_NUMBER() OVER (ORDER BY custom.id) AS rownum 
                         from custom where status = 1) 
                         AS temp WHERE rownum BETWEEN ? AND ?;   
                         """;
            ResultSet rs = JDBCHelped.executeQuery(sql, min, max);
            while (rs.next()) {
                Custom c;
                String id = rs.getString(1);
                String name = rs.getString(2);
                Date created_at = rs.getDate(3);
                Date updated_at = rs.getDate(4);
                boolean status = rs.getBoolean(5);
                c = new Custom(status, created_at, id, updated_at, name);
                List.add(c);
            }
            return List;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //them vao 12/12

    public ArrayList<Custom> getCBB() {
        ArrayList<Custom> List = new ArrayList<>();
        try {
            String sql = "select id, name_custom, created_at, updated_at , status from custom where status = 1;";
            ResultSet rs = JDBCHelped.executeQuery(sql);
            while (rs.next()) {
                Custom c;
                String id = rs.getString(1);
                String name = rs.getString(2);
                Date created_at = rs.getDate(3);
                Date updated_at = rs.getDate(4);
                boolean status = rs.getBoolean(5);
                c = new Custom(status, created_at, id, updated_at, name);
                List.add(c);
            }
            return List;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean KhoiPhuc(String id) {
        try {
            String sql = "update db_levents.custom set db_levents.custom.status = 1 where db_levents.custom.id = ?;";
            JDBCHelped.excuteUpdate(sql, id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Custom> getCustom_Stop(int min, int max) {
        ArrayList<Custom> List = new ArrayList<>();
        try {
            String sql = """
                         select * from (select 
                         id, 
                         name_custom, 
                         created_at, 
                         updated_at , 
                         status, 
                         ROW_NUMBER() OVER (ORDER BY custom.id) AS rownum 
                         from custom where status = 0) 
                         AS temp WHERE rownum BETWEEN ? AND ?;   
                         """;
            ResultSet rs = JDBCHelped.executeQuery(sql, min, max);
            while (rs.next()) {
                Custom c;
                String id = rs.getString(1);
                String name = rs.getString(2);
                Date created_at = rs.getDate(3);
                Date updated_at = rs.getDate(4);
                boolean status = rs.getBoolean(5);
                c = new Custom(status, created_at, id, updated_at, name);
                List.add(c);
            }
            return List;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
