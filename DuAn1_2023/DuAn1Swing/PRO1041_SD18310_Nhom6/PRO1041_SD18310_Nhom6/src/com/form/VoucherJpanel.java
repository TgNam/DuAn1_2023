/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.form;

import com.model.User;
import java.util.Date;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import com.model.Voucher;
import com.repository.UserRepository;
import com.repository.UserVoucherResponsitory;
import com.service.imple.UserImple;
import com.service.imple.VoucherImple;
import com.swing.EditButtons;
import com.swing.EditTextField;
import java.util.List;
import table.TableCustom;

/**
 *
 * @author lenovo
 */
public class VoucherJpanel extends javax.swing.JPanel {

    private VoucherImple voucherImple = new VoucherImple();
    private int index = -1;
    private UserVoucherResponsitory userVoucherResponsitory = new UserVoucherResponsitory();
    private UserImple userImple = new UserImple();
    private UserRepository userRepository = new UserRepository();

    //them cai nay 8/12
    EditButtons bt = new EditButtons();
    EditTextField txt = new EditTextField();

    public VoucherJpanel() {
        initComponents();
        FormVoucherPanel.setVisible(false);
        FormVoucherUpdate.setVisible(false);
        fillTableVoucher();
        fillTableKhachHang(userImple.getCustomer());
        //them vao 8/12
        TableCustom.apply(slpKH, TableCustom.TableType.MULTI_LINE);
        TableCustom.apply(slpKH2, TableCustom.TableType.MULTI_LINE);
        TableCustom.apply(slpVoucher, TableCustom.TableType.MULTI_LINE);

        txt.edit(txtSaleOf);
        txt.edit(txtSaleOf1);
        txt.edit(txtSearch);
        txt.edit(txtSearch1);

        bt.Edit(btnCreateVoucher);
        bt.Edit(btnCreate);
        bt.Edit(btnBack);
        bt.Edit(btnUUpdate1);
        bt.Edit(btnRemove1);
        bt.Edit(btnBack1);
        bt.Edit(btnResetTableKh1);
        bt.Edit(btnResetTableKh2);
        bt.Edit(btnSearch);
        bt.Edit(btnSearch1);
    }

    public void fillTableVoucher() {
        DefaultTableModel tableModel = (DefaultTableModel) tblVoucher.getModel();
        tableModel.setRowCount(0);
        voucherImple.resetStatus();
        // vòng lặp viết nhanh
        voucherImple.getAll().forEach(i -> tableModel.addRow(new Object[]{
            i.getId(), i.getSaleOf(), i.getStartAt(), i.getEndAt(), i.getCreatedAt(), i.getUpdatedAt(), i.getStatus().equals("1")?"Đang hoạt động":"Ngừng hoạt động"
        }));
    }

    public void fillTableKhachHang(List<User> list) {
        DefaultTableModel tableModel = (DefaultTableModel) tblKhachHang.getModel();
        tableModel.setRowCount(0);
        // vòng lặp viết nhanh
        list.forEach(i -> tableModel.addRow(new Object[]{
            i.getId(), i.getFullName(), i.getNumberPhone(), i.getEmail()
        }));
    }

    public void fillTableKhachHang1(List<User> list) {
        DefaultTableModel tableModel = (DefaultTableModel) tblKhachHang1.getModel();
        tableModel.setRowCount(0);
        // vòng lặp viết nhanh
        list.forEach(i -> tableModel.addRow(new Object[]{
            i.getId(), i.getFullName(), i.getNumberPhone(), i.getEmail()
        }));
        for (int i = 0; i < tblKhachHang1.getRowCount(); i++) {
            int check = userVoucherResponsitory.getCountVoucherUser(voucherImple.getAll().get(index).getId(), userImple.getCustomer().get(i).getId());
            if (check > 0) {
                tblKhachHang1.setValueAt(true, i, tblKhachHang.getColumnCount() - 1);
            }
        }
    }

