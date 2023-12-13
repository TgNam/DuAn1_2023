/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.repository;

import com.model.ExchangeBill;
import com.model.ReturnBill;
import com.model.ReturnBillDetail;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.service.imple.ProductDetailImple;
import com.service.imple.ReturnBillImple;

/**
 *
 * @author thiet
 */
public class ReturnBill_Detail_Repository {

    //start linh dz
    public boolean insert(ReturnBillDetail returnBillDetail) {
        String query = "INSERT INTO return_bill_detail(price_at_the_time_of_purchase, quantity_of_products_returned,"
                + " created_at, product_detail_id,return_bill_id,status,updated_at) VALUES (?,?,NOW(),?,?,?,NOW()) ";
        try {
            JDBCHelped.excuteUpdate(query, returnBillDetail.getPriceAtTheTimeOfPurchase(),
                    returnBillDetail.getQuantityOfProductsReturned(), returnBillDetail.getProductDetailId().getId(),
                    returnBillDetail.getReturnBillId().getId(), returnBillDetail.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<ReturnBillDetail> getAll() {
        String query = "SELECT * FROM return_bill_detail";
        List<ReturnBillDetail> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelped.executeQuery(query);
            while (rs.next()) {
                list.add(new ReturnBillDetail(rs.getBigDecimal(1), rs.getInt(2), rs.getDate(3),
                        rs.getString(4), new ProductDetailImple().getById(rs.getString(5)),
                        new ReturnBillImple().getByIdBill(rs.getString(6)), rs.getDate(7), rs.getString(8)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public List<ReturnBillDetail> getByIdBill(String idBill) {
        String query = "select * from return_bill_detail where return_bill_id  IN (select id from return_bill where bill_id = ?);";
        List<ReturnBillDetail> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelped.executeQuery(query, idBill);
            while (rs.next()) {
                list.add(new ReturnBillDetail(rs.getBigDecimal(1), rs.getInt(2), rs.getDate(3),
                        rs.getString(4), new ProductDetailImple().getById(rs.getString(5)),
                        new ReturnBillImple().getById(rs.getString(6)), rs.getDate(7), rs.getString(8)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }
    // end linh dz
    public boolean delete_returnBillDetal(ReturnBill returnBill) {
        try {
            String sql = """
                         DELETE FROM db_levents.return_bill_detail where return_bill_id = ? ;
                         """;
            JDBCHelped.excuteUpdate(sql, returnBill.getId());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
