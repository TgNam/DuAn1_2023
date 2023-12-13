/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.repository;

import java.util.Date;

/**
 *
 * @author TgNam
 */
public class AddressRepository {

    public boolean add_address(Date nowDate, String address) {
        try {
            String sql = "INSERT INTO address (created_at, updated_at, address_detail)\n"
                    + "VALUES (?, ?, ?);";
            JDBCHelped.excuteUpdate(sql, nowDate, nowDate, address);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean delete_address(Date nowDate, String address) {
        try {
            String sql = "delete FROM db_levents.address where created_at= ? and address_detail =?;";
            JDBCHelped.excuteUpdate(sql, nowDate, address);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean update_address(Date nowDate, String address, String id) {
        try {
            String sql = "Update db_levents.address set updated_at = ? , address_detail = ? where id =? ;";
            JDBCHelped.excuteUpdate(sql, nowDate, address, id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
