/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.repository;

import java.util.ArrayList;
import com.model.ExchangeBill;
import com.service.ExchangeService;
import java.sql.*;
import com.model.Bill;

/**
 *
 * @author thiet
 */
public class ExchangeRepository {

    public ArrayList<ExchangeBill> getList() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean Insert(ExchangeBill ex) {
        try {
            String sql = "INSERT INTO exchange_bill (created_at, bill_id, describe_reason,status)\n"
                    + "VALUES \n"
                    + "(?, ?,?,'1');";
            JDBCHelped.excuteUpdate(sql, ex.getCreatedAt(), ex.getBillId().getId(), ex.getDescribeReason());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    // load table 26/11/2023
    public ArrayList<ExchangeBill> getExchangeBill(){
        ArrayList<ExchangeBill> list = new ArrayList<>();
        try {
            String sql = "select id, bill_id, created_at, updated_at, describe_reason from exchange_bill;";
            ResultSet rs = JDBCHelped.executeQuery(sql);
            while (rs.next()) {                
                String id = rs.getString(1);
                String idBill = rs.getString(2);
                Date created = rs.getDate(3);
                Date updated = rs.getDate(4);
                String describe_reason = rs.getString(5);
                Bill b = new Bill(idBill);
                ExchangeBill exb = new ExchangeBill(b, created, id, updated, describe_reason);
                list.add(exb);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public ExchangeBill getExchangeBill_id(String idbill){
        ExchangeBill exchangeBill = new ExchangeBill();
        try {
            String sql = "SELECT id, bill_id, created_at, updated_at, describe_reason, status FROM db_levents.exchange_bill where bill_id = ?;";
            ResultSet rs = JDBCHelped.executeQuery(sql,idbill);
            while (rs.next()) {                
                String id = rs.getString(1);
                String idBill = rs.getString(2);
                Date created = rs.getDate(3);
                Date updated = rs.getDate(4);
                String describe_reason = rs.getString(5);
                Bill b = new Bill(idBill);
                exchangeBill = new ExchangeBill(b, created, id, updated, describe_reason, rs.getString(6));
            }
            return exchangeBill;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean update_Buil(String id) {
        try {
            String sql = """
                         UPDATE `db_levents`.`bill` SET `status` = '7' WHERE id = ?;
                         """;
            JDBCHelped.excuteUpdate(sql, id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean update_status(ExchangeBill exchangeBill) {
        try {
            String sql = """
                         update db_levents.exchange_bill set status = 1 where id = ?;
                         """;
            JDBCHelped.excuteUpdate(sql, exchangeBill.getId());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean delete_exchangeBill(String id ) {
        try {
            String sql = """
                         DELETE FROM db_levents.exchange_bill where bill_id = ?;
                         """;
            JDBCHelped.excuteUpdate(sql, id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
