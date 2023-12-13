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
public class UserRepository {

    public ArrayList<User> getUser_name_phone() {
        ArrayList<User> list = new ArrayList<>();
        try {
            String sql = "SELECT id, full_name, number_phone, email, status , account FROM db_levents.user";
            ResultSet rs = JDBCHelped.executeQuery(sql);
            while (rs.next()) {
                list.add(new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6))
                );
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public Address getAddress(Date created_at, String address_detail) {
        Address address = new Address();
        try {
            String sql = "SELECT id, address_detail  FROM db_levents.address where created_at= ? and address_detail =?";
            ResultSet rs = JDBCHelped.executeQuery(sql, created_at, address_detail);
            while (rs.next()) {
                address = new Address(rs.getString(1),rs.getString(2));
            }
            return address;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public boolean add_user(User user) {
        try {
            String sql = "insert into db_levents.user (created_at,updated_at,full_name,number_phone,status) value\n"
                    + "(?,?,?,?,'1');";
            JDBCHelped.excuteUpdate(sql, user.getCreatedAt(), user.getUpdatedAt(), user.getFullName(), user.getNumberPhone());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean add_user_all(User user, Date nowDate, String address) {
        try {
            String sql = "INSERT INTO user (created_at, updated_at, date_of_birth, email, full_name, password, number_phone, account, status, address_id)\n"
                    + "VALUES \n"
                    + "(?, ?, ?, ?, ?, ?, ?, ?, ?, (SELECT id FROM db_levents.address where created_at= ? and address_detail =?));";
            JDBCHelped.excuteUpdate(sql,
                    user.getCreatedAt(),
                    user.getUpdatedAt(),
                    user.getDateOfBirth(),
                    user.getEmail(),
                    user.getFullName(), 
                    user.getPassword(), 
                    user.getNumberPhone(),
                    user.getAccount(), 
                    user.getStatus(), 
                    nowDate, 
                    address
            );
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean Update_user_all(User user, String id) {
        try {
            String sql = "Update user set  updated_at = ? , date_of_birth = ?, email = ?, full_name = ?, password = ?, number_phone = ?, account = ? where id = ?;";
            JDBCHelped.excuteUpdate(sql,
                    user.getUpdatedAt(),
                    user.getDateOfBirth(),
                    user.getEmail(),
                    user.getFullName(), 
                    user.getPassword(), 
                    user.getNumberPhone(),
                    user.getAccount(), 
                    id 
            );
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean Update_status_user(String status, User user) {
        try {
            String sql = "UPDATE db_levents.user SET status = ? WHERE (id = ?);";
            JDBCHelped.excuteUpdate(sql,
                    status,
                    user.getId()
            );
            return true;
        } catch (Exception e) {
            return false;
        }
    }
            // get user của voucher
    public ArrayList<User> getKhachHangInVoucher() {
        ArrayList<User> list = new ArrayList<>();
        String query = "SELECT u.id, u.full_name, u.number_phone, u.email\n"
                + "FROM user u\n"
                + "WHERE EXISTS (\n"
                + "    SELECT 1\n"
                + "    FROM user_role ur\n"
                + "    INNER JOIN role r ON r.id = ur.role_id\n"
                + "    WHERE ur.user_id = u.id\n"
                + "      AND r.role_name = 'USER'\n"
                + ")";

        try {
            ResultSet rs = JDBCHelped.executeQuery(query);
            while (rs.next()) {
                list.add(new User(rs.getString("id"), rs.getString("full_name"), rs.getString("number_phone"), rs.getString("email"))    
                );
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
            // get user của voucher linhz
    public ArrayList<User> getCustomers() {
        ArrayList<User> list = new ArrayList<>();
        String query = "select * from user where id in (SELECT user_id FROM db_levents.user_role where role_id = 3) ";
        try {
            ResultSet rs = JDBCHelped.executeQuery(query);
            while (rs.next()) {
                list.add(new User(rs.getString("id"), rs.getString("full_name"), rs.getString("number_phone"), rs.getString("email"))    
                );
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public ArrayList<User> getCustomersById(int id) {
        ArrayList<User> list = new ArrayList<>();
        String query = "select * from user where id in (SELECT user_id FROM db_levents.user_role where role_id = 3) and id = ?";
        try {
            ResultSet rs = JDBCHelped.executeQuery(query,id);
            while (rs.next()) {
                list.add(new User(rs.getString("id"), rs.getString("full_name"), rs.getString("number_phone"), rs.getString("email"))    
                );
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public ArrayList<User> getCustomersByName(String name) {
        ArrayList<User> list = new ArrayList<>();
        String query = "select * from user where id in (SELECT user_id FROM db_levents.user_role where role_id = 3) and full_name like ?";
        try {
            ResultSet rs = JDBCHelped.executeQuery(query,"%"+name+"%");
            while (rs.next()) {
                list.add(new User(rs.getString("id"), rs.getString("full_name"), rs.getString("number_phone"), rs.getString("email"))    
                );
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    // end linhz----------------------------
    public boolean Update_user_address(String address_id, String id) {
        try {
            String sql = "UPDATE db_levents.user SET address_id = ? WHERE (id = ?);";
            JDBCHelped.excuteUpdate(sql,
                    address_id,
                    id
            );
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
