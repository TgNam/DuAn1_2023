/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import com.model.BillDetail;
import java.sql.*;
import com.model.Bill;
import com.model.Color;
import com.model.Custom;
import com.model.Material;
import com.model.Product;
import com.model.ProductDetail;
import com.model.SaleProduct;
import com.model.Size;
import com.model.Thickness;

/**
 *
 * @author TgNam
 */
public class BillDetailRepository {

    //select lấy dữ liệu hóa đơn chưa thanh toán
    public ArrayList<BillDetail> getBill_idBill(String id) {
        ArrayList<BillDetail> list = new ArrayList<>();
        try {
            String sql = "SELECT \n"
                    + " db_levents.product.name_product,\n"
                    + " db_levents.color.name_color,\n"
                    + " db_levents.size.name_size,\n"
                    + " db_levents.bill_detail.quantity_urchased,\n"
                    + " db_levents.product.product_price,\n"
                    + " db_levents.bill_detail.id,\n"
                    + " db_levents.product_detail.id,\n"
                    + " db_levents.sale_product.id,\n"
                    + " db_levents.sale_product.sale\n"
                    + "FROM db_levents.bill_detail\n"
                    + "join db_levents.product_detail on product_detail.id = bill_detail.product_detail_id\n"
                    + "join db_levents.product on product.id = product_detail.product_id\n"
                    + "LEFT join db_levents.sale_product on sale_product.id = product.sale_id\n"
                    + "join db_levents.color on color.id = product_detail.color_id\n"
                    + "join db_levents.size on size.id = product_detail.size_id\n"
                    + "where bill_id = ? ;";
            ResultSet rs = JDBCHelped.executeQuery(sql, id);
            while (rs.next()) {
                SaleProduct saleProduct = new SaleProduct(rs.getString(8), rs.getDouble(9));
                Product p = new Product(rs.getBigDecimal(5), saleProduct, rs.getString(1));
                ProductDetail pd = new ProductDetail(new Color(rs.getString(2)), rs.getString(7), p, new Size(rs.getString(3)));
                list.add(new BillDetail(rs.getString(6), pd, rs.getString(4))
                );

            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    //thêm sản phẩm chi tiết vào giỏ hàng 
    public boolean add_bill_datail(BillDetail bd, String bill_id, String pd_id) {
        try {
            String sql = "INSERT INTO db_levents.bill_detail (created_at, updated_at, price_now, quantity_urchased, bill_id, product_detail_id)\n"
                    + "VALUES \n"
                    + "(?, ?, null, ?, ?, ?);";
            JDBCHelped.excuteUpdate(sql, bd.getCreatedAt(), bd.getUpdatedAt(), bd.getQuantityPurchased(), bill_id, pd_id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    //cập nhật lại số lượng cho sản phẩm trong giỏ hàng  

    public boolean Update_bill_datail(int quantity_urchased, String billDetail_id) {
        try {
            String sql = "UPDATE db_levents.bill_detail SET quantity_urchased = ? WHERE (id = ?);";
            JDBCHelped.excuteUpdate(sql, quantity_urchased, billDetail_id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //Xóa billdetail của phần giỏ hàng thông qua id của billdetail
    public boolean delete_bill_datail_ShoppingCart(BillDetail bd) {
        try {
            String sql = "delete FROM db_levents.bill_detail where id = ?;";
            JDBCHelped.excuteUpdate(sql, bd.getId());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    //Xóa billdetail của phần giỏ hàng thông qua id của bill

    public boolean delete_bill_datail(Bill b) {
        try {
            String sql = "Delete FROM db_levents.bill_detail where bill_id = ?;";
            JDBCHelped.excuteUpdate(sql, b.getId());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean delete_product_datail(String bill_id, String pd_id) {
        try {
            String sql = "Delete FROM db_levents.bill_detail where bill_id = ? and product_detail_id = ?";
            JDBCHelped.excuteUpdate(sql, bill_id, pd_id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //them cai nay 22-11 2:45sa
    //doan nay ngay 25//11 moi sua them thang gia cua thang sp
    public ArrayList<BillDetail> getBill_all(String id) {
        ArrayList<BillDetail> list = new ArrayList<>();
        try {
            String sql = "SELECT\n"
                    + "    db_levents.product.name_product AS product_name,\n"
                    + "    custom.name_custom,\n"
                    + "    material.name_material,\n"
                    + "    thickness.gsm,\n"
                    + "    db_levents.color.name_color,\n"
                    + "    db_levents.size.name_size,\n"
                    + "    db_levents.bill_detail.price_now,\n"
                    + "    db_levents.bill_detail.quantity_urchased AS quantity_purchased,\n"
                    + "    db_levents.bill_detail.created_at,\n"
                    + "    db_levents.bill_detail.updated_at,\n"
                    + "    db_levents.product.product_price,\n"
                    + "    db_levents.product_detail.id\n"
                    + "FROM\n"
                    + "    db_levents.bill_detail\n"
                    + "LEFT JOIN\n"
                    + "    db_levents.product_detail ON db_levents.bill_detail.product_detail_id = db_levents.product_detail.id\n"
                    + "INNER JOIN\n"
                    + "    db_levents.color ON db_levents.product_detail.color_id = db_levents.color.id\n"
                    + "INNER JOIN\n"
                    + "    db_levents.size ON db_levents.product_detail.size_id = db_levents.size.id\n"
                    + "INNER JOIN\n"
                    + "    db_levents.product ON db_levents.product_detail.product_id = db_levents.product.id\n"
                    + "INNER JOIN\n"
                    + "    custom ON db_levents.product.custome_id = custom.id\n"
                    + "INNER JOIN\n"
                    + "    material ON db_levents.product.material_id = material.id\n"
                    + "INNER JOIN\n"
                    + "    thickness ON db_levents.product.thickness_id = thickness.id "
                    + " where db_levents.bill_detail.bill_id = ?;";
            ResultSet rs = JDBCHelped.executeQuery(sql, id);
            while (rs.next()) {
                String name_Product = rs.getString(1);
                String name_Custom = rs.getString(2);
                String name_Material = rs.getString(3);
                int gsm = rs.getInt(4);
                String name_Coler = rs.getString(5);
                String name_Size = rs.getString(6);
                BigDecimal price = rs.getBigDecimal(7);
                String quantity = rs.getString(8);
                Date created_at = rs.getDate(9);
                Date upddated_at = rs.getDate(10);
                BigDecimal price_buy = rs.getBigDecimal(11);
                String idProductDetails = rs.getString(12);

                Custom custom = new Custom(name_Custom);
                Material material = new Material(name_Material);
                Thickness thickness = new Thickness(gsm);
                Color color = new Color(name_Coler);
                Size size = new Size(name_Size);

                Product product = new Product(price_buy, custom, material, thickness, name_Product);
                ProductDetail pdt = new ProductDetail(idProductDetails,color, product, size);
                BillDetail bdt = new BillDetail(price, created_at, pdt, upddated_at, quantity);
                list.add(bdt);
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public boolean updateprice_nowByIdBillDetail(BigDecimal price_now, String id) {
        try {
            String sql = "update db_levents.bill_detail set price_now = ?,status = '1'  where id =? ;";
            JDBCHelped.excuteUpdate(sql, price_now,id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    //select lấy dữ liệu hóa đơn chưa thanh toán
    public ArrayList<BillDetail> getBill_idBill_0(String id) {
        ArrayList<BillDetail> list = new ArrayList<>();
        try {
            String sql = "SELECT \n"
                    + " db_levents.product.name_product,\n"
                    + " db_levents.color.name_color,\n"
                    + " db_levents.size.name_size,\n"
                    + " db_levents.bill_detail.quantity_urchased,\n"
                    + " db_levents.product.product_price,\n"
                    + " db_levents.bill_detail.id,\n"
                    + " db_levents.product_detail.id,\n"
                    + " db_levents.sale_product.id,\n"
                    + " db_levents.sale_product.sale\n"
                    + "FROM db_levents.bill_detail\n"
                    + "join db_levents.product_detail on product_detail.id = bill_detail.product_detail_id\n"
                    + "join db_levents.product on product.id = product_detail.product_id\n"
                    + "LEFT join db_levents.sale_product on sale_product.id = product.sale_id\n"
                    + "join db_levents.color on color.id = product_detail.color_id\n"
                    + "join db_levents.size on size.id = product_detail.size_id\n"
                    + "where bill_id = ? and product_detail.status = 0;";
            ResultSet rs = JDBCHelped.executeQuery(sql, id);
            while (rs.next()) {
                SaleProduct saleProduct = new SaleProduct(rs.getString(8), rs.getDouble(9));
                Product p = new Product(rs.getBigDecimal(5), saleProduct, rs.getString(1));
                ProductDetail pd = new ProductDetail(new Color(rs.getString(2)), rs.getString(7), p, new Size(rs.getString(3)));
                list.add(new BillDetail(rs.getString(6), pd, rs.getString(4))
                );

            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
