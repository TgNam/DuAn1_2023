/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.service.imple;

import com.model.ProductCategory;
import com.repository.Product_CategoryRepository;
import com.service.Product_CategoryServict;

/**
 *
 * @author thiet
 */
public class Product_CategoryImple implements Product_CategoryServict{
    Product_CategoryRepository pcs = new Product_CategoryRepository();
    
    @Override
    public boolean Insert(ProductCategory pc) {
        if(pcs.Insert(pc)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean Delete(ProductCategory pc) {
        if(pcs.Delete(pc)){
            return true;
        }else{
            return false;
        }
    }
    
}
