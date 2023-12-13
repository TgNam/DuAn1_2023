/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.form;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import com.model.Bill;
import com.model.Color;
import com.model.Custom;
import com.model.ExchangeBill;
import com.model.ExchangeBillDetail;
import com.model.Material;
import com.model.ProductDetail;
import com.model.Size;
import com.model.Thickness;
import com.repository.ExchangeRepository;
import com.repository.Exchange_DetailRepository;
import com.repository.ProductDetailRepository;
import com.service.ExchangeService;
import com.service.Exchange_detailServict;
import com.service.ProductDetailService;
import com.service.imple.Exchage_DetailImple;
import com.service.imple.ExchangeImple;
import com.service.imple.ProductDetailImple;
import com.swing.EditButtons;
import com.swing.EditTextField;
import table.TableCustom;

/**
 *
 * @author thiet
 */
public class ExchangeJDialog extends javax.swing.JDialog {

    /**
     * Creates new form ExchangeJDialog
     */
    private ProductDetailService pdds;
    private ExchangeService exs;
    private Exchange_detailServict exds;

    ArrayList<ProductDetail> List;

    private DefaultTableModel dtm;
    private DefaultTableModel dtmPR;
    double sum_Money;

    int index;
    String idBill;
    Timestamp created_at;
    double sum;

    //them cai nay 2/12
    EditButtons bt = new EditButtons();
    EditTextField txt = new EditTextField();

    public ExchangeJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        pdds = new ProductDetailImple();
        exs = new ExchangeImple();
        exds = new Exchage_DetailImple();
        List = new ArrayList<>();
        System.out.println(idBill);
        System.out.println(lblMoney_Sum.toString());
        this.loadProduct_Detail();

        //them 4/12
        TableCustom.apply(slpPr_Detail, TableCustom.TableType.MULTI_LINE);
        TableCustom.apply(slpPr_Ex, TableCustom.TableType.MULTI_LINE);

        bt.Edit(btnxoa);
        bt.Edit(btnHoanThanh);

    }

    public void setMoney(double money) {
        lblMoney_Sum.setText(String.valueOf(money));
        sum_Money = money;
    }

    public void setIDbill(String id) {
        idBill = id;
    }

    public void loadProduct_Detail() {
        dtm = (DefaultTableModel) this.tblProduct_Detail.getModel();
        dtm.setRowCount(0);
        for (ProductDetail pdt : this.pdds.getproduct_Exchange()) {
            Object[] ob = {
                pdt.getId(),
                pdt.getProductId().getName_product(),
                pdt.getProductId().getCustome_id().getNameCustom(),
                pdt.getProductId().getMaterial_id().getNameMaterial(),
                pdt.getProductId().getThickness_id().getGsm(),
                pdt.getColorId().getNameColor(),
                pdt.getSizeId().getNameSize(),
                pdt.getProductId().getProduct_price(),
                pdt.getQuantity()
            };
            dtm.addRow(ob);
        }
    }

    public void loadProduct_Ex() {
        dtmPR = (DefaultTableModel) this.tblProduct_Ex.getModel();
        dtmPR.setRowCount(0);
        for (ProductDetail pdt : this.List) {
            Object[] ob = {
                pdt.getId(),
                pdt.getProductId().getName_product(),
                pdt.getProductId().getCustome_id().getNameCustom(),
                pdt.getProductId().getMaterial_id().getNameMaterial(),
                pdt.getProductId().getThickness_id().getGsm(),
                pdt.getColorId().getNameColor(),
                pdt.getSizeId().getNameSize(),
                pdt.getProductId().getProduct_price(),
                pdt.getQuantity()
            };
            dtmPR.addRow(ob);
        }
    }

    private double calculateTotalSum(DefaultTableModel model) {
        double totalSum = 0;
        for (int i = 0; i < model.getRowCount(); i++) {
            int quantity = Integer.parseInt(model.getValueAt(i, 8).toString());
            double price = Double.parseDouble(model.getValueAt(i, 7).toString());
            totalSum += quantity * price;
        }
        return totalSum;
    }

    //them cai nay ngay 28//11
    private int findRowInTable_Product_Detail(String idPr) {
        for (int i = 0; i < tblProduct_Detail.getRowCount(); i++) {
            if (idPr.trim().equals(tblProduct_Detail.getValueAt(i, 0).toString().trim())) {
                return i;
            }
        }
        return -1;
    }

    private int findRowInTable_Product_Ex(String idEX) {
        for (int i = 0; i < tblProduct_Ex.getRowCount(); i++) {
            if (tblProduct_Ex.getValueAt(i, 0) == null) {
                return -1;
            } else if (idEX.trim().equals(tblProduct_Ex.getValueAt(i, 0).toString().trim())) {
                return i;
            }
        }
        return -1;

    }

