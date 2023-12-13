/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.service;

import com.model.ExchangeBill;
import java.util.ArrayList;
import com.model.ExchangeBillDetail;

/**
 *
 * @author thiet
 */
public interface Exchange_detailServict {
    public ArrayList<ExchangeBillDetail> list();
    public boolean insert(ExchangeBillDetail ex);
    //26/11
    public ArrayList<ExchangeBillDetail> getExchangeBillDetail(String id);
    boolean delete_exchangeBillDetal(ExchangeBill exchangeBill);
    ArrayList<ExchangeBillDetail> getExBill_idBill(String id) ;
}
