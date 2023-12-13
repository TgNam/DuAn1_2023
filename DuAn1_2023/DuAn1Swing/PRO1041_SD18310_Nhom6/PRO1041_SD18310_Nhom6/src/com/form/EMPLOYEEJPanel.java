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
public class EMPLOYEEJPanel extends javax.swing.JPanel {

    private DefaultTableModel tableModel;
    private Validate vl = new Validate();
    private Date nowDate = null;
    private UserRoleService urs = new UserRoleImple();
    private UserService us = new UserImple();
    private AddressService as = new AddressImple();
    private boolean search = true;
    private boolean search0 = true;
    
    //them vao 7/12
    private  EditButtons bt = new EditButtons();
    private EditTextField txt = new EditTextField();
    /**
     * Creates new form EMPLOYEEJPanel
     */
    public EMPLOYEEJPanel() {
        initComponents();
        datarowEmployee();
        datarowEmployee_0();
        txtaccount.setEditable(false);
        txtnumblephone.setEditable(false);
        
        //them 8/12
        bt.Edit(bthsearch1);
        bt.Edit(bthsearch0);
        bt.EditEmployee_and_client(bthUpdateEmployee);
        bt.EditEmployee_and_client(BthRemoveStatus);
        bt.EditEmployee_and_client(bthResetForm);
        bt.EditEmployee_and_client(bthKhoiPhuc);
        bt.EditEmployee_and_client(bthResetTable);
        
        txt.edit(txtSearch1);
        txt.edit(txtSearch0);
        txt.edit(txtfullname);
        txt.edit(txtemail);
        txt.edit(txtnumblephone);
        txt.edit(txtdateofbirth);
        txt.edit(txtaccount);
        txt.edit(txtpassword);
        txt.edit(txtaddress);
        
        TableCustom.apply(slpDanhSachNV, TableCustom.TableType.MULTI_LINE);
        TableCustom.apply(slpDanhSachXoa, TableCustom.TableType.MULTI_LINE);
    }

    //đổ đữ liệu cho bảng NhanVien
    public void datarowEmployee() {
        tableModel = (DefaultTableModel) tblEmploee.getModel();
        tableModel.setRowCount(0);
        
        for (UserRole userRole : urs.getAll_Employee(String.valueOf("1"))) {
            tableModel.addRow(new Object[]{
                userRole.getUserId().getFullName(),
                userRole.getUserId().getEmail(),
                userRole.getUserId().getNumberPhone(),
                userRole.getUserId().getDateOfBirth(),
                userRole.getUserId().getAccount(),
                userRole.getUserId().getPassword(),
                userRole.getUserId().getAddressId().getAddressDetail(),
                userRole.getRoleId().getRoleName(),
                userRole.getUserId().checkTrangThai()
            });
        }

    }

