package com.nhom_6.duan_1.controller.controllers;

import com.nhom_6.duan_1.model.entity.Bill;
import com.nhom_6.duan_1.model.entity.User;
import com.nhom_6.duan_1.serviceImp.BillServiceImp;
import com.nhom_6.duan_1.serviceImp.UserServiceImp;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("user")
public class Voucher {

    @Autowired
    UserServiceImp userServiceImp;

    @Autowired
    BillServiceImp billServiceImp;

    @PostMapping("/add-voucher")
    public String addVoucher(@ModelAttribute("voucher") String voucher, HttpSession session){

        User u = userServiceImp.getLogin(session);

        try {
            billServiceImp.addVoucher(voucher,u.getId());
            return "redirect:/user/shopping-cart";

        }catch (Exception e){
            return "redirect:/user/shopping-cart?error=true";
        }
    }

}
