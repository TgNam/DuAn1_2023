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
import javax.swing.table.DefaultTableModel;
import com.model.Address;
import com.model.User;
import com.model.UserRole;
import com.service.AddressService;
import com.service.UserRoleService;
import com.service.UserService;
import com.service.imple.AddressImple;
import com.service.imple.UserImple;
import com.service.imple.UserRoleImple;
import com.swing.EditButtons;
import com.swing.EditTextField;
import table.TableCustom;

/**
 *
 * @author TgNam
 */
public class UserJPanel extends javax.swing.JPanel {

    private DefaultTableModel tableModel;
    private Validate vl = new Validate();
    private Date nowDate = null;
    private UserRoleService urs = new UserRoleImple();
    private UserService us = new UserImple();
    private AddressService as = new AddressImple();
    private boolean search = true;
    private boolean search0 = true;

    //them cai nay 8/12
    EditButtons bt = new EditButtons();
    EditTextField txt = new EditTextField();
    /**
     * Creates new form UserJPanel
     */
    public UserJPanel() {
        initComponents();
        datarowUser();
        datarowUser_0();
        txtaccount.setEditable(false);
        txtnumblephone.setEditable(false);
        
        //them vao 8/12
        bt.EditEmployee_and_client(bthKhoiPhuc);
        bt.EditEmployee_and_client(bthResetTable);
        bt.EditEmployee_and_client(bthUpdateUser);
        bt.EditEmployee_and_client(BthRemoveStatus);
        bt.EditEmployee_and_client(bthResetForm);
        bt.Edit(bthsearch1);
        bt.Edit(bthsearch0);
        
        txt.edit(txtSearch1);
        txt.edit(txtSearch0);
        txt.edit(txtfullname);
        txt.edit(txtemail);
        txt.edit(txtnumblephone);
        txt.edit(txtdateofbirth);
        txt.edit(txtaccount);
        txt.edit(txtpassword);
        txt.edit(txtaddress);
        
        TableCustom.apply(slpDanhSachKH, TableCustom.TableType.MULTI_LINE);
        TableCustom.apply(slpDanhSachXoa, TableCustom.TableType.MULTI_LINE);
    }
    //đổ đữ liệu cho bảng User

    public void datarowUser() {
        tableModel = (DefaultTableModel) tblUser.getModel();
        tableModel.setRowCount(0);
        for (UserRole userRole : urs.getAll_User(String.valueOf("1"))) {
            tableModel.addRow(new Object[]{
                userRole.getUserId().getFullName(),
                userRole.getUserId().getEmail(),
                userRole.getUserId().getNumberPhone(),
                userRole.getUserId().getDateOfBirth(),
                userRole.getUserId().getAccount(),
                userRole.getUserId().getPassword(),
                userRole.getUserId().getAddressId().getAddressDetail(),
                urs.getUser_Bill(userRole.getUserId())
            });
        }
    }
    //đổ đữ liệu cho bảng XoaNhanVien

