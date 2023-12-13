/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author TgNam
 */
public class Product {

    private BigDecimal product_price;
    private Date created_at;
    private Custom custome_id;
    private String id;
    private Material material_id;
    private SaleProduct sale_id;
    private Thickness thickness_id;
    private Date updated_at;
    private String description;
    private String name_product;
    private String status;
    private byte[] image_Type;
    
    public Product() {
    }

    public Product(BigDecimal product_price, String name_product) {
        this.product_price = product_price;
        this.name_product = name_product;
    }

    public Product(BigDecimal product_price, SaleProduct sale_id, String name_product) {
        this.product_price = product_price;
        this.sale_id = sale_id;
        this.name_product = name_product;
    }
    
    public Product(BigDecimal product_price, Date created_at, String id, SaleProduct sale_id, Date updated_at, String name_product, String status) {
        this.product_price = product_price;
        this.created_at = created_at;
        this.id = id;
        this.sale_id = sale_id;
        this.updated_at = updated_at;
        this.name_product = name_product;
        this.status = status;
    }

    
    
    public Product(BigDecimal product_price, Date created_at, Custom custome_id, String id, Material material_id, SaleProduct sale_id, Thickness thickness_id, Date updated_at, String description, String name_product, String status) {
        this.product_price = product_price;
        this.created_at = created_at;
        this.custome_id = custome_id;
        this.id = id;
        this.material_id = material_id;
        this.sale_id = sale_id;
        this.thickness_id = thickness_id;
        this.updated_at = updated_at;
        this.description = description;
        this.name_product = name_product;
        this.status = status;
    }
    
      //them cai nay 22-11 2:45sa

    public Product(Custom custome_id, Material material_id, Thickness thickness_id, String name_product) {
        this.custome_id = custome_id;
        this.material_id = material_id;
        this.thickness_id = thickness_id;
        this.name_product = name_product;
    }

    public Product(BigDecimal product_price, Custom custome_id, Material material_id, Thickness thickness_id, String name_product) {
        this.product_price = product_price;
        this.custome_id = custome_id;
        this.material_id = material_id;
        this.thickness_id = thickness_id;
        this.name_product = name_product;
    }
    
    //them cai nay ngay 32/11

    public Product(BigDecimal product_price, Date created_at, Custom custome_id, String id, Material material_id, Thickness thickness_id, Date updated_at, String description, String name_product, String status) {
        this.product_price = product_price;
        this.created_at = created_at;
        this.custome_id = custome_id;
        this.id = id;
        this.material_id = material_id;
        this.thickness_id = thickness_id;
        this.updated_at = updated_at;
        this.description = description;
        this.name_product = name_product;
        this.status = status;
    }
    
    //them ngay 24/11

    public Product(String name_product) {
        this.name_product = name_product;
    }

    public Product(BigDecimal product_price, Custom custome_id, Material material_id, Thickness thickness_id, String description, String name_product) {
        this.product_price = product_price;
        this.custome_id = custome_id;
        this.material_id = material_id;
        this.thickness_id = thickness_id;
        this.description = description;
        this.name_product = name_product;
    }
    //them vao 1/12

    public Product(BigDecimal product_price, Date created_at, Custom custome_id, String id, Material material_id, Thickness thickness_id, Date updated_at, String description, String name_product, String status, byte[] image_Type) {
        this.product_price = product_price;
        this.created_at = created_at;
        this.custome_id = custome_id;
        this.id = id;
        this.material_id = material_id;
        this.thickness_id = thickness_id;
        this.updated_at = updated_at;
        this.description = description;
        this.name_product = name_product;
        this.status = status;
        this.image_Type = image_Type;
    }

    public Product(BigDecimal product_price, Custom custome_id, Material material_id, Thickness thickness_id, String description, String name_product, byte[] image_Type) {
        this.product_price = product_price;
        this.custome_id = custome_id;
        this.material_id = material_id;
        this.thickness_id = thickness_id;
        this.description = description;
        this.name_product = name_product;
        this.image_Type = image_Type;
    }
    
    //them 4/12

    public Product(String id, String status) {
        this.id = id;
        this.status = status;
    }
    
    

    public byte[] getImage_Type() {
        return image_Type;
    }

    public void setImage_Type(byte[] image_Type) {
        this.image_Type = image_Type;
    }
    
    

    public BigDecimal getProduct_price() {
        return product_price;
    }

    public void setProduct_price(BigDecimal product_price) {
        this.product_price = product_price;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Custom getCustome_id() {
        return custome_id;
    }

    public void setCustome_id(Custom custome_id) {
        this.custome_id = custome_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Material getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(Material material_id) {
        this.material_id = material_id;
    }

    public SaleProduct getSale_id() {
        return sale_id;
    }

    public void setSale_id(SaleProduct sale_id) {
        this.sale_id = sale_id;
    }

    public Thickness getThickness_id() {
        return thickness_id;
    }

    public void setThickness_id(Thickness thickness_id) {
        this.thickness_id = thickness_id;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName_product() {
        return name_product;
    }

    public void setName_product(String name_product) {
        this.name_product = name_product;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    // Cập nhật trạng thái của sản phẩm
    public String checkTrangThai(){
        if (this.status.equals("1")) {
            return "Còn hàng";
        }if (this.status.equals("0")) {
            return "Hết hàng";
        }else{
            return "Null";
        }
    }
    
}
