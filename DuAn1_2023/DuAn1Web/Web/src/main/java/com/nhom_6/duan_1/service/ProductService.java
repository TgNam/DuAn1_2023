package com.nhom_6.duan_1.service;

import com.nhom_6.duan_1.model.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> getAll();
    Product getById(Long id);
    Page<Product> getAll(Integer pageNo);
    List<Product> getProductsByCategoryId(Long id);
    List<Product> getProductByPrice(Integer start, Integer end);
    List<Product> getProductByColor(Long color);

    List<Product> getAllByStatus(String status);
}
