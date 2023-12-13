/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.service;

import com.form.Product;
import com.model.Category;
import java.util.ArrayList;

/**
 *
 * @author thiet
 */
public interface CategoryService {

    ArrayList<com.model.Product> getProduct_not_Category(String name_Pr, String name_Category, int min, int max);

    ArrayList<com.model.Product> getProduct_Category(String name_Pr, String name_Category, int min, int max);

    ArrayList<Category> getCategory_Action();

    ArrayList<Category> getCategory_not_Action();

    ArrayList<Category> getCategory_not_Action_Next(int min, int max);

    ArrayList<Category> getCategory_Action_Next(int min, int max);
    
    ArrayList<Category> getAll();
    
    public boolean Insert(Category c);

    public boolean update(String id, Category c);

    public boolean delete(String id);

    public boolean Restore(String id);
}
