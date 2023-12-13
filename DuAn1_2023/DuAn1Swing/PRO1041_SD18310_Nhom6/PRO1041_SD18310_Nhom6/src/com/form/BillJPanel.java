/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.form;

import Util.Validate;
import com.model.Bill;
import com.model.BillDetail;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import com.model.ProductDetail;
import com.model.User;
import com.model.Voucher;
import com.repository.VoucherResponsitory;
import com.service.BillDetailService;
import com.service.BillService;
import com.service.ProductDetailService;
import com.service.ProductService;
import com.service.UserRoleService;
import com.service.UserService;
import com.service.imple.BillDetailImple;
import com.service.imple.BillImple;
import com.service.imple.ProductDetailImple;
import com.service.imple.ProductImple;
import com.service.imple.UserImple;
import com.service.imple.UserRoleImple;
import com.swing.EditButtons;
import com.swing.EditTextField;
import table.TableCustom;

/**
 *
 * @author thiet
 */
public class BillJPanel extends javax.swing.JPanel {

    private DefaultTableModel tableModel;
    private ProductService productService = new ProductImple();
    private ProductDetailService productDetailService = new ProductDetailImple();
    private BillService billService = new BillImple();
    private BillDetailService billDetailService = new BillDetailImple();
    private UserService userService = new UserImple();
    private UserRoleService userRoleService = new UserRoleImple();
    private VoucherResponsitory voucherResponsitory = new VoucherResponsitory();
    private Validate vl = new Validate();
    private Date nowDate = null;
    private boolean checkKH = false;
    
    
    //them vao 12/12
    EditButtons bt = new EditButtons();
    EditTextField txt = new EditTextField();
    /**
     * Creates new form Exchange
     */
    public BillJPanel() {
        initComponents();
        datarowProcuct();
        datarowBill();
        resetProduct();
        jLKH.setVisible(false);
        txtUser.setVisible(false);
        lblUser.setVisible(false);
        resetformbill();
        
        //them vao 12/12
        txt.edit(txtUser);
        txt.edit(txtPhoneNumber);
        txt.edit(txtVoucher);
        txt.edit(txtsoluong);
        
        bt.Edit(bthdeleteproduct);
        bt.Edit(tblKTvoucher);
        bt.Edit(bthresetvoucher);
        bt.Edit(bthpayment);
        bt.Edit(bthresetShoppingCart);
        bt.Edit(bthdeleteBill);
        bt.Edit(bthADDProduct_GioHang);
        bt.Edit(BthResetProduct);
        bt.Edit(bthaddbill);
        
        TableCustom.apply(slpBill, TableCustom.TableType.MULTI_LINE);
        TableCustom.apply(slpProduct, TableCustom.TableType.MULTI_LINE);
        TableCustom.apply(slpProduct_detail, TableCustom.TableType.MULTI_LINE);
        TableCustom.apply(slpShoppingCart, TableCustom.TableType.MULTI_LINE);
    }

