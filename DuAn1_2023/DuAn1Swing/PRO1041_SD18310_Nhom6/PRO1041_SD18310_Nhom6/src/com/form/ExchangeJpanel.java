/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.form;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import com.model.Bill;
import com.model.BillDetail;
import com.model.ExchangeBill;
import com.model.ExchangeBillDetail;
import com.service.BillDetailService;
import com.service.BillService;
import com.service.ExchangeService;
import com.service.Exchange_detailServict;
import com.service.imple.BillDetailImple;
import com.service.imple.BillImple;
import com.service.imple.Exchage_DetailImple;
import com.service.imple.ExchangeImple;

/**
 *
 * @author thiet
 */
public class ExchangeJpanel extends javax.swing.JPanel {

    /**
     * Creates new form Exchange
     */
    //sua lai giao dien, lien ket vs inter that bai
    private BillService bs = new BillImple();
    private BillDetailService bdts = new BillDetailImple();
    private ExchangeService exs = new ExchangeImple();
    private Exchange_detailServict exb = new Exchage_DetailImple();
    
    
    DefaultTableModel dtm;

    String id;
    double money_Sum;
    String idEx;
    private JFrame frame;
    public ExchangeJpanel(JFrame f) {
        initComponents();
        frame = f;
        this.loadBill();
        this.loadBillDetail();
        this.tableExchangeBill();
    }

    public void loadBill() {
        dtm = (DefaultTableModel) this.tblBill.getModel();
        dtm.setRowCount(0);
        for (Bill b : this.bs.getAll()) {
            Object[] ob = {
                b.getId(),
                b.getUserId().getFullName(),
                b.getUserId().getNumberPhone(),
                b.getIntoMoney(),
                b.getTotalCost(),
                b.getAddressId().getAddressDetail(),
                b.getCreatedAt(),
                b.getDeliveryDate(),
                b.getUpdatedAt(),
                b.getVoucherId().getId(),
                b.getStatus()
            };
            dtm.addRow(ob);
        }
    }

    public void loadBillDetail() {
        dtm = (DefaultTableModel) this.tblBillCT.getModel();
        dtm.setRowCount(0);
        for (BillDetail bdt : this.bdts.getbill_all(id)) {
            Object[] ob = {
                bdt.getProductDetailId().getProductId().getName_product(),
                bdt.getProductDetailId().getProductId().getCustome_id().getNameCustom(),
                bdt.getProductDetailId().getProductId().getMaterial_id().getNameMaterial(),
                bdt.getProductDetailId().getProductId().getThickness_id().getGsm(),
                bdt.getProductDetailId().getColorId().getNameColor(),
                bdt.getProductDetailId().getSizeId().getNameSize(),
                bdt.getPriceNow(),
                bdt.getQuantityPurchased(),
                bdt.getCreatedAt(),
                bdt.getUpdatedAt(),
                bdt.getProductDetailId().getProductId().getProduct_price()
            };
            dtm.addRow(ob);
        }
    }

