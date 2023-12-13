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
public class SaleProduct {
    private String id;
    private Date created_at;
    private Date updated_at;
    private Date start_at;
    private Date end_at;
    private double sale;
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Date getStart_at() {
        return start_at;
    }

    public void setStart_at(Date start_at) {
        this.start_at = start_at;
    }

    public Date getEnd_at() {
        return end_at;
    }

    public void setEnd_at(Date end_at) {
        this.end_at = end_at;
    }

    public double getSale() {
        return sale;
    }

    public void setSale(double sale) {
        this.sale = sale;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public SaleProduct(String id, Date created_at, Date updated_at, Date start_at, Date end_at, double sale, String status) {
        this.id = id;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.start_at = start_at;
        this.end_at = end_at;
        this.sale = sale;
        this.status = status;
    }

    public SaleProduct() {
    }

    public SaleProduct(String id, double sale) {
        this.id = id;
        this.sale = sale;
    }

   
    
    // Cập nhật trạng thái của sale sản phẩm
    public String checkTrangThai(){
        if (this.status.equals("1")) {
            return "Đang hoạt động";
        }if (this.status.equals("0")) {
            return "Ngừng hoạt động";
        }else{
            return "Null";
        }
    }
}
