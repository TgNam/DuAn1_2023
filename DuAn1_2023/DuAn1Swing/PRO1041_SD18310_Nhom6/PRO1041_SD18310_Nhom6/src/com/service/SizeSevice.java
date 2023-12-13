/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.service;

import java.util.ArrayList;
import com.model.Size;

/**
 *
 * @author thiet
 */
public interface SizeSevice {
    ArrayList<Size> getAll();
    public boolean Insert(Size sz);
    public boolean Update(String id, Size sz);
     //them vao 1/12
    public boolean remove(String id);
    ArrayList<Size> getSize_Sell(int min, int max);
    //them vao 12/12
    
    ArrayList<Size> getCBB();
    ArrayList<Size> getSize_Stop(int min, int max);
    public boolean getKP(String id);
}
