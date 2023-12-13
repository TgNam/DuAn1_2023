package com.nhom_6.duan_1.service;

import com.nhom_6.duan_1.model.entity.User;
import jakarta.servlet.http.HttpSession;

public interface UserService {

    User getLogin(HttpSession session);
}
