/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.service;

import java.util.ArrayList;
import com.model.ExchangeBill;

/**
 *
 * @author thiet
 */
public interface ExchangeService {
    public ArrayList<ExchangeBill> getList();
    public boolean Insert(ExchangeBill ex);
    //them 26/11
    public ArrayList<ExchangeBill> getExchangeBills();
    ExchangeBill getExchangeBill_id(String idbill);
    
    //them vao 5/12
    public boolean getUpdate_Bill(String id);
    boolean update_status(ExchangeBill exchangeBill);
    boolean delete_exchangeBill(String id);
}
