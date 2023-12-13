/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.service;

import java.util.ArrayList;
import com.model.Custom;

/**
 *
 * @author thiet
 */
public interface CustomService {
    ArrayList<Custom> getAll();
    public boolean them(Custom c);
    public boolean sua(String id, Custom c);
     //them vao 1/12
    public boolean xoa(String id);
    ArrayList<Custom> getCustom_Sell(int min, int max);
    
    //them vao 12/12
    ArrayList<Custom> getCBB();
    public boolean getKP(String id);
    ArrayList<Custom> getCustom_Stop(int min, int max);
}
