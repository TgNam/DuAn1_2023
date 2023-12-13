/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model;

import java.util.Date;

public class Address {
    // private fields
    private Date createdAt;
    private String id;
    private Date updatedAt;
    private String addressDetail;
    private String commune;
    private String district;
    private String province;

    public Address() {
    }

    public Address(String id, String addressDetail) {
        this.id = id;
        this.addressDetail = addressDetail;
    }
    
    public Address(Date createdAt, String id, Date updatedAt, String addressDetail, String commune, String district, String province) {
        this.createdAt = createdAt;
        this.id = id;
        this.updatedAt = updatedAt;
        this.addressDetail = addressDetail;
        this.commune = commune;
        this.district = district;
        this.province = province;
    }

       //them thang conntractor nay
    public Address(String addressDetail) {
        this.addressDetail = addressDetail;
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

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    
}

