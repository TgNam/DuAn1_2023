package com.nhom_6.duan_1.serviceImp;

import com.nhom_6.duan_1.model.entity.Category;
import com.nhom_6.duan_1.repository.CategoryResponsitory;
import com.nhom_6.duan_1.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    private CategoryResponsitory categoryResponsitory;

    @Override
    public List<Category> getAll() {
        return categoryResponsitory.findAll();
    }

    @Override
    public Optional<Category> getById(Long id) {
        if (id != -1) {
            try {
                Category category = categoryResponsitory.findById(id)
                        .orElseThrow(() -> new ClassNotFoundException("Category not found with id: " + id));
                return Optional.of(category);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            return Optional.empty();
        }
    }

}
