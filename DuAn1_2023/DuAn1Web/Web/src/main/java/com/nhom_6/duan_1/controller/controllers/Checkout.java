package com.nhom_6.duan_1.controller.controllers;

import com.nhom_6.duan_1.model.entity.Address;
import com.nhom_6.duan_1.model.entity.Bill;
import com.nhom_6.duan_1.model.entity.User;
import com.nhom_6.duan_1.repository.AddressResponsitory;
import com.nhom_6.duan_1.repository.BillResponsitory;
import com.nhom_6.duan_1.service.BillDetailService;
import com.nhom_6.duan_1.serviceImp.BillServiceImp;
import com.nhom_6.duan_1.serviceImp.UserServiceImp;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user/checkout")
public class Checkout {
    @Autowired
    UserServiceImp userServiceImp;
    @Autowired
    BillServiceImp billServiceImp;

    @Autowired
    BillResponsitory billResponsitory;

    @Autowired
    AddressResponsitory addressResponsitory;

    @GetMapping("")
    public String checkoutPage(Model model,
                               HttpSession session,
                               @RequestParam(value = "succ",required = false) Boolean succ){

        User u = userServiceImp.getLogin(session);
        Bill bill = billServiceImp.getCartByUserId(u.getId());

        boolean isLogin = false;
        if(u != null){
            isLogin = true;
            model.addAttribute("userLogin",u);
        }

        if(bill == null || bill.getBillDetails().size() == 0){
            model.addAttribute("exits",false);
        }

        else{
            model.addAttribute("exits",true);
        }

        System.out.println((boolean) model.getAttribute("exits"));
        model.addAttribute("isLogin",isLogin);
        model.addAttribute("bill",bill);
        model.addAttribute("page","checkout");
        return "layout/index";
    }

    @PostMapping("/order")
    public String order(HttpSession session, @ModelAttribute("address") String address){
        User u = userServiceImp.getLogin(session);
        Bill bill = billServiceImp.getCartByUserId(u.getId());
        Address add = new Address();
        add.setAddressDetail(address);
        add.setBill(bill);
        addressResponsitory.save(add);

        bill.setAddress(add);
        bill.setStatus("2");
        billResponsitory.save(bill);

        return "redirect:/user/checkout/thank-you";
    }

    @GetMapping("/thank-you")
    public String thankYou(Model model,
                           HttpSession session){
        User u = userServiceImp.getLogin(session);
        Bill bill = billServiceImp.getCartByUserId(u.getId());

        boolean isLogin = false;
        if(u != null){
            isLogin = true;
            model.addAttribute("userLogin",u);
        }
        model.addAttribute("isLogin",isLogin);
        model.addAttribute("page","thankiu");
        return "layout/index";
    }
}
