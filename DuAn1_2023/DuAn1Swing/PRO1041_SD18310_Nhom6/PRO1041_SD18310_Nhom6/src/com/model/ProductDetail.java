/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model;

/**
 *
 * @author TgNam
 */
import java.util.Date;

public class ProductDetail {

    // private fields
    private int quantity;
    private Color colorId;
    private Date createdAt;
    private String id;
    private Product productId;
    private Size sizeId;
    private Date updatedAt;
    private String status;
    private byte[] image;
    
    public ProductDetail(int quantity, Color colorId, Date createdAt, String id, Product productId, Size sizeId, Date updatedAt, String status) {
        this.quantity = quantity;
        this.colorId = colorId;
        this.createdAt = createdAt;
        this.id = id;
        this.productId = productId;
        this.sizeId = sizeId;
        this.updatedAt = updatedAt;
        this.status = status;
    }

    public ProductDetail(Color colorId, Product productId, Size sizeId) {
        this.colorId = colorId;
        this.productId = productId;
        this.sizeId = sizeId;
    }

    public ProductDetail(String id, Color colorId, Product productId, Size sizeId) {
        this.id = id;
        this.colorId = colorId;
        this.productId = productId;
        this.sizeId = sizeId;
    }

    public ProductDetail() {
    }

    //them cai nay 24-11 2:45sa
    public ProductDetail(int quantity, Color colorId, String id, Product productId, Size sizeId) {
        this.quantity = quantity;
        this.colorId = colorId;
        this.id = id;
        this.productId = productId;
        this.sizeId = sizeId;
    }

    public ProductDetail(int quantity, Color colorId, Product productId, Size sizeId) {
        this.quantity = quantity;
        this.colorId = colorId;
        this.productId = productId;
        this.sizeId = sizeId;
    }

    public ProductDetail(Color colorId, String id, Product productId, Size sizeId) {
        this.colorId = colorId;
        this.id = id;
        this.productId = productId;
        this.sizeId = sizeId;
    }
    // them 1/12
    public ProductDetail(int quantity, Color colorId, Product productId, Size sizeId, byte[] image) {
        this.quantity = quantity;
        this.colorId = colorId;
        this.productId = productId;
        this.sizeId = sizeId;
        this.image = image;
    }
    
     public ProductDetail(int quantity, Color colorId, Date createdAt, String id, Product productId, Size sizeId, Date updatedAt, String status, byte[] image) {
        this.quantity = quantity;
        this.colorId = colorId;
        this.createdAt = createdAt;
        this.id = id;
        this.productId = productId;
        this.sizeId = sizeId;
        this.updatedAt = updatedAt;
        this.status = status;
        this.image = image;
        
    }
    
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public ProductDetail(String id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Color getColorId() {
        return colorId;
    }

    public void setColorId(Color colorId) {
        this.colorId = colorId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public Size getSizeId() {
        return sizeId;
    }

    public void setSizeId(Size sizeId) {
        this.sizeId = sizeId;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Cập nhật trạng thái của sản phẩm
    public String checkTrangThai() {
        if (this.status.equals("1")) {
            return "Còn hàng";
        }
        if (this.status.equals("0")) {
            return "Hết hàng";
        } else {
            return "Null";
        }
    }

}
