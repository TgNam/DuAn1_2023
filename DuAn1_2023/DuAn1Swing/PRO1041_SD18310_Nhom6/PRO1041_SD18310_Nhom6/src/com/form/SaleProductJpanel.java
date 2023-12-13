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
import com.model.Product;
import com.model.SaleProduct;
import com.service.ProductService;
import com.service.SaleProductService;
import com.service.imple.ProductImple;
import com.service.imple.SaleProductImple;
import com.swing.EditButtons;
import com.swing.EditTextField;
import table.TableCustom;

/**
 *
 * @author TgNam
 */
public class SaleProductJpanel extends javax.swing.JPanel {

    private DefaultTableModel tableModel;
    private ProductService Ps = new ProductImple();
    private SaleProductService Sps = new SaleProductImple();
    private Validate vl = new Validate();
    private Date nowDate = null;

    //them vao 7/12
    private EditButtons bt = new EditButtons();
    private EditTextField txt = new EditTextField();

    public SaleProductJpanel() {
        initComponents();
        datarowProcuct();
        datarowSaleProcuct();

        //them vao 7/12
        TableCustom.apply(slpKhuyenMai, TableCustom.TableType.MULTI_LINE);
        TableCustom.apply(slpSanPham, TableCustom.TableType.MULTI_LINE);
        
        bt.Edit(bthluu);
        bt.Edit(bthmoi);
        bt.EditEmployee_and_client(bthcapnhat);
        bt.EditEmployee_and_client(bthxoa);
        bt.EditEmployee_and_client(btnThemAll);
        
    }

    //đổ đữ liệu cho bảng sản phẩm
    public void datarowProcuct() {
        tableModel = (DefaultTableModel) tblsanpham.getModel();
        tableModel.setRowCount(0);
        int index = 1;
        for (Product product : Ps.getList_sale()) {
            tableModel.addRow(new Object[]{index++, product.getId(), product.getName_product(), product.getProduct_price(), product.getSale_id().getId(), product.getSale_id().getSale() + " %"});
        }
    }

    //đổ đữ liệu cho bảng SaleProduct
    public void datarowSaleProcuct() {
        tableModel = (DefaultTableModel) tblkhuyenmai.getModel();
        tableModel.setRowCount(0);
        int index = 1;
        for (SaleProduct saleProduct : Sps.getList()) {
            tableModel.addRow(new Object[]{saleProduct.getId(), saleProduct.getStart_at(), saleProduct.getEnd_at(), saleProduct.getSale() + " %", saleProduct.checkTrangThai()});
        }
    }

    //đổ dữ liệu lên form 
    public void filltableKM(int index) {
        SaleProduct ps = Sps.getList().get(index);
        txtsale.setText(String.valueOf(ps.getSale()));
        txttgbd.setText(String.valueOf(ps.getStart_at()));
        txttgkt.setText(String.valueOf(ps.getEnd_at()));
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

    //check ngày kết thúc
    public Date checkDateEndAt() {
        Date date;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String endAt = txttgkt.getText();
            if (!vl.isCheckDate(endAt)) {
                JOptionPane.showMessageDialog(this, "Định dạng ngày không hợp lệ hoặc năm sinh không hợp lệ của ô ngày kết thúc!");
                return null;
            }
            date = sdf.parse(endAt);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng ngày yyyy-MM-dd");
            return null;
        }
    }