    public void minus_Quantity() {
        int row = tblBillCT.getSelectedRow();
        if (row < 0) {
            return;
        } else {
            int quantity_product = Integer.parseInt(tblBillCT.getValueAt(row, 7).toString());
            System.out.println(quantity_product);
            double monney_Product = Double.parseDouble(tblBillCT.getValueAt(row, 10).toString());
            int quantity = Integer.parseInt(JOptionPane.showInputDialog(this, "Moi ban nhap"));
            if (quantity > 0) {
                System.out.println(quantity);
                try {
                    int resultQuantity = quantity_product - quantity;
                    money_Sum += quantity * monney_Product;
                    dtm.setValueAt(resultQuantity, row, 7);
                 System.out.println("tong tien la" + money_Sum);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return;
            } else {
                System.out.println("no");
                return;
            }
        }
    }
    
    //26/11
    
     public void loadExchangeBillDetail(){
        dtm = (DefaultTableModel) this.tblExChangeBillDetail.getModel();
        dtm.setRowCount(0);
        for (ExchangeBillDetail e : exb.getExchangeBillDetail(idEx)) {
            dtm.addRow(new Object[]{
                e.getId(),
                e.getExchangeBillId().getId(),
                e.getProductDetailId().getProductId().getName_product(),
                e.getProductDetailId().getProductId().getCustome_id().getNameCustom(),
                e.getProductDetailId().getColorId().getNameColor(),
                e.getProductDetailId().getProductId().getThickness_id().getGsm(),
                e.getProductDetailId().getProductId().getMaterial_id().getNameMaterial(),
                e.getProductDetailId().getSizeId().getNameSize(),
                e.getCreatedAt(),
                e.getUpdatedAt(),
                e.getQuantityOfProductsReturned()
            });
        }
    }
     
     public void tableExchangeBill(){
        dtm = (DefaultTableModel) tblEchangeBill.getModel();
        dtm.setRowCount(0);
        for (ExchangeBill e : exs.getExchangeBills()) {
            dtm.addRow(new Object[]{
                e.getId(),
                e.getBillId().getId(),
                e.getCreatedAt(),
                e.getUpdatedAt(),
                e.getDescribeReason()
            });
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

        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txttim = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBill = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblBillCT = new javax.swing.JTable();
        btnDoihang = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblEchangeBill = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblExChangeBillDetail = new javax.swing.JTable();

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel2.setText("Tìm kiếm");

        tblBill.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Customer name", "Number_Phone", "Into_Money", "Total_Money", "Addres_Detall", "Created_at", "Delivery_Date", "Updated_at", "Voucher", "Status"
            }
        ));
        tblBill.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBillMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblBillMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblBill);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txttim, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(596, 596, 596)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txttim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sản phẩm đổi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        tblBillCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name_Product", "Custom", "Material", "gsm", "Coler", "Size", "Price_now", "Quantity_urchased", "Created_at", "Updated_at", "price(Present)"
            }
        ));
        tblBillCT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBillCTMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblBillCT);

        btnDoihang.setText("Đổi hàng");
        btnDoihang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoihangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2)
                .addGap(18, 18, 18)
                .addComponent(btnDoihang, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDoihang))
                .addGap(0, 18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
        );

        jTabbedPane2.addTab("Đổi Hàng", jPanel1);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Hóa đơn đổi hàng"));

        tblEchangeBill.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "bill_id", "created_at", "updated_at", "nulldescribe_reason"
            }
        ));
        tblEchangeBill.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEchangeBillMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblEchangeBill);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 972, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Hóa đơn sản phẩm chi tiết"));

        tblExChangeBillDetail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Id_Exchange_Bill", "name_product", "name_custom", "name_color", "gsm", "name_material", "name_size", "created_at", "updated_at", "quantity_of_products_returned"
            }
        ));
        jScrollPane3.setViewportView(tblExChangeBillDetail);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(224, 224, 224))
        );

        jTabbedPane2.addTab("Hóa đơn đổi", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 678, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblBillMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBillMousePressed

    }//GEN-LAST:event_tblBillMousePressed

    private void tblBillMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBillMouseClicked
        // TODO add your handling code here:
        int row = tblBill.getSelectedRow();
        id = tblBill.getValueAt(row, 0).toString();
        System.out.println(id);
        this.loadBillDetail();
    }//GEN-LAST:event_tblBillMouseClicked

    private void tblBillCTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBillCTMouseClicked
        // TODO add your handling code here:
//         int row = tblBill.getSelectedRow();
        this.minus_Quantity();
//        this.loadBillDetail();
    }//GEN-LAST:event_tblBillCTMouseClicked

    private void btnDoihangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoihangActionPerformed
        // TODO add your handling code here:
        ExchangeJDialog ex = new ExchangeJDialog(frame, true);
        int row = tblBill.getSelectedRow();
        id = tblBill.getValueAt(row, 0).toString();
        ex.setMoney(money_Sum);
        ex.setIDbill(id);
        ex.setVisible(true);
        
    }//GEN-LAST:event_btnDoihangActionPerformed

    private void tblEchangeBillMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEchangeBillMouseClicked
        int row =tblEchangeBill.getSelectedRow();
        idEx = tblEchangeBill.getValueAt(row, 0).toString();
        this.loadExchangeBillDetail();
    }//GEN-LAST:event_tblEchangeBillMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDoihang;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable tblBill;
    private javax.swing.JTable tblBillCT;
    private javax.swing.JTable tblEchangeBill;
    private javax.swing.JTable tblExChangeBillDetail;
    private javax.swing.JTextField txttim;
    // End of variables declaration//GEN-END:variables
}
