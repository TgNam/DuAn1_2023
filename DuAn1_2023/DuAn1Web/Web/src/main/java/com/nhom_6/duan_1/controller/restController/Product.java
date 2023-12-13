package com.nhom_6.duan_1.controller.restController;

import com.nhom_6.duan_1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class Product {
    @Autowired
    ProductService productService;
    @GetMapping("/find-by-category/{id}")
    public ResponseEntity<?> getByCategory(@PathVariable Long id){
        try {
            return ResponseEntity.ok(productService.getProductsByCategoryId(id));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