    public void resetForm() {
        txtSaleOf.setText("");
        jXStartAt.setDate(null);
        jXEndAt.setDate(null);

        for (int i = 0; i < tblKhachHang.getRowCount(); i++) {
            tblKhachHang.setValueAt(false, i, tblKhachHang.getColumnCount() - 1);
        }

        tblVoucher.clearSelection();
        index = -1;
    }

    public boolean validateForm() {
        if (txtSaleOf.getText().trim().isEmpty() || jXStartAt.getDate() == null || jXEndAt.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Lỗi dữ liệu trống", "Lỗi", 2);
            return false;
        }
        Double saleOf = Double.valueOf(txtSaleOf.getText().trim());
        if (saleOf < 0) {
            JOptionPane.showMessageDialog(this, "Giảm giá không được âm", "Lỗi", 2);
            return false;
        }
        if (saleOf > 100) {
            JOptionPane.showMessageDialog(this, "Giảm giá chỉ nằm trong khoảng từ 0 - 100", "Lỗi", 2);
            return false;
        }
        Date startDate = jXStartAt.getDate();
        Date endDate = jXEndAt.getDate();

        if (startDate != null && endDate != null && startDate.compareTo(endDate) >= 0) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải nhỏ hơn ngày kết thúc!", "Lỗi ngày", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            Double.valueOf(txtSaleOf.getText().trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Giảm giá phải là số", "Lỗi", 2);
            return false;
        }
        return true;
    }

    public boolean validateForm1() {
        if (txtSaleOf1.getText().trim().isEmpty() || jXStartAt1.getDate() == null || jXEndAt1.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Lỗi dữ liệu sai", "Lỗi", 2);
            return false;
        }
        Double saleOf = Double.valueOf(txtSaleOf1.getText().trim());
        if (saleOf < 0) {
            JOptionPane.showMessageDialog(this, "Giảm giá không được âm", "Lỗi", 2);
            return false;
        }
        if (saleOf > 100) {
            JOptionPane.showMessageDialog(this, "Giảm giá chỉ nằm trong khoảng từ 0 - 100", "Lỗi", 2);
            return false;
        }
        Date startDate = jXStartAt1.getDate();
        Date endDate = jXEndAt1.getDate();

        if (startDate != null && endDate != null && startDate.compareTo(endDate) >= 0) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải nhỏ hơn ngày kết thúc!", "Lỗi ngày", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            Double.valueOf(txtSaleOf1.getText().trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Giảm giá phải là số", "Lỗi", 2);
            return false;
        }
        return true;
    }

    private String generateRandomId() {
        int numberOfDigits = 10;
        Random random = new Random();
        StringBuilder idBuilder = new StringBuilder();
        for (int i = 0; i < numberOfDigits; i++) {
            int digit = random.nextInt(10);
            idBuilder.append(digit);
        }
        return idBuilder.toString();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FormVoucherPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jXStartAt = new org.jdesktop.swingx.JXDatePicker();
        jXEndAt = new org.jdesktop.swingx.JXDatePicker();
        btnCreate = new javax.swing.JButton();
        txtSaleOf = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        slpKH = new javax.swing.JScrollPane();
        tblKhachHang = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnResetTableKh1 = new javax.swing.JButton();
        TableVoucherPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        slpVoucher = new javax.swing.JScrollPane();
        tblVoucher = new javax.swing.JTable();
        btnCreateVoucher = new javax.swing.JButton();
        FormVoucherUpdate = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jXStartAt1 = new org.jdesktop.swingx.JXDatePicker();
        jXEndAt1 = new org.jdesktop.swingx.JXDatePicker();
        btnUUpdate1 = new javax.swing.JButton();
        btnRemove1 = new javax.swing.JButton();
        txtSaleOf1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        slpKH2 = new javax.swing.JScrollPane();
        tblKhachHang1 = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        btnBack1 = new javax.swing.JButton();
        txtSearch1 = new javax.swing.JTextField();
        btnSearch1 = new javax.swing.JButton();
        btnResetTableKh2 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        FormVoucherPanel.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel3.setText("Giảm giá: ");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 76, 24));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel5.setText("Thời gian bắt đầu: ");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 96, -1));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel6.setText("Thời gian kết thúc: ");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 96, -1));
        jPanel1.add(jXStartAt, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 350, -1));
        jPanel1.add(jXEndAt, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 350, -1));

        btnCreate.setBackground(new java.awt.Color(51, 51, 51));
        btnCreate.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnCreate.setForeground(new java.awt.Color(255, 255, 255));
        btnCreate.setText("CREATE");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });
        jPanel1.add(btnCreate, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 352, 44));

        txtSaleOf.setBorder(null);
        jPanel1.add(txtSaleOf, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 340, 24));

        jLabel14.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel14.setText("________________________________________________________________________");
        jLabel14.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 360, 10));

        jLabel2.setBackground(new java.awt.Color(3, 155, 216));
        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("TẠO VOUCHER");
        jLabel2.setToolTipText("");
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        jLabel2.setOpaque(true);

        slpKH.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblKhachHang.setAutoCreateRowSorter(true);
        tblKhachHang.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Tên khách hàng", "Số điện thoại", "Email", "Chọn"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        slpKH.setViewportView(tblKhachHang);
        if (tblKhachHang.getColumnModel().getColumnCount() > 0) {
            tblKhachHang.getColumnModel().getColumn(0).setMinWidth(100);
            tblKhachHang.getColumnModel().getColumn(0).setPreferredWidth(10);
            tblKhachHang.getColumnModel().getColumn(0).setMaxWidth(100);
            tblKhachHang.getColumnModel().getColumn(2).setResizable(false);
            tblKhachHang.getColumnModel().getColumn(3).setResizable(false);
            tblKhachHang.getColumnModel().getColumn(4).setMinWidth(70);
            tblKhachHang.getColumnModel().getColumn(4).setPreferredWidth(5);
            tblKhachHang.getColumnModel().getColumn(4).setMaxWidth(70);
        }

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel7.setText("Chọn khách hàng:");

        btnBack.setBackground(new java.awt.Color(51, 51, 51));
        btnBack.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnBack.setForeground(new java.awt.Color(255, 255, 255));
        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });

        btnSearch.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSearch.setText("Tìm kiếm");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnResetTableKh1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/reset.png"))); // NOI18N
        btnResetTableKh1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetTableKh1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout FormVoucherPanelLayout = new javax.swing.GroupLayout(FormVoucherPanel);
        FormVoucherPanel.setLayout(FormVoucherPanelLayout);
        FormVoucherPanelLayout.setHorizontalGroup(
            FormVoucherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FormVoucherPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(FormVoucherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(FormVoucherPanelLayout.createSequentialGroup()
                        .addGroup(FormVoucherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
                            .addGroup(FormVoucherPanelLayout.createSequentialGroup()
                                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(FormVoucherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(slpKH, javax.swing.GroupLayout.PREFERRED_SIZE, 728, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(FormVoucherPanelLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnResetTableKh1)
                                .addGap(18, 18, 18)
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        FormVoucherPanelLayout.setVerticalGroup(
            FormVoucherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FormVoucherPanelLayout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(FormVoucherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch)
                    .addComponent(btnResetTableKh1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(FormVoucherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FormVoucherPanelLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(slpKH, javax.swing.GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE))
                .addContainerGap())
        );

        add(FormVoucherPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 9, 1140, 650));

        TableVoucherPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(3, 155, 216));
        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Danh sách voucher");
        jLabel1.setToolTipText("");
        jLabel1.setOpaque(true);

        tblVoucher.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Giảm giá", "Ngày bắt đầu", "Ngày kết thúc", "Ngày tạo", "Ngày cập nhật", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblVoucher.setFocusTraversalPolicyProvider(true);
        tblVoucher.setInheritsPopupMenu(true);
        tblVoucher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVoucherMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblVoucherMousePressed(evt);
            }
        });
        slpVoucher.setViewportView(tblVoucher);

        btnCreateVoucher.setBackground(new java.awt.Color(51, 51, 51));
        btnCreateVoucher.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnCreateVoucher.setForeground(new java.awt.Color(255, 255, 255));
        btnCreateVoucher.setText("Tạo voucher");
        btnCreateVoucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateVoucherActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout TableVoucherPanelLayout = new javax.swing.GroupLayout(TableVoucherPanel);
        TableVoucherPanel.setLayout(TableVoucherPanelLayout);
        TableVoucherPanelLayout.setHorizontalGroup(
            TableVoucherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TableVoucherPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TableVoucherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(slpVoucher, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1128, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TableVoucherPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCreateVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        TableVoucherPanelLayout.setVerticalGroup(
            TableVoucherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TableVoucherPanelLayout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(slpVoucher, javax.swing.GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCreateVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add(TableVoucherPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 9, 1140, 650));

        FormVoucherUpdate.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel8.setText("Giảm giá: ");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 76, 24));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel10.setText("Thời gian bắt đầu: ");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 96, -1));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel11.setText("Thời gian kết thúc: ");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 96, -1));

        jXStartAt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jXStartAt1ActionPerformed(evt);
            }
        });
        jPanel2.add(jXStartAt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 330, -1));
        jPanel2.add(jXEndAt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 330, -1));

        btnUUpdate1.setBackground(new java.awt.Color(51, 51, 51));
        btnUUpdate1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnUUpdate1.setForeground(new java.awt.Color(255, 255, 255));
        btnUUpdate1.setText("UPDATE");
        btnUUpdate1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUUpdate1ActionPerformed(evt);
            }
        });
        jPanel2.add(btnUUpdate1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 140, 44));

        btnRemove1.setBackground(new java.awt.Color(51, 51, 51));
        btnRemove1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnRemove1.setForeground(new java.awt.Color(255, 255, 255));
        btnRemove1.setText("REMOVE");
        btnRemove1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemove1ActionPerformed(evt);
            }
        });
        jPanel2.add(btnRemove1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 220, 140, 43));

        txtSaleOf1.setBorder(null);
        jPanel2.add(txtSaleOf1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 320, 24));

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel9.setText("_____________________________________________________________________");
        jLabel9.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 340, 10));

        jLabel12.setBackground(new java.awt.Color(3, 155, 216));
        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("CHỈNH SỬA VOUCHER");
        jLabel12.setToolTipText("");
        jLabel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        jLabel12.setOpaque(true);

        slpKH2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblKhachHang1.setAutoCreateRowSorter(true);
        tblKhachHang1.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        tblKhachHang1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Tên khách hàng", "Số điện thoại", "Email", "Chọn"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        slpKH2.setViewportView(tblKhachHang1);
        if (tblKhachHang1.getColumnModel().getColumnCount() > 0) {
            tblKhachHang1.getColumnModel().getColumn(0).setMinWidth(100);
            tblKhachHang1.getColumnModel().getColumn(0).setPreferredWidth(10);
            tblKhachHang1.getColumnModel().getColumn(0).setMaxWidth(100);
            tblKhachHang1.getColumnModel().getColumn(2).setResizable(false);
            tblKhachHang1.getColumnModel().getColumn(3).setResizable(false);
            tblKhachHang1.getColumnModel().getColumn(4).setMinWidth(70);
            tblKhachHang1.getColumnModel().getColumn(4).setPreferredWidth(5);
            tblKhachHang1.getColumnModel().getColumn(4).setMaxWidth(70);
        }

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel13.setText("Chọn khách hàng");

        btnBack1.setBackground(new java.awt.Color(51, 51, 51));
        btnBack1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnBack1.setForeground(new java.awt.Color(255, 255, 255));
        btnBack1.setText("Back");
        btnBack1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBack1ActionPerformed(evt);
            }
        });

        txtSearch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearch1ActionPerformed(evt);
            }
        });

        btnSearch1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSearch1.setText("Tìm kiếm");
        btnSearch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearch1ActionPerformed(evt);
            }
        });

        btnResetTableKh2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/reset.png"))); // NOI18N
        btnResetTableKh2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetTableKh2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout FormVoucherUpdateLayout = new javax.swing.GroupLayout(FormVoucherUpdate);
        FormVoucherUpdate.setLayout(FormVoucherUpdateLayout);
        FormVoucherUpdateLayout.setHorizontalGroup(
            FormVoucherUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FormVoucherUpdateLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(FormVoucherUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(FormVoucherUpdateLayout.createSequentialGroup()
                        .addGroup(FormVoucherUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
                            .addGroup(FormVoucherUpdateLayout.createSequentialGroup()
                                .addComponent(btnBack1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(FormVoucherUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(FormVoucherUpdateLayout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnResetTableKh2)
                                .addGap(18, 18, 18)
                                .addComponent(txtSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(slpKH2, javax.swing.GroupLayout.PREFERRED_SIZE, 746, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        FormVoucherUpdateLayout.setVerticalGroup(
            FormVoucherUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FormVoucherUpdateLayout.createSequentialGroup()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(FormVoucherUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(FormVoucherUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(txtSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSearch1))
                    .addComponent(btnResetTableKh2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(FormVoucherUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FormVoucherUpdateLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBack1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(slpKH2, javax.swing.GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE))
                .addContainerGap())
        );

        add(FormVoucherUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 9, 1140, 650));
    }// </editor-fold>//GEN-END:initComponents

    private void tblVoucherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVoucherMouseClicked

    }//GEN-LAST:event_tblVoucherMouseClicked

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        // tạo voucher
        if (validateForm()) {
            Voucher voucher = new Voucher(Double.valueOf(txtSaleOf.getText().trim()),
                    jXEndAt.getDate(), generateRandomId(), jXStartAt.getDate());

            if (voucherImple.insert(voucher)) {
                JOptionPane.showMessageDialog(this, "Tạo Voucher thành công");

                int rowCount = tblKhachHang.getRowCount();
                // lấy giá trị của dòng đang được tích
                int rowSelected = -1;

                for (int i = 0; i < rowCount; i++) {
                    Boolean isChecked = (Boolean) tblKhachHang.getValueAt(i, tblKhachHang.getColumnCount() - 1); // tblKhachHang.getColumnCount() -1 là chỉ số cột của checkbox trong table

                    if (isChecked != null && isChecked) {
                        // Đối tượng tại hàng i đã được chọn
                        // Bạn có thể thực hiện các thao tác cần thiết với đối tượng này ở đây
                        rowSelected = i;

                        // tạo ra voucher với những khách hàng nào được sử dụng voucher này
                        String userId = (String) tblKhachHang1.getValueAt(i, 0);
                        userVoucherResponsitory.createAll(voucher.getId(), userId);
                    }
                }
                resetForm();
                fillTableVoucher();
                TableVoucherPanel.setVisible(true);
                FormVoucherPanel.setVisible(false);
                FormVoucherUpdate.setVisible(false);
                //--------------------------------------
            } else {
                JOptionPane.showMessageDialog(this, "Tạo Voucher thất bại", "Quản lý user", JOptionPane.ERROR_MESSAGE);
            }

        }
    }//GEN-LAST:event_btnCreateActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        resetForm();
        TableVoucherPanel.setVisible(true);
        FormVoucherPanel.setVisible(false);
        FormVoucherUpdate.setVisible(false);
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnCreateVoucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateVoucherActionPerformed
        // nút chuyển trang tạo voucher
        resetForm();
        TableVoucherPanel.setVisible(false);
        FormVoucherPanel.setVisible(true);
        FormVoucherUpdate.setVisible(false);
    }//GEN-LAST:event_btnCreateVoucherActionPerformed

    private void tblVoucherMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVoucherMousePressed
        index = tblVoucher.getSelectedRow();
        if (index != -1 && evt.getClickCount() == 2) {
            index = tblVoucher.getSelectedRow();
            Voucher voucher = voucherImple.getAll().get(index);
            txtSaleOf1.setText(voucher.getSaleOf() + "");
            jXStartAt1.setDate(voucher.getStartAt());
            jXEndAt1.setDate(voucher.getEndAt());

            // khi click vào dòng này sẽ chuyển qua form chỉnh sửa của voucher
            fillTableKhachHang1(userImple.getCustomer());
            TableVoucherPanel.setVisible(false);
            FormVoucherPanel.setVisible(false);
            FormVoucherUpdate.setVisible(true);
        }

        for (int i = 0; i < tblKhachHang1.getRowCount(); i++) {
            int check = userVoucherResponsitory.getCountVoucherUser(voucherImple.getAll().get(index).getId(), userImple.getCustomer().get(i).getId());
            if (check > 0) {
                tblKhachHang1.setValueAt(true, i, tblKhachHang.getColumnCount() - 1);
            }
        }
    }//GEN-LAST:event_tblVoucherMousePressed

    private void btnUUpdate1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUUpdate1ActionPerformed
        // update voucher
        if (index != -1) {
            if (validateForm1()) {
                Voucher voucher = voucherImple.getAll().get(index);

                voucher.setSaleOf(Double.valueOf(txtSaleOf1.getText().trim()));
                voucher.setStartAt(jXStartAt1.getDate());
                voucher.setEndAt(jXEndAt1.getDate());

                if (voucherImple.update(voucher)) {

                    int rowCount = tblKhachHang1.getRowCount();
                    // lấy giá trị của dòng đang được tích
                    int rowSelected = -1;
                    userVoucherResponsitory.removeAllByVoucherId(Long.valueOf(voucher.getId()));
                    Boolean isChecked = null;
                    for (int i = 0; i < rowCount; i++) {
                        isChecked = (Boolean) tblKhachHang1.getValueAt(i, tblKhachHang1.getColumnCount() - 1); // tblKhachHang.getColumnCount() -1 là chỉ số cột của checkbox trong table
                        if (isChecked != null && isChecked) {
                            // Đối tượng tại hàng i đã được chọn
                            // Bạn có thể thực hiện các thao tác cần thiết với đối tượng này ở đây
                            rowSelected = i;

                            // tạo ra voucher với những khách hàng nào được sử dụng voucher này
//                            String userId = userImple.getCustomer().get(rowSelected).getId();
                            String userId = (String) tblKhachHang1.getValueAt(i, 0);
                            userVoucherResponsitory.createAll(voucher.getId(), userId);
                        }
                    }
                    //--------------------------------------
                    JOptionPane.showMessageDialog(this, "Update Voucher thành công");
                    resetForm();
                    fillTableVoucher();
                    TableVoucherPanel.setVisible(true);
                    FormVoucherPanel.setVisible(false);
                    FormVoucherUpdate.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(this, "Update Voucher thất bại", "Quản lý user", JOptionPane.ERROR_MESSAGE);
                }
            }

        }
    }//GEN-LAST:event_btnUUpdate1ActionPerformed

    private void btnRemove1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemove1ActionPerformed
        if (index != -1) {
            Voucher voucher = voucherImple.getAll().get(index);

            if (voucherImple.delete(voucher.getId())) {
                JOptionPane.showMessageDialog(this, "Xóa Voucher thành công");
                userVoucherResponsitory.removeAllByVoucherId(Long.valueOf(voucher.getId()));
                resetForm();
                fillTableVoucher();
                TableVoucherPanel.setVisible(true);
                FormVoucherPanel.setVisible(false);
                FormVoucherUpdate.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this, "Voucher đang được sử dụng", "Quản lý user", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn Voucher muốn xóa!", "Quản lý voucher", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnRemove1ActionPerformed

    private void btnBack1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBack1ActionPerformed
        resetForm();
        fillTableVoucher();
        TableVoucherPanel.setVisible(true);
        FormVoucherPanel.setVisible(false);
        FormVoucherUpdate.setVisible(false);
    }//GEN-LAST:event_btnBack1ActionPerformed

    private void jXStartAt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jXStartAt1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jXStartAt1ActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String text = txtSearch.getText();
        if (text != null && !text.trim().equals("")) {
            try {
                fillTableKhachHang(userRepository.getCustomersById(Integer.valueOf(text)));
                for (int i = 0; i < tblKhachHang.getRowCount(); i++) {
                    int check = userVoucherResponsitory.getCountVoucherUser(voucherImple.getAll().get(index).getId(), userRepository.getCustomersById(Integer.valueOf(text)).get(i).getId());
                    if (check > 0) {
                        tblKhachHang.setValueAt(true, i, tblKhachHang.getColumnCount() - 1);
                    }
                }
            } catch (NumberFormatException nbe) {
                fillTableKhachHang(userRepository.getCustomersByName(text));
                for (int i = 0; i < tblKhachHang.getRowCount(); i++) {
                    int check = userVoucherResponsitory.getCountVoucherUser(voucherImple.getAll().get(index).getId(), userRepository.getCustomersByName(text).get(i).getId());
                    if (check > 0) {
                        tblKhachHang.setValueAt(true, i, tblKhachHang.getColumnCount() - 1);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập gì", "Loi", 2);
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void txtSearch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearch1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearch1ActionPerformed

    private void btnSearch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearch1ActionPerformed
        String text = txtSearch1.getText();
        if (text != null && !text.trim().equals("")) {
            try {
                fillTableKhachHang1(userRepository.getCustomersById(Integer.valueOf(text)));
                for (int i = 0; i < tblKhachHang1.getRowCount(); i++) {
                    int check = userVoucherResponsitory.getCountVoucherUser(voucherImple.getAll().get(index).getId(), userRepository.getCustomersById(Integer.valueOf(text)).get(i).getId());
                    if (check > 0) {
                        tblKhachHang1.setValueAt(true, i, tblKhachHang1.getColumnCount() - 1);
                    }
                }
            } catch (NumberFormatException nbe) {
                fillTableKhachHang1(userRepository.getCustomersByName(text));
                for (int i = 0; i < tblKhachHang1.getRowCount(); i++) {
                    int check = userVoucherResponsitory.getCountVoucherUser(voucherImple.getAll().get(index).getId(), userRepository.getCustomersByName(text).get(i).getId());
                    if (check > 0) {
                        tblKhachHang1.setValueAt(true, i, tblKhachHang1.getColumnCount() - 1);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập gì", "Loi", 2);
        }
    }//GEN-LAST:event_btnSearch1ActionPerformed

    private void btnResetTableKh1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetTableKh1ActionPerformed
        fillTableKhachHang(userImple.getCustomer());
        txtSearch.setText("");
    }//GEN-LAST:event_btnResetTableKh1ActionPerformed

    private void btnResetTableKh2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetTableKh2ActionPerformed
        fillTableKhachHang1(userImple.getCustomer());
        txtSearch1.setText("");
    }//GEN-LAST:event_btnResetTableKh2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel FormVoucherPanel;
    private javax.swing.JPanel FormVoucherUpdate;
    private javax.swing.JPanel TableVoucherPanel;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnBack1;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnCreateVoucher;
    private javax.swing.JButton btnRemove1;
    private javax.swing.JButton btnResetTableKh1;
    private javax.swing.JButton btnResetTableKh2;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSearch1;
    private javax.swing.JButton btnUUpdate1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private org.jdesktop.swingx.JXDatePicker jXEndAt;
    private org.jdesktop.swingx.JXDatePicker jXEndAt1;
    private org.jdesktop.swingx.JXDatePicker jXStartAt;
    private org.jdesktop.swingx.JXDatePicker jXStartAt1;
    private javax.swing.JScrollPane slpKH;
    private javax.swing.JScrollPane slpKH2;
    private javax.swing.JScrollPane slpVoucher;
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JTable tblKhachHang1;
    private javax.swing.JTable tblVoucher;
    private javax.swing.JTextField txtSaleOf;
    private javax.swing.JTextField txtSaleOf1;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSearch1;
    // End of variables declaration//GEN-END:variables
}
