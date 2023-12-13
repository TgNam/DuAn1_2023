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

public class Color {
    // private fields
    private Date createdAt;
    private String id;
    private Date updatedAt;
    private String nameColor;
    private boolean status;
    
    public Color() {
    }

    public Color(String nameColor) {
        this.nameColor = nameColor;
    }
    
    public Color(Date createdAt, String id, Date updatedAt, String nameColor) {
        this.createdAt = createdAt;
        this.id = id;
        this.updatedAt = updatedAt;
        this.nameColor = nameColor;
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

    public String getNameColor() {
        return nameColor;
    }

    public void setNameColor(String nameColor) {
        this.nameColor = nameColor;
    }
    
}

