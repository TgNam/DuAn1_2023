package com.model.response;

import java.math.BigDecimal;

/**
 *
 * @author lenovo
 */
public class ProductRes {
    private String codeProduct;
    private String name;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal total;

    public ProductRes() {
    }

    public ProductRes(String codeProduct, String name, Integer quantity, BigDecimal unitPrice, BigDecimal total) {
        this.codeProduct = codeProduct;
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.total = total;
    }

    public String getCodeProduct() {
        return codeProduct;
    }

    public void setCodeProduct(String codeProduct) {
        this.codeProduct = codeProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
    
    
}
