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

public class User {

    // private fields
    private Address addressId;
    private Date createdAt;
    private Date dateOfBirth;
    private String id;
    private Token tokenId;
    private Date updatedAt;
    private String account;
    private String email;
    private String fullName;
    private String numberPhone;
    private String password;
    private String status;

    public User() {
    }
    // chức năng voucher

    public User(String id, String fullName, String numberPhone, String email) {
        this.id = id;
        this.fullName = fullName;
        this.numberPhone = numberPhone;
        this.email = email;
    }

    public User(Address addressId, Date dateOfBirth, String id, String account, String email, String fullName, String numberPhone, String password, String status) {
        this.addressId = addressId;
        this.dateOfBirth = dateOfBirth;
        this.id = id;
        this.account = account;
        this.email = email;
        this.fullName = fullName;
        this.numberPhone = numberPhone;
        this.password = password;
        this.status = status;
    }

    public User(String fullName, String numberPhone) {
        this.fullName = fullName;
        this.numberPhone = numberPhone;
    }

    public User(String id, String fullName, String numberPhone) {
        this.id = id;
        this.fullName = fullName;
        this.numberPhone = numberPhone;
    }
    public User(String id, String fullName, String numberPhone, String email, String status, String account) {
        this.id = id;
        this.fullName = fullName;
        this.numberPhone = numberPhone;
        this.email = email;
        this.status = status;
        this.account = account;
    }
    public User(Date createdAt, Date updatedAt, String fullName, String numberPhone) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.fullName = fullName;
        this.numberPhone = numberPhone;
    }

    public User(Address addressId, Date createdAt, Date dateOfBirth, String id, Token tokenId, Date updatedAt, String account, String email, String fullName, String numberPhone, String password, String status) {
        this.addressId = addressId;
        this.createdAt = createdAt;
        this.dateOfBirth = dateOfBirth;
        this.id = id;
        this.tokenId = tokenId;
        this.updatedAt = updatedAt;
        this.account = account;
        this.email = email;
        this.fullName = fullName;
        this.numberPhone = numberPhone;
        this.password = password;
        this.status = status;
    }

    //ngay 28/11 them cai nay
   

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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Token getTokenId() {
        return tokenId;
    }

    public void setTokenId(Token tokenId) {
        this.tokenId = tokenId;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Cập nhật trạng thái của sản phẩm
    public String checkTrangThai() {
        if (this.status.equals("1")) {
            return "Đang hoạt động";
        }
        if (this.status.equals("0")) {
            return "Đã nghỉ làm";
        } else {
            return "Null";
        }
    }

}
