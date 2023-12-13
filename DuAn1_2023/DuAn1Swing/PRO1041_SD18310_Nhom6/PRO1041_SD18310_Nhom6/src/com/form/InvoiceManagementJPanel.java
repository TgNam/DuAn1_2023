/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.form;

import Util.Validate;
import com.model.Address;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import com.model.Bill;
import com.model.BillDetail;
import com.model.ExchangeBill;
import com.model.ExchangeBillDetail;
import com.model.ReturnBill;
import com.model.ReturnBillDetail;
import com.repository.Return_Bill_Repository;
import com.repository.VoucherResponsitory;
import com.service.AddressService;
import com.service.BillDetailService;
import com.service.BillService;
import com.service.ExchangeService;
import com.service.Exchange_detailServict;
import com.service.ProductDetailService;
import com.service.ProductService;
import com.service.ReturnBillDetailService;
import com.service.ReturnBillService;
import com.service.UserRoleService;
import com.service.UserService;
import com.service.imple.AddressImple;
import com.service.imple.BillDetailImple;
import com.service.imple.BillImple;
import com.service.imple.Exchage_DetailImple;
import com.service.imple.ExchangeImple;
import com.service.imple.ProductDetailImple;
import com.service.imple.ProductImple;
import com.service.imple.ReturnBillDetailImple;
import com.service.imple.ReturnBillImple;
import com.service.imple.UserImple;
import com.service.imple.UserRoleImple;
import com.swing.EditButtons;
import com.swing.EditTextField;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JPanel;
import table.TableCustom;

/**
 *
 * @author TgNam
 */
public class InvoiceManagementJPanel extends javax.swing.JPanel {

    private DefaultTableModel tableModel = new DefaultTableModel();
    private BillDetailService billDetailService = new BillDetailImple();
    private ProductService productService = new ProductImple();
    private ProductDetailService productDetailService = new ProductDetailImple();
    private BillService billService = new BillImple();
    private UserService userService = new UserImple();
    private UserRoleService userRoleService = new UserRoleImple();
    private VoucherResponsitory voucherResponsitory = new VoucherResponsitory();
    private AddressService addressService = new AddressImple();
    private Validate vl = new Validate();
    private String checkStatus = "1";
    private JFrame jFrame = new JFrame();
    private Exchange_detailServict exchange_detailServict = new Exchage_DetailImple();
    private ReturnsForm returnsForm = new ReturnsForm(jFrame, true,this);
    private List<BillDetail> listProductReturn = null;
    private ExchangeService exchangeService = new ExchangeImple();
    private ReturnBillService returnBillService = new ReturnBillImple();
    private ReturnBillDetailService returnBillDetailService = new ReturnBillDetailImple();
    private Exchange_detailServict exchangeDetailServict = new Exchage_DetailImple();
    //them cai nay 30/11
    private List<BillDetail> listProductExchang = null;
    private Exchang_Bill ex = new Exchang_Bill(jFrame, true);
    private String checkclick = "0";
    private Date nowDate = null;
    private UserService us = new UserImple();
    //them vao 9/12
    private EditButtons bt = new EditButtons();
    private EditTextField txt = new EditTextField();

    
    /**
     * Creates new form InvoiceManagementJPanel
     */
    public InvoiceManagementJPanel() {
        initComponents();
        columns_no_checkbox();
        columns_tblBill();
        btnDoiHang.setVisible(false);
        btnTraHang.setVisible(false);
        btnInPhieuGH.setVisible(false);
        bthXacNhan.setVisible(false);
        bthHuy.setVisible(false);

        //them vao 9/12
        bt.Edit(bthBill1);
        bt.Edit(bthBill2);
        bt.Edit(bthBill3);
        bt.Edit(bthBill46);
        bt.Edit(bthBill57);
        bt.Edit(bthHuy);
        bt.Edit(bthXacNhan);
        bt.Edit(btnDoiHang);
        bt.Edit(btnTraHang);
        bt.Edit(btnInPhieuGH);

        TableCustom.apply(slpBill, TableCustom.TableType.MULTI_LINE);
        TableCustom.apply(slpBill_Details, TableCustom.TableType.MULTI_LINE);

    }

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        datarowBill("3", "3");
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

