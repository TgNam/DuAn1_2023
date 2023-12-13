/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.untils;
import java.sql.*;
/**
 *
 * @author thiet
 */
public class JDBC {
    static String url = "jdbc:mysql://localhost:3306/db_levents";//len mang xem doi lại sao cho hop ly nha
    static String name = "root";
    static String pass = "root";
    
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, name, pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
    
    public static void main(String[] args) {
        Connection conn = getConnection();
        if (conn != null) {
            System.out.println("thanh cong");
        } else {
            System.out.println("that bai");
        }
    }
}
