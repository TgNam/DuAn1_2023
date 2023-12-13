package com.nhom_6.duan_1.serviceImp;

import com.nhom_6.duan_1.model.entity.Color;
import com.nhom_6.duan_1.repository.ColorResponsitory;
import com.nhom_6.duan_1.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ColorServiceImp implements ColorService {
    @Autowired
    private ColorResponsitory colorResponsitory;

    @Override
    public List<Color> getAll() {
        return colorResponsitory.findAll();
    }

    @Override
    public Color getById(Long idColor) {
        Color color = colorResponsitory.findById(idColor).orElseThrow(() -> {
            try {
                throw new ClassNotFoundException("Not found color");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        return color;
    }

    @Override
    public Color getByName(String nameColor) {
        return colorResponsitory.getByName(nameColor);
    }
}
