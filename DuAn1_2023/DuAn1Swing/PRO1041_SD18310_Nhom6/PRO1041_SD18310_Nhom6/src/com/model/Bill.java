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
public class Bill {
    private BigDecimal intoMoney;
    private BigDecimal totalCost;
    private Address addressId;
    private Date createdAt;
    private Date deliveryDate;
    private String id;
    private Date updatedAt;
    private User userId;
    private Voucher voucherId;
    private String status;

    public Bill() {
    }

    public Bill(Address addressId, Date createdAt, String id, User userId, String status) {
        this.addressId = addressId;
        this.createdAt = createdAt;
        this.id = id;
        this.userId = userId;
        this.status = status;
    }
    
    public Bill(Date createdAt, String id, User userId, String status) {
        this.createdAt = createdAt;
        this.id = id;
        this.userId = userId;
        this.status = status;
    }

    public Bill(Date createdAt, Date deliveryDate, Date updatedAt, User userId) {
        this.createdAt = createdAt;
        this.deliveryDate = deliveryDate;
        this.updatedAt = updatedAt;
        this.userId = userId;
    }

    // tao cai nay

    public Bill(String id) {
        this.id = id;
    }
    
    
    public Bill(BigDecimal intoMoney, BigDecimal totalCost, Address addressId, Date createdAt, Date deliveryDate, String id, Date updatedAt, User userId, Voucher voucherId, String status) {
        this.intoMoney = intoMoney;
        this.totalCost = totalCost;
        this.addressId = addressId;
        this.createdAt = createdAt;
        this.deliveryDate = deliveryDate;
        this.id = id;
        this.updatedAt = updatedAt;
        this.userId = userId;
        this.voucherId = voucherId;
        this.status = status;
    } 
    
    public BigDecimal getIntoMoney() {
        return intoMoney;
    }

    public void setIntoMoney(BigDecimal intoMoney) {
        this.intoMoney = intoMoney;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public Address getAddressId() {
        return addressId;
    }

    public void setAddressId(Address addressId) {
        this.addressId = addressId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
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

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Voucher getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(Voucher voucherId) {
        this.voucherId = voucherId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    // Cập nhật trạng thái của sản phẩm
    public String checkTrangThai(){
        if (this.status.equals("1")) {
            return "Đã thanh toán";
        }else if(this.status.equals("0")) {
            return "Chưa thanh toán";
        }else if(this.status.equals("2")) {
            return "Đang giao";
        }else if(this.status.equals("3")) {
            return "Hoàn thành";
        }else if(this.status.equals("4")) {
            return "Chờ xác nhận trả hàng";
        }else if(this.status.equals("5")) {
            return "Trả hàng thành công";
        }else if(this.status.equals("6")) {
            return "Chờ xác nhận đổi hàng";
        }else if(this.status.equals("7")) {
            return "Đổi thành công";
        }else{
            return "Null";
        }
    }

    @Override
    public String toString() {
        return "Bill{" + "intoMoney=" + intoMoney + ", totalCost=" + totalCost + ", addressId=" + addressId + ", createdAt=" + createdAt + ", deliveryDate=" + deliveryDate + ", id=" + id + ", updatedAt=" + updatedAt + ", userId=" + userId + ", voucherId=" + voucherId + ", status=" + status + '}';
    }
    
}