    public void columns_tblBill() {
        tableModel = new DefaultTableModel();
        String[] column = {"STT", "Mã hóa đơn", "Tên khách hàng", "Số điện thoại", "Trạng thái", "Ngày tạo"};
        tableModel.setColumnIdentifiers(column);
        TableColumnModel columnModel = tblBillDetails.getColumnModel();
        tblBill.setModel(tableModel);
    }

    public void columns_tblBill_li_do() {
        tableModel = new DefaultTableModel();
        String[] column = {"STT", "Mã hóa đơn", "Tên khách hàng", "Số điện thoại", "Trạng thái", "Ngày tạo", "Lý do"};
        tableModel.setColumnIdentifiers(column);
        TableColumnModel columnModel = tblBillDetails.getColumnModel();
        tblBill.setModel(tableModel);
    }

    public void columns_no_price_checkbox() {
        tableModel = new DefaultTableModel();
        String[] column = {"STT", "Tên Sản Phẩm", "Màu", "Size", "Số Lượng"};
        tableModel.setColumnIdentifiers(column);
        TableColumnModel columnModel = tblBillDetails.getColumnModel();
        tblBillDetails.setModel(tableModel);
    }

    public void columns_no_checkbox() {
        tableModel = new DefaultTableModel();
        String[] column = {"STT", "Tên Sản Phẩm", "Màu", "Size", "Số Lượng", "Đơn Giá"};
        tableModel.setColumnIdentifiers(column);
        TableColumnModel columnModel = tblBillDetails.getColumnModel();
        tblBillDetails.setModel(tableModel);
    }

