package com.nhom_6.duan_1.repository;

import com.nhom_6.duan_1.model.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SizeResponsitory extends JpaRepository<Size,Long>{
    @Query("SELECT s FROM Size s WHERE s.nameSize =:name")
    public Size getByName(String name);

    @Query(" SELECT DISTINCT pd.size FROM ProductDetail pd " +
            " where  pd.product.id = :id ")
    List<Size> findAllByProduct_Id(@Param("id") Long id);

}
