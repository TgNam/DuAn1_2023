package com.nhom_6.duan_1.controller.controllers;

import com.nhom_6.duan_1.model.entity.Product;
import com.nhom_6.duan_1.model.entity.User;
import com.nhom_6.duan_1.service.ProductService;
import com.nhom_6.duan_1.serviceImp.UserServiceImp;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/")
public class Home {
    @Autowired
    UserServiceImp userServiceImp;


    @Autowired
    ProductService productServicel;
    @GetMapping
    public String home(Model model, HttpSession session) {
        User u = userServiceImp.getLogin(session);
        boolean  isLogin = false;
        if(u != null){
            isLogin = true;
            model.addAttribute("userLogin",u);
        }
        List pl = productServicel.getAllByStatus("1");
        Collections.sort(pl, new Comparator<Product>() {
            @Override
            public int compare(Product product, Product t1) {
                return product.getCreatedAt().compareTo(t1.getCreatedAt());
            }
        });
        List<Product> sublist = pl;
        if(pl.size() > 8){
            sublist = pl.subList(0, 8);
        }
        model.addAttribute("products",sublist);
        model.addAttribute("isLogin",isLogin);
        model.addAttribute("page","home");
        return "layout/index";
    }
}
