/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model;

/**
 *
 * @author TgNam
 */
import java.math.BigDecimal;
import java.util.Date;

public class BillDetail {
    // private fields
    private BigDecimal priceNow;
    private Bill billId;
    private Date createdAt;
    private String id;
    private ProductDetail productDetailId;
    private Date updatedAt;
    private String quantityPurchased;

    public BillDetail() {
    }

    public BillDetail(String id, ProductDetail productDetailId, String quantityPurchased) {
        this.id = id;
        this.productDetailId = productDetailId;
        this.quantityPurchased = quantityPurchased;
    }

    

    public BillDetail(Date createdAt, Date updatedAt, String quantityPurchased) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.quantityPurchased = quantityPurchased;
    }
    
    public BillDetail(BigDecimal priceNow, Bill billId, Date createdAt, String id, ProductDetail productDetailId, Date updatedAt, String quantityPurchased) {
        this.priceNow = priceNow;
        this.billId = billId;
        this.createdAt = createdAt;
        this.id = id;
        this.productDetailId = productDetailId;
        this.updatedAt = updatedAt;
        this.quantityPurchased = quantityPurchased;
    }
    
      //them cai nay 22-11 2:45sa 
    
    public BillDetail(BigDecimal priceNow, Date createdAt, ProductDetail productDetailId, Date updatedAt, String quantityPurchased) {
        this.priceNow = priceNow;
        this.createdAt = createdAt;
        this.productDetailId = productDetailId;
        this.updatedAt = updatedAt;
        this.quantityPurchased = quantityPurchased;
    }

    public BigDecimal getPriceNow() {
        return priceNow;
    }

    public void setPriceNow(BigDecimal priceNow) {
        this.priceNow = priceNow;
    }

    public Bill getBillId() {
        return billId;
    }

    public void setBillId(Bill billId) {
        this.billId = billId;
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

    public ProductDetail getProductDetailId() {
        return productDetailId;
    }

    public void setProductDetailId(ProductDetail productDetailId) {
        this.productDetailId = productDetailId;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getQuantityPurchased() {
        return quantityPurchased;
    }

    public void setQuantityPurchased(String quantityPurchased) {
        this.quantityPurchased = quantityPurchased;
    }

    @Override
    public String toString() {
        return "BillDetail{" + "priceNow=" + priceNow + ", billId=" + billId + ", createdAt=" + createdAt + ", id=" + id + ", productDetailId=" + productDetailId + ", updatedAt=" + updatedAt + ", quantityPurchased=" + quantityPurchased + '}';
    }
    
    
}

