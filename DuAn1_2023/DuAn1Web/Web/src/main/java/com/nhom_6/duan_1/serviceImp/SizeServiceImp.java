package com.nhom_6.duan_1.serviceImp;

import com.nhom_6.duan_1.model.entity.Size;
import com.nhom_6.duan_1.repository.SizeResponsitory;
import com.nhom_6.duan_1.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeServiceImp implements SizeService {

    @Autowired
    private SizeResponsitory sizeResponsitory;
    @Override
    public List<Size> getAll() {
        return sizeResponsitory.findAll();
    }

    @Override
    public Size getById(Long id) {
        Size size = sizeResponsitory.findById(id).orElseThrow(() -> {
            try {
                throw new ClassNotFoundException("Size not found");
            } catch (ClassNotFoundException e){
                throw new RuntimeException();
            }
        });
        return size;
    }

    @Override
    public Size getByName(String name) {
        return sizeResponsitory.getByName(name);
    }
}
