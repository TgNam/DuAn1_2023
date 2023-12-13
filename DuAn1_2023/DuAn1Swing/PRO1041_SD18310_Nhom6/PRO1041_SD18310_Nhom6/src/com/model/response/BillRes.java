/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model.response;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author lenovo
 */
public class BillRes {
    private String code;
    private String customeName;
    private String customeAddress;
    private String customePhone;
    private List<ProductRes> products;
    private BigDecimal saleOfMoney;
    private BigDecimal total;

    public BillRes() {
    }

    public BillRes(String code, String customeName, String customeAddress, String customePhone, List<ProductRes> products, BigDecimal saleOfMoney, BigDecimal total) {
        this.code = code;
        this.customeName = customeName;
        this.customeAddress = customeAddress;
        this.customePhone = customePhone;
        this.products = products;
        this.saleOfMoney = saleOfMoney;
        this.total = total;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCustomeName() {
        return customeName;
    }

    public void setCustomeName(String customeName) {
        this.customeName = customeName;
    }

    public String getCustomeAddress() {
        return customeAddress;
    }

    public void setCustomeAddress(String customeAddress) {
        this.customeAddress = customeAddress;
    }

    public String getCustomePhone() {
        return customePhone;
    }

    public void setCustomePhone(String customePhone) {
        this.customePhone = customePhone;
    }

    public List<ProductRes> getProducts() {
        return products;
    }

    public void setProducts(List<ProductRes> products) {
        this.products = products;
    }

    public BigDecimal getSaleOfMoney() {
        return saleOfMoney;
    }

    public void setSaleOfMoney(BigDecimal saleOfMoney) {
        this.saleOfMoney = saleOfMoney;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
    
    
}
