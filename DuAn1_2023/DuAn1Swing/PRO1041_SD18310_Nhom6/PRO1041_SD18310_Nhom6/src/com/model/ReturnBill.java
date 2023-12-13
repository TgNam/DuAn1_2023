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

public class ReturnBill {
    // private fields
    private BigDecimal totalCost;
    private Bill billId;
    private Date createdAt;
    private String id;
    private Date updatedAt;
    private String reasonDescription;
    private String status;

    public ReturnBill(BigDecimal totalCost, Bill billId, Date createdAt, String id, Date updatedAt, String reasonDescription, String status) {
        this.totalCost = totalCost;
        this.billId = billId;
        this.createdAt = createdAt;
        this.id = id;
        this.updatedAt = updatedAt;
        this.reasonDescription = reasonDescription;
        this.status = status;
    }
    
    
    public ReturnBill(BigDecimal totalCost, Bill billId, Date createdAt, String id, Date updatedAt, String reasonDescription) {
        this.totalCost = totalCost;
        this.billId = billId;
        this.createdAt = createdAt;
        this.id = id;
        this.updatedAt = updatedAt;
        this.reasonDescription = reasonDescription;
    }

    public ReturnBill(BigDecimal totalCost, Bill billId,String reasonDescription) {
        this.totalCost = totalCost;
        this.billId = billId;
        this.reasonDescription = reasonDescription;
    }

    public ReturnBill() {
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
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

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getReasonDescription() {
        return reasonDescription;
    }

    public void setReasonDescription(String reasonDescription) {
        this.reasonDescription = reasonDescription;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

