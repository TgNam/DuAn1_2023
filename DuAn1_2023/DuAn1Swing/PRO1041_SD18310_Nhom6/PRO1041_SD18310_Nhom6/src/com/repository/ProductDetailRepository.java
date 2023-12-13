/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.repository;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import com.model.Color;
import com.model.Custom;
import com.model.Material;
import com.model.Product;
import com.model.ProductDetail;
import com.model.Size;
import com.model.Thickness;

/**
 *
 * @author TgNam
 */
public class ProductDetailRepository {

    //select lấy toàn bộ dữ liệu của product
    public ArrayList<ProductDetail> getAll() {
        ArrayList<ProductDetail> list = new ArrayList<>();
        try {
            String sql = "SELECT \n"
                    + "db_levents.product_detail.quantity,\n"
                    + "db_levents.color.name_color,\n"
                    + "db_levents.product_detail.created_at,\n"
                    + "db_levents.product_detail.id,\n"
                    + "db_levents.product.name_product,\n"
                    + "db_levents.size.name_size,\n"
                    + "db_levents.product_detail.updated_at,\n"
                    + "db_levents.product.product_price,\n"
                    + "db_levents.product_detail.status \n"
                    + "FROM db_levents.product_detail\n"
                    + "join db_levents.product on product.id = product_detail.product_id\n"
                    + "join db_levents.color on color.id = product_detail.color_id\n"
                    + "join db_levents.size on size.id = product_detail.size_id;";
            ResultSet rs = JDBCHelped.executeQuery(sql);
            while (rs.next()) {
                list.add(new ProductDetail(
                        rs.getInt(1),
                        new Color(rs.getString(2)),
                        rs.getDate(3),
                        rs.getString(4),
                        new Product(rs.getBigDecimal(8), rs.getString(5)),
                        new Size(rs.getString(6)),
                        rs.getDate(7),
                        rs.getString(9))
                );
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    //them cai nay 22-11 6:00Am
    public ArrayList<ProductDetail> getProductDetails_Exchange() {
        ArrayList<ProductDetail> list = new ArrayList<>();
        try {
//            String sql = "select \n"
//                    + "db_levents.product_detail.id,\n"
//                    + "db_levents.product.name_product,\n"
//                    + "db_levents.custom.name_custom,\n"
//                    + "db_levents.material.name_material,\n"
//                    + "db_levents.thickness.gsm,\n"
//                    + "db_levents.color.name_color,\n"
//                    + "db_levents.size.name_size,\n"
//                    + "db_levents.product.product_price,\n"
//                    + "db_levents.product_detail.quantity\n"
//                    + "from db_levents.product \n"
//                    + "inner join db_levents.product_detail  on db_levents.product.id = db_levents.product_detail.product_id\n"
//                    + "inner join db_levents.color on db_levents.product_detail.color_id = db_levents.color.id \n"
//                    + "inner join db_levents.size  on db_levents.product_detail.size_id = db_levents.size.id\n"
//                    + "inner join custom on product.custome_id = custom.id \n"
//                    + "inner join material on product.material_id = material.id \n"
//                    + "inner join thickness on product.thickness_id = thickness.id\n"
//                    + "where db_levents.product.status = 1;";
            String sql = """
                         SELECT 
                             pd.id AS product_detail_id,
                             p.name_product,
                             c.name_custom,
                             m.name_material,
                             t.gsm AS thickness_gsm,
                             col.name_color,
                             s.name_size,
                              CASE 
                             WHEN sp.id IS NOT NULL AND sp.start_at <= CURDATE() AND sp.end_at >= CURDATE() THEN
                                 p.product_price - (p.product_price * (sp.sale / 100))
                             ELSE 
                                 p.product_price
                         END AS discounted_price,
                             pd.quantity ,
                             sp.sale
                         FROM 
                             db_levents.product AS p
                         left join 
                         	db_levents.sale_product sp on p.sale_id = sp.id
                         INNER JOIN 
                             db_levents.product_detail AS pd ON p.id = pd.product_id
                         INNER JOIN 
                             db_levents.color AS col ON pd.color_id = col.id 
                         INNER JOIN 
                             db_levents.size AS s ON pd.size_id = s.id
                         INNER JOIN 
                             db_levents.custom AS c ON p.custome_id = c.id 
                         INNER JOIN 
                             db_levents.material AS m ON p.material_id = m.id 
                         INNER JOIN 
                             db_levents.thickness AS t ON p.thickness_id = t.id
                         WHERE 
                             p.status = 1;
                         """;
            ResultSet rs = JDBCHelped.executeQuery(sql);
            while (rs.next()) {
                String id = rs.getString(1);
                String name_Product = rs.getString(2);
                String name_Custom = rs.getString(3);
                String name_Material = rs.getString(4);
                int gsm = rs.getInt(5);
                String name_Coler = rs.getString(6);
                String name_Size = rs.getString(7);
                BigDecimal price = rs.getBigDecimal(8);
                int quantity = rs.getInt(9);
                Custom custom = new Custom(name_Custom);
                Material material = new Material(name_Material);
                Thickness thickness = new Thickness(gsm);
                Color color = new Color(name_Coler);
                Size size = new Size(name_Size);

                Product product = new Product(price, custom, material, thickness, name_Product);
                ProductDetail pdt = new ProductDetail(quantity, color, id, product, size);

                list.add(pdt);
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    //them vao ngay 22-11 8:15am
//    private ArrayList<ProductDetail> ListPrddt;
//
//    public ProductDetailRepository() {
//        this.ListPrddt = new ArrayList<>();
//    }

//    public void Insert_Ex(ProductDetail prdt){
//        this.ListPrddt.add(prdt);
//    }
//    public void Delete_Ex(int location){
//        this.ListPrddt.remove(location);
//    }
//
//    public ArrayList<ProductDetail> getListPrddt() {
//        return ListPrddt;
//    }
//
//    public void setListPrddt(ArrayList<ProductDetail> ListPrddt) {
//        this.ListPrddt = ListPrddt;
//    }
    //them vao 24/11
    public ArrayList<ProductDetail> getProductDetails_id(String idPR) {
        ArrayList<ProductDetail> list = new ArrayList<>();
        try {
            String sql = "SELECT db_levents.product_detail.id, \n"
                    + "db_levents.product.name_product, \n"
                    + "db_levents.color.name_color, \n"
                    + "db_levents.size.name_size, \n"
                    + "db_levents.product_detail.created_at, \n"
                    + "db_levents.product_detail.updated_at, \n"
                    + "db_levents.product_detail.quantity, \n"
                    + "db_levents.product_detail.status \n"
                    + "FROM db_levents.product_detail \n"
                    + "inner join db_levents.color on db_levents.product_detail.color_id =db_levents.color.id \n"
                    + "inner join db_levents.size  on db_levents.product_detail.size_id = db_levents.size.id\n"
                    + "inner join db_levents.product on db_levents.product_detail.product_id = db_levents.product.id where db_levents.product.id = ? and  db_levents.product_detail.status not in (select db_levents.product_detail.status  from db_levents.product_detail where db_levents.product_detail.status  = '0' );";

            ResultSet rs = JDBCHelped.executeQuery(sql, idPR);
            while (rs.next()) {
                String id = rs.getString(1);
                String name_Product = rs.getString(2);
                String name_Coler = rs.getString(3);
                String name_Size = rs.getString(4);
                Date created_at = rs.getDate(5);
                Date updated_at = rs.getDate(6);
                int quantity = rs.getInt(7);
                String status = rs.getString(8);

                Color color = new Color(name_Coler);
                Size size = new Size(name_Size);

                Product product = new Product(name_Product);
                ProductDetail pdt = new ProductDetail(quantity, color, created_at, id, product, size, updated_at, status);

                list.add(pdt);
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public ArrayList<ProductDetail> getProductDetails_Sell() {
        ArrayList<ProductDetail> list = new ArrayList<>();
        try {
            String sql = "SELECT db_levents.product_detail.id, \n"
                    + "db_levents.product.name_product, \n"
                    + "db_levents.color.name_color, \n"
                    + "db_levents.size.name_size, \n"
                    + "db_levents.product_detail.created_at, \n"
                    + "db_levents.product_detail.updated_at, \n"
                    + "db_levents.product_detail.quantity, \n"
                    + "db_levents.product_detail.status \n"
                    + "FROM db_levents.product_detail \n"
                    + "inner join db_levents.color on db_levents.product_detail.color_id =db_levents.color.id \n"
                    + "inner join db_levents.size  on db_levents.product_detail.size_id = db_levents.size.id\n"
                    + "inner join db_levents.product on db_levents.product_detail.product_id = db_levents.product.id where db_levents.product_detail.status = '1';";

            ResultSet rs = JDBCHelped.executeQuery(sql);
            while (rs.next()) {
                String id = rs.getString(1);
                String name_Product = rs.getString(2);
                String name_Coler = rs.getString(3);
                String name_Size = rs.getString(4);
                Date created_at = rs.getDate(5);
                Date updated_at = rs.getDate(6);
                int quantity = rs.getInt(7);
                String status = rs.getString(8);

                Color color = new Color(name_Coler);
                Size size = new Size(name_Size);

                Product product = new Product(name_Product);
                ProductDetail pdt = new ProductDetail(quantity, color, created_at, id, product, size, updated_at, status);

                list.add(pdt);
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public ArrayList<ProductDetail> getProductDetails_id_Stop_Selling() {
        ArrayList<ProductDetail> list = new ArrayList<>();
        try {
            String sql = "SELECT db_levents.product_detail.id, \n"
                    + "db_levents.product.name_product, \n"
                    + "db_levents.color.name_color, \n"
                    + "db_levents.size.name_size, \n"
                    + "db_levents.product_detail.created_at, \n"
                    + "db_levents.product_detail.updated_at, \n"
                    + "db_levents.product_detail.quantity, \n"
                    + "db_levents.product_detail.status \n"
                    + "FROM db_levents.product_detail \n"
                    + "inner join db_levents.color on db_levents.product_detail.color_id =db_levents.color.id \n"
                    + "inner join db_levents.size  on db_levents.product_detail.size_id = db_levents.size.id\n"
                    + "inner join db_levents.product on db_levents.product_detail.product_id = db_levents.product.id where db_levents.product_detail.status = 0;";

            ResultSet rs = JDBCHelped.executeQuery(sql);
            while (rs.next()) {
                String id = rs.getString(1);
                String name_Product = rs.getString(2);
                String name_Coler = rs.getString(3);
                String name_Size = rs.getString(4);
                Date created_at = rs.getDate(5);
                Date updated_at = rs.getDate(6);
                int quantity = rs.getInt(7);
                String status = rs.getString(8);

                Color color = new Color(name_Coler);
                Size size = new Size(name_Size);

                Product product = new Product(name_Product);
                ProductDetail pdt = new ProductDetail(quantity, color, created_at, id, product, size, updated_at, status);

                list.add(pdt);
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean Insert(ProductDetail prd) {
        try {
//            String sql = "INSERT INTO `db_levents`.`product_detail` (`quantity`, `color_id`, `product_id`, `size_id`, `status`, `created_at`, `updated_at`, image_data) VALUES \n"
//                    + "(?, \n"
//                    + "(select id from db_levents.color where db_levents.color.name_color = ?), \n"
//                    + "(select id from db_levents.product  where db_levents.product.name_product = ?),\n"
//                    + "(select id from db_levents.size where db_levents.size.name_size = ?), '1', curdate(), curdate(), ?);";
            String sql = """
                         INSERT INTO db_levents.product_detail (
                             quantity, 
                             color_id, 
                             product_id, 
                             size_id, 
                             status, 
                             created_at, 
                             updated_at, 
                             image_data
                         ) VALUES (
                             ?,
                             (SELECT id FROM db_levents.color WHERE db_levents.color.name_color = ? LIMIT 1),
                             (SELECT id FROM db_levents.product WHERE db_levents.product.name_product = ? LIMIT 1),
                             (SELECT id FROM db_levents.size WHERE db_levents.size.name_size = ? LIMIT 1),
                             '1', 
                             curdate(), 
                             curdate(), 
                             ?
                         );
                         """;
            JDBCHelped.excuteUpdate(sql, prd.getQuantity(), prd.getColorId().getNameColor(), prd.getProductId().getName_product(), prd.getSizeId().getNameSize(), prd.getImage());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean Update(String id, ProductDetail prd) {
        try {
//            String sql = "UPDATE `db_levents`.`product_detail` SET \n"
//                    + "`quantity` = ?, \n"
//                    + "`color_id` = (select id from db_levents.color where db_levents.color.name_color = ?), \n"
//                    + "`product_id` = (select id from db_levents.product  where db_levents.product.name_product = ?), \n"
//                    + "`size_id` = (select id from db_levents.size where db_levents.size.name_size = ?), \n"
//                    + "`updated_at` = curdate() ,"
//                    + "image_data = ?\n"
//                    + "WHERE (`id` = ?);";
            String sql = """
                              UPDATE db_levents.product_detail SET
                                                              quantity = ?,
                                                              color_id = (SELECT id FROM db_levents.color WHERE db_levents.color.name_color = ?),
                                                              product_id = (SELECT id FROM db_levents.product WHERE db_levents.product.name_product = ? limit 1),
                                                              size_id = (SELECT id FROM db_levents.size WHERE db_levents.size.name_size = ?),
                                                              updated_at = CURDATE(),
                                                              image_data = ?
                                                          WHERE id = ?;
                             """;
            JDBCHelped.excuteUpdate(sql, prd.getQuantity(), prd.getColorId().getNameColor(), prd.getProductId().getName_product(), prd.getSizeId().getNameSize(), prd.getImage(), id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean Delete(String id) {
        try {
            String sql = "UPDATE `db_levents`.`product_detail` SET `status` = '0' WHERE (`id` = ?);";
            JDBCHelped.excuteUpdate(sql, id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<ProductDetail> get_ProductDetails_id_Bill(String id, String status) {
        ArrayList<ProductDetail> list = new ArrayList<>();
        try {
            String sql = "SELECT \n"
                    + "db_levents.product_detail.quantity,\n"
                    + "db_levents.color.name_color,\n"
                    + "db_levents.product_detail.created_at,\n"
                    + "db_levents.product_detail.id,\n"
                    + "db_levents.product.name_product,\n"
                    + "db_levents.size.name_size,\n"
                    + "db_levents.product_detail.updated_at,\n"
                    + "db_levents.product.product_price,\n"
                    + "db_levents.product_detail.status \n"
                    + "FROM db_levents.product_detail\n"
                    + "join db_levents.product on product.id = product_detail.product_id\n"
                    + "join db_levents.color on color.id = product_detail.color_id\n"
                    + "join db_levents.size on size.id = product_detail.size_id\n"
                    + "where db_levents.product.id = ? and product_detail.status = ? and db_levents.product_detail.quantity  > 0;";

            ResultSet rs = JDBCHelped.executeQuery(sql, id, status);
            while (rs.next()) {
                list.add(new ProductDetail(
                        rs.getInt(1),
                        new Color(rs.getString(2)),
                        rs.getDate(3),
                        rs.getString(4),
                        new Product(rs.getBigDecimal(8), rs.getString(5)),
                        new Size(rs.getString(6)),
                        rs.getDate(7),
                        rs.getString(9))
                );
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    //them  cai nay 25/11
    public ArrayList<ProductDetail> getProductDetails_Stop_Selling(int min, int max) {
        ArrayList<ProductDetail> list = new ArrayList<>();
        try {
            String sql = """
                         select * from 
                         (SELECT 
                             pd.id,
                             p.name_product,
                             c.name_color,
                             s.name_size,
                             pd.created_at,
                             pd.updated_at,
                             pd.quantity,
                             pd.status,
                             ROW_NUMBER() OVER (ORDER BY pd.id) AS rownum 
                         FROM 
                             db_levents.product_detail pd
                         INNER JOIN 
                             db_levents.color c ON pd.color_id = c.id
                         INNER JOIN 
                             db_levents.size s ON pd.size_id = s.id
                         INNER JOIN 
                             db_levents.product p ON pd.product_id = p.id
                         WHERE 
                            pd.status NOT IN ('1') and p.status = '1') 
                         AS temp WHERE rownum BETWEEN ? AND ?;   
                         """;
            ResultSet rs = JDBCHelped.executeQuery(sql, min, max);
            while (rs.next()) {
                String id = rs.getString(1);
                String name_Product = rs.getString(2);
                String name_Coler = rs.getString(3);
                String name_Size = rs.getString(4);
                Date created_at = rs.getDate(5);
                Date updated_at = rs.getDate(6);
                int quantity = rs.getInt(7);
                String status = rs.getString(8);

                Color color = new Color(name_Coler);
                Size size = new Size(name_Size);

                Product product = new Product(name_Product);
                ProductDetail pdt = new ProductDetail(quantity, color, created_at, id, product, size, updated_at, status);

                list.add(pdt);
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean Restore(String id) {
        try {
            String sql = "UPDATE `db_levents`.`product_detail` SET `status` = '1' WHERE (`id` = ?);";
            JDBCHelped.excuteUpdate(sql, id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //them cai nay ngay 28//11
    public ArrayList<ProductDetail> getProductDetails_Selling_Next(String idSP, int min, int max) {
        ArrayList<ProductDetail> list = new ArrayList<>();
        try {
            String sql = """
                         select * from 
                                                  (SELECT 
                                                      pd.id,
                                                      p.name_product,
                                                      c.name_color,
                                                      s.name_size,
                                                      pd.created_at,
                                                      pd.updated_at,
                                                      pd.quantity,
                                                      pd.status,
                                                      pd.image_data,
                                                      ROW_NUMBER() OVER (ORDER BY pd.id) AS rownum 
                                                  FROM 
                                                      db_levents.product_detail pd
                                                  INNER JOIN 
                                                      db_levents.color c ON pd.color_id = c.id
                                                  INNER JOIN 
                                                      db_levents.size s ON pd.size_id = s.id
                                                  INNER JOIN 
                                                      db_levents.product p ON pd.product_id = p.id
                                                  WHERE 
                                                     pd.status = '1' and p.id = ?) 
                                                  AS temp WHERE rownum BETWEEN ? AND ?;   
                         """;
            ResultSet rs = JDBCHelped.executeQuery(sql, idSP, min, max);
            while (rs.next()) {
                String id = rs.getString(1);
                String name_Product = rs.getString(2);
                String name_Coler = rs.getString(3);
                String name_Size = rs.getString(4);
                Date created_at = rs.getDate(5);
                Date updated_at = rs.getDate(6);
                int quantity = rs.getInt(7);
                String status = rs.getString(8);
                byte[] img = rs.getBytes(9);
                Color color = new Color(name_Coler);
                Size size = new Size(name_Size);

                Product product = new Product(name_Product);
                ProductDetail pdt = new ProductDetail(quantity, color, created_at, id, product, size, updated_at, status, img);

                list.add(pdt);
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    //them cai nay ngay 29//11
    public boolean getMinus_product_Detail(String id, int quantity) {
        try {
            String sql = "update db_levents.product_detail set db_levents.product_detail.quantity = db_levents.product_detail.quantity - ? where db_levents.product_detail.id = ?;";
            JDBCHelped.excuteUpdate(sql, quantity, id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // linh dz 
    public ProductDetail getById(String id) {
        ProductDetail productDetail = new ProductDetail();
        try {
            String sql = "SELECT \n"
                    + "db_levents.product_detail.quantity,\n"
                    + "db_levents.color.name_color,\n"
                    + "db_levents.product_detail.created_at,\n"
                    + "db_levents.product_detail.id,\n"
                    + "db_levents.product.name_product,\n"
                    + "db_levents.size.name_size,\n"
                    + "db_levents.product_detail.updated_at,\n"
                    + "db_levents.product.product_price,\n"
                    + "db_levents.product_detail.status \n"
                    + "FROM db_levents.product_detail\n"
                    + "join db_levents.product on product.id = product_detail.product_id\n"
                    + "join db_levents.color on color.id = product_detail.color_id\n"
                    + "join db_levents.size on size.id = product_detail.size_id "
                    + " where db_levents.product_detail.id = ? ;";
            ResultSet rs = JDBCHelped.executeQuery(sql, id);
            while (rs.next()) {
                productDetail = new ProductDetail(
                        rs.getInt(1),
                        new Color(rs.getString(2)),
                        rs.getDate(3),
                        rs.getString(4),
                        new Product(rs.getBigDecimal(8), rs.getString(5)),
                        new Size(rs.getString(6)),
                        rs.getDate(7),
                        rs.getString(9)
                );
            }
            return productDetail;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean Update_procuct_detail_billdetail(String id) {
        try {
            String sql = "update db_levents.product_detail set status = 0 where id =?;";
            JDBCHelped.excuteUpdate(sql, id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    // end link    
}
