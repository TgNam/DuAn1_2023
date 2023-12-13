package com.nhom_6.duan_1.controller.controllers;

import com.nhom_6.duan_1.model.entity.*;
import com.nhom_6.duan_1.serviceImp.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ProductSeviceImp productSeviceImp;
    @Autowired
    private CategoryServiceImp categoryServiceImp;
    @Autowired
    private ProductDetailsServiceImp productDetailsServiceImp;
    @Autowired
    private ColorServiceImp colorServiceImp;

    @Autowired
    UserServiceImp userServiceImp;

    @GetMapping("")
    public String shop(@RequestParam(name = "category", required = false) Long category,
                       @RequestParam(name = "start", required = false) Integer start,
                       @RequestParam(name = "end", required = false) Integer end,
                       @RequestParam(name = "color",required = false) Long color,
                       @RequestParam(name = "name",required = false) String name,
                       HttpSession session,
                       Model model) {


        List<Product> list;
        list = productSeviceImp.getAllByStatus("1");

        if(category!= null) {
            list = productSeviceImp.getProductsByCategoryId(category);
        }
        else if(end != null && start !=null) {
            list =productSeviceImp.getProductByPrice(start,end);
        }
        else if(color!=null) {
            list =productSeviceImp.getProductByColor(color);
        }else if(name != null){
            list = productSeviceImp.searchByName(name);
        }
        User u = userServiceImp.getLogin(session);
        boolean isLogin = false;
        if(u != null){
            isLogin = true;
            model.addAttribute("userLogin",u);
        }
        model.addAttribute("isLogin",isLogin);
        model.addAttribute("page", "shop");
        model.addAttribute("listCategory", categoryServiceImp.getAll());
        model.addAttribute("listColor", colorServiceImp.getAll());
        model.addAttribute("listProduct", list);

        return "layout/index";
    }
}
