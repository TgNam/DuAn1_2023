/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.service;

import java.util.ArrayList;
import java.util.Date;
import com.model.SaleProduct;

/**
 *
 * @author TgNam
 */
public interface SaleProductService {
    ArrayList<SaleProduct> getList();
    String add(SaleProduct sp);
    String delete_ID_created_at(Date created_at);
    String delete_ID(String id);
    String update(SaleProduct sp, String id);
}
