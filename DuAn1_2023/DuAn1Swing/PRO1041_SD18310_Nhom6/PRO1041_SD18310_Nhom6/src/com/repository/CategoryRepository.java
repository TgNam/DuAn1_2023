/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.repository;

import com.model.Category;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import com.model.Product;
import java.util.Date;
import com.model.Custom;
import com.model.Material;
import com.model.Thickness;

/**
 *
 * @author thiet
 */
public class CategoryRepository {

    //them vao 3/12
    public ArrayList<Product> getProduct_not_category(String name_Pr, String name_category, int min, int max) {
        ArrayList<Product> list = new ArrayList<>();
        try {
            String sql = """
                         SELECT *
                         FROM (
                             SELECT 
                                 product.id,
                                 product.name_product,
                                 product.created_at,
                                 product.updated_at,
                                 custom.name_custom,
                                 product.product_price,
                                 material.name_material,
                                 thickness.gsm,
                                 product.DESCRIPTION,
                                 product.status,
                                 product.image_data,
                                 ROW_NUMBER() OVER (ORDER BY product.id) AS rownum
                             FROM product
                             INNER JOIN custom ON product.custome_id = custom.id
                             INNER JOIN material ON product.material_id = material.id
                             INNER JOIN thickness ON product.thickness_id = thickness.id
                             WHERE product.status = '1' 
                         	AND product.name_product LIKE ?
                         	AND product.id NOT IN (SELECT db_levents.product_category.product_id FROM db_levents.product_category 
                             WHERE db_levents.product_category.category_id = (SELECT id FROM db_levents.category 
                             WHERE db_levents.category.name_category = ?
                                     )
                                 )
                         ) AS temp
                         WHERE rownum BETWEEN ? AND ?;
                         """;
            ResultSet rs = JDBCHelped.executeQuery(sql, name_Pr,name_category.trim(), min, max);
            while (rs.next()) {
                Product pr;
                String id = rs.getString(1);
                String name_Product = rs.getString(2);
                java.sql.Date Create_at = rs.getDate(3);
                java.sql.Date Updated_at = rs.getDate(4);
                String custom = rs.getString(5);
                BigDecimal price = rs.getBigDecimal(6);
                String material = rs.getString(7);
                int thickness = rs.getInt(8);
                String describe = rs.getString(9);
                String status = rs.getString(10);
                byte[] img = rs.getBytes(11);
                pr = new Product(price, Create_at, new Custom(custom), id, new Material(material), new Thickness(thickness), Updated_at, describe, name_Product, status, img);
                list.add(pr);
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public ArrayList<Product> getProduct_category(String name_Pr, String name_category, int min, int max) {
        ArrayList<Product> list = new ArrayList<>();
        try {
            String sql = """
SELECT *
                         FROM (
                             SELECT 
                                 product.id,
                                 product.name_product,
                                 product.created_at,
                                 product.updated_at,
                                 custom.name_custom,
                                 product.product_price,
                                 material.name_material,
                                 thickness.gsm,
                                 product.DESCRIPTION,
                                 product.status,
                                 product.image_data,
                                 ROW_NUMBER() OVER (ORDER BY product.id) AS rownum
                             FROM product
                             INNER JOIN custom ON product.custome_id = custom.id
                             INNER JOIN material ON product.material_id = material.id
                             INNER JOIN thickness ON product.thickness_id = thickness.id
                             WHERE product.status = '1' 
                         	AND product.name_product LIKE ?
                         	AND product.id IN (SELECT db_levents.product_category.product_id FROM db_levents.product_category 
                             WHERE db_levents.product_category.category_id = (SELECT id FROM db_levents.category 
                             WHERE db_levents.category.name_category = ?
                                     )
                                 )
                         ) AS temp
                         WHERE rownum BETWEEN ? AND ?;                         
                         """;
            ResultSet rs = JDBCHelped.executeQuery(sql, name_Pr,name_category, min, max);
            while (rs.next()) {
                Product pr;
                String id = rs.getString(1);
                String name_Product = rs.getString(2);
                java.sql.Date Create_at = rs.getDate(3);
                java.sql.Date Updated_at = rs.getDate(4);
                String custom = rs.getString(5);
                BigDecimal price = rs.getBigDecimal(6);
                String material = rs.getString(7);
                int thickness = rs.getInt(8);
                String describe = rs.getString(9);
                String status = rs.getString(10);
                byte[] img = rs.getBytes(11);
                pr = new Product(price, Create_at, new Custom(custom), id, new Material(material), new Thickness(thickness), Updated_at, describe, name_Product, status, img);
                list.add(pr);
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public ArrayList<Category> getAll() {
        ArrayList<Category> List_Ctg = new ArrayList<>();
        try {
            String sql = "select id, name_category, created_at, updated_at, status from db_levents.category;";
            ResultSet rs = JDBCHelped.executeQuery(sql);
            while (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);
                Date ngayT = rs.getDate(3);
                Date ngayS = rs.getDate(4);
                boolean tt = rs.getBoolean(5);
                Category ctg = new Category(tt, ngayT, id, ngayS, name);
                List_Ctg.add(ctg);
            }
            return List_Ctg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Category> getCategory_Action() {
        ArrayList<Category> List_Ctg = new ArrayList<>();
        try {
            String sql = "select id, name_category, created_at, updated_at, status from db_levents.category where status = 1;";
            ResultSet rs = JDBCHelped.executeQuery(sql);
            while (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);
                Date ngayT = rs.getDate(3);
                Date ngayS = rs.getDate(4);
                boolean tt = rs.getBoolean(5);
                Category ctg = new Category(tt, ngayT, id, ngayS, name);
                List_Ctg.add(ctg);
            }
            return List_Ctg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Category> getCategory_not_Action() {
        ArrayList<Category> List_Ctg = new ArrayList<>();
        try {
            String sql = "select id, name_category, created_at, updated_at, status from db_levents.category where status = 0;";
            ResultSet rs = JDBCHelped.executeQuery(sql);
            while (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);
                Date ngayT = rs.getDate(3);
                Date ngayS = rs.getDate(4);
                boolean tt = rs.getBoolean(5);
                Category ctg = new Category(tt, ngayT, id, ngayS, name);
                List_Ctg.add(ctg);
            }
            return List_Ctg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Category> getCategory_Action_Next(int min, int max) {
        ArrayList<Category> List_Ctg = new ArrayList<>();
        try {
            String sql = """
                         SELECT *
                         FROM (
                         select id, name_category, created_at, updated_at, status, ROW_NUMBER() OVER (ORDER BY category.id) AS rownum from db_levents.category where status = 1
                         ) AS temp
                         WHERE rownum BETWEEN ? AND ?;
                         """;
            ResultSet rs = JDBCHelped.executeQuery(sql, min, max);
            while (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);
                Date ngayT = rs.getDate(3);
                Date ngayS = rs.getDate(4);
                boolean tt = rs.getBoolean(5);
                Category ctg = new Category(tt, ngayT, id, ngayS, name);
                List_Ctg.add(ctg);
            }
            return List_Ctg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Category> getCategory_not_Action_Next(int min, int max) {
        ArrayList<Category> List_Ctg = new ArrayList<>();
        try {
            String sql = """
                         SELECT *
                         FROM (
                         select id, name_category, created_at, updated_at, status, ROW_NUMBER() OVER (ORDER BY category.id) AS rownum from db_levents.category where status = 0
                         ) AS temp
                         WHERE rownum BETWEEN ? AND ?;
                         """;
            ResultSet rs = JDBCHelped.executeQuery(sql, min, max);
            while (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);
                Date ngayT = rs.getDate(3);
                Date ngayS = rs.getDate(4);
                boolean tt = rs.getBoolean(5);
                Category ctg = new Category(tt, ngayT, id, ngayS, name);
                List_Ctg.add(ctg);
            }
            return List_Ctg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean Insert(Category c) {
        try {
            String sql = "INSERT INTO `db_levents`.`category` (`name_category`, `created_at`, `updated_at`, `status`) VALUES (?,curdate(), curdate(), b'1');";
            JDBCHelped.excuteUpdate(sql, c.getNameCategory());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(String id, Category c) {
        try {
            String sql = "UPDATE db_levents.category SET name_category = ? WHERE id = ?;";
            JDBCHelped.excuteUpdate(sql, c.getNameCategory(), id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(String id) {
        try {
            String sql = "UPDATE db_levents.category SET status = 0 WHERE id = ?;";
            JDBCHelped.excuteUpdate(sql, id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean Restore(String id) {
        try {
            String sql = "UPDATE db_levents.category SET status = 1 WHERE id = ?;";
            JDBCHelped.excuteUpdate(sql, id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
