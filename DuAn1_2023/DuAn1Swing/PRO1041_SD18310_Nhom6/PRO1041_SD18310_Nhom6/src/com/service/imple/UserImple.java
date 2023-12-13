/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.service.imple;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.model.Address;
import com.model.User;
import com.repository.UserRepository;
import com.service.UserService;

/**
 *
 * @author TgNam
 */
public class UserImple implements UserService {

    UserRepository ur = new UserRepository();

    @Override
    public ArrayList<User> getUser_name_phone() {
        return ur.getUser_name_phone();
    }

    @Override
    public String add_user(User user) {
        if (ur.add_user(user)) {
            return "Thêm thành công!";
        } else {
            return "Thêm thất bại!";
        }
    }

    @Override
    public boolean add_user_all(User user, Date nowDate, String address) {
        if (ur.add_user_all(user, nowDate, address)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean Update_user_all(User user, String id) {
        if (ur.Update_user_all(user, id)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean Update_status_user(String status, User user) {
        if (ur.Update_status_user(status, user)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<User> getCustomer() {
        return ur.getCustomers();
    }

    @Override
    public boolean Update_user_address(String address_id, String id) {
        if (ur.Update_user_address(address_id, id)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Address getAddress(Date created_at, String address_detail) {
        return ur.getAddress(created_at, address_detail);
    }

}
