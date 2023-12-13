/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.model.Color;
import com.model.Custom;
import com.model.ExchangeBill;
import com.model.ExchangeBillDetail;
import com.model.Material;
import com.model.Product;
import com.model.ProductDetail;
import com.model.Size;
import com.model.Thickness;
import com.service.Exchange_detailServict;

/**
 *
 * @author thiet
 */
public class Exchange_DetailRepository {

    public ArrayList<ExchangeBillDetail> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean insert(ExchangeBillDetail ex) {
        try {
            String sql = "INSERT INTO exchange_bill_detail (\n"
                    + "created_at,\n"
                    + "updated_at, \n"
                    + "ex_change_bill_id, \n"
                    + "quantity_of_products_returned, \n"
                    + "product_detail_id, \n"
                    + "status)\n"
                    + "VALUES \n"
                    + "(curdate(), \n"
                    + "curdate(), \n"
                    + "(select id from db_levents.exchange_bill where db_levents.exchange_bill.created_at = ?),\n"
                    + "?,\n"
                    + "(select id from db_levents.product_detail where id = ?),\n"
                    + "'1');";
            JDBCHelped.excuteUpdate(sql, ex.getExchangeBillId().getCreatedAt(), ex.getQuantityOfProductsReturned(), ex.getProductDetailId().getId());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

     // 26/11/2023
    public ArrayList<ExchangeBillDetail> getExBill(String id) {
        ArrayList<ExchangeBillDetail> list = new ArrayList<>();
        try {
            String sql = "select "
                    + "exchange_bill_detail.id, "
                    + "exchange_bill.id, "
                    + "name_product, "
                    + "name_custom, "
                    + "name_color,"
                    + "thickness.gsm, "
                    + "material.name_material, "
                    + "name_size, "
                    + "exchange_bill_detail.created_at, "
                    + "exchange_bill_detail.updated_at,"
                    + "exchange_bill_detail.quantity_of_products_returned  "
                    + "from exchange_bill_detail\n"
                    + "	inner join product_detail On exchange_bill_detail.product_detail_id = product_detail.id\n"
                    + "    inner join product On product_detail.product_id = product.id\n"
                    + "    inner join custom On product.custome_id = custom.id\n"
                    + "    inner join color On product_detail.color_id = color.id\n"
                    + "    inner join size On product_detail.size_id = size.id\n"
                    + "    inner join exchange_bill On exchange_bill_detail.ex_change_bill_id = exchange_bill.id \n"
                    + "    inner join thickness On product.thickness_id = thickness.id\n"
                    + "    inner join material On product.material_id = material.id\n"
                    + "    where exchange_bill.id = ?;";
            ResultSet rs = JDBCHelped.executeQuery(sql, id);
            while (rs.next()) {
                String idExchangeDetail = rs.getString(1);
                String idExchangeBill = rs.getString(2);
                String name = rs.getString(3);
                String custom = rs.getString(4);
                String color = rs.getString(5);
                int gms = rs.getInt(6);
                String material = rs.getString(7);
                String size = rs.getString(8);
                Date created = rs.getDate(9);
                Date updated = rs.getDate(10);
                int quantity_of_products_returned = rs.getInt(11);

                Custom ct = new Custom(custom);
                Color cl = new Color(color);
                Thickness tn = new Thickness(gms);
                Material mr = new Material(material);
                Size sz = new Size(size);
                
                Product product = new Product(ct, mr, tn, name);
                ProductDetail productDetail= new ProductDetail(cl, product, sz);
                ExchangeBill ex = new ExchangeBill(idExchangeBill);
                ExchangeBillDetail exbill = new ExchangeBillDetail(quantity_of_products_returned, created, ex, idExchangeDetail, productDetail, updated);
                list.add(exbill);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
    
//    public boolean setCho_XacNhan(){
        public boolean delete_exchangeBillDetal(ExchangeBill exchangeBill) {
        try {
            String sql = """
                         DELETE FROM db_levents.exchange_bill_detail where ex_change_bill_id = ? ;
                         """;
            JDBCHelped.excuteUpdate(sql, exchangeBill.getId());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
        
    public ArrayList<ExchangeBillDetail> getExBill_idBill(String id) {
        ArrayList<ExchangeBillDetail> list = new ArrayList<>();
        try {
            String sql = """
                         select 
                         exchange_bill_detail.id,
                         exchange_bill.id,
                         name_product,
                         name_custom,
                         name_color,
                         thickness.gsm,
                         material.name_material,
                         name_size,
                         exchange_bill_detail.created_at,
                         exchange_bill_detail.updated_at,
                         exchange_bill_detail.quantity_of_products_returned
                         from exchange_bill_detail
                         inner join product_detail On exchange_bill_detail.product_detail_id = product_detail.id
                         inner join product On product_detail.product_id = product.id
                         inner join custom On product.custome_id = custom.id
                         inner join color On product_detail.color_id = color.id
                         inner join size On product_detail.size_id = size.id
                         inner join exchange_bill On exchange_bill_detail.ex_change_bill_id = exchange_bill.id 
                         inner join thickness On product.thickness_id = thickness.id
                         inner join material On product.material_id = material.id
                         where bill_id = ?;
                         """;
            ResultSet rs = JDBCHelped.executeQuery(sql, id);
            while (rs.next()) {
                String idExchangeDetail = rs.getString(1);
                String idExchangeBill = rs.getString(2);
                String name = rs.getString(3);
                String custom = rs.getString(4);
                String color = rs.getString(5);
                int gms = rs.getInt(6);
                String material = rs.getString(7);
                String size = rs.getString(8);
                Date created = rs.getDate(9);
                Date updated = rs.getDate(10);
                int quantity_of_products_returned = rs.getInt(11);

                Custom ct = new Custom(custom);
                Color cl = new Color(color);
                Thickness tn = new Thickness(gms);
                Material mr = new Material(material);
                Size sz = new Size(size);
                
                Product product = new Product(ct, mr, tn, name);
                ProductDetail productDetail= new ProductDetail(cl, product, sz);
                ExchangeBill ex = new ExchangeBill(idExchangeBill);
                ExchangeBillDetail exbill = new ExchangeBillDetail(quantity_of_products_returned, created, ex, idExchangeDetail, productDetail, updated);
                list.add(exbill);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}
