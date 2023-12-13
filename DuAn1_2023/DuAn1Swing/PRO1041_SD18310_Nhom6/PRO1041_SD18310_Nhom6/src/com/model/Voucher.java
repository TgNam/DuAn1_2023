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

public class Voucher {

    // private fields
    private double saleOf;
    private Date createdAt;
    private Date endAt;
    private String id;
    private Date startAt;
    private Date updatedAt;
    private String status;

    public Voucher() {
    }

    public Voucher(double saleOf, Date createdAt, Date endAt, String id, Date startAt, String status) {
        this.saleOf = saleOf;
        this.createdAt = createdAt;
        this.endAt = endAt;
        this.id = id;
        this.startAt = startAt;
        this.status = status;
    }

    public Voucher(double saleOf, Date createdAt, Date endAt, String id, Date startAt, Date updatedAt, String status) {
        this.saleOf = saleOf;
        this.createdAt = createdAt;
        this.endAt = endAt;
        this.id = id;
        this.startAt = startAt;
        this.updatedAt = updatedAt;
        this.status = status;
    }

    public Voucher(double saleOf,  Date endAt, String id, Date startAt) {
        this.saleOf = saleOf;
        this.endAt = endAt;
        this.id = id;
        this.startAt = startAt;
    }


    public Voucher(String id) {
        this.id = id;
    }

    public double getSaleOf() {
        return saleOf;
    }

    public void setSaleOf(double saleOf) {
        this.saleOf = saleOf;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
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
