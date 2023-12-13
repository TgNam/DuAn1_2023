package com.main;

import com.event.EventMenuSelected;
import com.form.AddCreart;
import java.awt.Color;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import com.form.BillJPanel;
import com.form.EMPLOYEEJPanel;
import com.form.InvoiceManagementJPanel;
import com.form.LoginJDialog;
import com.form.Product;
import com.form.SaleProductJpanel;
import com.form.ThongKeCuaLinh;
import com.form.UserJPanel;
import com.form.VoucherJpanel;
import com.untils.XImage;
import util.UserLogin;

public class Main extends javax.swing.JFrame {

    private Product sanPham;
    private UserJPanel khachHang;
    private EMPLOYEEJPanel nhanVien;
    private BillJPanel hoaDon;
    private SaleProductJpanel dotGiamGia;
    private VoucherJpanel phieuGiamGia;
    private AddCreart themNguoiDung;
    private InvoiceManagementJPanel quanLyHoaDon;

    private AddCreart themUser;
    private LoginJDialog login;
    //them vao 11/12
//    String cv;

    public Main() {
        initComponents(); 
        login = new  LoginJDialog(this, rootPaneCheckingEnabled);
        login.setVisible(true);

        setBackground(new Color(0, 0, 0, 0));
        sanPham = new Product();
        khachHang = new UserJPanel();
        nhanVien = new EMPLOYEEJPanel();
        hoaDon = new BillJPanel();
        dotGiamGia = new SaleProductJpanel();
        phieuGiamGia = new VoucherJpanel();
        quanLyHoaDon = new InvoiceManagementJPanel();
//<<<<<<< HEAD
        themUser = new AddCreart();
//=======
        themNguoiDung = new AddCreart();
//        UserLogin.getUserLogin().setChucVu("Admin");
       
//>>>>>>> 2829913106edc236ab11d9ff58648223e44f876c
        if(UserLogin.getUserLogin().getChucVu().equalsIgnoreCase("Admin")){
            menu.initMoving(Main.this);
            menu.addEventMenuSelected(new EventMenuSelected() {
                @Override
                public void selected(int index) {
                    if (index == 0) {
                        setForm(sanPham);
                    } else if (index == 1) {
                        setForm(khachHang);
                    } else if (index == 2) {
                        setForm(nhanVien);
                    } else if (index == 3) {
                        setForm(hoaDon);
                    } else if (index == 4) {
                        setForm(dotGiamGia);
                    } else if (index == 5) {
                        setForm(phieuGiamGia);
                    } else if (index == 6) {
                        setForm(quanLyHoaDon);
                    } else if (index == 7) {
                        setForm(themNguoiDung);
                    } else if (index == 8) {
                        setForm(new ThongKeCuaLinh());
                    } else if (index == 9) {
                        int shutDown = JOptionPane.showConfirmDialog(new JFrame(), "Bạn có muốn thoát không", "Thoát", JOptionPane.YES_NO_OPTION);
                        if (shutDown == JOptionPane.YES_OPTION) {
                            System.exit(0);
                        }
                    }
                }
            });
        }else{
             menu.initMoving(Main.this);
            menu.addEventMenuSelected(new EventMenuSelected() {
                @Override
                public void selected(int index) {
                    if (index == 0) {
                        JOptionPane.showMessageDialog(rootPane, "Khong the vao duoc!");
                    } else if (index == 1) {
                        JOptionPane.showMessageDialog(rootPane, "Khong the vao duoc!");
                    } else if (index == 2) {
                        JOptionPane.showMessageDialog(rootPane, "Khong the vao duoc!");
                    } else if (index == 3) {
                        setForm(hoaDon);
                    } else if (index == 4) {
                        JOptionPane.showMessageDialog(rootPane, "Khong the vao duoc!");
                    } else if (index == 5) {
                        JOptionPane.showMessageDialog(rootPane, "Khong the vao duoc!");
                    } else if (index == 6) {
                        setForm(quanLyHoaDon);
                    } else if (index == 7) {
                        JOptionPane.showMessageDialog(rootPane, "Khong the vao duoc!");
                    } else if (index == 8) {
                        setForm(new ThongKeCuaLinh());
                    } else if (index == 9) {
                        int shutDown = JOptionPane.showConfirmDialog(new JFrame(), "Bạn có muốn thoát không", "Thoát", JOptionPane.YES_NO_OPTION);
                        if (shutDown == JOptionPane.YES_OPTION) {
                            System.exit(0);
                        }
                    }
                }
            });
        }
        
//<<<<<<< HEAD
////        setForm(new ThongKeCuaLinh());
//=======
//        //  set when system open start with home form
//        setForm(sanPham);
//>>>>>>> fb856bee1cd6f1203ae61d074911589fce42f164
    }

 

    private void setForm(JComponent com) {
        mainPanel.removeAll();
        mainPanel.add(com);
        mainPanel.repaint();
        mainPanel.revalidate();
    }

    //them vao 11/12
//    public void status(String st) {
//        cv = st;
//    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new com.swing.PanelBorder();
        menu = new com.component.Menu();
        header2 = new com.component.Header();
        mainPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        header2.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));
        mainPanel.setOpaque(false);
        mainPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(header2, javax.swing.GroupLayout.DEFAULT_SIZE, 1150, Short.MAX_VALUE)
                    .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addComponent(header2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.component.Header header2;
    private javax.swing.JPanel mainPanel;
    private com.component.Menu menu;
    private com.swing.PanelBorder panelBorder1;
    // End of variables declaration//GEN-END:variables
}
