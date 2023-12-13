package com.nhom_6.duan_1.repository;

import com.nhom_6.duan_1.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryResponsitory extends JpaRepository<Category, Long> {
    Optional<Category> findById(Long id);
}
