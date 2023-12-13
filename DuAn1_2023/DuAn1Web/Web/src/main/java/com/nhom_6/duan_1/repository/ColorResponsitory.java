package com.nhom_6.duan_1.repository;

import com.nhom_6.duan_1.model.entity.Color;
import com.nhom_6.duan_1.model.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ColorResponsitory extends JpaRepository<Color, Long> {
    @Query("SELECT c FROM Color c WHERE c.nameColor = :nameColor")
    public Color getByName(@Param("nameColor") String nameColor);

    @Query(" SELECT DISTINCT pd.color FROM ProductDetail pd " +
            " where  pd.product.id = :productId and pd.size.id = :sizeId and pd.quantity > 0 ")
    List<Color> findAllByProduct_IdAndSize_Id(@Param("sizeId") Long sizeId,@Param("productId") Long productId);
}
