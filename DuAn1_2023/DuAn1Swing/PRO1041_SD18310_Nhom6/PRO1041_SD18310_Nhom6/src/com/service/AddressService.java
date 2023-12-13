/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.service;

import java.util.Date;

/**
 *
 * @author TgNam
 */
public interface AddressService {
    String add_address(Date nowDate, String address);
    void delete_address(Date nowDate, String address);
    void update_address(Date nowDate, String address, String id);
}
