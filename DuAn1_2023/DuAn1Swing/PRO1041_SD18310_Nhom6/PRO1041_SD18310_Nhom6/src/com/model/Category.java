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

public class Category {
    // private fields
    private boolean status;
    private Date createdAt;
    private String id;
    private Date updatedAt;
    private String nameCategory;

    public Category() {
    }

    public Category(boolean status, Date createdAt, String id, Date updatedAt, String nameCategory) {
        this.status = status;
        this.createdAt = createdAt;
        this.id = id;
        this.updatedAt = updatedAt;
        this.nameCategory = nameCategory;
    }
    //them cai nay 3/12

    public Category(String nameCategory) {
        this.nameCategory = nameCategory;
    }
    
    //them 4/12

    public Category(boolean status, String id) {
        this.status = status;
        this.id = id;
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

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }
    
}

