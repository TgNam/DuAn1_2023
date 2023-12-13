/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.service.imple;

import java.util.Date;
import com.repository.AddressRepository;
import com.service.AddressService;

/**
 *
 * @author TgNam
 */
public class AddressImple implements AddressService{
    AddressRepository ar = new AddressRepository();
    @Override
    public String add_address(Date nowDate, String address) {
        if (ar.add_address(nowDate, address)) {
            return "Thêm thành công!";
        }
        else{
            return "Thêm thất bại!";
        }
    }

    @Override
    public void delete_address(Date nowDate, String address) {
        ar.delete_address(nowDate, address);
    }

    @Override
    public void update_address(Date nowDate, String address, String id) {
        ar.update_address(nowDate, address, id);
    }
    
}
