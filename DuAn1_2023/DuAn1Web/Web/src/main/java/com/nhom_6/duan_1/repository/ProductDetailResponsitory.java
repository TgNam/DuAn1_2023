package com.nhom_6.duan_1.repository;

import com.nhom_6.duan_1.model.entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductDetailResponsitory extends JpaRepository<ProductDetail,Long> {
    @Query("SELECT pd FROM ProductDetail pd WHERE pd.size.id = :sizeId")
    public List<ProductDetail> getBySizeId(@Param("sizeId") Long sizeId);

    @Query("SELECT pd FROM ProductDetail pd WHERE pd.color.id = :idColor")
    public List<ProductDetail> getByIdColor(@Param("idColor") Long idColor);
}
