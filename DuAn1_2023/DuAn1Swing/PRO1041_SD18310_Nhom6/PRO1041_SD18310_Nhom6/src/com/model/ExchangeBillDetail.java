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

public class ExchangeBillDetail {
    // private fields
    private int quantityOfProductsReturned;
    private Date createdAt;
    private ExchangeBill exchangeBillId;
    private String id;
    private ProductDetail productDetailId;
    private Date updatedAt;
    private String status;

    public ExchangeBillDetail() {
    }

    public ExchangeBillDetail(int quantityOfProductsReturned, Date createdAt, ExchangeBill exchangeBillId, String id, ProductDetail productDetailId, Date updatedAt, String status) {
        this.quantityOfProductsReturned = quantityOfProductsReturned;
        this.createdAt = createdAt;
        this.exchangeBillId = exchangeBillId;
        this.id = id;
        this.productDetailId = productDetailId;
        this.updatedAt = updatedAt;
        this.status = status;
    }
    
    // them cai nay
    
    public ExchangeBillDetail(int quantityOfProductsReturned, ExchangeBill exchangeBillId, ProductDetail productDetailId) {
        this.quantityOfProductsReturned = quantityOfProductsReturned;
        this.exchangeBillId = exchangeBillId;
        this.productDetailId = productDetailId;
    }
    
    //ngay 26//11
    public ExchangeBillDetail(int quantityOfProductsReturned, Date createdAt, ExchangeBill exchangeBillId, String id, ProductDetail productDetailId, Date updatedAt) {
        this.quantityOfProductsReturned = quantityOfProductsReturned;
        this.createdAt = createdAt;
        this.exchangeBillId = exchangeBillId;
        this.id = id;
        this.productDetailId = productDetailId;
        this.updatedAt = updatedAt;
    }

    public int getQuantityOfProductsReturned() {
        return quantityOfProductsReturned;
    }

    public void setQuantityOfProductsReturned(int quantityOfProductsReturned) {
        this.quantityOfProductsReturned = quantityOfProductsReturned;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public ExchangeBill getExchangeBillId() {
        return exchangeBillId;
    }

    public void setExchangeBillId(ExchangeBill exchangeBillId) {
        this.exchangeBillId = exchangeBillId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}

