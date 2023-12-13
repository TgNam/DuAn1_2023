/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.form;

import Util.Validate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import com.model.User;
import com.service.AddressService;
import com.service.UserRoleService;
import com.service.UserService;
import com.service.imple.AddressImple;
import com.service.imple.UserImple;
import com.service.imple.UserRoleImple;
import com.swing.EditButtons;
import com.swing.EditTextField;
import java.awt.FlowLayout;

/**
 *
 * @author TgNam
 */
public class AddCreart extends javax.swing.JPanel {

    private Validate vl = new Validate();
    private Date nowDate = null;
    private UserRoleService urs = new UserRoleImple();
    private UserService us = new UserImple();
    private AddressService as = new AddressImple();
    
    //them vao 7/12
    private  EditButtons bt = new EditButtons();
    private EditTextField txt = new EditTextField();
    /**
     * Creates new form AddCreart
     */
    public AddCreart() {
        initComponents();
        pnlTong.setLayout(new FlowLayout(FlowLayout.CENTER));
        bt.EditAddCreart(bthadd);
        bt.EditAddCreart(bthResetForm);
        
        txt.edit(txtfullname);
        txt.edit(txtemail);
        txt.edit(txtnumblephone);
        txt.edit(txtaddress);
        txt.edit(txtdateofbirth);
        txt.edit(txtaccount);
        txt.edit(txtpassword);
    }