    public void columns_yes_checkbox() {
        tblBillDetails.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tblBillDetails.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "STT", "Tên sản phẩm", "Màu", "Size", "Số lượng", "Đơn giá", ""
                }
        ) {
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tblBillDetails.setRowHeight(25);
    }

    public void datarowBill(String status1, String status2) {
        tableModel = (DefaultTableModel) tblBill.getModel();
        tableModel.setRowCount(0);
        int index = 1;
        // Lấy ngày hôm nay
        Date today = new Date();

        // Lấy ngày 3 ngày trước
        Date threeDaysAgo = getDateWithDaysOffset(-3);

        if (status1.equalsIgnoreCase("3") && status2.equalsIgnoreCase("3")) {
            for (Bill bill : billService.getBill_status(status1, status2)) {
                if (bill.getCreatedAt().after(threeDaysAgo) && bill.getCreatedAt().before(today)) {
                    String LyDo = null;
                    String idBill = bill.getId();
                    ReturnBill returnBill = returnBillService.getBy_IdBill(idBill);
                    String reason_description = returnBill.getReasonDescription();
                    ExchangeBill exchangeBill = exchangeService.getExchangeBill_id(idBill);
                    String describe_reason = exchangeBill.getDescribeReason();
                    if (describe_reason != null) {
                        LyDo = describe_reason;
                    }
                    if (reason_description != null) {
                        LyDo = reason_description;
                    }
                    tableModel.addRow(new Object[]{
                        index++,
                        bill.getId(),
                        bill.getUserId().getFullName(),
                        bill.getUserId().getNumberPhone(),
                        bill.checkTrangThai(),
                        bill.getCreatedAt(),
                        LyDo
                    });
                }
            }
        } else {
            for (Bill bill : billService.getBill_status(status1, status2)) {
                String LyDo = null;
                String idBill = bill.getId();
                ReturnBill returnBill = returnBillService.getBy_IdBill(idBill);
                String reason_description = returnBill.getReasonDescription();
                ExchangeBill exchangeBill = exchangeService.getExchangeBill_id(idBill);
                String describe_reason = exchangeBill.getDescribeReason();
                if (describe_reason != null) {
                    LyDo = describe_reason;
                }
                if (reason_description != null) {
                    LyDo = reason_description;
                }
                tableModel.addRow(new Object[]{
                    index++,
                    bill.getId(),
                    bill.getUserId().getFullName(),
                    bill.getUserId().getNumberPhone(),
                    bill.checkTrangThai(),
                    bill.getCreatedAt(),
                    LyDo
                });
            }
        }

    }
    
    //them vao 11/12

    private Date getDateWithDaysOffset(int daysOffset) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, daysOffset);
        return calendar.getTime();
    }

    public void loadBillDetail(String id) {
        tableModel = (DefaultTableModel) this.tblBillDetails.getModel();
        tableModel.setRowCount(0);
        int index = 1;
        for (BillDetail bdt : this.billDetailService.getbill_all(id)) {
            Object[] ob = {
                index++,
                bdt.getProductDetailId().getProductId().getName_product(),
                bdt.getProductDetailId().getColorId().getNameColor(),
                bdt.getProductDetailId().getSizeId().getNameSize(),
                bdt.getQuantityPurchased(),
                bdt.getPriceNow()
            };
            tableModel.addRow(ob);
        }
    }

    public void loadBillReturn(List<ReturnBillDetail> list) {
        tableModel = (DefaultTableModel) this.tblBillDetails.getModel();
        tableModel.setRowCount(0);
        int index = 1;
        for (ReturnBillDetail rbd : list) {
            Object[] ob = {
                index++,
                rbd.getProductDetailId().getProductId().getName_product(),
                rbd.getProductDetailId().getColorId().getNameColor(),
                rbd.getProductDetailId().getSizeId().getNameSize(),
                rbd.getQuantityOfProductsReturned(),
                rbd.getPriceAtTheTimeOfPurchase()
            };
            tableModel.addRow(ob);
        }
    }

    public void loadBillExchangeBill(List<ExchangeBillDetail> list) {
        tableModel = (DefaultTableModel) this.tblBillDetails.getModel();
        tableModel.setRowCount(0);
        int index = 1;
        for (ExchangeBillDetail ex : list) {
            Object[] ob = {
                index++,
                ex.getProductDetailId().getProductId().getName_product(),
                ex.getProductDetailId().getColorId().getNameColor(),
                ex.getProductDetailId().getSizeId().getNameSize(),
                ex.getQuantityOfProductsReturned(),};
            tableModel.addRow(ob);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelFormTraHang = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        slpBill = new javax.swing.JScrollPane();
        tblBill = new javax.swing.JTable();
        bthBill1 = new javax.swing.JButton();
        bthBill57 = new javax.swing.JButton();
        bthBill46 = new javax.swing.JButton();
        bthBill3 = new javax.swing.JButton();
        bthBill2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        slpBill_Details = new javax.swing.JScrollPane();
        tblBillDetails = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnDoiHang = new javax.swing.JButton();
        btnTraHang = new javax.swing.JButton();
        bthXacNhan = new javax.swing.JButton();
        bthHuy = new javax.swing.JButton();
        btnInPhieuGH = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        panelFormTraHang.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("LỊCH SỬ HÓA ĐƠN");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setText("Hóa đơn");

        tblBill.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã hóa đơn", "Tên khách hàng", "Số điện thoại", "Trạng thái", "Ngày tạo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblBill.setSelectionBackground(new java.awt.Color(102, 102, 102));
        tblBill.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBillMouseClicked(evt);
            }
        });
        slpBill.setViewportView(tblBill);
        if (tblBill.getColumnModel().getColumnCount() > 0) {
            tblBill.getColumnModel().getColumn(0).setMinWidth(50);
            tblBill.getColumnModel().getColumn(0).setMaxWidth(50);
            tblBill.getColumnModel().getColumn(1).setMinWidth(70);
            tblBill.getColumnModel().getColumn(1).setMaxWidth(70);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(slpBill, javax.swing.GroupLayout.DEFAULT_SIZE, 1128, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(slpBill, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bthBill1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        bthBill1.setText("Hóa đơn đã thanh toán");
        bthBill1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthBill1ActionPerformed(evt);
            }
        });

        bthBill57.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        bthBill57.setText("Hóa đơn trả hàng/đổi hàng");
        bthBill57.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthBill57ActionPerformed(evt);
            }
        });

        bthBill46.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        bthBill46.setText("Hóa đơn chờ trả hàng/đổi hàng");
        bthBill46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthBill46ActionPerformed(evt);
            }
        });

        bthBill3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        bthBill3.setText("Hóa đơn hoàn thành");
        bthBill3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthBill3ActionPerformed(evt);
            }
        });

        bthBill2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        bthBill2.setText("Hóa đơn đang giao");
        bthBill2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthBill2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelFormTraHangLayout = new javax.swing.GroupLayout(panelFormTraHang);
        panelFormTraHang.setLayout(panelFormTraHangLayout);
        panelFormTraHangLayout.setHorizontalGroup(
            panelFormTraHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormTraHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bthBill1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(bthBill2, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bthBill3, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(bthBill46, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(bthBill57, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(panelFormTraHangLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(panelFormTraHangLayout.createSequentialGroup()
                .addGap(424, 424, 424)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelFormTraHangLayout.setVerticalGroup(
            panelFormTraHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormTraHangLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelFormTraHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bthBill2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bthBill1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bthBill3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bthBill46, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bthBill57, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        tblBillDetails.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tblBillDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên sản phẩm", "Màu", "Size", "Số lượng", "Đơn giá", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblBillDetails.setRowHeight(25);
        tblBillDetails.setSelectionBackground(new java.awt.Color(102, 102, 102));
        tblBillDetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBillDetailsMouseClicked(evt);
            }
        });
        slpBill_Details.setViewportView(tblBillDetails);

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setText("Chi tiết hóa đơn");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(slpBill_Details, javax.swing.GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(slpBill_Details, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnDoiHang.setText("ĐỔI HÀNG");
        btnDoiHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoiHangActionPerformed(evt);
            }
        });
        jPanel1.add(btnDoiHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 371, 30));

        btnTraHang.setText("TRẢ HÀNG");
        btnTraHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTraHangActionPerformed(evt);
            }
        });
        jPanel1.add(btnTraHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 371, 30));

        bthXacNhan.setText("XÁC NHẬN");
        bthXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthXacNhanActionPerformed(evt);
            }
        });
        jPanel1.add(bthXacNhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 371, 30));

        bthHuy.setText("HUỶ");
        bthHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthHuyActionPerformed(evt);
            }
        });
        jPanel1.add(bthHuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 371, 30));

        btnInPhieuGH.setText("IN PHIẾU GIAO HÀNG");
        btnInPhieuGH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInPhieuGHActionPerformed(evt);
            }
        });
        jPanel1.add(btnInPhieuGH, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 371, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelFormTraHang, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(panelFormTraHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void bthBill1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthBill1ActionPerformed
        columns_no_checkbox();
        columns_tblBill();
        datarowBill(String.valueOf("1"), String.valueOf("1"));
        checkStatus = "1";
        btnDoiHang.setVisible(false);
        btnTraHang.setVisible(false);
        btnInPhieuGH.setVisible(true);
        bthXacNhan.setVisible(true);
        bthHuy.setVisible(false);
        checkclick = "3";
    }//GEN-LAST:event_bthBill1ActionPerformed

    private void bthBill2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthBill2ActionPerformed
        columns_no_checkbox();
        columns_tblBill();
        datarowBill(String.valueOf("2"), String.valueOf("2"));
        checkStatus = "2";
        btnDoiHang.setVisible(false);
        btnTraHang.setVisible(false);
        btnInPhieuGH.setVisible(false);
        bthXacNhan.setVisible(true);
        bthHuy.setVisible(true);
        checkclick = "1";
    }//GEN-LAST:event_bthBill2ActionPerformed

    public JButton getBthBill3() {
        return bthBill3;
    }
    
    
    private void bthBill3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthBill3ActionPerformed
        columns_yes_checkbox();
        columns_tblBill();
        datarowBill(String.valueOf("3"), String.valueOf("3"));
        checkStatus = "3";
        btnDoiHang.setVisible(true);
        btnTraHang.setVisible(true);
        btnInPhieuGH.setVisible(false);
        bthXacNhan.setVisible(false);
        bthHuy.setVisible(false);
        checkclick = "0";
    }//GEN-LAST:event_bthBill3ActionPerformed

    private void bthBill46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthBill46ActionPerformed
        columns_no_checkbox();
        columns_tblBill_li_do();
        datarowBill(String.valueOf("4"), String.valueOf("6"));
        checkStatus = "46";
        btnDoiHang.setVisible(false);
        btnTraHang.setVisible(false);
        btnInPhieuGH.setVisible(false);
        bthXacNhan.setVisible(true);
        bthHuy.setVisible(true);
        checkclick = "2";
    }//GEN-LAST:event_bthBill46ActionPerformed

    private void bthBill57ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthBill57ActionPerformed
        columns_no_checkbox();
        columns_tblBill_li_do();
        datarowBill(String.valueOf("5"), String.valueOf("7"));
        checkStatus = "57";
        btnDoiHang.setVisible(false);
        btnTraHang.setVisible(false);
        btnInPhieuGH.setVisible(false);
        bthXacNhan.setVisible(false);
        bthHuy.setVisible(false);
        checkclick = "0";
    }//GEN-LAST:event_bthBill57ActionPerformed

    private void tblBillMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBillMouseClicked
        try {
            int row = tblBill.getSelectedRow();
            if (row >= 0) {
                if (checkStatus.equals("1")) {
                    Bill bill = billService.getBill_status("1", "1").get(row);
                    String id = bill.getId();
                    loadBillDetail(id);
                } else if (checkStatus.equals("2")) {
                    Bill bill = billService.getBill_status("2", "2").get(row);
                    String id = bill.getId();
                    loadBillDetail(id);
                } else if (checkStatus.equals("3")) {
                    Bill bill = billService.getBill_status("3", "3").get(row);
                    String id = bill.getId();
                    loadBillDetail(id);
                } else if (checkStatus.equals("46")) {
                    Bill bill = billService.getBill_status("4", "6").get(row);
                    String id = bill.getId();
                    String status = bill.getStatus();
                    if (status.equals("6")) {
                        columns_no_price_checkbox();
                        List<ExchangeBillDetail> listEx = exchange_detailServict.getExBill_idBill(id);
                        loadBillExchangeBill(listEx);
                    }
                    if (status.equals("4")) {
                        columns_no_checkbox();
                        List<ReturnBillDetail> listRbd = new ReturnBillDetailImple().getByIdBill(id);
                        loadBillReturn(listRbd);
                    }

                } else if (checkStatus.equals("57")) {
                    Bill bill = billService.getBill_status("5", "7").get(row);
                    String id = bill.getId();
                    String status = bill.getStatus();
                    if (status.equals("5")) {
                        columns_no_checkbox();
                        List<ReturnBillDetail> listRbd = new ReturnBillDetailImple().getByIdBill(id);
                        loadBillReturn(listRbd);
                    }
                    if (status.equals("7")) {
                        columns_no_price_checkbox();
                        List<ExchangeBillDetail> listEx = exchange_detailServict.getExBill_idBill(id);
                        loadBillExchangeBill(listEx);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi dữ liệu");
        }
    }//GEN-LAST:event_tblBillMouseClicked

    private void btnTraHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTraHangActionPerformed
        int rowCount = tblBillDetails.getRowCount();
        int indexBill = tblBill.getSelectedRow();
        if (indexBill != -1) {
            Bill bill = billService.getBill_status(checkStatus, checkStatus).get(indexBill);
            // Lấy ra các sản phẩm có trong bill này
            List<BillDetail> billDetails = billDetailService.getbill_all(bill.getId());
            Boolean isChecked = null;
            listProductReturn = new ArrayList<>();
            returnsForm = new ReturnsForm(jFrame, true,this);
            // duyệt qua từng dòng với i là đại diện cho index của các dòng
            for (int i = 0; i < rowCount; i++) {
                isChecked = (Boolean) tblBillDetails.getValueAt(i, tblBillDetails.getColumnCount() - 1);
                if (isChecked != null && isChecked) {
                    // nếu dòng này đang được tích thì lấy ra BillDetail tại dòng đó
                    billDetails.get(i).setBillId(bill);
                    listProductReturn.add(billDetails.get(i));
                }
            }
            if (listProductReturn.size() == 0) {
                JOptionPane.showMessageDialog(this, "Chọn sản phẩm muốn trả", "Trả hàng", JOptionPane.ERROR_MESSAGE);
                return;
            }
            returnsForm.setBillDetails(listProductReturn);
            returnsForm.setIdBill(Long.valueOf(bill.getId()));
            returnsForm.setVisible(true);
            
        } else {
            JOptionPane.showMessageDialog(this, "Chưa chọn hóa đơn", "Lỗi", 0);
            return;
        }
    }//GEN-LAST:event_btnTraHangActionPerformed

    private void tblBillDetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBillDetailsMouseClicked
        int rowCount = tblBillDetails.getRowCount();
        int indexBill = tblBill.getSelectedRow();
        if (indexBill != -1) {
            Bill bill = billService.getBill_status(checkStatus, checkStatus).get(indexBill);
        }
    }//GEN-LAST:event_tblBillDetailsMouseClicked

    private void btnDoiHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoiHangActionPerformed
        int rowCount = tblBillDetails.getRowCount();
        int indexBill = tblBill.getSelectedRow();
        if (indexBill != -1) {
            Bill bill = billService.getBill_status(checkStatus, checkStatus).get(indexBill);
            // Lấy ra các sản phẩm có trong bill này
            List<BillDetail> billDetails = billDetailService.getbill_all(bill.getId());
            Boolean isChecked = null;
            listProductExchang = new ArrayList<>();
            ex = new Exchang_Bill(jFrame, true);
            // duyệt qua từng dòng với i là đại diện cho index của các dòng
            for (int i = 0; i < rowCount; i++) {
                isChecked = (Boolean) tblBillDetails.getValueAt(i, tblBillDetails.getColumnCount() - 1);
                if (isChecked != null && isChecked) {
                    // nếu dòng này đang được tích thì lấy ra BillDetail tại dòng đó
                    billDetails.get(i).setBillId(bill);
                    listProductExchang.add(billDetails.get(i));
                }
            }
            if (listProductExchang.size() == 0) {
                JOptionPane.showMessageDialog(this, "Chọn sản phẩm muốn trả", "Trả hàng", JOptionPane.ERROR_MESSAGE);
                return;
            }
            ex.setBillDetails(listProductExchang);
            ex.setIdBill(Long.valueOf(bill.getId()));
            ex.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Chưa chọn hóa đơn", "Lỗi", 0);
            return;
        }
    }//GEN-LAST:event_btnDoiHangActionPerformed

    private void btnInPhieuGHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInPhieuGHActionPerformed
        int row = tblBill.getSelectedRow();
        if (row != -1) {
            Bill bill = billService.getBill_status("1", "1").get(row);
            String addressDetail = bill.getAddressId().getAddressDetail();
            String id = bill.getId();
            String address = null;
            if (addressDetail != null) {
                billService.printerBill(Long.valueOf(id));
                JOptionPane.showMessageDialog(this, "Hóa đơn đã được lưu vào thư mục HoaDon trong ổ C", "Xuất hóa đơn", 1);
                billService.updateStatusById(id, Integer.parseInt("2"));
            } else {
                address = JOptionPane.showInputDialog("Nhập địa chỉ");
                if (address == null || address.equals("")) {
                    tblBill.clearSelection();
                    tblBillDetails.clearSelection();
                    JOptionPane.showMessageDialog(this, "Bạn chưa nhập địa chỉ", "Địac chỉ", 0);
                    return;
                } else {
                    nowDate = getCurrentDateTime();
                    addressService.add_address(nowDate, address);
                    Address addressObject = us.getAddress(nowDate, address);
                    String idaddressObject = addressObject.getId();
                    billService.update_address(idaddressObject, id);
                    billService.printerBill(Long.valueOf(id));
                    JOptionPane.showMessageDialog(this, "Hóa đơn đã được lưu vào thư mục HoaDon trong ổ C", "Xuất hóa đơn", 1);
                    billService.updateStatusById(id, Integer.parseInt("2"));
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn muốn xuất", "Xuất hóa đơn", 0);
        }
        datarowBill(String.valueOf("1"), String.valueOf("1"));
    }//GEN-LAST:event_btnInPhieuGHActionPerformed

    private void bthXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthXacNhanActionPerformed
        int row = tblBill.getSelectedRow();
        if (row != -1) {
            if (checkclick.equals("1")) {
                Bill bill = billService.getBill_status("2", "2").get(row);
                String id = bill.getId();
                billService.updateStatusById(id, Integer.parseInt("3"));
                datarowBill(String.valueOf("2"), String.valueOf("2"));
            }
            if (checkclick.equals("2")) {
                Bill bill = billService.getBill_status("4", "6").get(row);
                String idBill = bill.getId();
                String statusBill = bill.getStatus();
                ReturnBill returnBill = returnBillService.getBy_IdBill(idBill);
                ExchangeBill exchangeBill = exchangeService.getExchangeBill_id(idBill);
                if (statusBill.equals("6")) {
                    exchangeService.update_status(exchangeBill);
                    billService.updateStatusById(idBill, Integer.parseInt("7"));
                }
                if (statusBill.equals("4")) {
                    returnBillService.update_status(returnBill);
                    billService.updateStatusById(idBill, Integer.parseInt("5"));
                }
                datarowBill(String.valueOf("4"), String.valueOf("6"));
            }
            if (checkclick.equals("3")) {
                Bill bill = billService.getBill_status("1", "1").get(row);
                String addressDetail = bill.getAddressId().getAddressDetail();
                String idBill = bill.getId();
                String address = "Tại quầy bán hàng";
                nowDate = getCurrentDateTime();
                addressService.add_address(nowDate, address);
                Address addressObject = us.getAddress(nowDate, address);
                String idaddressObject = addressObject.getId();
                billService.update_address(idaddressObject, idBill);
                billService.updateStatusById(idBill, Integer.parseInt("3"));
                datarowBill(String.valueOf("1"), String.valueOf("1"));
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn");
        }

    }//GEN-LAST:event_bthXacNhanActionPerformed

    private void bthHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthHuyActionPerformed
        int row = tblBill.getSelectedRow();
        if (row != -1) {
            if (checkclick.equals("1")) {
                Bill bill = billService.getBill_status("2", "2").get(row);
                String id = bill.getId();
                billService.updateStatusById(id, Integer.parseInt("1"));
                datarowBill(String.valueOf("2"), String.valueOf("2"));
            }
            if (checkclick.equals("2")) {
                Bill bill = billService.getBill_status("4", "6").get(row);
                String idBill = bill.getId();
                String statusBill = bill.getStatus();
                ReturnBill returnBill = returnBillService.getBy_IdBill(idBill);
                ExchangeBill exchangeBill = exchangeService.getExchangeBill_id(idBill);
                if (statusBill.equals("4")) {
                    returnBillDetailService.delete_returnBillDetal(returnBill);
                    returnBillService.delete_returnBill(idBill);
                }
                if (statusBill.equals("6")) {
                    exchangeDetailServict.delete_exchangeBillDetal(exchangeBill);
                    exchangeService.delete_exchangeBill(idBill);
                }
                billService.updateStatusById(idBill, Integer.parseInt("3"));
                datarowBill(String.valueOf("4"), String.valueOf("6"));
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn");
        }
    }//GEN-LAST:event_bthHuyActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bthBill1;
    private javax.swing.JButton bthBill2;
    private javax.swing.JButton bthBill3;
    private javax.swing.JButton bthBill46;
    private javax.swing.JButton bthBill57;
    private javax.swing.JButton bthHuy;
    private javax.swing.JButton bthXacNhan;
    private javax.swing.JButton btnDoiHang;
    private javax.swing.JButton btnInPhieuGH;
    private javax.swing.JButton btnTraHang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel panelFormTraHang;
    private javax.swing.JScrollPane slpBill;
    private javax.swing.JScrollPane slpBill_Details;
    private javax.swing.JTable tblBill;
    private javax.swing.JTable tblBillDetails;
    // End of variables declaration//GEN-END:variables
}
