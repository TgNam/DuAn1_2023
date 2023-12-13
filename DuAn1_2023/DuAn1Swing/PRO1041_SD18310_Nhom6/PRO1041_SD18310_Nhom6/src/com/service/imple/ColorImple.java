/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.service.imple;

import java.util.ArrayList;
import com.model.Color;
import com.repository.ColorRepository;
import com.service.ColorService;

/**
 *
 * @author thiet
 */
public class ColorImple implements ColorService{
    ColorRepository cl = new ColorRepository();
    @Override
    public ArrayList<Color> getAll() {
        return cl.getListCL();
    }

    @Override
    public boolean them(Color c) {
        if(cl.Insert(c)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean sua(String id, Color c) {
        if(cl.Update(id, c)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean xoa(String id) {
        if(cl.Delete(id)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public ArrayList<Color> getColor_Sell(int min, int max) {
        return cl.getColor_sell(min, max);
    }

    @Override
    public ArrayList<Color> getCBB() {
        return cl.getCBB();
    }

    @Override
    public boolean getKP(String id) {
        if(cl.KhoiPhuc(id)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public ArrayList<Color> getColor_Stop(int min, int max) {
        return cl.getColor_Stop(min, max);
    }
    
}
