package com.nhom_6.duan_1.service;

import com.nhom_6.duan_1.model.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> getAll();
    Optional<Category> getById(Long id);

}
