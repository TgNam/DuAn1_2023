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

public class ExchangeBill {
    // private fields
    private Bill billId;
    private Date createdAt;
    private String id;
    private Date updatedAt;
    private String describeReason;
    private String status;

    public ExchangeBill(Bill billId, Date createdAt, String id, Date updatedAt, String describeReason, String status) {
        this.billId = billId;
        this.createdAt = createdAt;
        this.id = id;
        this.updatedAt = updatedAt;
        this.describeReason = describeReason;
        this.status = status;
    }
    
    public ExchangeBill(Bill billId, Date createdAt, String id, Date updatedAt, String describeReason) {
        this.billId = billId;
        this.createdAt = createdAt;
        this.id = id;
        this.updatedAt = updatedAt;
        this.describeReason = describeReason;
    }
    
    //them cai nay

    public ExchangeBill(Bill billId, Date createdAt, String describeReason) {
        this.billId = billId;
        this.createdAt = createdAt;
        this.describeReason = describeReason;
    }

    public ExchangeBill(Date createdAt) {
        this.createdAt = createdAt;
    }
    
    //26/11

    public ExchangeBill(String id) {
        this.id = id;
    }
    
    

    public ExchangeBill() {
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

    public String getDescribeReason() {
        return describeReason;
    }

    public void setDescribeReason(String describeReason) {
        this.describeReason = describeReason;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}