    //lấy thời gian hiện tại
    public static Date getCurrentDateTime() {
        try {
            // Lấy thời gian hiện tại
            Date currentDate = new Date();

            // Định dạng ngày tháng năm giờ phút giây
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            // Chuyển đổi thành chuỗi theo định dạng
            String formattedDateTime = dateFormat.format(currentDate);

            // Chuyển lại thành đối tượng Date
            Date date = dateFormat.parse(formattedDateTime);

            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void reset() {
        txtfullname.setText("");
        txtemail.setText("");
        txtnumblephone.setText("");
        txtdateofbirth.setText("");
        txtaccount.setText("");
        txtpassword.setText("");
        txtaddress.setText("");
        rdoadmin.setSelected(true);
    }

    // check ngày sinh
    public Date checkDateOfBirch() {
        Date date;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateofbirth = txtdateofbirth.getText();
            if (!vl.isCheckDate(dateofbirth)) {
                JOptionPane.showMessageDialog(this, "Định dạng ngày sinh không đúng!");
                return null;
            }
            date = sdf.parse(dateofbirth);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng ngày yyyy-MM-dd");
            return null;
        }
    }
    // check số điện thoại 

    public String checknumblephone() {
        String numblephone = txtnumblephone.getText();
        boolean checkName_phone = true;
        try {
            if (numblephone.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng không được để trống số điện thoại!");
            } else if (!vl.isCheckPhone(numblephone)) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng số điện thoại!");
                return null;
            }
            if (checkName_phone) {
                for (User userl : us.getUser_name_phone()) {
                    if (userl.getNumberPhone().equals(numblephone)) {
                        checkName_phone = false;
                    }
                }
            }
            if (!checkName_phone) {
                JOptionPane.showMessageDialog(this, "Số điện thoại đã được sử dụng vui lòng sử dụng số diện thoại khác!");
                return null;
            } else {
                return numblephone;
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi số điện thoại!");
            return null;
        }
    }

    public String checkaccount() {
        String account = txtaccount.getText();
        boolean checkName_account = true;
        try {
            if (account.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng không được để trống tài khoản!");
            }
            if (!vl.isCheckTXT(account)) {
                JOptionPane.showMessageDialog(this, "Vui lòng kí tự không được vượt quá 100 kí tự!");
                return null;
            }
            if (checkName_account) {
                for (User userl : us.getUser_name_phone()) {
                    if (userl.getAccount() != null && !userl.getAccount().isEmpty()) {
                        // Tiếp tục xử lý khi giá trị không null và rỗng
                        if (userl.getAccount().equals(account)) {
                            checkName_account = false;
                        }
                    }

                }
            }
            if (!checkName_account) {
                JOptionPane.showMessageDialog(this, "Tài khoản này đã được sử dụng vui lòng sử dụng tài khoản khác!");
                return null;
            } else {
                return account;
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi tài khoản!");
            return null;
        }
    }

    public User checkadd() {
        User user = null;
        Date dateofbirth = checkDateOfBirch();
        // Lấy giá trị từ trường nhập liệu tên đầy đủ
        String fullname = txtfullname.getText();
        // Lấy giá trị từ trường nhập liệu email
        String email = txtemail.getText();
        // Gọi phương thức checknumblephone() để kiểm tra và lấy giá trị số điện thoại
        String numblephone = checknumblephone();
        // Lấy giá trị từ trường nhập liệu tài khoản
        String account = checkaccount();
        // Lấy giá trị từ trường nhập liệu mật khẩu
        String password = txtpassword.getText();
        // Lấy giá trị từ trường nhập liệu địa chỉ
        String address = txtaddress.getText();
        // Lấy thời điểm hiện tại và lưu vào biến nowDate
        nowDate = getCurrentDateTime();
        try {
            if (fullname.trim().isEmpty() || email.trim().isEmpty() || password.trim().isEmpty() || address.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng không được để trống các ô nhập!");
                return null;
            } else if (!vl.isCheckName(fullname) || !vl.isCheckName(password)) {
                JOptionPane.showMessageDialog(this, "Vui lòng kí tự của tên và mật khẩu không được vượt quá 40 kí tự!");
                return null;
            } else if (!vl.isCheckTXT(address) || !vl.isCheckTXT(email)) {
                JOptionPane.showMessageDialog(this, "Vui lòng kí tự của địa chỉ và email không được vượt quá 100 kí tự!");
                return null;
            } else if (dateofbirth == null || numblephone == null || account == null) {
                return null;
            } else {
                return new User(null, nowDate, dateofbirth, null, null, nowDate, account, email, fullname, numblephone, password, String.valueOf(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Vui Lòng nhập lại!");
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        pnlTong = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        bthadd = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        bthResetForm = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtfullname = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtemail = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtnumblephone = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtaddress = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtdateofbirth = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtaccount = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtpassword = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        rdocustomer = new com.swing.RadioButtonCustom();
        rdoadmin = new com.swing.RadioButtonCustom();
        rdoemployee = new com.swing.RadioButtonCustom();

        setBackground(new java.awt.Color(255, 255, 255));

        pnlTong.setBackground(new java.awt.Color(255, 255, 255));
        pnlTong.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setText("Tài Khoản:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(388, 66, -1, -1));

        bthadd.setBackground(new java.awt.Color(153, 204, 255));
        bthadd.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        bthadd.setText("Thêm");
        bthadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthaddActionPerformed(evt);
            }
        });
        jPanel1.add(bthadd, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 290, 680, 52));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel8.setText("Địa Chỉ:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 50, -1));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel7.setText("Mật Khẩu:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(388, 125, -1, -1));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel9.setText("Vai Trò:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(388, 191, -1, -1));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setText("Ngày Sinh:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, -1, -1));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("Số điện thoại:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 80, -1));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("Email:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 40, -1));

        bthResetForm.setBackground(new java.awt.Color(153, 204, 255));
        bthResetForm.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        bthResetForm.setText("Xóa From Thông Tin");
        bthResetForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthResetFormActionPerformed(evt);
            }
        });
        jPanel1.add(bthResetForm, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 360, 680, 47));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setText("Họ và tên:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 70, -1));

        txtfullname.setBorder(null);
        jPanel1.add(txtfullname, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 280, 24));

        jLabel10.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel10.setText("____________________________________________________________");
        jLabel10.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 320, 10));

        txtemail.setBorder(null);
        jPanel1.add(txtemail, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 280, 24));

        jLabel11.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel11.setText("____________________________________________________________");
        jLabel11.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 330, 10));

        txtnumblephone.setBorder(null);
        jPanel1.add(txtnumblephone, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 280, 24));

        jLabel12.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel12.setText("____________________________________________________________");
        jLabel12.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 320, 10));

        txtaddress.setBorder(null);
        jPanel1.add(txtaddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 280, 24));

        jLabel13.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel13.setText("____________________________________________________________");
        jLabel13.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 330, 10));

        txtdateofbirth.setBorder(null);
        jPanel1.add(txtdateofbirth, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 30, 280, 24));

        jLabel14.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel14.setText("____________________________________________________________");
        jLabel14.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 50, 340, 10));

        txtaccount.setBorder(null);
        jPanel1.add(txtaccount, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 90, 280, 24));

        jLabel15.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel15.setText("____________________________________________________________");
        jLabel15.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 110, 330, 10));

        txtpassword.setBorder(null);
        jPanel1.add(txtpassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 150, 280, 24));

        jLabel16.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel16.setText("____________________________________________________________");
        jLabel16.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 170, 340, 10));

        buttonGroup1.add(rdocustomer);
        rdocustomer.setText("CUSTOMER");
        jPanel1.add(rdocustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 220, -1, -1));

        buttonGroup1.add(rdoadmin);
        rdoadmin.setText("ADMIN");
        jPanel1.add(rdoadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 220, -1, -1));

        buttonGroup1.add(rdoemployee);
        rdoemployee.setText("EMPLOYEE");
        jPanel1.add(rdoemployee, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 220, -1, -1));

        javax.swing.GroupLayout pnlTongLayout = new javax.swing.GroupLayout(pnlTong);
        pnlTong.setLayout(pnlTongLayout);
        pnlTongLayout.setHorizontalGroup(
            pnlTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTongLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(381, Short.MAX_VALUE))
        );
        pnlTongLayout.setVerticalGroup(
            pnlTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTongLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(191, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1150, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pnlTong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 670, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pnlTong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void bthaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthaddActionPerformed
        User user = checkadd();
        if (user != null) {
            String address = txtaddress.getText();
            String numblephone = txtnumblephone.getText();
            nowDate = getCurrentDateTime();
            as.add_address(nowDate, address);
            boolean userS = us.add_user_all(user, nowDate, address);
            if (userS) {
                JOptionPane.showMessageDialog(this, "Thêm thành công !");
                String namerole = null;
                if (rdoadmin.isSelected()) {
                    namerole = "1";
                } else if (rdoemployee.isSelected()) {
                    namerole = "2";
                } else if (rdocustomer.isSelected()) {
                    namerole = "3";
                }
                urs.add_user_role(user, namerole);
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại !");
                as.delete_address(nowDate, address);
            }
        }
        reset();
    }//GEN-LAST:event_bthaddActionPerformed

    private void bthResetFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthResetFormActionPerformed
        reset();

    }//GEN-LAST:event_bthResetFormActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bthResetForm;
    private javax.swing.JButton bthadd;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel pnlTong;
    private com.swing.RadioButtonCustom rdoadmin;
    private com.swing.RadioButtonCustom rdocustomer;
    private com.swing.RadioButtonCustom rdoemployee;
    private javax.swing.JTextField txtaccount;
    private javax.swing.JTextField txtaddress;
    private javax.swing.JTextField txtdateofbirth;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txtfullname;
    private javax.swing.JTextField txtnumblephone;
    private javax.swing.JTextField txtpassword;
    // End of variables declaration//GEN-END:variables
}
