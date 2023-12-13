package com.nhom_6.duan_1.service;

import com.nhom_6.duan_1.model.entity.ProductDetail;

import java.util.List;

public interface ProductDetailsService {
    public List<ProductDetail> getAll();
    public ProductDetail getById(Long id);
    public List<ProductDetail> getBySizeId(Long sizeId);
    public List<ProductDetail> getByIdColor(Long id);
}
