package com.nhom_6.duan_1.serviceImp;

import com.nhom_6.duan_1.model.entity.Category;
import com.nhom_6.duan_1.model.entity.Color;
import com.nhom_6.duan_1.model.entity.Product;
import com.nhom_6.duan_1.model.entity.ProductDetail;
import com.nhom_6.duan_1.repository.CategoryResponsitory;
import com.nhom_6.duan_1.repository.ColorResponsitory;
import com.nhom_6.duan_1.repository.ProductRepository;
import com.nhom_6.duan_1.service.ProductDetailsService;
import com.nhom_6.duan_1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductSeviceImp implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryResponsitory categoryResponsitory;

    @Autowired
    ColorResponsitory colorResponsitory;

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllByStatus(String status) {
        return productRepository.findAllWithStatus(status);
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() ->{
                    try {
                        throw new Exception("Product not found");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }


    @Override
    public Page<Product> getAll(Integer page) {
        Pageable pageable = PageRequest.of(page, 2);
        return productRepository.findAll(pageable);
    }

    public List<Product> searchByName(String content){
        return productRepository.searchAllByNameProduct(content);
    }


    @Override
    public List<Product> getProductsByCategoryId(Long id) {
        Category category = categoryResponsitory.findById(id)
                .orElseThrow(() -> {
                    try {
                        throw new ClassNotFoundException("Not found category");
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                });
        List<Product> products = category.getProducts();

        return products;
    }

    @Override
    public List<Product> getProductByColor(Long color) {
        Color  colorE = colorResponsitory.findById(color)
                .orElse(null);
        if(colorE == null) return null;

        return productRepository.findAllByColor_Id(color);
    }

    @Override
    public List<Product> getProductByPrice(Integer start, Integer end) {
        List<Product> list = new ArrayList<>();
        if (start == 0 && end == 0) {
            return productRepository.findAll();
        }
        for (Product i : productRepository.findAll()) {
            if (i.getProductPrice() >= start * 1000 && i.getProductPrice() <= end * 1000) {
                list.add(i);
            }
        }
        return list;
    }

}