//    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        slpPr_Detail = new javax.swing.JScrollPane();
        tblProduct_Detail = new javax.swing.JTable();
        slpPr_Ex = new javax.swing.JScrollPane();
        tblProduct_Ex = new javax.swing.JTable();
        btnHoanThanh = new javax.swing.JButton();
        btnxoa = new javax.swing.JButton();
        cbbReson = new com.swing.Combobox();
        jLabel1 = new javax.swing.JLabel();
        lblMoney_Sum = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblProduct_Detail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Name_Product", "Name_Custom", "Name_Meterial", "gsm", "Name_color", "Name_Size", "Product_price", "Quantity"
            }
        ));
        tblProduct_Detail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProduct_DetailMouseClicked(evt);
            }
        });
        slpPr_Detail.setViewportView(tblProduct_Detail);

        jPanel2.add(slpPr_Detail, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 39, 960, 202));

        tblProduct_Ex.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "id", "Name_Product", "Name_Custom", "Name_Meterial", "gsm", "Name_color", "Name_Size", "Product_price", "Quantity"
            }
        ));
        tblProduct_Ex.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProduct_ExMouseClicked(evt);
            }
        });
        slpPr_Ex.setViewportView(tblProduct_Ex);

        jPanel2.add(slpPr_Ex, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 286, 703, 191));

        btnHoanThanh.setText("Hoàn thành");
        btnHoanThanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoanThanhActionPerformed(evt);
            }
        });
        jPanel2.add(btnHoanThanh, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 380, 100, -1));

        btnxoa.setText("Xóa");
        btnxoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaActionPerformed(evt);
            }
        });
        jPanel2.add(btnxoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 380, 100, -1));

        cbbReson.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Kích cỡ không phù hợp", "Màu sắc không đúng", "Chất liệu không tốt", "Sản phẩm bị hỏng", "Sự không hài lòng với chất lượng", "Thay đổi ý kiến", "Sai sót trong đặt hàng", "Phong cách không phù hợp", "Vấn đề về bảo hành" }));
        jPanel2.add(cbbReson, new org.netbeans.lib.awtextra.AbsoluteConstraints(779, 292, 190, -1));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setText("Tổng tiền trả");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(721, 253, -1, -1));

        lblMoney_Sum.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblMoney_Sum.setText("0.0 đ");
        jPanel2.add(lblMoney_Sum, new org.netbeans.lib.awtextra.AbsoluteConstraints(844, 256, 75, -1));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel2.setText("Danh sách sản phẩm đổi");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 253, -1, -1));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel3.setText("Danh sách sản phẩm ");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, -1));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setText("lí do:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(721, 296, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaActionPerformed
        int row = tblProduct_Ex.getSelectedRow();
        int rowprd = tblProduct_Ex.getRowCount();
        int rowpr = tblProduct_Detail.getRowCount();
        int row_Detail = tblProduct_Detail.getSelectedRow();

        if (row < 0) {
            return;
        }
        int quantity_product = Integer.parseInt(tblProduct_Ex.getValueAt(row, 8).toString());
        int quantity = Integer.parseInt(JOptionPane.showInputDialog(this, "Moi ban nhap so luong: "));
        if (quantity > 0) {
            int resultQauntity = quantity_product - quantity;
            tblProduct_Ex.setValueAt(String.valueOf(resultQauntity), row, 8);
            //kien tra xem ton tai chua

            int checkPr = this.findRowInTable_Product_Detail(tblProduct_Ex.getValueAt(row, 0).toString().trim());
            if (checkPr != -1) {
                int currentQuantity_Detail = (int) tblProduct_Detail.getValueAt(checkPr, 8);
                tblProduct_Detail.setValueAt(currentQuantity_Detail + quantity, checkPr, 8);
            }

            double sum2 = calculateTotalSum(dtmPR);
            System.out.println("so tien" + sum2);

            lblMoney_Sum.setText(String.valueOf(sum_Money - sum2));

            for (int i = 0; i < rowprd; i++) {
                String id = tblProduct_Ex.getValueAt(i, 0).toString();
                String name_Product = tblProduct_Ex.getValueAt(i, 1).toString().trim();
                String name_Custom = tblProduct_Ex.getValueAt(i, 2).toString().trim();
                String name_Material = tblProduct_Ex.getValueAt(i, 3).toString().trim();
                int gsm = Integer.parseInt(tblProduct_Ex.getValueAt(i, 4).toString());
                String name_Coler = tblProduct_Ex.getValueAt(i, 5).toString().trim();
                String name_Size = tblProduct_Ex.getValueAt(i, 6).toString().trim();
                BigDecimal price = new BigDecimal(tblProduct_Ex.getValueAt(row, 7).toString().trim());
                int sl = Integer.parseInt(tblProduct_Ex.getValueAt(i, 8).toString());

                Custom custom = new Custom(name_Custom);
                Material material = new Material(name_Material);
                Thickness thickness = new Thickness(gsm);
                Color color = new Color(name_Coler);
                Size size = new Size(name_Size);

                com.model.Product product = new com.model.Product(price, custom, material, thickness, name_Product);
                ProductDetail pdt = new ProductDetail(sl, color, id, product, size);

                List.set(i, pdt);

            }

            this.loadProduct_Ex();
        } else {
            return;
        }
    }//GEN-LAST:event_btnxoaActionPerformed


    private void btnHoanThanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoanThanhActionPerformed
        int row = tblProduct_Ex.getRowCount();
        System.out.println("id build " + idBill);
        LocalDateTime today = LocalDateTime.now();
        Timestamp todayEx = new java.sql.Timestamp(today.atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli());
        created_at = todayEx;
        ExchangeBill ex = new ExchangeBill(new Bill(idBill), created_at, cbbReson.getSelectedItem().toString());
        System.out.println("id bill: " + ex.getBillId().getId());
        System.out.println("ngay tao: " + ex.getCreatedAt());
        System.out.println("mo ta: " + ex.getDescribeReason());
        boolean successMessageShown = false;
        if (Double.parseDouble(lblMoney_Sum.getText().trim()) == 0) {
            if (exs.Insert(ex)) {
//            JOptionPane.showMessageDialog(this, "Them 1");
                for (int i = 0; i < row; i++) {
                    int sl = Integer.parseInt(tblProduct_Ex.getValueAt(i, 8).toString());
                    String idSP = tblProduct_Ex.getValueAt(i, 0).toString();
                    ExchangeBillDetail exd = new ExchangeBillDetail(sl, new ExchangeBill(created_at), new ProductDetail(idSP));
                    if (this.exds.insert(exd) == true && this.pdds.getQuantity(idSP, sl) && this.exs.getUpdate_Bill(idBill)) {
                        if (!successMessageShown) {
                            JOptionPane.showMessageDialog(this, "Đổi hàng thành công");
                            successMessageShown = true;
                        }
                        this.loadProduct_Detail();
                    } else {
                        JOptionPane.showMessageDialog(this, "them 2 that bai");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "that bai");
            }
        }else{
           int check =  JOptionPane.showConfirmDialog(this, "Số tiền cảu bạn vẫn còn thừa. Bạn có chắc chứ?", "Đổi hàng",  JOptionPane.YES_NO_OPTION);
           if(check == JOptionPane.YES_OPTION){
               if (exs.Insert(ex)) {
//            JOptionPane.showMessageDialog(this, "Them 1");
                for (int i = 0; i < row; i++) {
                    int sl = Integer.parseInt(tblProduct_Ex.getValueAt(i, 8).toString());
                    String idSP = tblProduct_Ex.getValueAt(i, 0).toString();
                    ExchangeBillDetail exd = new ExchangeBillDetail(sl, new ExchangeBill(created_at), new ProductDetail(idSP));
                    if (this.exds.insert(exd) == true && this.pdds.getQuantity(idSP, sl) && this.exs.getUpdate_Bill(idBill)) {
                        if (!successMessageShown) {
                            JOptionPane.showMessageDialog(this, "Đổi hàng thành công");
                            successMessageShown = true;
                        }
                        this.loadProduct_Detail();
                    } else {
                        JOptionPane.showMessageDialog(this, "them 2 that bai");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "that bai");
            }
           }else{
               JOptionPane.showMessageDialog(this, "Mời bạn chọn tiếp.");
               return;
           } 
        }


    }//GEN-LAST:event_btnHoanThanhActionPerformed

    private void tblProduct_ExMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProduct_ExMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblProduct_ExMouseClicked

    private void tblProduct_DetailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProduct_DetailMouseClicked
        int row = tblProduct_Detail.getSelectedRow();
        System.out.println("id b:" + idBill);
        if (row < 0) {
            return;
        } else {
            int quantity_product = Integer.parseInt(tblProduct_Detail.getValueAt(row, 8).toString());
            int quantity = Integer.parseInt(JOptionPane.showInputDialog(this, "Moi ban nhap so luong: "));
            if (quantity > Integer.parseInt(tblProduct_Detail.getValueAt(row, 8).toString())) {
                JOptionPane.showMessageDialog(this, "Khong duoc nhap qua so luong sp trong kho");
                return;
            }

            if (quantity > 0) {
                int resultQauntity = quantity_product - quantity;
                dtm.setValueAt(resultQauntity, row, 8);

                //                kien tra xem ton tai chua
                String idCheck = tblProduct_Detail.getValueAt(row, 0).toString();
                System.out.println(idCheck);
                int checkEx = this.findRowInTable_Product_Ex(idCheck);
                System.out.println("row" + checkEx);

                if (checkEx != -1) {
                    int currentQuantity_Ex = (int) tblProduct_Ex.getValueAt(checkEx, 8);
                    tblProduct_Ex.setValueAt(currentQuantity_Ex + quantity, checkEx, 8);
                    String id = tblProduct_Detail.getValueAt(row, 0).toString().trim();
                    String name_Product = tblProduct_Detail.getValueAt(row, 1).toString().trim();
                    String name_Custom = tblProduct_Detail.getValueAt(row, 2).toString().trim();
                    String name_Material = tblProduct_Detail.getValueAt(row, 3).toString().trim();
                    int gsm = Integer.parseInt(tblProduct_Detail.getValueAt(row, 4).toString());
                    String name_Coler = tblProduct_Detail.getValueAt(row, 5).toString().trim();
                    String name_Size = tblProduct_Detail.getValueAt(row, 6).toString().trim();
                    BigDecimal price = new BigDecimal(tblProduct_Detail.getValueAt(row, 7).toString().trim());

                    Custom custom = new Custom(name_Custom);
                    Material material = new Material(name_Material);
                    Thickness thickness = new Thickness(gsm);
                    Color color = new Color(name_Coler);
                    Size size = new Size(name_Size);

                    com.model.Product product = new com.model.Product(price, custom, material, thickness, name_Product);
                    ProductDetail pdt = new ProductDetail(currentQuantity_Ex + quantity, color, id, product, size);
                    this.List.set(checkEx, pdt);
                    this.loadProduct_Ex();
                    sum = calculateTotalSum(dtmPR);
                    lblMoney_Sum.setText(String.valueOf(sum_Money - sum));
                    if ((sum_Money - sum) < 0) {
                        JOptionPane.showMessageDialog(this, "Vì số sản phẩm bạn chọn lớn hơn số tiền gốc nên sẽ bị xóa hết sản phẩm.");
                        this.List.removeAll(List);
                        this.loadProduct_Ex();
                        sum = calculateTotalSum(dtmPR);
                        lblMoney_Sum.setText(String.valueOf(sum_Money - sum));
                        return;
                    }
                } else {
                    String id = tblProduct_Detail.getValueAt(row, 0).toString().trim();
                    String name_Product = tblProduct_Detail.getValueAt(row, 1).toString().trim();
                    String name_Custom = tblProduct_Detail.getValueAt(row, 2).toString().trim();
                    String name_Material = tblProduct_Detail.getValueAt(row, 3).toString().trim();
                    int gsm = Integer.parseInt(tblProduct_Detail.getValueAt(row, 4).toString());
                    String name_Coler = tblProduct_Detail.getValueAt(row, 5).toString().trim();
                    String name_Size = tblProduct_Detail.getValueAt(row, 6).toString().trim();
                    BigDecimal price = new BigDecimal(tblProduct_Detail.getValueAt(row, 7).toString().trim());

                    Custom custom = new Custom(name_Custom);
                    Material material = new Material(name_Material);
                    Thickness thickness = new Thickness(gsm);
                    Color color = new Color(name_Coler);
                    Size size = new Size(name_Size);

                    com.model.Product product = new com.model.Product(price, custom, material, thickness, name_Product);
                    ProductDetail pdt = new ProductDetail(quantity, color, id, product, size);
                    this.List.add(pdt);
                    this.loadProduct_Ex();
                    sum = calculateTotalSum(dtmPR);
                    lblMoney_Sum.setText(String.valueOf(sum_Money - sum));
                    if ((sum_Money - sum) < 0) {
                        JOptionPane.showMessageDialog(this, "Vì số sản phẩm bạn chọn lớn hơn số tiền gốc nên sẽ bị xóa hết sản phẩm.");
                        this.List.removeAll(List);
                        this.loadProduct_Ex();
                        sum = calculateTotalSum(dtmPR);
                        lblMoney_Sum.setText(String.valueOf(sum_Money - sum));
                        return;
                    }
                }

            }
        }
    }//GEN-LAST:event_tblProduct_DetailMouseClicked

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
            java.util.logging.Logger.getLogger(ExchangeJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ExchangeJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ExchangeJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ExchangeJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ExchangeJDialog dialog = new ExchangeJDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnHoanThanh;
    private javax.swing.JButton btnxoa;
    private com.swing.Combobox cbbReson;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblMoney_Sum;
    private javax.swing.JScrollPane slpPr_Detail;
    private javax.swing.JScrollPane slpPr_Ex;
    private javax.swing.JTable tblProduct_Detail;
    private javax.swing.JTable tblProduct_Ex;
    // End of variables declaration//GEN-END:variables
}
