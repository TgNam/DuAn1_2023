package com.nhom_6.duan_1.service;

import com.nhom_6.duan_1.model.entity.Color;

import java.util.List;

public interface ColorService {
    public List<Color> getAll();
    public Color getById(Long idColor);
    public Color getByName(String nameColor);
}
