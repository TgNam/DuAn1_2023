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

public class ShiftAssignment {
    // private fields
    private BigDecimal finalAmount;
    private BigDecimal startingAmount;
    private Date createdAt;
    private Date endAt;
    private String id;
    private Date startAt;
    private Date updatedAt;
    private User userId;
    private WorkSchedule workSchedulesId;
    private String description;
    private String status;

    public ShiftAssignment() {
    }

    public ShiftAssignment(BigDecimal finalAmount, BigDecimal startingAmount, Date createdAt, Date endAt, String id, Date startAt, Date updatedAt, User userId, WorkSchedule workSchedulesId, String description, String status) {
        this.finalAmount = finalAmount;
        this.startingAmount = startingAmount;
        this.createdAt = createdAt;
        this.endAt = endAt;
        this.id = id;
        this.startAt = startAt;
        this.updatedAt = updatedAt;
        this.userId = userId;
        this.workSchedulesId = workSchedulesId;
        this.description = description;
        this.status = status;
    }

    public BigDecimal getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(BigDecimal finalAmount) {
        this.finalAmount = finalAmount;
    }

    public BigDecimal getStartingAmount() {
        return startingAmount;
    }

    public void setStartingAmount(BigDecimal startingAmount) {
        this.startingAmount = startingAmount;
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

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public WorkSchedule getWorkSchedulesId() {
        return workSchedulesId;
    }

    public void setWorkSchedulesId(WorkSchedule workSchedulesId) {
        this.workSchedulesId = workSchedulesId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}

