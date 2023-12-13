/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.service;

import com.model.Material;
import java.util.ArrayList;
import com.model.Thickness;

/**
 *
 * @author thiet
 */
public interface ThicknessService {
    ArrayList<Thickness> getAll();
    public boolean them(Thickness tk);
    public boolean sua(String id, Thickness tk);
     //them vao 1/12
    public boolean xoa(String id);
    ArrayList<Thickness> getThickness_sell(int min, int max);
    //them vao 12/12
    ArrayList<Thickness> getCBB();
    public boolean getKP(String id);
    ArrayList<Thickness> getThickness_Stop(int min, int max);
}
