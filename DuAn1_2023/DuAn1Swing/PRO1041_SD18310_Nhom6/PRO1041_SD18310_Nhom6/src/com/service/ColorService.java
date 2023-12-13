/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.service;

import java.util.ArrayList;
import com.model.Color;

/**
 *
 * @author thiet
 */
public interface ColorService {
    ArrayList<Color> getAll();
    public boolean them(Color c);
    public boolean  sua(String id, Color c);
     //them vao 1/12
    public boolean xoa(String id);
    ArrayList<Color> getColor_Sell(int min, int max);
    //them vao 12/12
    ArrayList<Color> getCBB();
    public boolean getKP(String id);
    ArrayList<Color> getColor_Stop(int min, int max);
}
