package com.nhom_6.duan_1.controller.controllers;

import com.nhom_6.duan_1.model.entity.User;
import com.nhom_6.duan_1.serviceImp.UserServiceImp;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Login {

    @Autowired
    UserServiceImp userServiceImp;

    @GetMapping("/login")
    public String loginPage(HttpSession session){
        User u = userServiceImp.getLogin(session);
        boolean isLogin = false;
        if(u != null){
            return "redirect:/";
        }
        return "/web/login";
    }
}
