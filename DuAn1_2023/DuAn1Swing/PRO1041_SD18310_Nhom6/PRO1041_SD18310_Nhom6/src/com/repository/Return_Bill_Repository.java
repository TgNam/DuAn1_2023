package com.repository;

import com.model.Bill;
import com.model.ReturnBill;
import java.math.BigDecimal;
import java.sql.*;

/**
 *
 * @author thiet
 */
public class Return_Bill_Repository {

    public boolean Insert(ReturnBill rb) {
        try {
            String sql = """
                         INSERT INTO return_bill (created_at, total_cost, bill_id, reason_description)
                         VALUES 
                         ('2023-11-04 10:00:00', '320000', '1','Hàng lỗi');
                         """;
            JDBCHelped.excuteUpdate(sql, rb.getCreatedAt(), rb.getBillId().getId(), rb.getReasonDescription());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //start linh dz
    public boolean insert(ReturnBill returnBill) {
        String query = "INSERT INTO return_bill(total_cost, bill_id, created_at, reason_description) VALUES (?,?,NOW(),?) ";
        try {
            JDBCHelped.excuteUpdate(query, returnBill.getTotalCost(), returnBill.getBillId().getId(), returnBill.getReasonDescription());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public ReturnBill getByIdBill(String idBill) {
        String query = "select * from return_bill WHERE bill_id = ?";
        ReturnBill returnBill = null;
        try {
            ResultSet rs = JDBCHelped.executeQuery(query, idBill);
            if (rs.next()) {
                returnBill = new ReturnBill(rs.getBigDecimal(1), new BillRepository().getById(rs.getLong(2)), rs.getDate(3), rs.getString(4), rs.getDate(5), rs.getString(6));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return returnBill;
    }
   
    public ReturnBill getById(String id) {
        String query = "select * from return_bill WHERE id = ?";
        ReturnBill returnBill = null;
        try {
            ResultSet rs = JDBCHelped.executeQuery(query, id);
            if (rs.next()) {
                returnBill = new ReturnBill(rs.getBigDecimal(1), new BillRepository().getById(rs.getLong(2)), rs.getDate(3), rs.getString(4), rs.getDate(5), rs.getString(6));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return returnBill;
    }
    // end linh dz
        public ReturnBill getBy_IdBill(String idBill) {
        String sql = "select total_cost, bill_id, created_at, id, updated_at, reason_description, status from return_bill WHERE bill_id = ?";
        ReturnBill returnBill = new ReturnBill();
        try {
            ResultSet rs = JDBCHelped.executeQuery(sql, idBill);
            if (rs.next()) {
                Bill b = new Bill(rs.getString(2));
                returnBill = new ReturnBill(rs.getBigDecimal(1), b, rs.getDate(3), rs.getString(4), rs.getDate(5), rs.getString(6),rs.getString(7));
            }
            return returnBill;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }       
    }
        
   // BingChiLing is here
    public BigDecimal getSumIntoMoneyByYear(int year) {
        String query = "SELECT SUM(total_cost) FROM db_levents.return_bill where YEAR(created_at) = ? ";
        try {
            ResultSet rs = JDBCHelped.executeQuery(query, year);
            if (rs != null && rs.next()) {
                return rs.getBigDecimal(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BigDecimal.ZERO;
    }

    public BigDecimal getTotalMoney() {
        String query = "select SUM(total_cost) from return_bill;";
        try {
            ResultSet rs = JDBCHelped.executeQuery(query);
            if (rs != null && rs.next()) {
                return rs.getBigDecimal(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BigDecimal.ZERO;
    }

    public BigDecimal getTotalMoney(int year) {
        String query = "select SUM(total_cost) from return_bill WHERE YEAR(created_at) = ?;";
        try {
            ResultSet rs = JDBCHelped.executeQuery(query, year);
            if (rs != null && rs.next()) {
                return rs.getBigDecimal(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BigDecimal.ZERO;
    }

    public BigDecimal getTotalMoneyByIdBill(Long idBill) {
        String query = "select SUM(total_cost) from return_bill WHERE bill_id = ?";
        try {
            ResultSet rs = JDBCHelped.executeQuery(query, idBill);
            if (rs != null && rs.next()) {
                return rs.getBigDecimal(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BigDecimal.ZERO;
    }

    public BigDecimal getTotalMoney(int year, int month) {
        String query = "select SUM(total_cost) from return_bill WHERE YEAR(created_at) = ? and MONTH(created_at) = ?;";
        try {
            ResultSet rs = JDBCHelped.executeQuery(query, year, month);
            if (rs != null && rs.next()) {
                return rs.getBigDecimal(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BigDecimal.ZERO;
    }

    public String getQuantityByDate() {
        String query = "select COUNT(*) from db_levents.return_bill ";
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

    public String getQuantityByDate(int year) {
        String query = "select COUNT(*) from db_levents.return_bill where YEAR(created_at) = ?";
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

    public String getQuantityByDate(int year, int month) {
        String query = "select COUNT(*) from db_levents.return_bill where YEAR(created_at) = ? and MONTH(created_at) = ?";
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
    // end

        public boolean update_status(ReturnBill returnBill) {
        String query = "update db_levents.return_bill set status = 1 where id = ?;";
        try {
            JDBCHelped.excuteUpdate(query, returnBill.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
        
        public boolean delete_returnBill(String id) {
        String query = "DELETE FROM db_levents.return_bill where bill_id = ?;";
        try {
            JDBCHelped.excuteUpdate(query, id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
