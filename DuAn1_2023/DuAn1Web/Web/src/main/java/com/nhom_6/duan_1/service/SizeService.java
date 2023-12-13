package com.nhom_6.duan_1.service;

import com.nhom_6.duan_1.model.entity.Size;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SizeService {
    public List<Size> getAll();
    public Size getById(Long id);
    public Size getByName(String name);
}
