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

public class ReturnBillDetail {
    // private fields
    private BigDecimal priceAtTheTimeOfPurchase;
    private int quantityOfProductsReturned;
    private Date createdAt;
    private String id;
    private ProductDetail productDetailId;
    private ReturnBill returnBillId;
    private Date updatedAt;
    private String status;

    public ReturnBillDetail() {
    }

    public ReturnBillDetail(BigDecimal priceAtTheTimeOfPurchase, int quantityOfProductsReturned, Date createdAt, String id, ProductDetail productDetailId, ReturnBill returnBillId, Date updatedAt, String status) {
        this.priceAtTheTimeOfPurchase = priceAtTheTimeOfPurchase;
        this.quantityOfProductsReturned = quantityOfProductsReturned;
        this.createdAt = createdAt;
        this.id = id;
        this.productDetailId = productDetailId;
        this.returnBillId = returnBillId;
        this.updatedAt = updatedAt;
        this.status = status;
    }

    public ReturnBillDetail(BigDecimal priceAtTheTimeOfPurchase, int quantityOfProductsReturned, ProductDetail productDetailId, ReturnBill returnBillId, String status) {
        this.priceAtTheTimeOfPurchase = priceAtTheTimeOfPurchase;
        this.quantityOfProductsReturned = quantityOfProductsReturned;
        this.productDetailId = productDetailId;
        this.returnBillId = returnBillId;
        this.status = status;
    }

    public BigDecimal getPriceAtTheTimeOfPurchase() {
        return priceAtTheTimeOfPurchase;
    }

    public void setPriceAtTheTimeOfPurchase(BigDecimal priceAtTheTimeOfPurchase) {
        this.priceAtTheTimeOfPurchase = priceAtTheTimeOfPurchase;
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

    public ReturnBill getReturnBillId() {
        return returnBillId;
    }

    public void setReturnBillId(ReturnBill returnBillId) {
        this.returnBillId = returnBillId;
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

