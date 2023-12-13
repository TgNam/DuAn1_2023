/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.service.imple;

import java.util.ArrayList;
import com.model.Custom;
import com.repository.CustomRepository;
import com.service.CustomService;

/**
 *
 * @author thiet
 */
public class CustomImple implements CustomService{
    CustomRepository ct = new CustomRepository();
    @Override
    public ArrayList<Custom> getAll() {
        return ct.getListCustom();
    }

    @Override
    public boolean them(Custom c) {
        if(ct.Insert(c)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean sua(String id, Custom c) {
        if(ct.Update(id, c)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean xoa(String id) {
        if(ct.Delete(id)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public ArrayList<Custom> getCustom_Sell(int min, int max) {
        return ct.getCustom_Sell(min, max);
    }

    @Override
    public ArrayList<Custom> getCBB() {
        return ct.getCBB();
    }

    @Override
    public boolean getKP(String id) {
        if(ct.KhoiPhuc(id)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public ArrayList<Custom> getCustom_Stop(int min, int max) {
        return ct.getCustom_Stop(min, max);
    }
    
}
