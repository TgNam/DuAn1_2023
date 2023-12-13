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

public class Thickness {
    // private fields
    private int gsm;
    private boolean status;
    private Date createdAt;
    private String id;
    private Date updatedAt;

    public Thickness() {
    }

    public Thickness(int gsm, boolean status, Date createdAt, String id, Date updatedAt) {
        this.gsm = gsm;
        this.status = status;
        this.createdAt = createdAt;
        this.id = id;
        this.updatedAt = updatedAt;
    }
      //them cai nay 22-11 2:45sa

    public Thickness(int gsm) {
        this.gsm = gsm;
    }
    
    public int getGsm() {
        return gsm;
    }

    public void setGsm(int gsm) {
        this.gsm = gsm;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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
    
}