    //đổ đữ liệu cho bảng XoaNhanVien
    public void datarowEmployee_0() {
        tableModel = (DefaultTableModel) tblEmployee_0.getModel();
        tableModel.setRowCount(0);
        for (UserRole userRole : urs.getAll_Employee(String.valueOf("0"))) {
            tableModel.addRow(new Object[]{
                userRole.getUserId().getFullName(),
                userRole.getUserId().getEmail(),
                userRole.getUserId().getNumberPhone(),
                userRole.getUserId().getDateOfBirth(),
                userRole.getUserId().getAccount(),
                userRole.getUserId().getPassword(),
                userRole.getUserId().getAddressId().getAddressDetail(),
                userRole.getRoleId().getRoleName(),
                userRole.getUserId().checkTrangThai()
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
    
    public void fillTableEmployee(int index) {
        UserRole ur = urs.getAll_Employee(String.valueOf("1")).get(index);
        txtfullname.setText(ur.getUserId().getFullName());
        txtemail.setText(ur.getUserId().getEmail());
        txtnumblephone.setText(ur.getUserId().getNumberPhone());
        txtdateofbirth.setText(String.valueOf(ur.getUserId().getDateOfBirth()));
        txtaccount.setText(ur.getUserId().getAccount());
        txtpassword.setText(ur.getUserId().getPassword());
        txtaddress.setText(ur.getUserId().getAddressId().getAddressDetail());
        if (ur.getRoleId().getRoleName().equalsIgnoreCase("ADMIN")) {
            rdoadmin.setSelected(true);
        } else {
            rdoemployee.setSelected(true);
        }
    }

    public void fillTableEmployeeSearch(int index, String name) {
        UserRole ur = urs.getSearch_Employee(String.valueOf("1"), name).get(index);
        txtfullname.setText(ur.getUserId().getFullName());
        txtemail.setText(ur.getUserId().getEmail());
        txtnumblephone.setText(ur.getUserId().getNumberPhone());
        txtdateofbirth.setText(String.valueOf(ur.getUserId().getDateOfBirth()));
        txtaccount.setText(ur.getUserId().getAccount());
        txtpassword.setText(ur.getUserId().getPassword());
        txtaddress.setText(ur.getUserId().getAddressId().getAddressDetail());
        if (ur.getRoleId().getRoleName().equalsIgnoreCase("ADMIN")) {
            rdoadmin.setSelected(true);
        } else {
            rdoemployee.setSelected(true);
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

    public User checkupdate() {
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        tbpTong = new com.swing.SwingTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        slpDanhSachNV = new javax.swing.JScrollPane();
        tblEmploee = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        bthsearch1 = new javax.swing.JButton();
        txtSearch1 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        bthUpdateEmployee = new javax.swing.JButton();
        bthResetForm = new javax.swing.JButton();
        BthRemoveStatus = new javax.swing.JButton();
        rdoadmin = new com.swing.RadioButtonCustom();
        rdoemployee = new com.swing.RadioButtonCustom();
        txtfullname = new javax.swing.JTextField();
        txtemail = new javax.swing.JTextField();
        txtnumblephone = new javax.swing.JTextField();
        txtdateofbirth = new javax.swing.JTextField();
        txtaccount = new javax.swing.JTextField();
        txtpassword = new javax.swing.JTextField();
        txtaddress = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        slpDanhSachXoa = new javax.swing.JScrollPane();
        tblEmployee_0 = new javax.swing.JTable();
        bthsearch0 = new javax.swing.JButton();
        bthKhoiPhuc = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        bthResetTable = new javax.swing.JButton();
        txtSearch0 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblEmploee.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Họ Và Tên", "Email", "Số điện thoại", "Ngày Sinh", "Tài Khoản", "Mật Khẩu", "Địa Chỉ", "Vai Trò", "Trạng thái"
            }
        ));
        tblEmploee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEmploeeMouseClicked(evt);
            }
        });
        slpDanhSachNV.setViewportView(tblEmploee);

        jPanel3.add(slpDanhSachNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 850, 530));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel1.setText("Tìm Kiếm");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, -1));

        bthsearch1.setBackground(new java.awt.Color(204, 255, 255));
        bthsearch1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        bthsearch1.setText("Tìm Kiếm");
        bthsearch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthsearch1ActionPerformed(evt);
            }
        });
        jPanel3.add(bthsearch1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 29, 95, 30));

        txtSearch1.setBorder(null);
        jPanel3.add(txtSearch1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 430, 24));

        jLabel11.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel11.setText("___________________________________________________________________________________________");
        jLabel11.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 490, 10));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setText("Họ và tên:");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 60, -1));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("Email:");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 40, -1));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("Số điện thoại:");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setText("Ngày Sinh:");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 80, -1));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setText("Tài Khoản:");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 70, -1));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel7.setText("Mật Khẩu:");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 70, -1));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel8.setText("Địa Chỉ:");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 50, -1));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel9.setText("Vai Trò:");
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, -1, -1));

        bthUpdateEmployee.setBackground(new java.awt.Color(153, 204, 255));
        bthUpdateEmployee.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        bthUpdateEmployee.setText("Update Thông Tin Nhân Viên");
        bthUpdateEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthUpdateEmployeeActionPerformed(evt);
            }
        });
        jPanel4.add(bthUpdateEmployee, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, 240, 30));

        bthResetForm.setBackground(new java.awt.Color(153, 204, 255));
        bthResetForm.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        bthResetForm.setText("Xóa From Thông Tin");
        bthResetForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthResetFormActionPerformed(evt);
            }
        });
        jPanel4.add(bthResetForm, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 530, 240, 30));

        BthRemoveStatus.setBackground(new java.awt.Color(153, 204, 255));
        BthRemoveStatus.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        BthRemoveStatus.setText("Xóa  Nhân Viên");
        BthRemoveStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BthRemoveStatusActionPerformed(evt);
            }
        });
        jPanel4.add(BthRemoveStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 570, 240, 30));

        buttonGroup1.add(rdoadmin);
        rdoadmin.setText("ADMIN");
        jPanel4.add(rdoadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, 90, -1));

        buttonGroup1.add(rdoemployee);
        rdoemployee.setText("EMPLOYEE");
        jPanel4.add(rdoemployee, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 450, 100, -1));

        txtfullname.setBorder(null);
        jPanel4.add(txtfullname, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 230, 24));

        txtemail.setBorder(null);
        jPanel4.add(txtemail, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 230, 24));

        txtnumblephone.setBorder(null);
        jPanel4.add(txtnumblephone, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 230, 24));

        txtdateofbirth.setBorder(null);
        jPanel4.add(txtdateofbirth, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 230, 24));

        txtaccount.setBorder(null);
        jPanel4.add(txtaccount, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 230, 24));

        txtpassword.setBorder(null);
        jPanel4.add(txtpassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 230, 24));

        txtaddress.setBorder(null);
        jPanel4.add(txtaddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, 230, 24));

        jLabel18.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel18.setText("______________________________________________");
        jLabel18.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel4.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 240, 10));

        jLabel20.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel20.setText("______________________________________________");
        jLabel20.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel4.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 240, 10));

        jLabel21.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel21.setText("______________________________________________");
        jLabel21.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel4.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 240, 10));

        jLabel22.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel22.setText("______________________________________________");
        jLabel22.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel4.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 240, 10));

        jLabel23.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel23.setText("______________________________________________");
        jLabel23.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel4.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 240, 10));

        jLabel24.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel24.setText("______________________________________________");
        jLabel24.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel4.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 240, 10));

        jLabel25.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel25.setText("______________________________________________");
        jLabel25.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel4.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 240, 10));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 872, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        tbpTong.addTab("Danh Sách Nhân Viên Đang Làm Việc", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tblEmployee_0.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Họ Và Tên", "Email", "Số điện thoại", "Ngày Sinh", "Tài Khoản", "Mật Khẩu", "Địa Chỉ", "Vai Trò", "Trạng thái"
            }
        ));
        slpDanhSachXoa.setViewportView(tblEmployee_0);

        bthsearch0.setBackground(new java.awt.Color(204, 255, 255));
        bthsearch0.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        bthsearch0.setText("Tìm Kiếm");
        bthsearch0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthsearch0ActionPerformed(evt);
            }
        });

        bthKhoiPhuc.setBackground(new java.awt.Color(153, 204, 255));
        bthKhoiPhuc.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        bthKhoiPhuc.setText("Khôi Phục Nhân Viên");
        bthKhoiPhuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthKhoiPhucActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel10.setText("Nhân Viên Đã Nghỉ Việc");

        bthResetTable.setBackground(new java.awt.Color(153, 204, 255));
        bthResetTable.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        bthResetTable.setText("Reset Table");
        bthResetTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthResetTableActionPerformed(evt);
            }
        });

        txtSearch0.setBorder(null);

        jLabel19.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel19.setText("___________________________________________________________________________________________");
        jLabel19.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSearch0, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(bthsearch0, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(93, 93, 93)
                                .addComponent(bthKhoiPhuc, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(bthResetTable, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 230, Short.MAX_VALUE))
                    .addComponent(slpDanhSachXoa, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtSearch0, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(bthsearch0, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(slpDanhSachXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bthKhoiPhuc, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bthResetTable, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbpTong.addTab("Danh Sách Nhân Viên Đã Nghỉ Việc", jPanel2);

        add(tbpTong, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1160, 670));
    }// </editor-fold>//GEN-END:initComponents

    private void tblEmploeeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEmploeeMouseClicked
        int index = tblEmploee.getSelectedRow();
        if (search == true) {
            fillTableEmployee(index);
        }
        if (search == false) {
            fillTableEmployeeSearch(index, txtSearch1.getText());
        }
    }//GEN-LAST:event_tblEmploeeMouseClicked

    private void bthResetFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthResetFormActionPerformed
        reset();
        datarowEmployee();
        datarowEmployee_0();
        search = true;
    }//GEN-LAST:event_bthResetFormActionPerformed

    private void bthUpdateEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthUpdateEmployeeActionPerformed
        int index = tblEmploee.getSelectedRow();
        if (index >= 0) {
            if (search == true) {
                UserRole ur = urs.getAll_Employee(String.valueOf("1")).get(index);
                if (ur != null) {
                    User user = checkupdate();
                    if (user != null) {
                        String iduser = ur.getUserId().getId();
                        String idaddress = ur.getUserId().getAddressId().getId();
                        String addressdetail = txtaddress.getText();
                        as.update_address(nowDate, addressdetail, idaddress);
                        boolean userS = us.Update_user_all(user, iduser);
                        if (userS) {
                            JOptionPane.showMessageDialog(this, "Update thành công !");
                            String idrole = rdoadmin.isSelected() == true ? "1" : "2";
                            urs.Update_user_role(ur.getUserId(), idrole);
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
            if (search == false) {
                String name = txtSearch1.getText();
                UserRole ur = urs.getSearch_Employee(String.valueOf("1"), name).get(index);
                if (ur != null) {
                    User user = checkupdate();
                    if (user != null) {
                        String iduser = ur.getUserId().getId();
                        String idaddress = ur.getUserId().getAddressId().getId();
                        String addressdetail = txtaddress.getText();
                        as.update_address(nowDate, addressdetail, idaddress);
                        boolean userS = us.Update_user_all(user, iduser);
                        if (userS) {
                            JOptionPane.showMessageDialog(this, "Update thành công !");
                            String idrole = rdoadmin.isSelected() == true ? "1" : "2";
                            urs.Update_user_role(ur.getUserId(), idrole);
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
        } else {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn nhân viên nào ");
        }
        reset();
        datarowEmployee();
        search = true;
    }//GEN-LAST:event_bthUpdateEmployeeActionPerformed

    private void BthRemoveStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BthRemoveStatusActionPerformed
        int index = tblEmploee.getSelectedRow();
        if (index >= 0) {
            if (search == true) {
                UserRole ur = urs.getAll_Employee(String.valueOf("1")).get(index);
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
                UserRole ur = urs.getSearch_Employee(String.valueOf("1"), name).get(index);
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
        datarowEmployee();
        datarowEmployee_0();
        search = true;
    }//GEN-LAST:event_BthRemoveStatusActionPerformed

    private void bthKhoiPhucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthKhoiPhucActionPerformed
        int index = tblEmployee_0.getSelectedRow();
        if (index >= 0) {
            if (search0 == true) {
                UserRole ur = urs.getAll_Employee(String.valueOf("0")).get(index);
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
                UserRole ur = urs.getSearch_Employee(String.valueOf("0"), name).get(index);
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
        datarowEmployee();
        datarowEmployee_0();
        search = true;
    }//GEN-LAST:event_bthKhoiPhucActionPerformed

    private void bthsearch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthsearch1ActionPerformed
        String status = String.valueOf("1");
        String nameSearch = txtSearch1.getText();
        tableModel = (DefaultTableModel) tblEmploee.getModel();
        tableModel.setRowCount(0);
        for (UserRole userRole : urs.getSearch_Employee(status, nameSearch)) {
            tableModel.addRow(new Object[]{
                userRole.getUserId().getFullName(),
                userRole.getUserId().getEmail(),
                userRole.getUserId().getNumberPhone(),
                userRole.getUserId().getDateOfBirth(),
                userRole.getUserId().getAccount(),
                userRole.getUserId().getPassword(),
                userRole.getUserId().getAddressId().getAddressDetail(),
                userRole.getRoleId().getRoleName(),
                userRole.getUserId().checkTrangThai()
            });
        }
        search = false;
    }//GEN-LAST:event_bthsearch1ActionPerformed

    private void bthsearch0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthsearch0ActionPerformed
        String status = String.valueOf("0");
        String nameSearch = txtSearch0.getText();
        tableModel = (DefaultTableModel) tblEmployee_0.getModel();
        tableModel.setRowCount(0);
        for (UserRole userRole : urs.getSearch_Employee(status, nameSearch)) {
            tableModel.addRow(new Object[]{
                userRole.getUserId().getFullName(),
                userRole.getUserId().getEmail(),
                userRole.getUserId().getNumberPhone(),
                userRole.getUserId().getDateOfBirth(),
                userRole.getUserId().getAccount(),
                userRole.getUserId().getPassword(),
                userRole.getUserId().getAddressId().getAddressDetail(),
                userRole.getRoleId().getRoleName(),
                userRole.getUserId().checkTrangThai()
            });
        }
        search0 = false;
    }//GEN-LAST:event_bthsearch0ActionPerformed

    private void bthResetTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthResetTableActionPerformed
        datarowEmployee();
        datarowEmployee_0();
        search = true;
    }//GEN-LAST:event_bthResetTableActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BthRemoveStatus;
    private javax.swing.JButton bthKhoiPhuc;
    private javax.swing.JButton bthResetForm;
    private javax.swing.JButton bthResetTable;
    private javax.swing.JButton bthUpdateEmployee;
    private javax.swing.JButton bthsearch0;
    private javax.swing.JButton bthsearch1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
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
    private javax.swing.JPanel jPanel4;
    private com.swing.RadioButtonCustom rdoadmin;
    private com.swing.RadioButtonCustom rdoemployee;
    private javax.swing.JScrollPane slpDanhSachNV;
    private javax.swing.JScrollPane slpDanhSachXoa;
    private javax.swing.JTable tblEmploee;
    private javax.swing.JTable tblEmployee_0;
    private com.swing.SwingTabbedPane tbpTong;
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
