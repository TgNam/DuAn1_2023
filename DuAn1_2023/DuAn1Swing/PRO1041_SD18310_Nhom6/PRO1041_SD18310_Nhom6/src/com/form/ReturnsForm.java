/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import com.model.Bill;
import com.model.BillDetail;
import com.model.ReturnBill;
import com.model.ReturnBillDetail;
import com.service.imple.BillDetailImple;
import com.service.imple.BillImple;
import com.service.imple.ReturnBillDetailImple;
import com.service.imple.ReturnBillImple;
import com.swing.EditButtons;
import com.swing.EditTextField;
import javax.swing.JPanel;
import table.TableCustom;

/**
 *
 * @author lenovo
 */
public class ReturnsForm extends javax.swing.JDialog {

    private int stt = 0;
    private List<BillDetail> billDetails = new ArrayList<>();
    private Bill bill = null;
    private BillImple billImple = new BillImple();
    private BillDetailImple billDetailImple = new BillDetailImple();
    InvoiceManagementJPanel  rootPanel ;

    public static InvoiceManagementJPanel invoid = null;
    private Long idBill;

     //them vao 9/12
    private EditButtons bt = new EditButtons();
    private EditTextField txt = new EditTextField();
    
    public void setIdBill(Long idBill) {
        this.idBill = idBill;
    }

    public void setBillDetails(List<BillDetail> billDetails) {
        this.billDetails = billDetails;
    }

    public ReturnsForm(java.awt.Frame parent, boolean modal, InvoiceManagementJPanel root) {
        super(parent, modal);
        initComponents();
        this.rootPanel = root;
        //them vao 12/9
        bt.Edit(btnHuy);
        bt.Edit(btnXacNhan);
        
        txt.edit(txtMaKH);
        txt.edit(txtTenKH);
        txt.edit(txtHoanTra);
        txt.edit(txtDaTT);
        
        TableCustom.apply(slpProductReturnBill, TableCustom.TableType.MULTI_LINE);
    }

    public void loadTableProductReturn() {
        DefaultTableModel tableModel = (DefaultTableModel) tblProductReturn.getModel();
        tableModel.setRowCount(0);
        stt = 1;
        for (BillDetail bdt : billDetails) {
            Object[] ob = {
                stt++,
                bdt.getBillId().getId(),
                bdt.getProductDetailId().getProductId().getName_product(),
                bdt.getQuantityPurchased(),
                bdt.getProductDetailId().getColorId().getNameColor(),
                bdt.getProductDetailId().getSizeId().getNameSize(),
                bdt.getPriceNow()
            };
            tableModel.addRow(ob);
        }
    }

    public void reset() {
        txtDaTT.setText("");
        txtHoanTra.setText("");
        cboLyDo.setSelectedIndex(0);
        txtMaKH.setText("");
        txtTenKH.setText("");
    }

