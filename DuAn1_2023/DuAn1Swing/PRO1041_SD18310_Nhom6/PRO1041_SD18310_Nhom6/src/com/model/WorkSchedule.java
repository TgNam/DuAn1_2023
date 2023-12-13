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
import java.sql.Time;

public class WorkSchedule {
    // private fields
    private Time endAt;
    private Time startAt;
    private boolean status;
    private Date createdAt;
    private String id;
    private Date updatedAt;
    private String description;
    private String nameWorkSchedule;

    public WorkSchedule() {
    }

    public WorkSchedule(Time endAt, Time startAt, boolean status, Date createdAt, String id, Date updatedAt, String description, String nameWorkSchedule) {
        this.endAt = endAt;
        this.startAt = startAt;
        this.status = status;
        this.createdAt = createdAt;
        this.id = id;
        this.updatedAt = updatedAt;
        this.description = description;
        this.nameWorkSchedule = nameWorkSchedule;
    }

    public Time getEndAt() {
        return endAt;
    }

    public void setEndAt(Time endAt) {
        this.endAt = endAt;
    }

    public Time getStartAt() {
        return startAt;
    }

    public void setStartAt(Time startAt) {
        this.startAt = startAt;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNameWorkSchedule() {
        return nameWorkSchedule;
    }

    public void setNameWorkSchedule(String nameWorkSchedule) {
        this.nameWorkSchedule = nameWorkSchedule;
    }
    
}

