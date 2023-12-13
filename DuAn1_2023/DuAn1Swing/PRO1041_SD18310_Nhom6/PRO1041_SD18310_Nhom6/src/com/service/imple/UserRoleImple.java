/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.service.imple;

import java.util.ArrayList;
import java.util.Date;
import com.model.User;
import com.model.UserRole;
import com.repository.UserRoleRepository;
import com.service.UserRoleService;

/**
 *
 * @author TgNam
 */
public class UserRoleImple implements UserRoleService{
    UserRoleRepository urr = new UserRoleRepository();
    @Override
    public ArrayList<UserRole> getAll_Employee(String status) {
        return urr.getAll_Employee(status);
    }

    @Override
    public String add_user_role(User user, String id) {
        if (urr.add_user_role(user, id)) {
            return "Thêm thành công!";
        }
        else{
            return "Thêm thất bại!";
        }
    }

    @Override
    public boolean Update_user_role(User user, String id) {
        if (urr.Update_user_role(user, id)) {
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public ArrayList<UserRole> getSearch_Employee(String status, String name) {
       return urr.getSearch_Employee(status, name);
    }

    @Override
    public ArrayList<UserRole> getAll_User(String status) {
        return urr.getAll_User(status);
    }

    @Override
    public String getUser_Bill(User user) {
        return urr.getUser_Bill(user);
    }

    @Override
    public ArrayList<UserRole> getSearch_User(String status, String name) {
        return urr.getSearch_User(status, name);
    }
}
