/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.repository;
import java.sql.*;
import com.model.UserVoucher;

/**
 *
 * @author lenovo
 */
public class UserVoucherResponsitory {

    // tạo tất cả user được chọn
    public boolean createAll(String voucherId, String userId) {
        String query = "INSERT INTO user_voucher VALUES (?,?)";
        try {
            Integer row = JDBCHelped.excuteUpdate(query,  userId, voucherId);
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
    
    
    
        public boolean removeAllByVoucherId(Long voucherId) {
         String query = "DELETE FROM db_levents.user_voucher WHERE voucher_id = ?";
         try {
             try (PreparedStatement pr = new DBconnection().getConnection().prepareStatement(query)) {
                 pr.setLong(1, voucherId);
                 int rowsDeleted = pr.executeUpdate();
                 System.out.println("Number of rows deleted: " + rowsDeleted);
                 return rowsDeleted > 0;
             }
         } catch (Exception e) {
             e.printStackTrace();
         }
         return false;
     }

        
        
    
    public Integer getCountVoucherUser(String idVoucher, String idUser) {
        String query = "select count(*) from user_voucher uv where uv.user_id =? and uv.voucher_id = ?";
        
        try {
            ResultSet rs = JDBCHelped.executeQuery(query,idUser,idVoucher);
            
            if(rs.next()){
             return rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }
}
