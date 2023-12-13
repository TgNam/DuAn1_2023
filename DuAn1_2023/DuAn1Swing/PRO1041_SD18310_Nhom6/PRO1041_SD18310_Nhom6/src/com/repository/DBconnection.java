/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author TgNam
 */
public class DBconnection {

    private static String DATABASE = "jdbc:mysql://localhost:3306/db_levents";
    private static String USER = "root";
    private static String PASSWORD = "root";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(DATABASE, USER, PASSWORD);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
