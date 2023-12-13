/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import com.model.Bill;
import java.sql.*;
import java.util.List;
import com.model.Address;
import com.model.User;
import com.model.Voucher;
import com.service.imple.VoucherImple;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author TgNam
 */
public class BillRepository {

    //select lấy dữ liệu hóa đơn chưa thanh toán
    public ArrayList<Bill> getBill_0() {
        ArrayList<Bill> list = new ArrayList<>();
        try {
            String sql = "SELECT\n"
                    + "  bill.created_at,\n"
                    + "  bill.id,\n"
                    + "  user.full_name,\n"
                    + "  user.number_phone,\n"
                    + "  bill.status\n"
                    + "FROM db_levents.bill\n"
                    + "join db_levents.user on user.id = bill.user_id where bill.status in (0);";
            ResultSet rs = JDBCHelped.executeQuery(sql);
            while (rs.next()) {
                list.add(new Bill(rs.getDate(1), rs.getString(2), new User(rs.getString(3), rs.getString(4)), rs.getString(5))
                );
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean add_bill(Bill b) {
        try {
            String sql = "INSERT INTO db_levents.bill (created_at, updated_at, delivery_date, total_cost, into_money, status, user_id,voucher_id,address_id)\n"
                    + "VALUES \n"
                    + "(?, ?, ?, null, null, 0, (SELECT id FROM db_levents.user where number_phone = ?), null, null);";
            JDBCHelped.excuteUpdate(sql, b.getCreatedAt(), b.getUpdatedAt(), b.getDeliveryDate(), b.getUserId().getNumberPhone());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //Them thang getBill_all
    //ngay 27/11 update lai thang nay
    public ArrayList<Bill> getBil_All() {
        ArrayList<Bill> list = new ArrayList<>();
        try {
//<<<<<<< HEAD
            String sql = "select \n"
                    + "db_levents.bill.id, \n"
                    + "db_levents.user.full_name,\n"
                    + "db_levents.user.number_phone ,\n"
                    + "db_levents.bill.into_money,\n"
                    + "db_levents.bill.total_cost,\n"
                    + "db_levents.address.address_detail,\n"
                    + "db_levents.bill.created_at,\n"
                    + "db_levents.bill.delivery_date,\n"
                    + "db_levents.bill.updated_at,\n"
                    + "db_levents.bill.voucher_id,\n"
                    + "db_levents.bill.status\n"
                    + "from db_levents.bill\n"
                    + "inner join db_levents.user on db_levents.bill.user_id = db_levents.user.id\n"
                    + "inner join db_levents.address on db_levents.bill.address_id = db_levents.address.id\n"
                    + "left join db_levents.user_role on db_levents.user.id = db_levents.user_role.user_id\n"
                    + "inner join db_levents.role on db_levents.user_role.role_id = db_levents.role.id\n"
                    + "where db_levents.bill.status = '1' and not exists (select db_levents.exchange_bill.bill_id from db_levents.exchange_bill where db_levents.exchange_bill.bill_id = db_levents.bill.id ) and not exists (select * from db_levents.return_bill where db_levents.return_bill.bill_id = db_levents.bill.id);";

//            String sql = "SELECT\n"
//                    + "    db_levents.bill.id,\n"
//                    + "    db_levents.user.full_name,\n"
//                    + "    db_levents.user.number_phone,\n"
//                    + "    db_levents.bill.into_money,\n"
//                    + "    db_levents.bill.total_cost,\n"
//                    + "    db_levents.address.address_detail,\n"
//                    + "    db_levents.bill.created_at,\n"
//                    + "    db_levents.bill.delivery_date,\n"
//                    + "    db_levents.bill.updated_at,\n"
//                    + "    db_levents.bill.voucher_id,\n"
//                    + "    db_levents.bill.status,\n"
//                    + "    db_levents.user.id\n"
//                    + "FROM\n"
//                    + "    db_levents.bill\n"
//                    + "INNER JOIN\n"
//                    + "    db_levents.user ON db_levents.bill.user_id = db_levents.user.id\n"
//                    + "INNER JOIN\n"
//                    + "    db_levents.address ON db_levents.bill.address_id = db_levents.address.id\n"
//                    + "LEFT JOIN\n"
//                    + "    db_levents.user_role ON db_levents.user.id = db_levents.user_role.user_id\n"
//                    + "INNER JOIN\n"
//                    + "    db_levents.role ON db_levents.user_role.role_id = db_levents.role.id\n"
//                    + "WHERE\n"
//                    + "    db_levents.bill.status = '1';";
//>>>>>>> 2585752ea5a2841a1e2e0a49d8956104b0dfd1e2
            ResultSet rs = JDBCHelped.executeQuery(sql);
            while (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);
                String number_Phone = rs.getString(3);
                BigDecimal into_money = rs.getBigDecimal(4);
                BigDecimal total_cost = rs.getBigDecimal(5);
                String address_detail = rs.getString(6);
                Date created_at = rs.getDate(7);
                Date delivery_date = rs.getDate(8);
                Date updated_at = rs.getDate(9);
                String voucher = rs.getString(10);
                String status = rs.getString(11);
//                String idUser = rs.getString(12);
                Bill b;
                b = new Bill(into_money, total_cost, new Address(address_detail), created_at, delivery_date, id, updated_at, new User(name, number_Phone), new Voucher(voucher), status);
                list.add(b);
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    //Xóa bill thông qua id
    public boolean delete_bill_id(Bill b) {
        try {
            String sql = "Delete FROM db_levents.bill where id = ?;";
            JDBCHelped.excuteUpdate(sql, b.getId());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // anh linh đã ghé thăm chỗ này
    public Bill getById(Long idBill) {
        String query = "SELECT\n"
                + "    b.id,\n"
                + "    u.full_name,\n"
                + "    u.number_phone,\n"
                + "    b.into_money,\n"
                + "    b.total_cost,\n"
                + "    a.address_detail,\n"
                + "    b.created_at,\n"
                + "    b.delivery_date,\n"
                + "    b.updated_at,\n"
                + "    b.voucher_id,\n"
                + "    b.status,\n"
                + "    u.id\n"
                + "FROM\n"
                + "    db_levents.bill b\n"
                + "INNER JOIN\n"
                + "    db_levents.user u ON b.user_id = u.id\n"
                + "INNER JOIN\n"
                + "    db_levents.address a ON b.address_id = a.id\n"
                + "LEFT JOIN\n"
                + "    db_levents.user_role ur ON u.id = ur.user_id\n"
                + "INNER JOIN\n"
                + "    db_levents.role r ON ur.role_id = r.id\n"
                + "WHERE\n"
                + "    b.id = ?; ";
        try {
            ResultSet rs = JDBCHelped.executeQuery(query, idBill);
            if (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);
                String number_Phone = rs.getString(3);
                BigDecimal into_money = rs.getBigDecimal(4);
                BigDecimal total_cost = rs.getBigDecimal(5);
                String address_detail = rs.getString(6);
                Date created_at = rs.getDate(7);
                Date delivery_date = rs.getDate(8);
                Date updated_at = rs.getDate(9);
                String voucher = rs.getString(10);
                String status = rs.getString(11);
                String idUser = rs.getString(12);

                Address address = new Address(address_detail);
                User user = new User(idUser, name, number_Phone);
                Voucher voucherObj = new VoucherImple().getById(voucher);

                Bill bill = new Bill(into_money, total_cost, address, created_at, delivery_date, id, updated_at, user, voucherObj, status);

                return bill;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateStatusById(String id, int status) {
        String query = "UPDATE bill SET status = ? WHERE id =?";
        try {
            int row = JDBCHelped.excuteUpdate(query, status, id);
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    // bye----------

    public ArrayList<Bill> getBill_status(String status1, String status2) {
        ArrayList<Bill> list = new ArrayList<>();
        try {
            String sql = """
                         SELECT  
                                             bill.created_at,
                                             bill.id,
                                             user.full_name,
                                             user.number_phone,
                                             bill.status , 
                                             user.id,
                                             bill.address_id,
                                             address.address_detail
                                             FROM db_levents.bill
                                             join db_levents.user on user.id = bill.user_id 
                                             left join db_levents.address on address.id =bill.address_id
                                             where bill.status in (?,?);
                         """;
            ResultSet rs = JDBCHelped.executeQuery(sql, status1, status2);
            while (rs.next()) {
                Address address = new Address(rs.getString(6), rs.getString(7));
                list.add(new Bill(address, rs.getDate(1), rs.getString(2), new User(rs.getString(6), rs.getString(3), rs.getString(4)), rs.getString(5))
                );
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean updateVoucherByIdBill(String voucher_id, String id) {
        try {
            String sql = "update db_levents.bill set voucher_id = ? where id =? ;";
            JDBCHelped.excuteUpdate(sql, voucher_id, id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean updatemoneyByIdBill(BigDecimal into_money, BigDecimal total_cost, String id) {
        try {
            String sql = "update db_levents.bill set into_money = ?,total_cost = ?,status = '1' where id =? ;";
            JDBCHelped.excuteUpdate(sql, into_money, total_cost, id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update_Status(String status, String idBill) {
        try {
            String sql = "update db_levents.bill set status = ? where id =? ;";
            JDBCHelped.excuteUpdate(sql, status, idBill);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update_address(String address_id, String idBill) {
        try {
            String sql = "Update db_levents.bill set address_id = ? where id = ?;";
            JDBCHelped.excuteUpdate(sql, address_id, idBill);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // link 
    public Set<String> getYearCreated() {
        Set<String> set = new HashSet<>();
        String query = "SELECT YEAR(created_at) FROM db_levents.bill;";
        try {
            ResultSet rs = JDBCHelped.executeQuery(query);
            while (rs.next()) {
                set.add(rs.getString(1));
            }
            return set;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getQuantity() {
        String query = "select COUNT(*) from db_levents.bill";
        try {
            ResultSet rs = JDBCHelped.executeQuery(query);
            if (rs != null && rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0";
    }

    public String getQuantity(int year) {
        String query = "select COUNT(*) from db_levents.bill where YEAR(created_at) = ? ";
        try {
            ResultSet rs = JDBCHelped.executeQuery(query, year);
            if (rs != null && rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0";
    }
    
    public String getQuantity(int year, int month) {
        String query = "select COUNT(*) from db_levents.bill where YEAR(created_at) = ? and MONTH(created_at) = ?";
        try {
            ResultSet rs = JDBCHelped.executeQuery(query, year, month);
            if (rs != null && rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0";
    }
    
    public String getIdByUpdatedAt(int status) {
        String query = "select id from db_levents.bill WHERE status = ? ";
        try {
            ResultSet rs = JDBCHelped.executeQuery(query, status);
            if (rs != null && rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0";
    }
    
    public String getIdByUpdatedAt(int status, int year) {
        String query = "select id from db_levents.bill WHERE status = ? and YEAR(updated_at) = ? ";
        try {
            ResultSet rs = JDBCHelped.executeQuery(query, status,year);
            if (rs != null && rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0";
    }
    
    public String getIdByUpdatedAt(int status, int year,int month) {
        String query = "select id from db_levents.bill WHERE status = ? and YEAR(updated_at) = ? and MONTH(updated_at) = ? ";
        try {
            ResultSet rs = JDBCHelped.executeQuery(query, status,year, month);
            if (rs != null && rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0";
    }
    // end
}
