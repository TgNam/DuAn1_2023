/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.service;

import java.util.ArrayList;
import java.util.Date;
import com.model.User;
import com.model.UserRole;

/**
 *
 * @author TgNam
 */
public interface UserRoleService {
    ArrayList<UserRole> getAll_Employee(String status);
    ArrayList<UserRole> getSearch_Employee(String status, String name);
    ArrayList<UserRole> getAll_User(String status);
    ArrayList<UserRole> getSearch_User(String status, String name);
    String getUser_Bill(User user);
    String add_user_role(User user, String id);
    boolean Update_user_role(User user, String id);
}
