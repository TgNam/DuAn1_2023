package com.nhom_6.duan_1.controller.restController;

import com.nhom_6.duan_1.model.entity.Product;
import com.nhom_6.duan_1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductImageController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/{productId}/image", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getProductImage(@PathVariable Long productId) {
        // Lấy Product từ cơ sở dữ liệu theo productId
        Product product = productService.getById(productId);
        // Trả về mảng byte chứa dữ liệu hình ảnh
        return product.getImageByte();
    }
}