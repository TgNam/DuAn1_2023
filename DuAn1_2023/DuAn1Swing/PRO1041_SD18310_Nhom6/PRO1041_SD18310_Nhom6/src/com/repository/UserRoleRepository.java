/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import com.model.Address;
import com.model.Role;
import com.model.User;
import com.model.UserRole;

/**
 *
 * @author TgNam
 */
public class UserRoleRepository {

    public ArrayList<UserRole> getAll_Employee(String status) {
        ArrayList<UserRole> list = new ArrayList<>();
        try {
            String sql = "SELECT\n"
                    + "                               u.id AS user_ID,\n"
                    + "                               u.full_name AS user_full_name,\n"
                    + "                               u.email AS user_email,\n"
                    + "                               u.date_of_birth AS user_date_of_birth,\n"
                    + "                               u.number_phone AS user_number_phone,\n"
                    + "                               a.address_detail AS user_address_detail,\n"
                    + "                               u.account AS user_account,\n"
                    + "                               u.password AS user_password,\n"
                    + "                               r.id AS role_id,\n"
                    + "                               r.role_name AS user_role,\n"
                    + "                               u.address_id AS idAd,\n"
                    + "                               u.status\n"
                    + "                           FROM user AS u\n"
                    + "                           LEFT JOIN user_role AS ur ON u.id = ur.user_id\n"
                    + "                           LEFT JOIN role AS r ON ur.role_id = r.id\n"
                    + "                           LEFT JOIN address AS a ON u.address_id = a.id\n"
                    + "                           where r.id in (1,2) and u.status = ?;";
            ResultSet rs = JDBCHelped.executeQuery(sql, status);
            while (rs.next()) {
                Address address = new Address(rs.getString(11), rs.getString(6));
                Role role = new Role(rs.getString(9), rs.getString(10));
                User user = new User(address, rs.getDate(4), rs.getString(1), rs.getString(7), rs.getString(3), rs.getString(2), rs.getString(5), rs.getString(8), rs.getString(12));
                UserRole uRole = new UserRole(role, user);
                list.add(uRole);
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean add_user_role(User user, String id) {
        try {
            String sql = "INSERT INTO user_role (user_id, role_id) VALUES ((SELECT id FROM db_levents.user where created_at = ? and number_phone = ? LIMIT 1 ), ?  );";
            JDBCHelped.excuteUpdate(sql, user.getCreatedAt(), user.getNumberPhone(), id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean Update_user_role(User user, String id) {
        try {
            String sql = "Update db_levents.user_role set role_id = ? where user_id = ?;";
            JDBCHelped.excuteUpdate(sql, id, user.getId());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    // Thêm tham số name vào phương thức

    public ArrayList<UserRole> getSearch_Employee(String status, String name) {
        ArrayList<UserRole> list = new ArrayList<>();
        try {
            String sql = "SELECT\n"
                    + "    u.id AS user_ID,\n"
                    + "    u.full_name AS user_full_name,\n"
                    + "    u.email AS user_email,\n"
                    + "    u.date_of_birth AS user_date_of_birth,\n"
                    + "    u.number_phone AS user_number_phone,\n"
                    + "    a.address_detail AS user_address_detail,\n"
                    + "    u.account AS user_account,\n"
                    + "    u.password AS user_password,\n"
                    + "    r.id AS role_id,\n"
                    + "    r.role_name AS user_role,\n"
                    + "    u.address_id AS idAd,\n"
                    + "    u.status\n"
                    + "FROM user AS u\n"
                    + "LEFT JOIN user_role AS ur ON u.id = ur.user_id\n"
                    + "LEFT JOIN role AS r ON ur.role_id = r.id\n"
                    + "LEFT JOIN address AS a ON u.address_id = a.id\n"
                    + "WHERE r.id IN (1, 2) AND u.status = ? AND u.full_name LIKE ?;"; // Thêm điều kiện tìm kiếm theo tên

            ResultSet rs = JDBCHelped.executeQuery(sql, status, "%" + name + "%"); // Sử dụng tham số name và thêm '%' để tìm kiếm theo phần của tên
            while (rs.next()) {
                Address address = new Address(rs.getString(11), rs.getString(6));
                Role role = new Role(rs.getString(9), rs.getString(10));
                User user = new User(address, rs.getDate(4), rs.getString(1), rs.getString(7), rs.getString(3), rs.getString(2), rs.getString(5), rs.getString(8), rs.getString(12));
                UserRole uRole = new UserRole(role, user);
                list.add(uRole);
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public ArrayList<UserRole> getAll_User(String status) {
        ArrayList<UserRole> list = new ArrayList<>();
        try {
            String sql = "SELECT\n"
                    + "                               u.id AS user_ID,\n"
                    + "                               u.full_name AS user_full_name,\n"
                    + "                               u.email AS user_email,\n"
                    + "                               u.date_of_birth AS user_date_of_birth,\n"
                    + "                               u.number_phone AS user_number_phone,\n"
                    + "                               a.address_detail AS user_address_detail,\n"
                    + "                               u.account AS user_account,\n"
                    + "                               u.password AS user_password,\n"
                    + "                               r.id AS role_id,\n"
                    + "                               r.role_name AS user_role,\n"
                    + "                               u.address_id AS idAd,\n"
                    + "                               u.status\n"
                    + "                           FROM user AS u\n"
                    + "                           LEFT JOIN user_role AS ur ON u.id = ur.user_id\n"
                    + "                           LEFT JOIN role AS r ON ur.role_id = r.id\n"
                    + "                           LEFT JOIN address AS a ON u.address_id = a.id\n"
                    + "                           where r.id = 3 and u.status = ?;";
            ResultSet rs = JDBCHelped.executeQuery(sql, status);
            while (rs.next()) {
                Address address = new Address(rs.getString(11), rs.getString(6));
                Role role = new Role(rs.getString(9), rs.getString(10));
                User user = new User(address, rs.getDate(4), rs.getString(1), rs.getString(7), rs.getString(3), rs.getString(2), rs.getString(5), rs.getString(8), rs.getString(12));
                UserRole uRole = new UserRole(role, user);
                list.add(uRole);
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String getUser_Bill(User user) {
        String numberOfPurchases = String.valueOf("0");
        try {
            String sql = "SELECT COUNT(bill.id) as total_bills_with_status_1\n"
                    + "FROM user\n"
                    + "LEFT JOIN bill ON user.id = bill.user_id\n"
                    + "WHERE bill.status = '3' and user_id = ?\n"
                    + "GROUP BY user.id, user.full_name;";
            ResultSet rs = JDBCHelped.executeQuery(sql, user.getId());
            while (rs.next()) {
                numberOfPurchases = rs.getString(1);
            }
            return numberOfPurchases;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
        public ArrayList<UserRole> getSearch_User(String status, String name) {
        ArrayList<UserRole> list = new ArrayList<>();
        try {
            String sql = "SELECT\n"
                    + "    u.id AS user_ID,\n"
                    + "    u.full_name AS user_full_name,\n"
                    + "    u.email AS user_email,\n"
                    + "    u.date_of_birth AS user_date_of_birth,\n"
                    + "    u.number_phone AS user_number_phone,\n"
                    + "    a.address_detail AS user_address_detail,\n"
                    + "    u.account AS user_account,\n"
                    + "    u.password AS user_password,\n"
                    + "    r.id AS role_id,\n"
                    + "    r.role_name AS user_role,\n"
                    + "    u.address_id AS idAd,\n"
                    + "    u.status\n"
                    + "FROM user AS u\n"
                    + "LEFT JOIN user_role AS ur ON u.id = ur.user_id\n"
                    + "LEFT JOIN role AS r ON ur.role_id = r.id\n"
                    + "LEFT JOIN address AS a ON u.address_id = a.id\n"
                    + "WHERE r.id = 3 AND u.status = ? AND u.full_name LIKE ?;"; // Thêm điều kiện tìm kiếm theo tên

            ResultSet rs = JDBCHelped.executeQuery(sql, status, "%" + name + "%"); // Sử dụng tham số name và thêm '%' để tìm kiếm theo phần của tên
            while (rs.next()) {
                Address address = new Address(rs.getString(11), rs.getString(6));
                Role role = new Role(rs.getString(9), rs.getString(10));
                User user = new User(address, rs.getDate(4), rs.getString(1), rs.getString(7), rs.getString(3), rs.getString(2), rs.getString(5), rs.getString(8), rs.getString(12));
                UserRole uRole = new UserRole(role, user);
                list.add(uRole);
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
