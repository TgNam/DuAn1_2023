package com.nhom_6.duan_1.serviceImp;

import com.nhom_6.duan_1.model.entity.ProductDetail;
import com.nhom_6.duan_1.repository.ProductDetailResponsitory;
import com.nhom_6.duan_1.service.ProductDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDetailsServiceImp implements ProductDetailsService {

    @Autowired
    private ProductDetailResponsitory productDetailResponsitory;
    @Override
    public List<ProductDetail> getAll() {
        return productDetailResponsitory.findAll();
    }

    @Override
    public ProductDetail getById(Long id) {
        return productDetailResponsitory.findById(id)
                .orElseThrow(() -> {
            try {
                throw new ClassNotFoundException("Not found ProductDetails");
            } catch (ClassNotFoundException e){
                throw new RuntimeException();
            }
        });
    }

    @Override
    public List<ProductDetail> getBySizeId(Long sizeId) {
        return productDetailResponsitory.getBySizeId(sizeId);
    }

    @Override
    public List<ProductDetail> getByIdColor(Long id) {
        return productDetailResponsitory.getByIdColor(id);
    }


}
