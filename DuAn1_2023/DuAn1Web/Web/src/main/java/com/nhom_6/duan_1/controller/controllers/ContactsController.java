package com.nhom_6.duan_1.controller.controllers;

import com.nhom_6.duan_1.model.entity.Bill;
import com.nhom_6.duan_1.model.entity.User;
import com.nhom_6.duan_1.serviceImp.UserServiceImp;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contact")
public class ContactsController {

    @Autowired
    UserServiceImp userServiceImp;

    @GetMapping
    public String contacts(Model model, HttpSession session) {
        User u = userServiceImp.getLogin(session);
        boolean isLogin = false;
        if(u != null){
            isLogin = true;
            model.addAttribute("userLogin",u);
        }
        model.addAttribute("isLogin",isLogin);
        model.addAttribute("page", "contact");
        return "layout/index";
    }
}
