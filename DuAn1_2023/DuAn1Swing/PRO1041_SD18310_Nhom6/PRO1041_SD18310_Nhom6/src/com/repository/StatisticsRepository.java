/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.repository;

import com.model.ChartModel;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StatisticsRepository {

    // BingChiLing is here
    // dùng cho lấy tất cả doanh thu
    public BigDecimal getSumIntoMoneyByStatus(int status1, int status2, int status3) {
        String query = "SELECT SUM(into_money)  FROM db_levents.bill where status in (?,?,?);";
        try {
            ResultSet rs = JDBCHelped.executeQuery(query, status1, status2, status3);
            if (rs != null && rs.next()) {
                return rs.getBigDecimal(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BigDecimal.ZERO;
    }

    public BigDecimal getSumIntoMoneyByStatus(int status1, int status2, int status3, int year) {
        String query = "SELECT SUM(into_money)  FROM db_levents.bill where status in (?,?,?) and YEAR(created_at) = ?;";
        try {
            ResultSet rs = JDBCHelped.executeQuery(query, status1, status2, status3, year);
            if (rs != null && rs.next()) {
                return rs.getBigDecimal(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BigDecimal.ZERO;
    }

    // dùng cho lọc doanh thu
    public BigDecimal getSumIntoMoneyByStatus(int status1, int status2, int status3, int year, int month) {
        String query = "SELECT SUM(into_money)  FROM db_levents.bill where status in (?,?,?) and YEAR(created_at) = ? and MONTH(created_at) = ?;";
        try {
            ResultSet rs = JDBCHelped.executeQuery(query, status1, status2, status3, year, month);
            if (rs != null && rs.next()) {
                return rs.getBigDecimal(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BigDecimal.ZERO;
    }

    // dùng cho lọc bill trả hàng
    public BigDecimal getSumIntoMoneyByStatusAndUpdated(int status, int year, int month) {
        String query = "SELECT SUM(into_money) FROM db_levents.bill where status in (?,?,?) and YEAR(updated_at) = ? and MONTH(updated_at) = ?;";
        try {
            ResultSet rs = JDBCHelped.executeQuery(query, status, year, month);
            if (rs != null && rs.next()) {
                return rs.getBigDecimal(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BigDecimal.ZERO;
    }

    public BigDecimal getSumIntoMoneyByStatusAndUpdated(int status, int year) {
        String query = "SELECT SUM(into_money) FROM db_levents.bill where status in (?) and YEAR(updated_at) = ?";
        try {
            ResultSet rs = JDBCHelped.executeQuery(query, status, year);
            if (rs != null && rs.next()) {
                return rs.getBigDecimal(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BigDecimal.ZERO;
    }

    public BigDecimal getSumIntoMoneyByStatusAndYear(int status1, int status2, int status3, int year) {
        String query = "SELECT SUM(into_money)  FROM db_levents.bill where status in (?,?,?) and YEAR(created_at) = ? ;";
        try {
            ResultSet rs = JDBCHelped.executeQuery(query, status1, status2, status3, year);
            if (rs != null && rs.next()) {
                return rs.getBigDecimal(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BigDecimal.ZERO;
    }

    public String getQuantityBillByStatus(int status) {
        String query = "select COUNT(*) from db_levents.bill WHERE status = ?";
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

    public String getQuantityBillByStatus(int status, int year) {
        String query = "select COUNT(*) from db_levents.bill WHERE status = ? and YEAR(created_at) = ? ";
        try {
            ResultSet rs = JDBCHelped.executeQuery(query, status, year);
            if (rs != null && rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0";
    }

    public String getQuantityBillByStatus(int status, int year, int month) {
        String query = "select COUNT(*) from db_levents.bill WHERE status = ? and YEAR(created_at) = ? and MONTH(created_at) = ?";
        try {
            ResultSet rs = JDBCHelped.executeQuery(query, status, year, month);
            if (rs != null && rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0";
    }

    public List<ChartModel> getAllDataChart() {
        List<ChartModel> list = new ArrayList<>();
        String query = "SELECT date_format(created_at, '%M') AS 'month', SUM(into_money) AS 'doanh thu'\n"
                + "              FROM db_levents.bill where status in (3,5,7) and year(created_at) = YEAR(now())\n"
                + "               GROUP BY date_format(created_at, '%M')\n"
                + "                order by date_format(created_at, '%M') DESC;";

        try {
            ResultSet rs = JDBCHelped.executeQuery(query);
            while (rs.next()) {
                list.add(new ChartModel(rs.getString(1), rs.getDouble(2)));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    // end
}
