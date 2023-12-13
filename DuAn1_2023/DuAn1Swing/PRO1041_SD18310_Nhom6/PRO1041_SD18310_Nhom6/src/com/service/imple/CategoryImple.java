/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.service.imple;

import com.form.Product;
import com.model.Category;
import com.repository.CategoryRepository;
import com.service.CategoryService;
import java.util.ArrayList;

/**
 *
 * @author thiet
 */
public class CategoryImple implements CategoryService{
    CategoryRepository ctg = new CategoryRepository();

    @Override
    public ArrayList<com.model.Product> getProduct_not_Category(String name_Pr, String name_Category, int min, int max) {
        return ctg.getProduct_not_category(name_Pr, name_Category, min, max);
    }

    @Override
    public ArrayList<com.model.Product> getProduct_Category(String name_Pr, String name_Category, int min, int max) {
        return ctg.getProduct_category(name_Pr, name_Category, min, max);
    }

    @Override
    public ArrayList<Category> getCategory_Action() {
        return ctg.getCategory_Action();
    }

    @Override
    public ArrayList<Category> getCategory_not_Action() {
        return ctg.getCategory_not_Action();
    }

    @Override
    public ArrayList<Category> getCategory_not_Action_Next(int min, int max) {
        return ctg.getCategory_not_Action_Next(min, max);
    }

    @Override
    public ArrayList<Category> getCategory_Action_Next(int min, int max) {
        return ctg.getCategory_Action_Next(min, max);
    }

    @Override
    public boolean Insert(Category c) {
        if(ctg.Insert(c)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean update(String id, Category c) {
        if(ctg.update(id, c)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        if(ctg.delete(id)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean Restore(String id) {
        if(ctg.Restore(id)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public ArrayList<Category> getAll() {
        return ctg.getAll();
    }
    
    
}