    public void resetformbill() {
        jLtotal_cost.setText("0.0 ");
        jLinto_money.setText("0.0 ");
        jLGiamGia.setText("0.0 ");
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
            //e.printStackTrace();
            return null;
        }
    }

    //đổ đữ liệu cho bảng sản phẩm
    public void datarowProcuct() {
        tableModel = (DefaultTableModel) tblProduct.getModel();
        tableModel.setRowCount(0);
        int index = 1;
        for (com.model.Product product : productService.getList_sale()) {
            tableModel.addRow(new Object[]{
                index++,
                product.getId(),
                product.getName_product(),
                product.getProduct_price() + " VND",
                product.getSale_id().getSale() + " %",
                product.checkTrangThai()});
        }
    }

    //đổ đữ liệu cho bảng sản phẩm chi tiết
    public void datarowProcuctDetail(String idProduct) {
        tableModel = (DefaultTableModel) tblProductDetail.getModel();
        tableModel.setRowCount(0);
        int index = 1;
        for (ProductDetail productDetail : productDetailService.get_ProductDetails_id_Bill(idProduct, "1")) {
            tableModel.addRow(new Object[]{
                index++,
                productDetail.getProductId().getName_product(),
                productDetail.getColorId().getNameColor(),
                productDetail.getSizeId().getNameSize(),
                productDetail.getQuantity(),
                productDetail.checkTrangThai()});
        }
    }
    //đổ đữ liệu cho bảng bill

    public void datarowBill() {
        tableModel = (DefaultTableModel) tblbill.getModel();
        tableModel.setRowCount(0);
        int index = 1;
        for (Bill bill : billService.getListBill_0()) {
            tableModel.addRow(new Object[]{
                index++,
                bill.getId(),
                bill.getUserId().getFullName(),
                bill.getUserId().getNumberPhone(),
                bill.checkTrangThai(),
                bill.getCreatedAt()
            });
        }
    }

    //đổ đữ liệu cho bảng giỏ hàng
    public void datarowShoppingCart(String id) {
        tableModel = (DefaultTableModel) tblShoppingCart.getModel();
        tableModel.setRowCount(0);
        int index = 1;
        // Khởi tạo biến để lưu tổng giá tiền
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (BillDetail billDetail : billDetailService.getBill_idBill(id)) {
            // Lấy giá sản phẩm từ đối tượng billDetail
            BigDecimal Product_price = billDetail.getProductDetailId().getProductId().getProduct_price();
            // Thiết lập giá trị khuyến mãi ban đầu là 0
            BigDecimal Sale_Product_price = BigDecimal.ZERO;
            // Kiểm tra xem có sự giảm giá được định nghĩa trong đối tượng sản phẩm và có giá trị lớn hơn hoặc bằng 0 không
            if (billDetail.getProductDetailId().getProductId().getSale_id() != null && billDetail.getProductDetailId().getProductId().getSale_id().getSale() >= 0.0) {
                // Nếu có khuyến mãi, gán giá trị giảm giá cho biến Sale_Product_price
                Sale_Product_price = BigDecimal.valueOf(billDetail.getProductDetailId().getProductId().getSale_id().getSale());
            }
            BigDecimal unitPrice = Product_price.subtract(Product_price.multiply(Sale_Product_price.divide(BigDecimal.valueOf(100.0))));
            //tính số tiền của sản phẩm chi tiết đó đã mua
            BigDecimal totalPriceForItem = unitPrice.multiply(BigDecimal.valueOf(Double.valueOf(billDetail.getQuantityPurchased())));
            tableModel.addRow(new Object[]{
                index++,
                billDetail.getProductDetailId().getProductId().getName_product(),
                billDetail.getProductDetailId().getColorId().getNameColor(),
                billDetail.getProductDetailId().getSizeId().getNameSize(),
                billDetail.getQuantityPurchased(),
                unitPrice.setScale(2, RoundingMode.HALF_UP) + " VND"
            });
            // Cộng giá tiền của mỗi sản phẩm vào tổng
            totalAmount = totalAmount.add(totalPriceForItem);
        }
        resetformbill();
        jLtotal_cost.setText(String.valueOf(totalAmount.setScale(2, RoundingMode.HALF_UP)));
        jLinto_money.setText(String.valueOf(totalAmount.setScale(2, RoundingMode.HALF_UP)));

    }

    //thêm sản phẩm lên form SP
    public void filltableSP(com.model.Product product, int index) {
        ProductDetail pd = productDetailService.get_ProductDetails_id_Bill(product.getId(), "1").get(index);
        jLSTT.setText(String.valueOf(index + 1));
        jLname.setText(pd.getProductId().getName_product());
        jLColor.setText(pd.getColorId().getNameColor());
        jLSize.setText(pd.getSizeId().getNameSize());
    }

    public void resetProduct() {
        jLSTT.setText("");
        jLname.setText("");
        jLColor.setText("");
        jLSize.setText("");
        txtsoluong.setText("");
        tableModel = (DefaultTableModel) tblProductDetail.getModel();
        tableModel.setRowCount(0);
    }

    //Kiểm tra số điện thoại
    public Boolean checkphone() {
        try {
            String PhoneNumber = txtPhoneNumber.getText();
            nowDate = getCurrentDateTime();
            if (PhoneNumber.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng không được để trống!");
                return false;
            } else if (!vl.isCheckPhone(PhoneNumber)) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng số điện thoại cần 10 kí tự và bắt đầu bằng số 0");
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            //e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Vui Lòng nhập lại!");
            return false;
        }
    }

    //Kiểm tra tên khách hàng
    public Boolean checkKH() {
        try {
            String nameKH = txtUser.getText();
            nowDate = getCurrentDateTime();
            if (nameKH.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng không được để trống tên KH!");
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            //e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Vui Lòng nhập lại!");
            return false;
        }
    }

    //Kiểm tra số lượng
    public Boolean checkSl() {
        //Lấy vị trí click của sản phẩm chi tiết
        int indexProductDetail = tblProductDetail.getSelectedRow();
        //Lấy vị trí click của sản phẩm
        int indexProduct = tblProduct.getSelectedRow();
        if (indexProduct >= 0) {
            if (indexProductDetail >= 0) {
                try {
                    String SL = txtsoluong.getText();
                    nowDate = getCurrentDateTime();
                    //lấy dữ liệu của đối tượng sản phẩm tại vị trí indexProduct
                    com.model.Product product = productService.getList_sale().get(indexProduct);
                    ProductDetail pd = productDetailService.get_ProductDetails_id_Bill(product.getId(), "1").get(indexProductDetail);
                    if (SL.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Vui lòng không được để trống số lượng!");
                        return false;
                    } else if (Integer.valueOf(SL) > Integer.valueOf(pd.getQuantity())) {
                        JOptionPane.showMessageDialog(this, "Số lượng bạn mua vượt quá" + "\n" + "số lượng sản phẩm còn lại trong kho");
                        return false;
                    } else if (Integer.valueOf(SL) < Integer.valueOf("0")) {
                        JOptionPane.showMessageDialog(this, "Số lượng không được âm");
                        return false;
                    } else {
                        return true;
                    }
                } catch (Exception e) {
                    //e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Vui Lòng nhập lại!");
                    return false;
                }
            } else {
                JOptionPane.showMessageDialog(this, "Bạn cần chọn Sản phẩm chi tiết");
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Bạn cần chọn Sản phẩm");
            return false;
        }
    }

    //Kiểm tra id voucher
    public Boolean checkVoucher() {
        String nameVoucher = txtVoucher.getText();
        try {
//            
            if (nameVoucher.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng không được để trống tên Voucher!");
                return false;
            }
            if (!vl.isCheckVoucher(nameVoucher)) {
                JOptionPane.showMessageDialog(this, "Định dạng của voucher không đúng");
                return false;
            } //            else if (voucher == null) {
            //                JOptionPane.showMessageDialog(this, "Voucher không tồn tại!");
            //                return false;
            //            }
            else {
                return true;
            }
        } catch (Exception e) {
            //e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Vui Lòng nhập lại voucher!");
            return false;

        }
    }

    //lấy thông tin sản phẩm không bán nữa
    public ArrayList<BillDetail> listBillDetail_0() {
        //Lấy vị trí click của bill
        int indexBill = tblbill.getSelectedRow();
        ArrayList<BillDetail> list = new ArrayList<>();
        try {
            if (indexBill >= 0) {
                //lấy dữ liệu của đối tượng sản phẩm chi tiết  tại vị trí indexBill
                Bill bill = billService.getListBill_0().get(indexBill);
                for (BillDetail billDetail : billDetailService.getBill_idBill_0(bill.getId())) {
                    list.add(billDetail);
                }
                return list;
            } else {
                JOptionPane.showMessageDialog(this, "Bạn cần chọn Bill");
                return null;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi dữ liệu");
            //e.printStackTrace();
            return null;
        }
    }

    //hỏi xem người dùng có muốn xóa sản phẩm không bán nữa trong cửa hàng hay không 
    public boolean checkHoiDeleteListBillDetail_0() {
        try {
            if (!listBillDetail_0().isEmpty()) {
                String string0 = "";
                int index = 1;
                for (BillDetail billDetail : listBillDetail_0()) {
                    String string1 = billDetail.getProductDetailId().getProductId().getName_product()
                            + "|"
                            + billDetail.getProductDetailId().getColorId().getNameColor()
                            + "|"
                            + billDetail.getProductDetailId().getSizeId().getNameSize();
                    string0 = string0 + "\n" + string1;
                }
                int hoi = JOptionPane.showConfirmDialog(this, "Hiện tại trong giỏ hàng có sản phẩm:" + string0 + "\nĐã dừng bán bạn có muốn xóa sản phẩm đó \n" + "không");
                if (hoi != JOptionPane.YES_NO_OPTION) {
                    JOptionPane.showMessageDialog(this, "Bạn đã không xóa sản phẩm ");
                    return false;
                }
                //delete billDetail where id 
                for (BillDetail billDetail : listBillDetail_0()) {
                    billDetailService.delete_bill_datail_ShoppingCart(billDetail);
                }
                JOptionPane.showMessageDialog(this, "Bạn đã xóa sản phẩm ");
                return true;
            } else {
                return true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi dữ liệu");
            //e.printStackTrace();
            return false;
        }
    }
    //lấy thông tin sản phẩm vượt quá số lượng hàng trong kho 

    public ArrayList<BillDetail> listBillDetailQuantity() {
        //Lấy vị trí click của bill
        int indexBill = tblbill.getSelectedRow();
        ArrayList<BillDetail> list = new ArrayList<>();
        try {
            if (indexBill >= 0) {
                //lấy dữ liệu của đối tượng sản phẩm chi tiết  tại vị trí indexBill
                Bill bill = billService.getListBill_0().get(indexBill);
                for (BillDetail billDetail : billDetailService.getBill_idBill(bill.getId())) {
                    if (Integer.parseInt(billDetail.getQuantityPurchased()) > productDetailService.getById(billDetail.getProductDetailId().getId()).getQuantity()) {
                        list.add(billDetail);
                    }
                }
                return list;
            } else {
                JOptionPane.showMessageDialog(this, "Bạn cần chọn Bill");
                return null;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi dữ liệu");
            //e.printStackTrace();
            return null;
        }
    }

    //hỏi xem người dùng có muốn lấy số sản phẩm còn lại trong kho hay không hay không 
    public boolean checkHoiUpdateListBillDetail() {
        try {
            if (!listBillDetailQuantity().isEmpty()) {
                String string0 = "";
                for (BillDetail billDetail : listBillDetailQuantity()) {
                    String string1 = billDetail.getProductDetailId().getProductId().getName_product()
                            + "|"
                            + billDetail.getProductDetailId().getColorId().getNameColor()
                            + "|"
                            + billDetail.getProductDetailId().getSizeId().getNameSize();
                    string0 = string0 + "\n" + string1;
                }
                int hoi = JOptionPane.showConfirmDialog(this, "Hiện tại trong giỏ hàng có sản phẩm:" + string0 + "\nĐã vượt quá số lượng trong kho bạn có muốn \n" + "Lấy sản phẩm đó không");
                if (hoi != JOptionPane.YES_NO_OPTION) {
                    JOptionPane.showMessageDialog(this, "Bạn đã giữ sản phẩm ");
                    return false;
                }
                for (BillDetail billDetail : listBillDetailQuantity()) {
                    if (productDetailService.getById(billDetail.getProductDetailId().getId()).getQuantity()>0) {
                        billDetailService.Update_bill_datail(productDetailService.getById(billDetail.getProductDetailId().getId()).getQuantity(), billDetail.getId());
                        return false;
                    }else{
                        billDetailService.delete_bill_datail_ShoppingCart(billDetail);
                        String nameProduct = billDetail.getProductDetailId().getProductId().getName_product()+"|"+billDetail.getProductDetailId().getSizeId().getNameSize()+"|"+billDetail.getProductDetailId().getColorId().getNameColor();
                        JOptionPane.showMessageDialog(this, "Sản phẩm: "+nameProduct+" Đã hết hàng");
                        return false;
                    }                    
                }                
            } else {
                return true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi dữ liệu");
            //e.printStackTrace();
            return false;
        }
        return false;
    }

    //kiểm tra xem voucher có đc sử dụng hay không 
    public String checkVoucherUsedFlag() {
        String idVoucher = null;
        try {
            if (Double.parseDouble("0.0") < Double.parseDouble(jLGiamGia.getText())) {
                idVoucher = txtVoucher.getText();
//                voucherResponsitory.updateStatus(idVoucher);
                return idVoucher;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi dữ liệu");
            //e.printStackTrace();
        }
        return null;
    }

    //kiểm tra xem sản phẩm chi tiết đã hết hàng hay chưa
    public void checkProcuctDetail(String idProduct) {
        try {
            for (ProductDetail productDetail : productDetailService.get_ProductDetails_id_Bill(idProduct, "1")) {
            if (productDetail.getQuantity() == 0) {
                productDetailService.Update_procuct_detail_billdetail(productDetail.getId());
            }
        }
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    //kiểm tra xem sản phẩm  đã hết hàng hay chưa
    public void checkProcuct(String idProduct) {
        try {
            checkProcuctDetail(idProduct);
        if (productDetailService.get_ProductDetails_id_Bill(idProduct, "1").isEmpty()) {
            productService.delete_product_bill(idProduct);
        }
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    public void resetShoppingCart() {
        txtUser.setText("");
        txtPhoneNumber.setText("");
        jLKH.setVisible(false);
        txtPhoneNumber.setEditable(true);
        txtUser.setVisible(false);
        lblUser.setVisible(false);
        datarowBill();
        tableModel = (DefaultTableModel) tblShoppingCart.getModel();
        tableModel.setRowCount(0);
        resetformbill();
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
        jPanel3 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        slpBill = new javax.swing.JScrollPane();
        tblbill = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        slpShoppingCart = new javax.swing.JScrollPane();
        tblShoppingCart = new javax.swing.JTable();
        bthdeleteproduct = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLKH = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtPhoneNumber = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        lblUser = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        tblKTvoucher = new javax.swing.JButton();
        bthresetvoucher = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLtotal_cost = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLGiamGia = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLinto_money = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtVoucher = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        bthpayment = new javax.swing.JButton();
        bthaddbill = new javax.swing.JButton();
        bthdeleteBill = new javax.swing.JButton();
        bthresetShoppingCart = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        slpProduct = new javax.swing.JScrollPane();
        tblProduct = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        slpProduct_detail = new javax.swing.JScrollPane();
        tblProductDetail = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        bthADDProduct_GioHang = new javax.swing.JButton();
        BthResetProduct = new javax.swing.JButton();
        jLSTT = new javax.swing.JLabel();
        jLname = new javax.swing.JLabel();
        jLColor = new javax.swing.JLabel();
        jLSize = new javax.swing.JLabel();
        txtsoluong = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblbill.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã HD", "Tên KH", "SDT", "Trạng thái", "Ngày tạo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblbill.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblbillMouseClicked(evt);
            }
        });
        slpBill.setViewportView(tblbill);
        if (tblbill.getColumnModel().getColumnCount() > 0) {
            tblbill.getColumnModel().getColumn(0).setMinWidth(40);
            tblbill.getColumnModel().getColumn(0).setMaxWidth(40);
            tblbill.getColumnModel().getColumn(1).setMinWidth(50);
            tblbill.getColumnModel().getColumn(1).setMaxWidth(50);
        }

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel9.setText("Danh Sách hóa đơn");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(slpBill, javax.swing.GroupLayout.DEFAULT_SIZE, 736, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(slpBill, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 750, -1));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel10.setText("Giỏ hàng");

        tblShoppingCart.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên SP ", "Color", "Size", "Số lượng", "Đơn giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        slpShoppingCart.setViewportView(tblShoppingCart);
        if (tblShoppingCart.getColumnModel().getColumnCount() > 0) {
            tblShoppingCart.getColumnModel().getColumn(0).setMinWidth(40);
            tblShoppingCart.getColumnModel().getColumn(0).setMaxWidth(40);
            tblShoppingCart.getColumnModel().getColumn(2).setMinWidth(70);
            tblShoppingCart.getColumnModel().getColumn(2).setMaxWidth(70);
            tblShoppingCart.getColumnModel().getColumn(3).setMinWidth(70);
            tblShoppingCart.getColumnModel().getColumn(3).setMaxWidth(70);
            tblShoppingCart.getColumnModel().getColumn(4).setMinWidth(70);
            tblShoppingCart.getColumnModel().getColumn(4).setMaxWidth(70);
        }

        bthdeleteproduct.setText("Xóa SP");
        bthdeleteproduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthdeleteproductActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(slpShoppingCart, javax.swing.GroupLayout.DEFAULT_SIZE, 736, Short.MAX_VALUE)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(bthdeleteproduct, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(slpShoppingCart, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(bthdeleteproduct, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 296, 750, 325));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLKH.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLKH.setText("Tên KH:");
        jPanel10.add(jLKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel13.setText("SDT:");
        jPanel10.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        txtPhoneNumber.setBorder(null);
        jPanel10.add(txtPhoneNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(89, 20, 220, 24));

        jLabel21.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel21.setText("_____________________________________________");
        jLabel21.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel10.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, 230, 10));

        txtUser.setBorder(null);
        jPanel10.add(txtUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 220, 24));

        lblUser.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        lblUser.setText("_____________________________________________");
        lblUser.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel10.add(lblUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, 230, 10));

        jPanel9.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 340, 120));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel15.setText("Mã Voucher:");
        jPanel11.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 80, -1));

        tblKTvoucher.setText("Kiểm Tra Voucher");
        tblKTvoucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tblKTvoucherActionPerformed(evt);
            }
        });
        jPanel11.add(tblKTvoucher, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 130, 41));

        bthresetvoucher.setText("Làm mới Voucher");
        bthresetvoucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthresetvoucherActionPerformed(evt);
            }
        });
        jPanel11.add(bthresetvoucher, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 200, 130, 41));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel14.setText("Tổng tiền hàng:");
        jPanel4.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLtotal_cost.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLtotal_cost.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLtotal_cost.setText("360000");
        jPanel4.add(jLtotal_cost, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 150, -1));

        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel12.setText("VND");
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, -1, -1));

        jPanel11.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 320, 40));

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel16.setText("% Giảm giá:");
        jPanel13.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLGiamGia.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLGiamGia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLGiamGia.setText("3");
        jPanel13.add(jLGiamGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 190, -1));

        jLabel19.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel19.setText("%");
        jPanel13.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, -1, -1));

        jPanel11.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 320, 40));

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel17.setText("Thành Tiền:");
        jPanel14.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLinto_money.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLinto_money.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLinto_money.setText("349200");
        jPanel14.add(jLinto_money, new org.netbeans.lib.awtextra.AbsoluteConstraints(94, 10, 170, -1));

        jLabel18.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel18.setText("VND");
        jPanel14.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, -1, 20));

        jPanel11.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 320, 40));

        txtVoucher.setBorder(null);
        jPanel11.add(txtVoucher, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 220, 24));

        jLabel23.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel23.setText("_____________________________________________");
        jLabel23.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel11.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, 230, 10));

        jPanel9.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 340, 250));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bthpayment.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        bthpayment.setText("Thanh Toán");
        bthpayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthpaymentActionPerformed(evt);
            }
        });
        jPanel12.add(bthpayment, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 130, 50));

        bthaddbill.setText("Tạo hóa đơn");
        bthaddbill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthaddbillActionPerformed(evt);
            }
        });
        jPanel12.add(bthaddbill, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, 130, 50));

        bthdeleteBill.setText("Xóa");
        bthdeleteBill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthdeleteBillActionPerformed(evt);
            }
        });
        jPanel12.add(bthdeleteBill, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 130, 50));

        bthresetShoppingCart.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        bthresetShoppingCart.setText("Làm mới form");
        bthresetShoppingCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthresetShoppingCartActionPerformed(evt);
            }
        });
        jPanel12.add(bthresetShoppingCart, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 130, 50));

        jPanel9.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, 340, 160));

        jPanel3.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 41, 360, 580));

        jLabel11.setText("Tạo hóa đơn");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 10, -1, -1));

        swingTabbedPane1.addTab("Giỏ Hàng", jPanel3);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã SP", "Tên SP", "Giá", "% Khuyến Mãi", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductMouseClicked(evt);
            }
        });
        slpProduct.setViewportView(tblProduct);
        if (tblProduct.getColumnModel().getColumnCount() > 0) {
            tblProduct.getColumnModel().getColumn(0).setMinWidth(50);
            tblProduct.getColumnModel().getColumn(0).setMaxWidth(50);
            tblProduct.getColumnModel().getColumn(1).setMinWidth(60);
            tblProduct.getColumnModel().getColumn(1).setMaxWidth(100);
            tblProduct.getColumnModel().getColumn(2).setMinWidth(200);
            tblProduct.getColumnModel().getColumn(2).setMaxWidth(200);
            tblProduct.getColumnModel().getColumn(3).setMinWidth(140);
            tblProduct.getColumnModel().getColumn(3).setMaxWidth(140);
            tblProduct.getColumnModel().getColumn(4).setMinWidth(150);
            tblProduct.getColumnModel().getColumn(4).setMaxWidth(150);
        }

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel1.setText("Danh sách sản phẩm");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(slpProduct, javax.swing.GroupLayout.DEFAULT_SIZE, 756, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(slpProduct, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 27, 770, 290));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblProductDetail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên SP", "Color", "Size", "Số lượng", "Trạng thái"
            }
        ));
        tblProductDetail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductDetailMouseClicked(evt);
            }
        });
        slpProduct_detail.setViewportView(tblProductDetail);
        if (tblProductDetail.getColumnModel().getColumnCount() > 0) {
            tblProductDetail.getColumnModel().getColumn(0).setMinWidth(20);
            tblProductDetail.getColumnModel().getColumn(0).setMaxWidth(40);
            tblProductDetail.getColumnModel().getColumn(1).setMinWidth(200);
            tblProductDetail.getColumnModel().getColumn(1).setMaxWidth(230);
            tblProductDetail.getColumnModel().getColumn(2).setMinWidth(70);
            tblProductDetail.getColumnModel().getColumn(2).setMaxWidth(70);
        }

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel2.setText("Chi tiết sản phẩm");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(slpProduct_detail, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(slpProduct_detail, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 337, 770, 280));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel4.setText("STT:");
        jPanel6.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 58, -1, -1));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel5.setText("Tên SP:");
        jPanel6.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 108, -1, -1));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel6.setText("Color:");
        jPanel6.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 165, -1, -1));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel7.setText("Size:");
        jPanel6.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 221, -1, -1));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel8.setText("Số lượng:");
        jPanel6.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 277, -1, -1));

        bthADDProduct_GioHang.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        bthADDProduct_GioHang.setText("Thêm sản phẩm");
        bthADDProduct_GioHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthADDProduct_GioHangActionPerformed(evt);
            }
        });
        jPanel6.add(bthADDProduct_GioHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 310, 62));

        BthResetProduct.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        BthResetProduct.setText("Xóa Form");
        BthResetProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BthResetProductActionPerformed(evt);
            }
        });
        jPanel6.add(BthResetProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, 310, 61));

        jLSTT.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLSTT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLSTT.setText("1");
        jPanel6.add(jLSTT, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 58, 234, -1));

        jLname.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLname.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLname.setText("Levents");
        jPanel6.add(jLname, new org.netbeans.lib.awtextra.AbsoluteConstraints(71, 108, 213, -1));

        jLColor.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLColor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLColor.setText("Trắng");
        jPanel6.add(jLColor, new org.netbeans.lib.awtextra.AbsoluteConstraints(71, 165, 213, -1));

        jLSize.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLSize.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLSize.setText("Size 1 ");
        jPanel6.add(jLSize, new org.netbeans.lib.awtextra.AbsoluteConstraints(71, 221, 213, -1));

        txtsoluong.setBorder(null);
        jPanel6.add(txtsoluong, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 270, 209, 24));

        jLabel20.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel20.setText("__________________________________________");
        jLabel20.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel6.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 290, 210, 10));

        jPanel2.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(797, 27, 330, 590));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setText("Thêm sản phẩm vào giỏ hàng");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 0, -1, -1));

        swingTabbedPane1.addTab("Chọn Sản Phẩm", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(swingTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(swingTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 666, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblProductDetailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductDetailMouseClicked
        int indexProduct = tblProduct.getSelectedRow();
        com.model.Product product = productService.getList_sale().get(indexProduct);
        int indexProductDetail = tblProductDetail.getSelectedRow();
        filltableSP(product, indexProductDetail);
    }//GEN-LAST:event_tblProductDetailMouseClicked

    private void BthResetProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BthResetProductActionPerformed
        datarowProcuct();
        resetProduct();
    }//GEN-LAST:event_BthResetProductActionPerformed

    private void bthADDProduct_GioHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthADDProduct_GioHangActionPerformed
        //Lấy vị trí click của bill
        int indexBill = tblbill.getSelectedRow();
        //Lấy vị trí click của sản phẩm chi tiết
        int indexProductDetail = tblProductDetail.getSelectedRow();
        //Lấy vị trí click của sản phẩm
        int indexProduct = tblProduct.getSelectedRow();
        try {
            //kiểm tra vị trí của bill
            if (indexBill >= 0) {
                //kiểm tra vị trí của sản phẩm
                if (indexProduct >= 0) {
                    //kiểm tra vị trí của sản phẩm chi tiết
                    if (indexProductDetail >= 0) {
                        if (checkSl()) {
                            //lấy dữ liệu của đối tượng sản phẩm tại vị trí indexProduct
                            com.model.Product product = productService.getList_sale().get(indexProduct);
                            //kiểm tra sự tồn tại của sản phẩm 
                            if (product != null) {
                                //lấy dữ liệu của đối tượng sản phẩm chi tiết  tại vị trí indexProductDetail
                                ProductDetail pd = productDetailService.get_ProductDetails_id_Bill(product.getId(), "1").get(indexProductDetail);
                                //kiểm tra sự tồn tại của sản phẩm chi tiết 
                                if (pd != null) {
                                    //lấy dữ liệu của đối tượng sản phẩm chi tiết  tại vị trí indexBill
                                    Bill bill = billService.getListBill_0().get(indexBill);
                                    // lấy thời gian hiện tại 
                                    nowDate = getCurrentDateTime();
                                    //kiểm tra sự tồn tại của bill
                                    if (bill != null) {
                                        //Lấy id của Bill
                                        String idBill = bill.getId();
                                        //Lấy id của ProductDetail
                                        String idProductDetail = pd.getId();
                                        //Lấy id của BillDetail
                                        String idBillDetail = null;
                                        //kiểm tra đối tượng ProductDetail đã tồn tại hay chưa 
                                        boolean checkProductDetail = false;
                                        //tạo biến chứa giá trị của số lương của sản phẩm người dùng nhập
                                        int quantity_product_detail_txt = Integer.parseInt(txtsoluong.getText());
                                        //tạo biến chứa giá trị của số lương của sản phẩm trong billDetail
                                        int quantity_product_billDetail = 0;
                                        //tạo biến chứa giá trị của số lương của sản phẩm trong product_detail
                                        int quantity_product_detail = pd.getQuantity();
                                        //vòng lậy để so sánh id của ProductDetail có tồn tại trong BillDetail hay không 
                                        for (BillDetail billDetail : billDetailService.getBill_idBill(idBill)) {
                                            //kiểm tra xem đối tượng đã tồn tại hay chưa 
                                            if (billDetail.getProductDetailId().getId().equals(idProductDetail)) {
                                                //lấy id của billDetail đó 
                                                idBillDetail = billDetail.getId();
                                                //lấy dữ liệu của sản phẩm trong billdetail
                                                quantity_product_billDetail = Integer.parseInt(billDetail.getQuantityPurchased());
                                                //Đối tượng đã tồn tại chuyển trạng thái sang true
                                                checkProductDetail = true;
                                            }
                                        }
                                        //Khi tối tượng đã tồn tại rồi 
                                        if (checkProductDetail) {
                                            //biến chứa giá trị tổng khi thêm số lượng vào giỏ hàng 
                                            int all_quantity_product_billDetail = quantity_product_detail_txt + quantity_product_billDetail;
                                            //Kiểm tra xem số lượng tổng của sản phẩm nhập + vs sản phẩm trong giỏ hàng có lơn hơn số lượng trong kho hay không
                                            if (all_quantity_product_billDetail > quantity_product_detail) {
                                                JOptionPane.showMessageDialog(this, "Số lượng hàng trong giỏ vượt quá số lượng hàng còn lại trong kho!");
                                            } else {
                                                //kiểm tra xem đã cập nhật lại số lượng hay chưa
                                                boolean checkUpdateProductDetail = billDetailService.Update_bill_datail(all_quantity_product_billDetail, idBillDetail);
                                                if (checkUpdateProductDetail) {
                                                    JOptionPane.showMessageDialog(this, "Đã cập số lượng số lượng hàng trong giỏ hàng thành công!");
                                                } else {
                                                    JOptionPane.showMessageDialog(this, "Lỗi cập số lượng số lượng hàng trong giỏ hàng !");
                                                }
                                            }
                                        }
                                        //Khi tối tượng chưa tồn tại thì sẽ tạo ra 1 đối tượng billDetail mới  
                                        if (!checkProductDetail) {
                                            //kiểm tra xem đã thêm thành công chưa
                                            boolean checkAddProductDetail = billDetailService.add_bill_datail(
                                                    new BillDetail(nowDate, nowDate, txtsoluong.getText()),
                                                    idBill,
                                                    idProductDetail);
                                            if (checkAddProductDetail) {
                                                JOptionPane.showMessageDialog(this, "Đã thêm vào giỏ hàng thành công!");
                                            } else {
                                                JOptionPane.showMessageDialog(this, "Đã thêm vào giỏ hàng thất bại!");
                                            }
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(this, "Lỗi dữ liệu hóa đơn!");
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(this, "Lỗi dữ liệu sản phẩm chi tiết!");
                                }
                            } else {
                                JOptionPane.showMessageDialog(this, "Lỗi dữ liệu sản phẩm!");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Bạn cần chọn Sản phẩm chi tiết");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Bạn cần chọn Sản phẩm ");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Bạn cần chọn Bill");
            }
        } catch (Exception e) {
            //e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi dữ liệu");
        }
        resetProduct();
        datarowBill();
        tableModel = (DefaultTableModel) tblShoppingCart.getModel();
        tableModel.setRowCount(0);
    }//GEN-LAST:event_bthADDProduct_GioHangActionPerformed

    private void tblProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductMouseClicked
        int index = tblProduct.getSelectedRow();
        com.model.Product product = productService.getList_sale().get(index);
        datarowProcuctDetail(product.getId());
    }//GEN-LAST:event_tblProductMouseClicked

    private void bthresetShoppingCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthresetShoppingCartActionPerformed
        resetShoppingCart();
    }//GEN-LAST:event_bthresetShoppingCartActionPerformed

    private void bthdeleteBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthdeleteBillActionPerformed
        int indexBill = tblbill.getSelectedRow();
        try {
            if (indexBill >= 0) {
                Bill bill = billService.getListBill_0().get(indexBill);
                if (bill != null) {
                    int hoi = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa không?");
                    if (hoi != JOptionPane.YES_NO_OPTION) {
                        JOptionPane.showMessageDialog(this, "Bạn đã không xóa ");
                        return;
                    }
                    boolean checkBillDetail = billDetailService.delete_bill_datail(bill);
                    boolean checkBill = billService.delete_bill_id(bill);
                    if (checkBillDetail && checkBill) {
                        JOptionPane.showMessageDialog(this, "Đã Xóa Bill");
                    } else {
                        JOptionPane.showMessageDialog(this, "Xóa Bill bị lỗi");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Bạn cần chọn đối tượng Bill!");
            }
        } catch (Exception e) {
            //e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi dữ liệu!");
        }
        resetProduct();
        resetShoppingCart();
    }//GEN-LAST:event_bthdeleteBillActionPerformed

    private void bthaddbillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthaddbillActionPerformed
        try {
            if (checkphone() == true) {
                //tạo ra đối tượng user
                User u = null;
                //kiểm tra số điện thoại
                boolean checkName_phone = false;
                for (User user : userService.getUser_name_phone()) {
                    if (user.getNumberPhone().equals(txtPhoneNumber.getText())) {
                        // Nếu số điện thoại của người dùng trong danh sách khớp với số điện thoại nhập vào
                        // Gán người dùng tương ứng vào biến u và đặt cờ kiểm tra checkName_phone thành true
                        u = user;
                        checkName_phone = true;
                    }
                }
                //số điện thoại nếu tồn tại
                if (checkName_phone == true) {
                    if (u.getStatus().equals("1")) {
                        // Hiển thị và cấu hình các thành phần giao diện đồng thời
                        jLKH.setVisible(true); // Hiển thị nhãn chứa thông tin khách hàng
                        txtUser.setVisible(true); // Hiển thị ô văn bản để hiển thị thông tin tên khách hàng
                        lblUser.setVisible(true);
                        txtUser.setText(u.getFullName()); // Đặt văn bản trong ô là tên đầy đủ của khách hàng
                        txtUser.setEditable(false); // Vô hiệu hóa khả năng chỉnh sửa ô văn bản với thông tin khách hàng
                        // Vô hiệu hóa khả năng chỉnh sửa ô văn bản cho số điện thoại và hiển thị thông báo
                        txtPhoneNumber.setEditable(false); // Vô hiệu hóa khả năng chỉnh sửa ô văn bản cho số điện thoại
                        JOptionPane.showMessageDialog(this, "Số điện thoại của khách hàng: " + u.getFullName()); // Hiển thị hộp thoại thông báo với thông tin số điện thoại của khách hàng
                        // Lấy ngày và giờ hiện tại
                        nowDate = getCurrentDateTime();// Gán giá trị ngày và giờ hiện tại vào biến nowDate
                        //tạo hóa đơn mới
                        JOptionPane.showMessageDialog(this, billService.add_bill(new Bill(nowDate, nowDate, nowDate, u)));
                        resetShoppingCart();
                        return;
                    }
                    if (u.getStatus().equals("0")) {
                        JOptionPane.showMessageDialog(this, "Đối tượng này đã cấm!");
                        resetShoppingCart();
                        return;
                    }
                }
                if (checkName_phone == false) {
                    // Hiển thị và kích hoạt tính năng chỉnh sửa trên các thành phần giao diện
                    jLKH.setVisible(true); // Hiển thị nhãn chứa thông tin khách hàng
                    txtUser.setVisible(true); // Hiển thị ô văn bản để hiển thị thông tin tên khách hàng
                    lblUser.setVisible(true);
                    txtUser.setEditable(true); // Kích hoạt khả năng chỉnh sửa cho ô văn bản với thông tin tên khách hàng
                    // Lấy ngày và giờ hiện tại
                    nowDate = getCurrentDateTime(); // Gán giá trị ngày và giờ hiện tại vào biến nowDate
                }
                if (checkKH == true) {

                    if (checkKH() == true) {
                        u = new User(nowDate, nowDate, txtUser.getText(), txtPhoneNumber.getText());
                        //thêm user
                        userService.add_user(u);
                        //thêm role_user
                        userRoleService.add_user_role(u, String.valueOf("3"));
                        //thông báo kết quả
                        JOptionPane.showMessageDialog(this, billService.add_bill(new Bill(nowDate, nowDate, nowDate, new User(txtUser.getText(), txtPhoneNumber.getText()))));
                        checkKH = false;
                        resetShoppingCart();
                        return;
                    } else {
                        JOptionPane.showMessageDialog(this, "Thêm thất bại;");
                        checkKH = false;
                        return;
                    }

                }
                checkKH = true;
            }
        } catch (Exception e) {
            //e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi dữ liệu!");
        }
    }//GEN-LAST:event_bthaddbillActionPerformed

    private void bthpaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthpaymentActionPerformed
        //Lấy vị trí click của bill
        int indexBill = tblbill.getSelectedRow();
        try {
            if (indexBill >= 0) {
                if (checkHoiDeleteListBillDetail_0()) {
                    if (checkHoiUpdateListBillDetail()) {
                        //lấy dữ liệu của đối tượng sản phẩm chi tiết  tại vị trí indexBill
                        Bill bill = billService.getListBill_0().get(indexBill);
                        if (bill != null) {
                            String idBill = bill.getId();
                            BigDecimal into_money = BigDecimal.valueOf(Double.parseDouble(jLinto_money.getText()));
                            BigDecimal total_cost = BigDecimal.valueOf(Double.parseDouble(jLtotal_cost.getText()));
                            if (checkVoucherUsedFlag() != null) {
                                String idVoucher = checkVoucherUsedFlag();
                                boolean checkbillServiceVoucher = billService.updateVoucherByIdBill(idVoucher, idBill);
                                boolean checkbillServicemoney = billService.updatemoneyByIdBill(into_money, total_cost, idBill);
                                for (BillDetail billDetail : billDetailService.getBill_idBill(idBill)) {
                                    String idBillDetail = billDetail.getId();
                                    String idProductDetail = billDetail.getProductDetailId().getId();
                                    int quantityPurchased = Integer.parseInt(billDetail.getQuantityPurchased());
                                    // Lấy giá sản phẩm từ đối tượng billDetail
                                    BigDecimal Product_price = billDetail.getProductDetailId().getProductId().getProduct_price();
                                    // Thiết lập giá trị khuyến mãi ban đầu là 0
                                    BigDecimal Sale_Product_price = BigDecimal.ZERO;
                                    // Kiểm tra xem có sự giảm giá được định nghĩa trong đối tượng sản phẩm và có giá trị lớn hơn hoặc bằng 0 không
                                    if (billDetail.getProductDetailId().getProductId().getSale_id() != null && billDetail.getProductDetailId().getProductId().getSale_id().getSale() >= 0.0) {
                                        // Nếu có khuyến mãi, gán giá trị giảm giá cho biến Sale_Product_price
                                        Sale_Product_price = BigDecimal.valueOf(billDetail.getProductDetailId().getProductId().getSale_id().getSale());
                                    }
                                    BigDecimal unitPrice = Product_price.subtract(Product_price.multiply(Sale_Product_price.divide(BigDecimal.valueOf((long) 100.0))));
                                    billDetailService.updateprice_nowByIdBillDetail(unitPrice, idBillDetail);
                                    productDetailService.getQuantity(idProductDetail, quantityPurchased);

                                }
                                if (checkbillServiceVoucher && checkbillServicemoney) {
                                    JOptionPane.showMessageDialog(this, "Đã thanh toán thành công!");
                                } else {
                                    JOptionPane.showMessageDialog(this, "Đã thanh toán thất bại!");
                                }
                            } else {
                                boolean checkbillServicemoney = billService.updatemoneyByIdBill(into_money, total_cost, idBill);
                                for (BillDetail billDetail : billDetailService.getBill_idBill(idBill)) {
                                    String idBillDetail = billDetail.getId();
                                    String idProductDetail = billDetail.getProductDetailId().getId();
                                    int quantityPurchased = Integer.parseInt(billDetail.getQuantityPurchased());
                                    // Lấy giá sản phẩm từ đối tượng billDetail
                                    BigDecimal Product_price = billDetail.getProductDetailId().getProductId().getProduct_price();
                                    // Thiết lập giá trị khuyến mãi ban đầu là 0
                                    BigDecimal Sale_Product_price = BigDecimal.ZERO;
                                    // Kiểm tra xem có sự giảm giá được định nghĩa trong đối tượng sản phẩm và có giá trị lớn hơn hoặc bằng 0 không
                                    if (billDetail.getProductDetailId().getProductId().getSale_id() != null && billDetail.getProductDetailId().getProductId().getSale_id().getSale() >= 0.0) {
                                        // Nếu có khuyến mãi, gán giá trị giảm giá cho biến Sale_Product_price
                                        Sale_Product_price = BigDecimal.valueOf(billDetail.getProductDetailId().getProductId().getSale_id().getSale());
                                    }
                                    BigDecimal unitPrice = Product_price.subtract(Product_price.multiply(Sale_Product_price.divide(BigDecimal.valueOf((long) 100.0))));
                                    billDetailService.updateprice_nowByIdBillDetail(unitPrice, idBillDetail);
                                    productDetailService.getQuantity(idProductDetail, quantityPurchased);

                                }
                                if (checkbillServicemoney) {
                                    JOptionPane.showMessageDialog(this, "Đã thanh toán thành công!");
                                } else {
                                    JOptionPane.showMessageDialog(this, "Đã thanh toán thất bại!");
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Lỗi dữ liệu Bill");
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Bạn cần chọn Bill");
            }

        } catch (Exception e) {
            //e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi dữ liệu!");
        }
        resetShoppingCart();
    }//GEN-LAST:event_bthpaymentActionPerformed

    private void bthresetvoucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthresetvoucherActionPerformed
        jLGiamGia.setText("0.0");
        txtVoucher.setText("");
        jLinto_money.setText(jLtotal_cost.getText());
    }//GEN-LAST:event_bthresetvoucherActionPerformed

    private void tblKTvoucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tblKTvoucherActionPerformed
        try {
            int indexBill = tblbill.getSelectedRow();
            if (indexBill >= 0) {
                String idVoucher = txtVoucher.getText();
                BigDecimal total_cost = BigDecimal.valueOf(Double.parseDouble(jLinto_money.getText()));//200000
                BigDecimal into_money = BigDecimal.ZERO;
                boolean checkVoucher = false;
                BigDecimal sale = BigDecimal.ZERO;
                if (checkVoucher()) {
                    for (Voucher voucher : voucherResponsitory.getAllById(txtVoucher.getText(), "1")) {
                        if (voucher.getId().equals(idVoucher)) {
                            sale = BigDecimal.valueOf(voucher.getSaleOf());
                            checkVoucher = true;
                        }
                    }
                    if (checkVoucher) {
                        jLGiamGia.setText(String.valueOf(sale));
                        into_money = total_cost.subtract(total_cost.multiply(sale.divide(BigDecimal.valueOf(100.0))));
                        jLinto_money.setText(String.valueOf(into_money));
                    } else {
                        JOptionPane.showMessageDialog(this, "Voucher không tồn tại!");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Bạn cần chọn Bill!");
            }
        } catch (Exception e) {
            //e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi dữ liệu!");
        }
    }//GEN-LAST:event_tblKTvoucherActionPerformed

    private void bthdeleteproductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthdeleteproductActionPerformed
        int indexBillDetail = tblShoppingCart.getSelectedRow();
        int indexBill = tblbill.getSelectedRow();
        try {
            if (indexBill >= 0) {
                if (indexBillDetail >= 0) {
                    Bill bill = billService.getListBill_0().get(indexBill);
                    if (bill != null) {
                        BillDetail billDetail = billDetailService.getBill_idBill(bill.getId()).get(indexBillDetail);
                        if (billDetail != null) {
                            int hoi = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa không?");
                            if (hoi != JOptionPane.YES_NO_OPTION) {
                                JOptionPane.showMessageDialog(this, "Bạn đã không xóa sản phẩm trong giỏ hàng ");
                                return;
                            }
                            boolean check = billDetailService.delete_bill_datail_ShoppingCart(billDetail);
                            if (check) {
                                JOptionPane.showMessageDialog(this, "Đã Xóa sản phẩm");
                            } else {
                                JOptionPane.showMessageDialog(this, "=Xóa sản phẩm bị lỗi");
                            }
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Bạn cần chọn đối tượng trong giỏ hàng!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Bạn cần chọn đối tượng Bill!");
            }
        } catch (Exception e) {
            //e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi dữ liệu!");
        }
        resetProduct();
        resetformbill();
        datarowBill();
        tableModel = (DefaultTableModel) tblShoppingCart.getModel();
        tableModel.setRowCount(0);
    }//GEN-LAST:event_bthdeleteproductActionPerformed

    private void tblbillMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblbillMouseClicked
        //hiển thị sản phẩ trong giỏ hàng
        int index = tblbill.getSelectedRow();
        Bill b = billService.getListBill_0().get(index);
        datarowShoppingCart(b.getId());
    }//GEN-LAST:event_tblbillMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BthResetProduct;
    private javax.swing.JButton bthADDProduct_GioHang;
    private javax.swing.JButton bthaddbill;
    private javax.swing.JButton bthdeleteBill;
    private javax.swing.JButton bthdeleteproduct;
    private javax.swing.JButton bthpayment;
    private javax.swing.JButton bthresetShoppingCart;
    private javax.swing.JButton bthresetvoucher;
    private javax.swing.JLabel jLColor;
    private javax.swing.JLabel jLGiamGia;
    private javax.swing.JLabel jLKH;
    private javax.swing.JLabel jLSTT;
    private javax.swing.JLabel jLSize;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLinto_money;
    private javax.swing.JLabel jLname;
    private javax.swing.JLabel jLtotal_cost;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel lblUser;
    private javax.swing.JScrollPane slpBill;
    private javax.swing.JScrollPane slpProduct;
    private javax.swing.JScrollPane slpProduct_detail;
    private javax.swing.JScrollPane slpShoppingCart;
    private com.swing.SwingTabbedPane swingTabbedPane1;
    private javax.swing.JButton tblKTvoucher;
    private javax.swing.JTable tblProduct;
    private javax.swing.JTable tblProductDetail;
    private javax.swing.JTable tblShoppingCart;
    private javax.swing.JTable tblbill;
    private javax.swing.JTextField txtPhoneNumber;
    private javax.swing.JTextField txtUser;
    private javax.swing.JTextField txtVoucher;
    private javax.swing.JTextField txtsoluong;
    // End of variables declaration//GEN-END:variables
}
