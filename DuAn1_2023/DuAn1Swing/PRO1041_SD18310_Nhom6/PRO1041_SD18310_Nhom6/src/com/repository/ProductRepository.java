/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.repository;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import com.model.Product;
import java.util.Date;
import com.model.Custom;
import com.model.Material;
import com.model.SaleProduct;
import com.model.Thickness;

/**
 *
 * @author TgNam
 */
public class ProductRepository {

    //select lấy toàn bộ dữ liệu của product
    public ArrayList<Product> getAll_sale() {
        ArrayList<Product> list = new ArrayList<>();
        try {
            String sql = "select product.product_price,\n"
                    + "       product.created_at,\n"
                    + "       product.id, \n"
                    + "       sale_product.sale,\n"
                    + "       product.updated_at,\n"
                    + "       product.name_product,\n"
                    + "       product.status,product.sale_id  \n"
                    + "       from db_levents.product \n"
                    + "       LEFT join db_levents.sale_product on sale_product.id = product.sale_id\n"
                    + "       where product.status = 1;";
            ResultSet rs = JDBCHelped.executeQuery(sql);
            while (rs.next()) {
                list.add(new Product(
                        rs.getBigDecimal(1),
                        rs.getDate(2),
                        rs.getString(3),
                        new SaleProduct(rs.getString(8), rs.getDouble(4)),
                        rs.getDate(5),
                        rs.getString(6),
                        rs.getString(7))
                );
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean updateSale_ID_created_at(Date created_at, String id) {
        try {
            String sql = "Update product set sale_id = (select id from sale_product where created_at = ?) where id = ?";
            JDBCHelped.excuteUpdate(sql, created_at, id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean updateSale_ID(String id) {
        try {
            String sql = "Update product set sale_id = null where sale_id = ?";
            JDBCHelped.excuteUpdate(sql, id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // them cai nay ngay 23/11
    public ArrayList<Product> getNext(String tensp, int min, int max) {
        ArrayList<Product> list = new ArrayList<>();
        try {
            String sql = "select * from \n"
                    + "(select product.id, \n"
                    + "product.name_product, \n"
                    + "product.created_at, \n"
                    + "product.updated_at, \n"
                    + "custom.name_custom, \n"
                    + "product.product_price, \n"
                    + "material.name_material, \n"
                    + "thickness.gsm, \n"
                    + "product.DESCRIPTION, \n"
                    + "product.status,"
                    + "product.image_data,\n"
                    + "ROW_NUMBER() OVER (ORDER BY product.id) AS rownum \n"
                    + "from product \n"
                    + "inner join custom on product.custome_id = custom.id \n"
                    + "inner join material on product.material_id = material.id \n"
                    + "inner join thickness on product.thickness_id = thickness.id \n"
                    + "where product.status = '1' and product.name_product like '%"
                    +tensp
                    +"%'"
                    + ") \n"
                    + "AS temp WHERE rownum BETWEEN ? AND ?;";
            ;
            ResultSet rs = JDBCHelped.executeQuery(sql, min, max);
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

    public ArrayList<Product> getListSP_Sell() {
        ArrayList<Product> list = new ArrayList<>();
        try {
            String sql = "select "
                    + "product.id, "
                    + "product.name_product, "
                    + "product.created_at, "
                    + "product.updated_at, "
                    + "custom.name_custom, "
                    + "product.product_price, "
                    + "material.name_material, "
                    + "thickness.gsm,"
                    + "product.DESCRIPTION, "
                    + "product.status "
                    + "from product inner join custom on product.custome_id = custom.id inner join material on product.material_id = material.id inner join thickness on product.thickness_id = thickness.id where product.status = '1';";
            ResultSet rs = JDBCHelped.executeQuery(sql);
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
                pr = new Product(price, Create_at, new Custom(custom), id, new Material(material), new Thickness(thickness), Updated_at, describe, name_Product, status);
                list.add(pr);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Product> getListSP_Stop_Selling() {
        ArrayList<Product> list = new ArrayList<>();
        try {
            String sql = "select "
                    + "product.id, "
                    + "product.name_product, "
                    + "product.created_at, "
                    + "product.updated_at, "
                    + "custom.name_custom, "
                    + "product.product_price, "
                    + "material.name_material, "
                    + "thickness.gsm,"
                    + "product.DESCRIPTION, "
                    + "product.status "
                    + "from product inner join custom on product.custome_id = custom.id inner join material on product.material_id = material.id inner join thickness on product.thickness_id = thickness.id where product.status = '0';";
            ResultSet rs = JDBCHelped.executeQuery(sql);
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
                pr = new Product(price, Create_at, new Custom(custom), id, new Material(material), new Thickness(thickness), Updated_at, describe, name_Product, status);
                list.add(pr);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean Insert(Product pr) {
        try {
            String sql = "INSERT INTO product (created_at, name_product, product_price, custome_id, material_id,  thickness_id, description, status, image_data) "
                    + "VALUES  (curdate(), ?,?,"
                    + "(select id from custom where custom.name_custom =  ?),"
                    + "(select id from material where name_material = ?),"
                    + "(select id from thickness where gsm = ?),?,'1',?);";
            JDBCHelped.excuteUpdate(sql, pr.getName_product(), pr.getProduct_price(), pr.getCustome_id().getNameCustom(), pr.getMaterial_id().getNameMaterial(), pr.getThickness_id().getGsm(), pr.getDescription(), pr.getImage_Type());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean Update(String id, Product pr) {
        try {
            String sql = "UPDATE product set updated_at = curdate(), name_product = ?, product_price = ?, custome_id = (select id from custom where custom.name_custom =  ?), material_id = (select id from material where name_material = ?), thickness_id = (select id from thickness where gsm = ?), description = ?, image_data = ?  where id = ?;";
            JDBCHelped.excuteUpdate(sql, pr.getName_product(), pr.getProduct_price(), pr.getCustome_id().getNameCustom(), pr.getMaterial_id().getNameMaterial(), pr.getThickness_id().getGsm(), pr.getDescription(), pr.getImage_Type(), id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean Delete(String name) {
        try {
            String sql = "UPDATE `db_levents`.`product` SET `updated_at` =  curdate(), `status` = '0' WHERE name_product = ?;";
            JDBCHelped.excuteUpdate(sql, name);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //them cai nay ngay 25/11
    public ArrayList<Product> getNext_Product_stop_selling(int min, int max) {
        ArrayList<Product> list = new ArrayList<>();
        try {
            String sql = "select * from \n"
                    + "(select product.id, \n"
                    + "product.name_product, \n"
                    + "product.created_at, \n"
                    + "product.updated_at, \n"
                    + "custom.name_custom, \n"
                    + "product.product_price, \n"
                    + "material.name_material, \n"
                    + "thickness.gsm, \n"
                    + "product.DESCRIPTION, \n"
                    + "product.status,\n"
                    + "ROW_NUMBER() OVER (ORDER BY product.id) AS rownum \n"
                    + "from product \n"
                    + "inner join custom on product.custome_id = custom.id \n"
                    + "inner join material on product.material_id = material.id \n"
                    + "inner join thickness on product.thickness_id = thickness.id \n"
                    + "where product.status = '0') \n"
                    + "AS temp WHERE rownum BETWEEN ? AND ?;";
            ;
            ResultSet rs = JDBCHelped.executeQuery(sql, min, max);
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
                pr = new Product(price, Create_at, new Custom(custom), id, new Material(material), new Thickness(thickness), Updated_at, describe, name_Product, status);
                list.add(pr);
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean Restore(String id) {
        try {
            String sql = "UPDATE `db_levents`.`product` SET `status` = '1' WHERE (`id` = ?);";
            JDBCHelped.excuteUpdate(sql, id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete_product_bill(String id) {
        try {
            String sql = "UPDATE `db_levents`.`product` SET `status` = '1' WHERE (`id` = ?);";
            JDBCHelped.excuteUpdate(sql, id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Product getProcuct(String id) {
        Product product = new Product();
        try {
            String sql = "select product.product_price,\n"
                    + "       product.created_at,\n"
                    + "       product.id, \n"
                    + "       sale_product.sale,\n"
                    + "       product.updated_at,\n"
                    + "       product.name_product,\n"
                    + "       product.status,product.sale_id  \n"
                    + "       from db_levents.product \n"
                    + "       LEFT join db_levents.sale_product on sale_product.id = product.sale_id\n"
                    + "       where product.id= ?;";
            ResultSet rs = JDBCHelped.executeQuery(sql, id);
            while (rs.next()) {
                product = new Product(
                        rs.getBigDecimal(1),
                        rs.getDate(2),
                        rs.getString(3),
                        new SaleProduct(rs.getString(8), rs.getDouble(4)),
                        rs.getDate(5),
                        rs.getString(6),
                        rs.getString(7));
            }
            return product;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
