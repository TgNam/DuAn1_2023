
package com.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.model.Voucher;

/**
 *
 * @author lenovo
 */
public class VoucherResponsitory {

    public List<Voucher> getAll() {
        String query = "SELECT * FROM voucher";
        List<Voucher> list = new ArrayList<>();

        ResultSet rs = JDBCHelped.executeQuery(query);

        try {
            while (rs.next()) {
                list.add(new Voucher(rs.getDouble(1), rs.getDate(2), rs.getDate(3),
                        rs.getString(4), rs.getDate(5), rs.getDate(6), rs.getString(7)));
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public Voucher getById(String id) {
        String query = "SELECT * FROM voucher WHERE id = ?";
        ResultSet rs = JDBCHelped.executeQuery(query, id);
        Voucher voucher = null;
        try {
            while (rs.next()) {
                voucher = new Voucher(rs.getDouble(1), rs.getDate(2), rs.getDate(3),
                        rs.getString(4), rs.getDate(5), rs.getDate(6), rs.getString(7));
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return voucher;
    }

    public List<Voucher> getAllById(String id) {
        String query = "SELECT * FROM voucher WHERE id = ?";
        List<Voucher> list = new ArrayList<>();

        ResultSet rs = JDBCHelped.executeQuery(query, id);

        try {
            while (rs.next()) {
                list.add(new Voucher(rs.getDouble(1), rs.getDate(2), rs.getDate(3),
                        rs.getString(4), rs.getDate(5), rs.getDate(6), rs.getString(7)));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean create(Voucher voucher) {
        String query = "INSERT INTO voucher (id,sale_of,created_at,start_at,end_at,status) VALUES (?,?,NOW(6),?,?,?)";
        try {
            Integer row = JDBCHelped.excuteUpdate(query, voucher.getId(), voucher.getSaleOf(),
                    voucher.getStartAt(), voucher.getEndAt(), voucher.getStatus());
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Voucher voucher) {
        String query = "UPDATE voucher SET sale_of = ? ,updated_at = NOW(6) ,start_at = ? ,end_at = ? ,status = ? WHERE id = ?";
        try {
            Integer row = JDBCHelped.excuteUpdate(query, voucher.getSaleOf(), 
                    voucher.getStartAt(), voucher.getEndAt(), voucher.getStatus(), voucher.getId());
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean remove(String id) {
        String query = "DELETE FROM voucher WHERE id = ?";
        try {
            Integer row = JDBCHelped.excuteUpdate(query, id);
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean resetStatus() {
        String query = "CALL update_voucher_status() ;";
        try {
            DBconnection.getConnection().prepareCall(query).execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public boolean updateStatus(String id) {
        String query = "UPDATE voucher SET status = 0 WHERE id = ?";
        try {
            Integer row = JDBCHelped.excuteUpdate(query,id);
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public List<Voucher> getAllById(String id, String status) {
        String query = "SELECT * FROM voucher WHERE id = ? and status = ?";
        List<Voucher> list = new ArrayList<>();

        ResultSet rs = JDBCHelped.executeQuery(query, id,status);

        try {
            while (rs.next()) {
                list.add(new Voucher(rs.getDouble(1), rs.getDate(2), rs.getDate(3),
                        rs.getString(4), rs.getDate(5), rs.getDate(6), rs.getString(7)));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
