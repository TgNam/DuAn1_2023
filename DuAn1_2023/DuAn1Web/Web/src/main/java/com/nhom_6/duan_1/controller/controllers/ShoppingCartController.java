package com.nhom_6.duan_1.controller.controllers;

import com.nhom_6.duan_1.model.entity.Bill;
import com.nhom_6.duan_1.model.entity.User;
import com.nhom_6.duan_1.service.BillService;
import com.nhom_6.duan_1.service.UserService;
import com.nhom_6.duan_1.serviceImp.BillServiceImp;
import com.nhom_6.duan_1.serviceImp.UserServiceImp;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user/shopping-cart")
public class ShoppingCartController {

    @Autowired
    BillServiceImp billService;

    @Autowired
    UserServiceImp userService;

    @GetMapping
    public String shoppingCart(Model model,
                               HttpSession session,
                               @RequestParam(value = "error",required = false) Boolean error) {
        User user = userService.getLogin(session);
        model.addAttribute("page", "shopping-cart");
        Bill bill = billService.getCartByUserId(user.getId());
        Double saleOf=0.0;
        if(bill == null){
            bill = new Bill();
        }
        if(bill.getVoucher() != null) {
            saleOf = bill.getVoucher().getSaleOf();
        }
        boolean isLogin = false;
        if(user != null){
            isLogin = true;
            model.addAttribute("userLogin",user);
        }
        if(error != null){
            model.addAttribute("error",true);
        }else{
            model.addAttribute("error",false);
        }
        model.addAttribute("isLogin",isLogin);
        model.addAttribute("cart", bill);
        model.addAttribute("saleOf", saleOf);
        return "/layout/index";
    }
}
