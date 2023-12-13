/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.service;

import java.util.ArrayList;
import com.model.ProductDetail;

/**
 *
 * @author TgNam
 */
public interface ProductDetailService {

    ArrayList<ProductDetail> getList();

    //them vao ngay 22-11 6:00 AM
    ArrayList<ProductDetail> getproduct_Exchange();
    //them vao 24 /11
    ArrayList<ProductDetail> getProductDetails_id(String idPR);

    ArrayList<ProductDetail> getProduct_Detail_Sell();

    ArrayList<ProductDetail> getProduct_Detail_Stop_selling();
    //Thêm sản phẩm cho bên bán hàng
    ArrayList<ProductDetail> get_ProductDetails_id_Bill(String id, String status);
    
    public boolean them(ProductDetail prd);
    public boolean sua(String id,ProductDetail prd);
    public boolean xoa(String id);
    
     //them cai nay 25/11
    ArrayList<ProductDetail> getRestore_Product_Detail_stop_selling(int min, int max);
    public boolean khoiPhuc(String id);
    
    //them cai nay ngay 28//11
    ArrayList<ProductDetail> getProductDetail_Selling_Next(String idPR, int min, int max);
    //ngay 29//11
    public boolean getQuantity(String id , int quantity);
    
    // linh dz
    public ProductDetail getById(String id);
    boolean Update_procuct_detail_billdetail(String id);
}
