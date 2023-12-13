/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model;

import java.util.ArrayList;

/**
 *
 * @author lenovo
 */
public class ChartModel {
    private String month;
    private Double totalMoney;
    private String[] dataNull;

    public String[] getDataNull() {
        return dataNull;
    }

    public void setDataNull(String[] dataNull) {
        this.dataNull = dataNull;
    }
    
    public ChartModel() {
        String[] dataNull = {"Not enough data","Not enough data"};
        this.dataNull = dataNull;
    }

    public ChartModel(String month, Double totalMoney) {
        this.month = month;
        this.totalMoney = totalMoney;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }
    
    
}