    // check ngày bắt đầu
    public Date checkDateStartAt() {
        Date date;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String startAt = txttgbd.getText();
            if (!vl.isCheckDate(startAt)) {
                JOptionPane.showMessageDialog(this, "Định dạng ngày không hợp lệ hoặc năm sinh không hợp lệ của ô ngày bắt đầu!");
                return null;
            }
            date = sdf.parse(startAt);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng ngày yyyy-MM-dd");
            return null;
        }
    }

    public SaleProduct check() {
        SaleProduct nv = null;
        String sale = txtsale.getText();
        Date StartAt = checkDateStartAt();
        Date EndAt = checkDateEndAt();
        String status = rdohd.isSelected() == true ? "1" : "0";
        nowDate = getCurrentDateTime();
        try {
            if (sale.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng không được để trống!");
                return null;
            } else if (Double.parseDouble(sale) > Double.parseDouble("100")) {
                JOptionPane.showMessageDialog(this, "Vui lòng % giảm giá <100!");
                return null;
            } else {
                return new SaleProduct(null, nowDate, nowDate, StartAt, EndAt, Double.parseDouble(sale), status);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Vui Lòng nhập lại!");
            return null;
        }
    }

    public void reset() {
        txtsale.setText("");
        txttgbd.setText("");
        txttgkt.setText("");
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
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        slpSanPham = new javax.swing.JScrollPane();
        tblsanpham = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        slpKhuyenMai = new javax.swing.JScrollPane();
        tblkhuyenmai = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        bthluu = new javax.swing.JButton();
        bthcapnhat = new javax.swing.JButton();
        bthxoa = new javax.swing.JButton();
        bthmoi = new javax.swing.JButton();
        btnThemAll = new javax.swing.JButton();
        txtsale = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txttgbd = new javax.swing.JTextField();
        txttgkt = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        rdohd = new com.swing.RadioButtonCustom();
        rdonhd = new com.swing.RadioButtonCustom();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tblsanpham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã SP", "TênSP", "Giá", "Mã Sale", "MaKM", "Trạng Thái"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        slpSanPham.setViewportView(tblsanpham);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setText("Danh sách sẩn phẩm");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(slpSanPham)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(slpSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        tblkhuyenmai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Ngày bắt đầu", "Ngày kết thúc", "Giảm giá %", "Trạng thái"
            }
        ));
        tblkhuyenmai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblkhuyenmaiMouseClicked(evt);
            }
        });
        slpKhuyenMai.setViewportView(tblkhuyenmai);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setText("Danh sách khuyến mại");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(slpKhuyenMai, javax.swing.GroupLayout.DEFAULT_SIZE, 771, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(slpKhuyenMai, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setText("Khuyến mại");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, -1));

        jLabel4.setText("Mức Giảm Giá:");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        jLabel5.setText("TG bắt đầu:");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        jLabel6.setText("TG kết thúc:");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, -1));

        jLabel7.setText("Trạng Thái:");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, -1, -1));

        bthluu.setText("Lưu");
        bthluu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthluuActionPerformed(evt);
            }
        });
        jPanel4.add(bthluu, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 310, 60));

        bthcapnhat.setText("Cập nhật");
        bthcapnhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthcapnhatActionPerformed(evt);
            }
        });
        jPanel4.add(bthcapnhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 110, 90));

        bthxoa.setText("Xóa");
        bthxoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthxoaActionPerformed(evt);
            }
        });
        jPanel4.add(bthxoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 380, 110, 90));

        bthmoi.setText("Mới");
        bthmoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthmoiActionPerformed(evt);
            }
        });
        jPanel4.add(bthmoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, 310, 60));

        btnThemAll.setText("Thêm tất cả sản phẩm");
        btnThemAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemAllActionPerformed(evt);
            }
        });
        jPanel4.add(btnThemAll, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 570, 310, 62));

        txtsale.setBorder(null);
        jPanel4.add(txtsale, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 300, 24));

        jLabel18.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel18.setText("______________________________________________________________");
        jLabel18.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel4.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 310, 10));

        txttgbd.setBorder(null);
        jPanel4.add(txttgbd, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 300, 24));

        txttgkt.setBorder(null);
        jPanel4.add(txttgkt, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 300, 24));

        jLabel21.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel21.setText("______________________________________________________________");
        jLabel21.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel4.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 310, 10));

        jLabel22.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel22.setText("______________________________________________________________");
        jLabel22.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel4.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 310, 10));

        buttonGroup1.add(rdohd);
        rdohd.setSelected(true);
        rdohd.setText("Đang hoạt động");
        jPanel4.add(rdohd, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, -1, -1));

        buttonGroup1.add(rdonhd);
        rdonhd.setText("Ngừng hoạt động");
        jPanel4.add(rdonhd, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 250, -1, -1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblkhuyenmaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblkhuyenmaiMouseClicked
        int index = tblkhuyenmai.getSelectedRow();
        filltableKM(index);
    }//GEN-LAST:event_tblkhuyenmaiMouseClicked

    private void bthluuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthluuActionPerformed
        DefaultTableModel model = (DefaultTableModel) tblsanpham.getModel();
        boolean checksp = false;
        //thêm saleProduct
        SaleProduct sp = check();
        Sps.add(sp);
        int rowCount = model.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            Boolean isChecked = (Boolean) model.getValueAt(i, 6); // 5 là chỉ số cột của checkbox trong table

            if (isChecked != null && isChecked) {
                // Đối tượng tại hàng i đã được chọn
                // Bạn có thể thực hiện các thao tác cần thiết với đối tượng này ở đây
                Product p = Ps.getList_sale().get(i);
                Ps.updateSale_ID_created_at(nowDate, p.getId());
                checksp = true;
            }
        }
        if (checksp == true && sp != null) {
            JOptionPane.showMessageDialog(this, "Bạn đã thêm đợt giảm giá thành công");
        } else {
            Sps.delete_ID_created_at(nowDate);
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn sản phẩm " + "\n" + "Bạn đã thêm đợt giảm giá thất bại");
        }
        reset();
        datarowProcuct();
        datarowSaleProcuct();
    }//GEN-LAST:event_bthluuActionPerformed

    private void bthcapnhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthcapnhatActionPerformed
        int index = tblkhuyenmai.getSelectedRow();
        if (index >= 0) {
            SaleProduct sp = Sps.getList().get(index);
            JOptionPane.showMessageDialog(this, Sps.update(check(), sp.getId()));
        } else {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn đợt khuyến mãi cần sửa");
        }
        reset();
        datarowProcuct();
        datarowSaleProcuct();
    }//GEN-LAST:event_bthcapnhatActionPerformed

    private void bthxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthxoaActionPerformed
        int index = tblkhuyenmai.getSelectedRow();
        if (!(index < 0)) {
            int hoi = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa không?");
            if (hoi != JOptionPane.YES_NO_OPTION) {
                JOptionPane.showMessageDialog(this, "Bạn đã không xóa khuyến mãi");
                return;
            }
            SaleProduct sp = Sps.getList().get(index);
            Ps.updateSale_ID(sp.getId());
            JOptionPane.showMessageDialog(this, Sps.delete_ID(sp.getId()));
        } else {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn đợt khuyến mãi cần xóa");
        }
        reset();
        datarowProcuct();
        datarowSaleProcuct();
    }//GEN-LAST:event_bthxoaActionPerformed

    private void bthmoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthmoiActionPerformed
        reset();
    }//GEN-LAST:event_bthmoiActionPerformed

    private void btnThemAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemAllActionPerformed
        DefaultTableModel model = (DefaultTableModel) tblsanpham.getModel();
        int rowCount = model.getRowCount();
        SaleProduct sp = check();
        if (sp != null) {
            JOptionPane.showMessageDialog(this, Sps.add(sp));
            for (int i = 0; i < rowCount; i++) {
                Product p = Ps.getList_sale().get(i);
                Ps.updateSale_ID_created_at(nowDate, p.getId());
            }
            JOptionPane.showMessageDialog(this, "Thêm tất cả thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Thêm tất cả thát bại");
        }
        reset();
        datarowProcuct();
        datarowSaleProcuct();
    }//GEN-LAST:event_btnThemAllActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bthcapnhat;
    private javax.swing.JButton bthluu;
    private javax.swing.JButton bthmoi;
    private javax.swing.JButton bthxoa;
    private javax.swing.JButton btnThemAll;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private com.swing.RadioButtonCustom rdohd;
    private com.swing.RadioButtonCustom rdonhd;
    private javax.swing.JScrollPane slpKhuyenMai;
    private javax.swing.JScrollPane slpSanPham;
    private javax.swing.JTable tblkhuyenmai;
    private javax.swing.JTable tblsanpham;
    private javax.swing.JTextField txtsale;
    private javax.swing.JTextField txttgbd;
    private javax.swing.JTextField txttgkt;
    // End of variables declaration//GEN-END:variables
}
