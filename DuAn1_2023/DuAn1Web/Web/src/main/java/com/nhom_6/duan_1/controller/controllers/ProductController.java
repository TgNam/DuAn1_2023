package com.nhom_6.duan_1.controller.controllers;

import com.nhom_6.duan_1.model.entity.Product;
import com.nhom_6.duan_1.model.entity.User;
import com.nhom_6.duan_1.repository.ColorResponsitory;
import com.nhom_6.duan_1.repository.SizeResponsitory;
import com.nhom_6.duan_1.service.ProductService;
import com.nhom_6.duan_1.serviceImp.UserServiceImp;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    SizeResponsitory sizeResponsitory;

    @Autowired
    ColorResponsitory colorResponsitory;

    @Autowired
    UserServiceImp userServiceImp;

    @GetMapping
    public String product(Model model, HttpSession session) {
        User u = userServiceImp.getLogin(session);
        boolean isLogin = false;
        if(u != null){
            isLogin = true;
            model.addAttribute("userLogin",u);
        }
        model.addAttribute("isLogin",isLogin);
        model.addAttribute("page", "shop");
        return "layout/index";
    }
    @GetMapping("/product-details")
    public String productDetail(Model model,@RequestParam("id") Long id,HttpSession session) {
        User u = userServiceImp.getLogin(session);
        boolean isLogin = false;
        if(u != null){
            isLogin = true;
            model.addAttribute("userLogin",u);
        }

        List pl = productService.getAllByStatus("1");
        Collections.sort(pl, new Comparator<Product>() {
            @Override
            public int compare(Product product, Product t1) {
                return product.getCreatedAt().compareTo(t1.getCreatedAt());
            }
        });
        List<Product> sublist = pl;
        if(pl.size() > 4){
            sublist = pl.subList(0, 4);
        }
        model.addAttribute("productsDemo",sublist);
        model.addAttribute("isLogin",isLogin);
        model.addAttribute("sizes",sizeResponsitory.findAllByProduct_Id(id));
        model.addAttribute("product", productService.getById(id));
        model.addAttribute("page", "product-details");

        return "layout/index";
    }
    @GetMapping("/product-details/color-by-size")
    public ResponseEntity<?> getProductColorBySize(@RequestParam("sizeId") Long sizeId,
                                                   @RequestParam("productId") Long productId){
        try {
            System.out.println(sizeId);
            return ResponseEntity.ok(colorResponsitory.findAllByProduct_IdAndSize_Id(sizeId, productId));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e);
        }
    }
}