    public void datarowUser_0() {
        tableModel = (DefaultTableModel) tblUser_0.getModel();
        tableModel.setRowCount(0);
        for (UserRole userRole : urs.getAll_User(String.valueOf("0"))) {
            tableModel.addRow(new Object[]{
                userRole.getUserId().getFullName(),
                userRole.getUserId().getEmail(),
                userRole.getUserId().getNumberPhone(),
                userRole.getUserId().getDateOfBirth(),
                userRole.getUserId().getAccount(),
                userRole.getUserId().getPassword(),
                userRole.getUserId().getAddressId().getAddressDetail(),
                urs.getUser_Bill(userRole.getUserId())
            });
        }
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

    public void fillTableUser(int index) {
        UserRole ur = urs.getAll_User(String.valueOf("1")).get(index);
        if (ur.getUserId().getAccount() != null) {
            txtfullname.setText(ur.getUserId().getFullName());
            txtemail.setText(ur.getUserId().getEmail());
            txtnumblephone.setText(ur.getUserId().getNumberPhone());
            txtdateofbirth.setText(String.valueOf(ur.getUserId().getDateOfBirth()));
            txtaccount.setText(ur.getUserId().getAccount());
            txtpassword.setText(ur.getUserId().getPassword());
            txtaddress.setText(ur.getUserId().getAddressId().getAddressDetail());
            txtaccount.setEditable(false);
        }
        if (ur.getUserId().getAccount() == null) {
            txtfullname.setText(ur.getUserId().getFullName());
            txtemail.setText(ur.getUserId().getEmail());
            txtnumblephone.setText(ur.getUserId().getNumberPhone());
            txtdateofbirth.setText(String.valueOf(ur.getUserId().getDateOfBirth()));
            txtaccount.setText(ur.getUserId().getAccount());
            txtpassword.setText(ur.getUserId().getPassword());
            txtaddress.setText(ur.getUserId().getAddressId().getAddressDetail());
            txtaccount.setEditable(true);
        }

    }

    public void fillTableUserSearch(int index, String name) {
        UserRole ur = urs.getSearch_User(String.valueOf("1"), name).get(index);
        if (ur.getUserId().getAccount() != null) {
            txtfullname.setText(ur.getUserId().getFullName());
            txtemail.setText(ur.getUserId().getEmail());
            txtnumblephone.setText(ur.getUserId().getNumberPhone());
            txtdateofbirth.setText(String.valueOf(ur.getUserId().getDateOfBirth()));
            txtaccount.setText(ur.getUserId().getAccount());
            txtpassword.setText(ur.getUserId().getPassword());
            txtaddress.setText(ur.getUserId().getAddressId().getAddressDetail());
            txtaccount.setEditable(false);
        }
        if (ur.getUserId().getAccount() == null) {
            txtfullname.setText(ur.getUserId().getFullName());
            txtemail.setText(ur.getUserId().getEmail());
            txtnumblephone.setText(ur.getUserId().getNumberPhone());
            txtdateofbirth.setText(String.valueOf(ur.getUserId().getDateOfBirth()));
            txtaccount.setText(ur.getUserId().getAccount());
            txtpassword.setText(ur.getUserId().getPassword());
            txtaddress.setText(ur.getUserId().getAddressId().getAddressDetail());
            txtaccount.setEditable(true);
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

    public User checkupdate1() {
        User user = null;
        Date dateofbirth = checkDateOfBirch();
        String fullname = txtfullname.getText();
        String email = txtemail.getText();
        String numblephone = txtnumblephone.getText();
        String account = txtaccount.getText();
        String password = txtpassword.getText();
        String address = txtaddress.getText();
        nowDate = getCurrentDateTime();
        try {
            if (fullname.trim().isEmpty() || email.trim().isEmpty() || account.trim().isEmpty() || password.trim().isEmpty() || address.trim().isEmpty() || numblephone.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng không được để trống các ô nhập!");
                return null;
            } else if (!vl.isCheckName(fullname) || !vl.isCheckName(email) || !vl.isCheckName(account) || !vl.isCheckName(password)) {
                JOptionPane.showMessageDialog(this, "Vui lòng kí tự không được vượt quá 40 kí tự!");
                return null;
            } else if (!vl.isCheckTXT(address)) {
                JOptionPane.showMessageDialog(this, "Vui lòng kí tự không được vượt quá 100 kí tự!");
                return null;
            } else if (dateofbirth == null || !vl.isCheckPhone(numblephone)) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng kí tự của ngày sinh và số điện thoại!");
                return null;
            } else {
                return new User(null, nowDate, dateofbirth, null, null, nowDate, account, email, fullname, numblephone, password, String.valueOf(1));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Vui Lòng nhập lại!");
            return null;
        }
    }

    public User checkupdate2() {
        User user = null;
        Date dateofbirth = checkDateOfBirch();
        String fullname = txtfullname.getText();
        String email = txtemail.getText();
        String numblephone = txtnumblephone.getText();
        String account = checkaccount();
        String password = txtpassword.getText();
        String address = txtaddress.getText();
        nowDate = getCurrentDateTime();
        try {
            if (fullname.trim().isEmpty() || email.trim().isEmpty() || account.trim().isEmpty() || password.trim().isEmpty() || address.trim().isEmpty() || numblephone.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng không được để trống các ô nhập!");
                return null;
            } else if (!vl.isCheckName(fullname) || !vl.isCheckName(email) || !vl.isCheckName(password)) {
                JOptionPane.showMessageDialog(this, "Vui lòng kí tự không được vượt quá 40 kí tự!");
                return null;
            } else if (!vl.isCheckTXT(address)) {
                JOptionPane.showMessageDialog(this, "Vui lòng kí tự không được vượt quá 100 kí tự!");
                return null;
            } else if (dateofbirth == null || account == null) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng kí tự của ngày sinh và tài khoản!");
                return null;
            } else {
                return new User(null, nowDate, dateofbirth, null, null, nowDate, account, email, fullname, numblephone, password, String.valueOf(1));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Vui Lòng nhập lại!");
            return null;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        swingTabbedPane1 = new com.swing.SwingTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        slpDanhSachKH = new javax.swing.JScrollPane();
        tblUser = new javax.swing.JTable();
        bthsearch1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        bthUpdateUser = new javax.swing.JButton();
        bthResetForm = new javax.swing.JButton();
        BthRemoveStatus = new javax.swing.JButton();
        txtfullname = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtemail = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtdateofbirth = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtnumblephone = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtaccount = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtpassword = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txtaddress = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtSearch1 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        slpDanhSachXoa = new javax.swing.JScrollPane();
        tblUser_0 = new javax.swing.JTable();
        bthsearch0 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        bthKhoiPhuc = new javax.swing.JButton();
        bthResetTable = new javax.swing.JButton();
        txtSearch0 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Họ Và Tên", "Email", "Số điện thoại", "Ngày Sinh", "Tài Khoản", "Mật Khẩu", "Địa Chỉ", "Số lượt mua"
            }
        ));
        tblUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUserMouseClicked(evt);
            }
        });
        slpDanhSachKH.setViewportView(tblUser);

        jPanel1.add(slpDanhSachKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 67, 1126, 290));

        bthsearch1.setBackground(new java.awt.Color(204, 255, 255));
        bthsearch1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        bthsearch1.setText("Tìm Kiếm");
        bthsearch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthsearch1ActionPerformed(evt);
            }
        });
        jPanel1.add(bthsearch1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 20, 95, 39));

        jLabel1.setText("Tìm Kiếm");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 10, -1, 20));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setText("Số điện thoại:");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));

        jLabel5.setText("Ngày Sinh:");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 60, -1));

        jLabel2.setText("Họ và tên:");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel3.setText("Email:");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        jLabel6.setText("Tài Khoản:");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, -1, -1));

        jLabel7.setText("Mật Khẩu:");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 70, -1, -1));

        jLabel8.setText("Địa Chỉ:");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 130, -1, -1));

        bthUpdateUser.setBackground(new java.awt.Color(153, 204, 255));
        bthUpdateUser.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        bthUpdateUser.setText("Update Thông Tin Nhân Viên");
        bthUpdateUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthUpdateUserActionPerformed(evt);
            }
        });
        jPanel3.add(bthUpdateUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 10, 434, 40));

        bthResetForm.setBackground(new java.awt.Color(153, 204, 255));
        bthResetForm.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        bthResetForm.setText("Xóa From Thông Tin");
        bthResetForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthResetFormActionPerformed(evt);
            }
        });
        jPanel3.add(bthResetForm, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 110, 434, 40));

        BthRemoveStatus.setBackground(new java.awt.Color(153, 204, 255));
        BthRemoveStatus.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        BthRemoveStatus.setText("Xóa Nhân Viên");
        BthRemoveStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BthRemoveStatusActionPerformed(evt);
            }
        });
        jPanel3.add(BthRemoveStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 200, 434, 40));

        txtfullname.setBorder(null);
        jPanel3.add(txtfullname, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 230, 24));

        jLabel18.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel18.setText("_______________________________________________");
        jLabel18.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 240, 10));

        txtemail.setBorder(null);
        jPanel3.add(txtemail, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 230, 24));

        jLabel25.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel25.setText("_______________________________________________");
        jLabel25.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel3.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 240, 10));

        txtdateofbirth.setBorder(null);
        jPanel3.add(txtdateofbirth, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 230, 24));

        jLabel23.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel23.setText("_______________________________________________");
        jLabel23.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel3.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 240, 10));

        txtnumblephone.setBorder(null);
        jPanel3.add(txtnumblephone, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 230, 24));

        jLabel24.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel24.setText("_______________________________________________");
        jLabel24.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel3.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 240, 10));

        txtaccount.setBorder(null);
        jPanel3.add(txtaccount, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 30, 230, 24));

        jLabel22.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel22.setText("_______________________________________________");
        jLabel22.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel3.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 50, 240, 10));

        txtpassword.setBorder(null);
        jPanel3.add(txtpassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 90, 230, 24));

        jLabel26.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel26.setText("_______________________________________________");
        jLabel26.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel3.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 110, 240, 10));

        txtaddress.setBorder(null);
        jPanel3.add(txtaddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 150, 230, 24));

        jLabel27.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel27.setText("_______________________________________________");
        jLabel27.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel3.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 170, 240, 10));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 370, 1126, 250));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel11.setText("Khách Hàng");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        txtSearch1.setBorder(null);
        jPanel1.add(txtSearch1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 30, 430, 24));

        jLabel12.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel12.setText("________________________________________________________________________________________");
        jLabel12.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 50, 450, 10));

        swingTabbedPane1.addTab("Khách Hàng", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblUser_0.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Họ Và Tên", "Email", "Số điện thoại", "Ngày Sinh", "Tài Khoản", "Mật Khẩu", "Địa Chỉ", "Số lượt  mua"
            }
        ));
        slpDanhSachXoa.setViewportView(tblUser_0);

        jPanel2.add(slpDanhSachXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 64, 1120, 470));

        bthsearch0.setBackground(new java.awt.Color(204, 255, 255));
        bthsearch0.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        bthsearch0.setText("Tìm Kiếm");
        bthsearch0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthsearch0ActionPerformed(evt);
            }
        });
        jPanel2.add(bthsearch0, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 20, 110, 39));

        jLabel9.setText("Tìm Kiếm");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 10, -1, -1));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel10.setText("Khách Hàng Đã Xóa");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 215, -1));

        bthKhoiPhuc.setBackground(new java.awt.Color(153, 204, 255));
        bthKhoiPhuc.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        bthKhoiPhuc.setText("Khôi phục Khách Hàng");
        bthKhoiPhuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthKhoiPhucActionPerformed(evt);
            }
        });
        jPanel2.add(bthKhoiPhuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 550, 225, 60));

        bthResetTable.setBackground(new java.awt.Color(153, 204, 255));
        bthResetTable.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        bthResetTable.setText("Reset Table");
        bthResetTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthResetTableActionPerformed(evt);
            }
        });
        jPanel2.add(bthResetTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 550, 225, 60));

        txtSearch0.setBorder(null);
        jPanel2.add(txtSearch0, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 30, 430, 20));

        jLabel19.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel19.setText("___________________________________________________________________________________________");
        jLabel19.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel2.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 40, -1, -1));

        swingTabbedPane1.addTab("Đã Xóa", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(swingTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1138, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(swingTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUserMouseClicked
        int index = tblUser.getSelectedRow();
        if (search == true) {
            fillTableUser(index);
        }
        if (search == false) {
            fillTableUserSearch(index, txtSearch1.getText());
        }
    }//GEN-LAST:event_tblUserMouseClicked

    private void bthResetFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthResetFormActionPerformed
        reset();
        datarowUser();
        datarowUser_0();
        search = true;
    }//GEN-LAST:event_bthResetFormActionPerformed

    private void bthUpdateUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthUpdateUserActionPerformed
        try {
            int index = tblUser.getSelectedRow();
        if (index >= 0) {
            if (search == true) {
                UserRole ur = urs.getAll_User(String.valueOf("1")).get(index);
                if (ur.getUserId().getAccount() != null) {
                    if (ur != null) {
                        User user = checkupdate1();
                        if (user != null) {
                            String iduser = ur.getUserId().getId();
                            String idaddressupdate = ur.getUserId().getAddressId().getId();
                            String addressdetail = txtaddress.getText();
                            if (ur.getUserId().getAddressId().getAddressDetail()==null) {
                                nowDate = getCurrentDateTime();
                                System.out.println( nowDate);
                                as.add_address(nowDate, addressdetail);
                                Address address = us.getAddress(nowDate, addressdetail);
                                String idaddressadd = address.getId();
                                System.out.println(idaddressadd);
                                System.out.println(us.Update_user_address(idaddressadd , iduser));
                            }else{
                                as.update_address(nowDate, addressdetail, idaddressupdate);
                            }                            
                            boolean userS = us.Update_user_all(user, iduser);
                            if (userS) {
                                JOptionPane.showMessageDialog(this, "Update thành công !");
                            } else {
                                JOptionPane.showMessageDialog(this, "Update thất bại !");
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Lỗi ");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Lỗi");
                    }
                }
                if (ur.getUserId().getAccount() == null) {
                    if (ur != null) {
                        User user = checkupdate2();
                        if (user != null) {
                            String iduser = ur.getUserId().getId();
                            String idaddressupdate = ur.getUserId().getAddressId().getId();
                            String addressdetail = txtaddress.getText();
                            if (ur.getUserId().getAddressId().getAddressDetail()==null) {
                                nowDate = getCurrentDateTime();
                                as.add_address(nowDate, addressdetail);
                                Address address = us.getAddress(nowDate, addressdetail);
                                String idaddressadd = address.getId();
                                us.Update_user_address(idaddressadd , iduser);
                            }else{
                                as.update_address(nowDate, addressdetail, idaddressupdate);
                            }   
                            boolean userS = us.Update_user_all(user, iduser);
                            if (userS) {
                                JOptionPane.showMessageDialog(this, "Update thành công !");
                            } else {
                                JOptionPane.showMessageDialog(this, "Update thất bại !");
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Lỗi ");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Lỗi");
                    }
                }
            }
            if (search == false) {
                String name = txtSearch1.getText();
                UserRole ur = urs.getSearch_User(String.valueOf("1"), name).get(index);
                if (ur.getUserId().getAccount() != null) {
                    if (ur != null) {
                        User user = checkupdate1();
                        if (user != null) {
                            String iduser = ur.getUserId().getId();
                            String idaddressupdate = ur.getUserId().getAddressId().getId();
                            String addressdetail = txtaddress.getText();
                            if (ur.getUserId().getAddressId().getAddressDetail()==null) {
                                nowDate = getCurrentDateTime();
                                as.add_address(nowDate, addressdetail);
                                Address address = us.getAddress(nowDate, addressdetail);
                                String idaddressadd = address.getId();
                                us.Update_user_address(idaddressadd , iduser);
                            }else{
                                as.update_address(nowDate, addressdetail, idaddressupdate);
                            }   
                            boolean userS = us.Update_user_all(user, iduser);
                            if (userS) {
                                JOptionPane.showMessageDialog(this, "Update thành công !");
                            } else {
                                JOptionPane.showMessageDialog(this, "Update thất bại !");
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Lỗi ");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Lỗi");
                    }
                }
                if (ur.getUserId().getAccount() == null) {
                    if (ur != null) {
                        User user = checkupdate2();
                        if (user != null) {
                            String iduser = ur.getUserId().getId();
                            String idaddressupdate = ur.getUserId().getAddressId().getId();
                            String addressdetail = txtaddress.getText();
                            if (ur.getUserId().getAddressId().getAddressDetail()==null) {
                                nowDate = getCurrentDateTime();
                                as.add_address(nowDate, addressdetail);
                                Address address = us.getAddress(nowDate, addressdetail);
                                String idaddressadd = address.getId();
                                us.Update_user_address(idaddressadd , iduser);
                            }else{
                                as.update_address(nowDate, addressdetail, idaddressupdate);
                            }   
                            boolean userS = us.Update_user_all(user, iduser);
                            if (userS) {
                                JOptionPane.showMessageDialog(this, "Update thành công !");
                            } else {
                                JOptionPane.showMessageDialog(this, "Update thất bại !");
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Lỗi ");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Lỗi");
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn nhân viên nào ");
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        reset();
        datarowUser();
        search = true;
    }//GEN-LAST:event_bthUpdateUserActionPerformed

    private void BthRemoveStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BthRemoveStatusActionPerformed
        int index = tblUser.getSelectedRow();
        if (index >= 0) {
            if (search == true) {
                UserRole ur = urs.getAll_User(String.valueOf("1")).get(index);
                if (ur != null) {
                    boolean userS = us.Update_status_user(String.valueOf("0"), ur.getUserId());
                    if (userS) {
                        JOptionPane.showMessageDialog(this, "Xóa thành công !");
                    } else {
                        JOptionPane.showMessageDialog(this, "Xóa thất bại !");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Lỗi ");
                }
            }
            if (search == false) {
                String name = txtSearch1.getText();
                UserRole ur = urs.getSearch_User(String.valueOf("1"), name).get(index);
                if (ur != null) {
                    boolean userS = us.Update_status_user(String.valueOf("0"), ur.getUserId());
                    if (userS) {
                        JOptionPane.showMessageDialog(this, "Xóa thành công !");
                    } else {
                        JOptionPane.showMessageDialog(this, "Xóa thất bại !");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Lỗi ");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn nhân viên nào ");
        }
        reset();
        datarowUser();
        datarowUser_0();
        search = true;
    }//GEN-LAST:event_BthRemoveStatusActionPerformed

    private void bthKhoiPhucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthKhoiPhucActionPerformed
        int index = tblUser_0.getSelectedRow();
        if (index >= 0) {
            if (search0 == true) {
                UserRole ur = urs.getAll_User(String.valueOf("0")).get(index);
                if (ur != null) {
                    boolean userS = us.Update_status_user(String.valueOf("1"), ur.getUserId());
                    if (userS) {
                        JOptionPane.showMessageDialog(this, "Khôi phục thành công !");
                    } else {
                        JOptionPane.showMessageDialog(this, "Khôi phục thất bại !");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Lỗi ");
                }
            }
            if (search0 == false) {
                String name = txtSearch1.getText();
                UserRole ur = urs.getSearch_User(String.valueOf("0"), name).get(index);
                if (ur != null) {
                    boolean userS = us.Update_status_user(String.valueOf("1"), ur.getUserId());
                    if (userS) {
                        JOptionPane.showMessageDialog(this, "Khôi phục thành công !");
                    } else {
                        JOptionPane.showMessageDialog(this, "Khôi phục thất bại !");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Lỗi ");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn nhân viên nào ");
        }
        reset();
        datarowUser();
        datarowUser_0();
        search = true;
    }//GEN-LAST:event_bthKhoiPhucActionPerformed

    private void bthsearch0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthsearch0ActionPerformed
        String status = String.valueOf("0");
        String nameSearch = txtSearch0.getText();
        tableModel = (DefaultTableModel) tblUser_0.getModel();
        tableModel.setRowCount(0);
        for (UserRole userRole : urs.getSearch_User(status, nameSearch)) {
            tableModel.addRow(new Object[]{
                userRole.getUserId().getFullName(),
                userRole.getUserId().getEmail(),
                userRole.getUserId().getNumberPhone(),
                userRole.getUserId().getDateOfBirth(),
                userRole.getUserId().getAccount(),
                userRole.getUserId().getPassword(),
                userRole.getUserId().getAddressId().getAddressDetail(),
                urs.getUser_Bill(userRole.getUserId())
            });
        }
        search0 = false;
    }//GEN-LAST:event_bthsearch0ActionPerformed

    private void bthsearch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthsearch1ActionPerformed
        String status = String.valueOf("1");
        String nameSearch = txtSearch1.getText();
        tableModel = (DefaultTableModel) tblUser.getModel();
        tableModel.setRowCount(0);
        for (UserRole userRole : urs.getSearch_User(status, nameSearch)) {
            tableModel.addRow(new Object[]{
                userRole.getUserId().getFullName(),
                userRole.getUserId().getEmail(),
                userRole.getUserId().getNumberPhone(),
                userRole.getUserId().getDateOfBirth(),
                userRole.getUserId().getAccount(),
                userRole.getUserId().getPassword(),
                userRole.getUserId().getAddressId().getAddressDetail(),
                urs.getUser_Bill(userRole.getUserId())
            });
        }
        search = false;
    }//GEN-LAST:event_bthsearch1ActionPerformed

    private void bthResetTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthResetTableActionPerformed
        datarowUser();
        datarowUser_0();
        search = true;
    }//GEN-LAST:event_bthResetTableActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BthRemoveStatus;
    private javax.swing.JButton bthKhoiPhuc;
    private javax.swing.JButton bthResetForm;
    private javax.swing.JButton bthResetTable;
    private javax.swing.JButton bthUpdateUser;
    private javax.swing.JButton bthsearch0;
    private javax.swing.JButton bthsearch1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane slpDanhSachKH;
    private javax.swing.JScrollPane slpDanhSachXoa;
    private com.swing.SwingTabbedPane swingTabbedPane1;
    private javax.swing.JTable tblUser;
    private javax.swing.JTable tblUser_0;
    private javax.swing.JTextField txtSearch0;
    private javax.swing.JTextField txtSearch1;
    private javax.swing.JTextField txtaccount;
    private javax.swing.JTextField txtaddress;
    private javax.swing.JTextField txtdateofbirth;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txtfullname;
    private javax.swing.JTextField txtnumblephone;
    private javax.swing.JTextField txtpassword;
    // End of variables declaration//GEN-END:variables
}