    public void loadForm() {
        bill = billImple.getById(idBill);
        txtMaKH.setText(bill.getId());
        txtTenKH.setText(bill.getUserId().getFullName());
        txtDaTT.setText(bill.getIntoMoney() + "");
    }
    public void hidden(){
        this.setVisible(false);
        rootPanel.datarowBill("3","3");
        rootPanel.loadBillDetail("0");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelTraHang = new javax.swing.JPanel();
        slpProductReturnBill = new javax.swing.JScrollPane();
        tblProductReturn = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnHuy = new javax.swing.JButton();
        btnXacNhan = new javax.swing.JButton();
        txtHoanTra = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        cboLyDo = new com.swing.Combobox();
        txtMaKH = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtDaTT = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("ĐỔI HÀNG");
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        panelTraHang.setBackground(new java.awt.Color(255, 255, 255));
        panelTraHang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblProductReturn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tblProductReturn.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        tblProductReturn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã hóa đơn", "Tên sản phẩm", "Số  lượng ", "Màu ", "Size", "Đơn giá", "Nhập số lượng trả"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProductReturn.setRowHeight(25);
        tblProductReturn.setSelectionBackground(new java.awt.Color(102, 102, 102));
        tblProductReturn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductReturnMouseClicked(evt);
            }
        });
        slpProductReturnBill.setViewportView(tblProductReturn);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Danh sách sản phẩm muốn trả");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("Mã khách hàng:");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("Tên khách hàng: ");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setText("Đã thanh toán: ");
        jLabel5.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
                jLabel5AncestorMoved(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel7.setText("Hoàn trả:");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel9.setText("Lý do: ");

        btnHuy.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnHuy.setText("HỦY");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        btnXacNhan.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnXacNhan.setText("XÁC NHẬN TRẢ HÀNG");
        btnXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhanActionPerformed(evt);
            }
        });

        txtHoanTra.setBorder(null);

        jLabel12.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel12.setText("_____________________________________________");
        jLabel12.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        cboLyDo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Kích cỡ không phù hợp", "Màu sắc không đúng", "Chất liệu không tốt", "Sản phẩm bị hỏng", "Sự không hài lòng với chất lượng", "Thay đổi ý kiến", "Sai sót trong đặt hàng", "Phong cách không phù hợp", "Vấn đề về bảo hành" }));

        txtMaKH.setBorder(null);

        jLabel13.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel13.setText("_____________________________________________");
        jLabel13.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        txtTenKH.setBorder(null);

        jLabel14.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel14.setText("_____________________________________________");
        jLabel14.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        txtDaTT.setBorder(null);

        jLabel15.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel15.setText("_____________________________________________");
        jLabel15.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout panelTraHangLayout = new javax.swing.GroupLayout(panelTraHang);
        panelTraHang.setLayout(panelTraHangLayout);
        panelTraHangLayout.setHorizontalGroup(
            panelTraHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTraHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTraHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(slpProductReturnBill, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelTraHangLayout.createSequentialGroup()
                        .addGap(227, 227, 227)
                        .addGroup(panelTraHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTraHangLayout.createSequentialGroup()
                                .addGroup(panelTraHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panelTraHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtHoanTra, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(177, 177, 177))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTraHangLayout.createSequentialGroup()
                                .addGroup(panelTraHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDaTT, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panelTraHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboLyDo, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(231, 231, 231))
                            .addGroup(panelTraHangLayout.createSequentialGroup()
                                .addGroup(panelTraHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnXacNhan, javax.swing.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
                                    .addComponent(btnHuy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
            .addGroup(panelTraHangLayout.createSequentialGroup()
                .addGap(146, 146, 146)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 681, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(158, Short.MAX_VALUE))
        );
        panelTraHangLayout.setVerticalGroup(
            panelTraHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTraHangLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(slpProductReturnBill, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelTraHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTraHangLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(8, 8, 8)
                        .addGroup(panelTraHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelTraHangLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addGap(8, 8, 8)
                        .addGroup(panelTraHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelTraHangLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5))
                    .addGroup(panelTraHangLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(8, 8, 8)
                        .addGroup(panelTraHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtHoanTra, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelTraHangLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboLyDo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTraHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDaTT, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelTraHangLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(btnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(69, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 987, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelTraHang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 732, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelTraHang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tblProductReturnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductReturnMouseClicked
        int rowCount = tblProductReturn.getRowCount();
        bill = billImple.getById(idBill);

        List<BillDetail> billDetails = billDetailImple.getbill_all(bill.getId());
        Integer soLuongInput = 0;
        Integer soLuongGoc = 0;
        BigDecimal sumMoney = BigDecimal.ZERO;

        for (int i = 0; i < rowCount; i++) {
            if (tblProductReturn.getValueAt(i, 7) != null && tblProductReturn.getValueAt(i, 7) != "") {
                try {
                    soLuongInput = Integer.parseInt(tblProductReturn.getValueAt(i, 7).toString());
                    if (soLuongInput < 0) {
                        JOptionPane.showMessageDialog(this, "Số lượng không thể âm", "Lỗi", 2);
                        return;
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập số vào số lượng", "Lỗi", 2);
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Lỗi hệ thống", "Lỗi", 2);
                    return;
                }

                try {
                    soLuongGoc = Integer.parseInt(tblProductReturn.getValueAt(i, 3).toString());
                    if (soLuongInput > soLuongGoc) {
                        JOptionPane.showMessageDialog(this, "Số lượng muốn trả không phù hợp", "Lỗi", 2);
                        return;
                    }

                    BigDecimal productPrice = new BigDecimal(tblProductReturn.getValueAt(i, 6).toString());
                    BigDecimal productTotal = BigDecimal.valueOf(soLuongInput).multiply(productPrice);
                    sumMoney = sumMoney.add(productTotal);
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Lỗi hệ thống", "Lỗi", 2);
                    return;
                }
            }
            Double saleOf = 0.0;
            if (bill.getVoucherId() != null) {
                saleOf = bill.getVoucherId().getSaleOf(); // Giả sử saleOf là một giá trị double
            }
            BigDecimal discount = BigDecimal.valueOf(1).subtract(BigDecimal.valueOf(saleOf).divide(BigDecimal.valueOf(100)));
            BigDecimal tienTra = sumMoney.multiply(discount);
            txtHoanTra.setText(tienTra.toString());
        }

    }//GEN-LAST:event_tblProductReturnMouseClicked

    private void jLabel5AncestorMoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jLabel5AncestorMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel5AncestorMoved

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        this.dispose();
        this.setVisible(false);

    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanActionPerformed
        if (txtHoanTra.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa nhập số lượng", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (Double.valueOf(txtHoanTra.getText().trim()) <= 0) {
            JOptionPane.showMessageDialog(this, "Chưa nhập số lượng", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int rowCount = tblProductReturn.getRowCount();
        bill = billImple.getById(idBill);

        List<BillDetail> billDetails = billDetailImple.getbill_all(bill.getId());
        Integer soLuongInput = 0;
        Integer soLuongGoc = 0;
        BigDecimal sumMoney = BigDecimal.ZERO;

        for (int i = 0; i < rowCount; i++) {
            if (tblProductReturn.getValueAt(i, 7) != null && tblProductReturn.getValueAt(i, 7) != "") {
                try {
                    soLuongInput = Integer.parseInt(tblProductReturn.getValueAt(i, 7).toString());
                    if (soLuongInput < 0) {
                        JOptionPane.showMessageDialog(this, "Số lượng không thể âm", "Lỗi", 2);
                        return;
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập số vào số lượng", "Lỗi", 2);
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Lỗi hệ thống", "Lỗi", 2);
                    return;
                }

                try {
                    soLuongGoc = Integer.parseInt(tblProductReturn.getValueAt(i, 3).toString());
                    if (soLuongInput > soLuongGoc) {
                        JOptionPane.showMessageDialog(this, "Số lượng muốn trả không phù hợp", "Lỗi", 2);
                        return;
                    }

                    BigDecimal productPrice = new BigDecimal(tblProductReturn.getValueAt(i, 6).toString());
                    BigDecimal productTotal = BigDecimal.valueOf(soLuongInput).multiply(productPrice);
                    sumMoney = sumMoney.add(productTotal);
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Lỗi hệ thống", "Lỗi", 2);
                    return;
                }
            }
        }
        ReturnBillDetailImple returnBillDetailImple = new ReturnBillDetailImple();
        ReturnBillDetail returnBillDetail = null;
        ReturnBill returnBill = new ReturnBill(new BigDecimal(txtHoanTra.getText()), billImple.getById(Long.valueOf(bill.getId())), cboLyDo.getSelectedItem().toString());

        // nếu trả hàng thành công thì sẽ tạo ra các hóa đơn trả hàng chi tiết và chứa cá sản phẩm chi tiết
        int soLuongTra = 0;
        if (new ReturnBillImple().insert(returnBill)) {
            for (int i = 0; i < tblProductReturn.getRowCount(); i++) {
                String value = tblProductReturn.getValueAt(i, tblProductReturn.getColumnCount() - 1).toString();
                soLuongTra = Integer.parseInt(value);
                returnBillDetail = new ReturnBillDetail(billDetails.get(i).getPriceNow(), soLuongTra, billDetails.get(i).getProductDetailId(), new ReturnBillImple().getByIdBill(String.valueOf(bill.getId())), "4");
                new ReturnBillDetailImple().insert(returnBillDetail);
                // thay đổi trạng thái hóa đơn
                billImple.updateStatusById(bill.getId(), 4);
            }
            JOptionPane.showMessageDialog(this, "Gửi yêu cầu trả hàng thành công", "Trả hàng", 1);
            hidden();
            
            
        } else {
            JOptionPane.showMessageDialog(this, "Gửi yêu cầu trả hàng thất bại do lỗi hệ thống", "Trả hàng", 0);
        }
    }//GEN-LAST:event_btnXacNhanActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        loadTableProductReturn();
        loadForm();
    }//GEN-LAST:event_formWindowOpened

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ReturnsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReturnsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReturnsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReturnsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ReturnsForm dialog = new ReturnsForm(new javax.swing.JFrame(), true, null);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnXacNhan;
    private com.swing.Combobox cboLyDo;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel panelTraHang;
    private javax.swing.JScrollPane slpProductReturnBill;
    private javax.swing.JTable tblProductReturn;
    private javax.swing.JTextField txtDaTT;
    private javax.swing.JTextField txtHoanTra;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtTenKH;
    // End of variables declaration//GEN-END:variables
}
