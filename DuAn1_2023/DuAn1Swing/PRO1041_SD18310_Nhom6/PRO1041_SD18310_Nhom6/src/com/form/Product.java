/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.form;

import com.model.Category;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import com.model.Color;
import com.model.Custom;
import com.model.Material;
import com.model.ProductCategory;
import com.model.ProductDetail;
import com.model.Size;
import com.model.Thickness;
import com.service.CategoryService;
import com.service.ColorService;
import com.service.CustomService;
import com.service.MaterialServict;
import com.service.ProductDetailService;
import com.service.ProductService;
import com.service.Product_CategoryServict;
import com.service.SizeSevice;
import com.service.ThicknessService;
import com.service.imple.CategoryImple;
import com.service.imple.ColorImple;
import com.service.imple.CustomImple;
import com.service.imple.MaterialImple;
import com.service.imple.ProductDetailImple;
import com.service.imple.ProductImple;
import com.service.imple.Product_CategoryImple;
import com.service.imple.SizeImple;
import com.service.imple.ThicknessImple;
import com.swing.EditButtons;
import com.swing.EditTextField;
import java.awt.Font;
import java.io.ByteArrayOutputStream;
import javax.swing.JComboBox;
import javax.swing.JTable;
import table.TableCustom;

/**
 *
 * @author thiet
 */
public class Product extends javax.swing.JPanel {

    private ProductService pds;
    private ProductDetailService pdds;
    private MaterialServict mts;
    private ThicknessService tns;
    private CustomService cts;
    private SizeSevice ss;
    private ColorService cls = new ColorImple();
    //them vao 3/12
    private CategoryService ctg;

    //them vao 4/12
    private Product_CategoryServict pcs;

    File file = new File("");
    DefaultComboBoxModel dcm;

//up date them 2 thang moi 
    int minProduct_tab1 = 1;
    int maxProduct_tab1 = 10;
    int minProduct_detail = 1;
    int maxProduct_detail = 10;
    int minProduct_tab3 = 1;
    int maxProduct_tab3 = 10;
    //them cai nay ngay 28//11

    int maxProduct_Stop_Sell = 10;
    int minProduct_Stop_Sell = 1;
    int maxProduct_Detail_Stop_Sell = 10;
    int minProduct_Detail_Stop_Sell = 1;
    //them vao 3/12

    int maxProduct_not_Category = 10;
    int minProduct_not_Category = 1;
    int maxProduct_Category = 10;
    int minProduct_Category = 1;

    int maxCategory = 10;
    int minCategory = 1;
    int maxCategory_Delete = 10;
    int minCategory_Delete = 1;
    //them 1/12
    int maxAttribute = 10;
    int minAttribute = 1;

    //them 12/12
    int maxAttribute_Stop = 10;
    int minAttribute_stop = 1;
    //het
    //cai nay viet trc r
    String idProduct;
    String idProduct_Extra;
    String idProduct_Detail;
    String idTT;
    String idProduct_Stop_Sell;
    String idProduct_Detail_Stop_Sell;
    String nameImage_Product;
    String nameImage_Product_Detail;
    //them vao 3/12
    String namePr_Category = "%%";
    String nameCategory = "%%";
    String namePr_not_category = "%%";
    String id_Category;
    String id_Category_Delete;
    String id_Pr_Not_Ctegory;
    String id_Pr_Category;
    //them vao 4/12
    String id_cbbDanhMuc;
    //them vao 11/12
    byte[] name_img_mount;
    String idTT_Stop;
    //het
    //them cai nay 2/12
    EditButtons bt = new EditButtons();
    EditTextField txt = new EditTextField();
    BufferedImage editedImage;
    BufferedImage editedImage_Detail;

    /**
     * Creates new form Product
     */
    public Product() {
        initComponents();
        pds = new ProductImple();
        pdds = new ProductDetailImple();
        mts = new MaterialImple();
        tns = new ThicknessImple();
        cts = new CustomImple();
        ss = new SizeImple();
        cls = new ColorImple();
        //them 3/12
        this.ctg = new CategoryImple();
        //them 4/12
        this.pcs = new Product_CategoryImple();
//        this.editTbl(tblProduct);
//        this.editTbl(tblProduct_Detail1);
        //them 3/12
        this.loadcbbCustom();
        this.loadcbbMaterial();
        this.loadcbbThickness();
        this.loadcbbSize();
        this.loadcbbColor();
        this.loadcbbDanhMuc();
        nameCategory = String.valueOf(cbbCategory.getItemAt(0));

        this.loadCatory_not_Pr();
        this.loadCatory_Pr();
        this.loadCatory();
        this.load_Product();
        this.loadProduct_Stop();
        this.loadCatory_Delete();

        this.loadProduct_Deteail_Stop_Sell();

        this.loadSize();
        this.loadSize_Stop();
//        this.loadcbbProduct();
        //them vao 3/12
        //them 2/12
        TableCustom.apply(slpProduct, TableCustom.TableType.MULTI_LINE);
        TableCustom.apply(slpProduct_Detail, TableCustom.TableType.MULTI_LINE);
        TableCustom.apply(slpAttribute, TableCustom.TableType.MULTI_LINE);
        TableCustom.apply(slpProduct_Has_No_Category_Yet, TableCustom.TableType.MULTI_LINE);
        TableCustom.apply(slpProduct_Detail_Stop_Sell, TableCustom.TableType.MULTI_LINE);
        TableCustom.apply(slpProduc_Stop_sell, TableCustom.TableType.MULTI_LINE);
        TableCustom.apply(slpDanhMuc, TableCustom.TableType.MULTI_LINE);
        // them 3/12
        TableCustom.apply(slpDM, TableCustom.TableType.MULTI_LINE);
        TableCustom.apply(slpCategory_Delete, TableCustom.TableType.MULTI_LINE);
        bt.Edit(btnAdd_Product);
        bt.Edit(btnClear_Product);
        bt.Edit(btnFix_Produc);
        bt.Edit(btnStop_Sell_Product);

        bt.Edit(btnAdd_Product_Detail1);
        bt.Edit(btnClear_Product_Detail1);
        bt.Edit(btnFix_Product_Detail1);
        bt.Edit(btnStop_Sell_Product_Detail1);

        bt.Edit(btnAdd_Attribute);
        bt.Edit(btnClear_Attribute);
        bt.Edit(btnFix_Attribute);
        bt.Edit(btnStop_Working_Attribute);

        bt.Edit(btnRestore_Product);

        bt.Edit(btnRestore_Product_Detail_Stop_Sell);

        bt.Edit(btnStop_Working_Attribute);

        bt.Edit(btnNext__Product);
        bt.Edit(btnPre__Product);

        bt.Edit(btnNext_Product_Detail1);
        bt.Edit(btnPre_Product_Detail1);

        bt.Edit(btnNext_Attribute);
        bt.Edit(btnPre_Attribute);

        bt.Edit(btnNext_Product_Stop_Sell);
        bt.Edit(btnPre_Product_Stop_Sell);

        bt.Edit(btnNext_Product_Detail_Stop_Sell);
        bt.Edit(btnPre_Product_Detail_Stop_Sell);

        //them 3/12
        bt.Edit(btnScan_PR_Category);
        bt.Edit(btnScan_PR_Not_Category);
        bt.Edit(btnThem_pr_DM);
        bt.Edit(btnXoa_prDM);
        bt.Edit(btnNext_DanhMuc);
        bt.Edit(btnPre_DanhMuc);
        bt.Edit(btnNext_Product_Has_No_Category_Yet);
        bt.Edit(btnPre_Product_Has_No_Category_Yet);
        bt.Edit(btnPre_Category_Delete);
        bt.Edit(btnNext_Category_Delete);
        bt.Edit(btnKhoiPhuc);

        bt.Edit(btnAdd_DM);
        bt.Edit(btnSua_DM);
        bt.Edit(btnXoa_DM);
        bt.Edit(btnClear_DM);
        bt.Edit(btnPre_DM);
        bt.Edit(btnNext_DM);

        //them vao 10/12
        bt.Edit(btnTimSP);

        txt.edit(txtName_Product);
        txt.edit(txtPrice);
        txt.edit(txtQuantity1);
        txt.edit(txtName_Attribute);

        //ngay 3/12
        txt.edit(txtPr_Category);
        txt.edit(txtPr_Not_Category);
        txt.edit(txtDanhMuc);

        //them 12/12
        pnlProduct_Detail.setVisible(false);
        TableCustom.apply(slpAttribute_Stop, TableCustom.TableType.MULTI_LINE);

        btnAdd_Product.setEnabled(true);
        bt.Edit(btnAdd_Product);
        btnFix_Produc.setEnabled(false);
        btnFix_Produc.setBackground(new java.awt.Color(0, 0, 0));
        btnStop_Sell_Product.setEnabled(false);
        btnStop_Sell_Product.setBackground(new java.awt.Color(0, 0, 0));

        pnlProduct_Detail.setVisible(false);

        btnAdd_Product_Detail1.setEnabled(true);
        bt.Edit(btnAdd_Product_Detail1);
        btnFix_Product_Detail1.setEnabled(false);
        btnFix_Product_Detail1.setBackground(new java.awt.Color(0, 0, 0));
        btnStop_Sell_Product_Detail1.setEnabled(false);
        btnStop_Sell_Product_Detail1.setBackground(new java.awt.Color(0, 0, 0));
        bt.Edit(btnPre_Attribute_Stop);
        bt.Edit(btnNext_Attribute_Stop);
        bt.Edit(btnKhoiPhuc_Actribute);
    }

    public void load_Product() {
        DefaultTableModel dtm = (DefaultTableModel) this.tblProduct.getModel();
        dtm.setRowCount(0);
        int i = 1;

        for (com.model.Product sp : this.pds.getNext(txtTenTim.getText().trim(), minProduct_tab1, maxProduct_tab1)) {
            Object[] ob = {
                i,
                sp.getId(),
                sp.getName_product(),
                sp.getCustome_id().getNameCustom(),
                sp.getProduct_price(),
                sp.getMaterial_id().getNameMaterial(),
                sp.getThickness_id().getGsm()};
            dtm.addRow(ob);
            i++;
        }
    }
//xoa cai nay
//    public void load_Product_Extra() {
//        DefaultTableModel dtm = (DefaultTableModel) this.tblProduct_Extra.getModel();
//        dtm.setRowCount(0);
//
//        for (model.entity.Product sp : this.pds.getNext(minProduct_tab2, maxProduct_tab2)) {
//            Object[] ob = {
//                sp.getId(),
//                sp.getName_product(),
//                sp.getCustome_id().getNameCustom(),
//                sp.getMaterial_id().getNameMaterial(),
//                sp.getThickness_id().getGsm() + "gsm",};
//            dtm.addRow(ob);
//        }
//    }

    //  cai nay tam bo
//    public void loadCT() {
//        DefaultTableModel dtm = (DefaultTableModel) this.tblBangChiTiet.getModel();
//        dtm.setRowCount(0);
//        String tt = "";
//        for (ProductDetail spct : this.pdds.getProductDetails_id(idSP)) {
//
//            Object[] ob = {
//                spct.getId(),
//                spct.getSizeId().getNameSize(),
//                spct.getColorId().getNameColor(),
//                spct.getCreatedAt(),
//                spct.getUpdatedAt(),
//                spct.getQuantity()
//            };
//            dtm.addRow(ob);
//        }
//    }
    //thay the bang cai nay ngay 28//11
    public void load_Product_Detail() {
        DefaultTableModel dtm = (DefaultTableModel) this.tblProduct_Detail1.getModel();
        dtm.setRowCount(0);
        int i = 1;
        for (ProductDetail spct : this.pdds.getProductDetail_Selling_Next(idProduct, minProduct_detail, maxProduct_detail)) {
            Object[] ob = {
                i,
                spct.getId(),
                spct.getSizeId().getNameSize(),
                spct.getColorId().getNameColor(),
                spct.getQuantity()
            };
            dtm.addRow(ob);
            i++;
        }
    }

    public void loadSize() {
        DefaultTableModel dtm = (DefaultTableModel) this.tblAttribute.getModel();
        dtm.setRowCount(0);
        int i = 1;
        for (Size d : this.ss.getSize_Sell(minAttribute, maxAttribute)) {
            Object[] ob = {
                i,
                d.getId(),
                d.getNameSize(),
                d.getCreatedAt(),
                d.getUpdatedAt()
            };
            dtm.addRow(ob);
            i++;
        }
    }

    public void loadColer() {
        DefaultTableModel dtm = (DefaultTableModel) this.tblAttribute.getModel();
        dtm.setRowCount(0);
        int i = 1;
        for (Color d : this.cls.getColor_Sell(minAttribute, maxAttribute)) {
            Object[] ob = {
                i,
                d.getId(),
                d.getNameColor(),
                d.getCreatedAt(),
                d.getUpdatedAt()
            };
            dtm.addRow(ob);
            i++;
        }
    }

    public void loadMaterial() {
        DefaultTableModel dtm = (DefaultTableModel) this.tblAttribute.getModel();
        dtm.setRowCount(0);
        int i = 1;
        for (Material d : this.mts.getMaterial_Sell(minAttribute, maxAttribute)) {
            Object[] ob = {
                i,
                d.getId(),
                d.getNameMaterial(),
                d.getCreatedAt(),
                d.getUpdatedAt()
            };
            dtm.addRow(ob);
            i++;
        }
    }

    public void loadThickness() {
        DefaultTableModel dtm = (DefaultTableModel) this.tblAttribute.getModel();
        dtm.setRowCount(0);
        int i = 1;
        for (Thickness d : this.tns.getThickness_sell(minAttribute, maxAttribute)) {
            Object[] ob = {
                i,
                d.getId(),
                d.getGsm(),
                d.getCreatedAt(),
                d.getUpdatedAt()
            };
            dtm.addRow(ob);
            i++;
        }
    }

    public void loadCustom() {
        DefaultTableModel dtm = (DefaultTableModel) this.tblAttribute.getModel();
        dtm.setRowCount(0);
        int i = 1;
        for (Custom d : this.cts.getCustom_Sell(minAttribute, maxAttribute)) {
            Object[] ob = {
                i,
                d.getId(),
                d.getNameCustom(),
                d.getCreatedAt(),
                d.getUpdatedAt()
            };
            dtm.addRow(ob);
            i++;
        }
    }

    public void loadcbbCustom() {
        dcm = new DefaultComboBoxModel();
        dcm.removeAllElements();
        for (Custom ct : this.cts.getCBB()) {
            dcm.addElement(ct.getNameCustom());
        }
        cbbCustom.setModel(dcm);
    }

    public void loadcbbMaterial() {
        dcm = new DefaultComboBoxModel();
        dcm.removeAllElements();
        for (Material nl : this.mts.getCBB()) {
            dcm.addElement(nl.getNameMaterial());
        }
        cbbMaterial.setModel(dcm);
    }

    public void loadcbbThickness() {
        dcm = new DefaultComboBoxModel();
        dcm.removeAllElements();
        for (Thickness dd : this.tns.getCBB()) {
            String doDay = String.valueOf(dd.getGsm());
            dcm.addElement(doDay);
        }
        cbbThickness.setModel(dcm);
    }

    public void loadcbbSize() {
        dcm = new DefaultComboBoxModel();
        dcm.removeAllElements();
        for (Size s : this.ss.getCBB()) {
            String ten = String.valueOf(s.getNameSize());
            dcm.addElement(ten);
        }
        cbbSize1.setModel(dcm);
    }

    public void loadcbbColor() {
        dcm = new DefaultComboBoxModel();
        dcm.removeAllElements();
        for (Color m : this.cls.getCBB()) {
            String ten = String.valueOf(m.getNameColor());
            dcm.addElement(ten);
        }
        cbbColor1.setModel(dcm);
    }
    //xoa cai nay
//    public void loadcbbProduct() {
//        dcm = new DefaultComboBoxModel();
//        dcm.removeAllElements();
//        for (model.entity.Product pr : this.pds.getProduct_sell()) {
//            String ten = String.valueOf(pr.getName_product());
//            dcm.addElement(ten);
//        }
//        cbbProduct.setModel(dcm);
//    }

    public void clear() {
        //clear sp
        txtName_Product.setText("");
        txtPrice.setText("");
        this.loadcbbMaterial();
        cbbMaterial.setSelectedIndex(0);
        this.loadcbbThickness();
        cbbThickness.setSelectedIndex(0);
        this.loadCustom();
        cbbCustom.setSelectedIndex(0);
        txtDescribe.setText("");
        lblImage.setIcon(null);
        lblImage.setText("Ảnh");

        //clear sp chi tiet
        this.loadSize();
        cbbSize1.setSelectedIndex(0);
        this.loadcbbColor();
        cbbColor1.setSelectedIndex(0);
        txtQuantity1.setText("");
        lblImage_Detail.setIcon(null);
        lblImage_Detail.setText("Ảnh");

        //thuoc tinh
        txtName_Attribute.setText("");
        this.loadSize();
        this.loadSize_Stop();
        rdoSize.setSelected(true);
        //danh muc
        //danh muc sp
        txtPr_Category.setText("");
        txtPr_Not_Category.setText("");

        //quan li danh muc
        txtDanhMuc.setText("");

    }

    public ProductDetail formct() {
        String ten = txtName_Product.getText();
        String size = cbbSize1.getSelectedItem().toString().trim();
        String mau = cbbColor1.getSelectedItem().toString().trim();
        String sl = txtQuantity1.getText().trim();
        if (ten.equals("")) {
            JOptionPane.showMessageDialog(this, "Chưa có sản phẩm để thêm.");
            return null;
        }
        if (lblImage_Detail.getText().equalsIgnoreCase("Ảnh")) {
            JOptionPane.showMessageDialog(this, "Chưa chọn ảnh");
            return null;
        }
        try {
            int soLuong = Integer.parseInt(sl);
            try {
                byte[] img = readImageFile(nameImage_Product_Detail);
                ProductDetail spct = new ProductDetail(soLuong, new Color(mau), new com.model.Product(ten), new Size(size), img);
                return spct;
            } catch (Exception e) {
                //e.printStackTrace();
            }

        } catch (Exception e) {
            //e.printStackTrace();
        }
        return null;
    }

    public com.model.Product form() {
        String ten = txtName_Product.getText().trim();
        String vatLieu = cbbMaterial.getSelectedItem().toString();
        String gia = txtPrice.getText().trim();
        String doDay = cbbThickness.getSelectedItem().toString();
        String kieuDang = cbbCustom.getSelectedItem().toString();
        String moTa = txtDescribe.getText().trim();
        if (ten.equals("") || gia.equals("")) {
            JOptionPane.showMessageDialog(this, "chua nhap du tt");
            return null;
        }
        if (ten.length() > 50) {
            JOptionPane.showMessageDialog(this, "teen ko ddc qua 50 ky tu.");
            return null;
        }
        if (lblImage.getText().trim().equalsIgnoreCase("Ảnh")) {
            JOptionPane.showMessageDialog(this, "Chua them anh");
            return null;
        }
        try {
            double giaTien = Double.parseDouble(gia);
            BigDecimal tien = new BigDecimal(gia);
            if (giaTien < 0) {
                JOptionPane.showMessageDialog(this, "gia tien phai lon hon 0");
                return null;
            }
            int doDaySo = Integer.parseInt(doDay);
            try {
                byte[] img = readImageFile(nameImage_Product);
                if (img == null) {
                    img = name_img_mount;
                    com.model.Product sp = new com.model.Product(tien, new Custom(kieuDang), new Material(vatLieu), new Thickness(doDaySo), moTa, ten, img);
                    return sp;
                } else {
                    com.model.Product sp = new com.model.Product(tien, new Custom(kieuDang), new Material(vatLieu), new Thickness(doDaySo), moTa, ten, img);
                    return sp;
                }
            } catch (Exception e) {
//                //e.printStackTrace();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "day ko phai gia tien.");
//            //e.printStackTrace();
        }
        return null;
    }

    public boolean checkSP(String ten) {
        for (com.model.Product sp : this.pds.getProduct_sell()) {
            if (ten.equalsIgnoreCase(sp.getName_product())) {
                JOptionPane.showMessageDialog(this, "san pham da ton tai.");
                return false;
            }
        }
        return true;
    }

    public boolean checkSPKOSD(String ten) {
        for (com.model.Product sp : this.pds.getProduct_Stop_selling()) {
            if (ten.equalsIgnoreCase(sp.getName_product())) {
                JOptionPane.showMessageDialog(this, "san pham da ton tai trong ds ngung ban. Neu ban muon ban sp nay, vui long khoi phuc ");
                return false;
            }
        }
        return true;
    }

    public boolean checkSPCTNB(String ten, String size, String mau) {
        for (ProductDetail sp : this.pdds.getProduct_Detail_Stop_selling()) {
            if (ten.equalsIgnoreCase(sp.getProductId().getName_product()) && size.equalsIgnoreCase(sp.getSizeId().getNameSize()) && mau.equalsIgnoreCase(sp.getColorId().getNameColor())) {
                JOptionPane.showMessageDialog(this, "san pham da ton tai trong ds ngung ban. Neu ban muon ban sp nay, vui long khoi phuc ");
                return false;
            }
        }
        return true;
    }

    public boolean checkSPCTCB(String ten, String size, String mau) {
        for (ProductDetail sp : this.pdds.getProduct_Detail_Sell()) {
            if (ten.equalsIgnoreCase(sp.getProductId().getName_product()) && size.equalsIgnoreCase(sp.getSizeId().getNameSize()) && mau.equalsIgnoreCase(sp.getColorId().getNameColor())) {
                JOptionPane.showMessageDialog(this, "san pham da ton tai trong ds ngung ban. Neu ban muon ban sp nay, vui long khoi phuc ");
                return false;
            }
        }
        return true;
    }
    //them cai nay ngay 28//11

    public boolean checkNull_Table(ArrayList<?> myList) {
        return myList != null && !myList.isEmpty();
    }

    public void loadProduct_Stop() {
        DefaultTableModel dtm = (DefaultTableModel) this.tblProduc_Stop_sell.getModel();
        dtm.setRowCount(0);
        String tt = "";
        int i = 1;
        for (com.model.Product sp : this.pds.getNext_Product_Stop_selling(minProduct_Stop_Sell, maxProduct_Stop_Sell)) {
            Object[] ob = {
                i,
                sp.getId(),
                sp.getName_product(),
                sp.getCustome_id().getNameCustom(),
                sp.getProduct_price(),
                sp.getMaterial_id().getNameMaterial(),
                sp.getThickness_id().getGsm() + "gsm"
            };
            dtm.addRow(ob);
            i++;
        }
    }

    public void loadProduct_Deteail_Stop_Sell() {
        DefaultTableModel dtm = (DefaultTableModel) this.tblProduct_Detail_Stop_Sell.getModel();
        dtm.setRowCount(0);
        String tt = "";
        int i = 1;
        for (ProductDetail spct : this.pdds.getRestore_Product_Detail_stop_selling(minProduct_Detail_Stop_Sell, maxProduct_Detail_Stop_Sell)) {
            Object[] ob = {
                i,
                spct.getId(),
                spct.getProductId().getName_product(),
                spct.getSizeId().getNameSize(),
                spct.getColorId().getNameColor(),
                spct.getQuantity()
            };

            dtm.addRow(ob);
            i++;
        }
    }

    //them vao ngay 29/11
    //them moi 1/12
    private static byte[] readImageFile(String imagePath) throws IOException {
        File file = new File(imagePath);
        byte[] imageData = new byte[(int) file.length()];
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            fileInputStream.read(imageData);
        }
        return imageData;
    }

    private static BufferedImage getImageFromByteArray(byte[] imageData) {
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(imageData);
            return ImageIO.read(inputStream);
        } catch (IOException e) {
//            //e.printStackTrace();
        }
        return null;
    }

    //them vao 2.12
//    public void editTbl(JTable table){
//        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
//        table.getTableHeader().setOpaque(false);
////        table.getTableHeader().setBackground(new java.awt.Color(32,136,203));
//        table.getTableHeader().setForeground(new java.awt.Color(51,153,255));
//        table.setRowHeight( 25);
//    }
//    tehm vao 3/12
    public void loadCatory_not_Pr() {
        DefaultTableModel dtm = (DefaultTableModel) this.tblProduct_Not_Category.getModel();
        dtm.setRowCount(0);
        int i = 1;
        for (com.model.Product sp : this.ctg.getProduct_not_Category(namePr_not_category, nameCategory, minProduct_not_Category, maxProduct_not_Category)) {
            Object[] ob = {
                i,
                sp.getId(),
                sp.getName_product(),
                sp.getCustome_id().getNameCustom(),
                sp.getProduct_price(),
                sp.getMaterial_id().getNameMaterial(),
                sp.getThickness_id().getGsm() + "gsm"
            };
            dtm.addRow(ob);
            i++;
        }
    }

    public void loadCatory_Pr() {
        DefaultTableModel dtm = (DefaultTableModel) this.tblProduct_Category.getModel();
        dtm.setRowCount(0);
        int i = 1;
        for (com.model.Product sp : this.ctg.getProduct_Category(namePr_Category, nameCategory, minProduct_Category, maxProduct_Category)) {
            Object[] ob = {
                i,
                sp.getId(),
                sp.getName_product(),
                sp.getCustome_id().getNameCustom(),
                sp.getProduct_price(),
                sp.getMaterial_id().getNameMaterial(),
                sp.getThickness_id().getGsm() + "gsm"
            };
            dtm.addRow(ob);
            i++;
        }
    }

    public void loadcbbDanhMuc() {
        dcm = new DefaultComboBoxModel();
        dcm.removeAllElements();
        for (Category category : this.ctg.getCategory_Action()) {
            String ten = String.valueOf(category.getNameCategory());
            dcm.addElement(ten);
        }
        cbbCategory.setModel(dcm);
    }

    public void loadCatory() {
        DefaultTableModel dtm = (DefaultTableModel) this.tblDM.getModel();
        dtm.setRowCount(0);
        int i = 1;
        for (Category category : this.ctg.getCategory_Action_Next(minCategory, maxCategory)) {
            Object[] ob = {
                i,
                category.getId(),
                category.getNameCategory()
            };
            dtm.addRow(ob);
            i++;
        }
    }

    public void loadCatory_Delete() {
        DefaultTableModel dtm = (DefaultTableModel) this.tblCategory_Delete.getModel();
        dtm.setRowCount(0);
        int i = 1;
        for (Category category : this.ctg.getCategory_not_Action_Next(minCategory_Delete, maxCategory_Delete)) {
            Object[] ob = {
                i,
                category.getId(),
                category.getNameCategory()
            };
            dtm.addRow(ob);
            i++;
        }
    }

    public boolean checkCategory(String name) {
        for (Category category : this.ctg.getAll()) {
            if (name.trim().equalsIgnoreCase(category.getNameCategory().trim())) {
                return true;
            }
        }
        return false;
    }

    public Category getForm_Category() {
        String name = txtDanhMuc.getText().trim();
        if (name.equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Chua ghi du du lieu.");
            return null;
        }
        if (this.checkCategory(name) == true) {
            JOptionPane.showMessageDialog(this, "San pham nay da ton tai");
            return null;
        }
        Category c = new Category(name);
        return c;
    }

    //them 4/12
    public String getID_Category(String name) {
        for (Category c : this.ctg.getAll()) {
            if (name.equals(c.getNameCategory())) {
                return c.getId();
            }
        }
        return null;
    }

    public ProductCategory getProduct_Category_Form_Add() {
//        if(id_Pr_Not_Ctegory.equalsIgnoreCase("")){
//            return null;
//        }
        com.model.Product pr = new com.model.Product(id_Pr_Not_Ctegory, "1");
        Category c = new Category(true, id_cbbDanhMuc);
        ProductCategory p = new ProductCategory(c, pr);
        return p;
    }

    public ProductCategory getProduct_Category_Form_Remove() {
//        if(id_Pr_Not_Ctegory.equalsIgnoreCase("")){
//            return null;
//        }
        com.model.Product pr = new com.model.Product(id_Pr_Category, "1");
        Category c = new Category(true, id_cbbDanhMuc);
        ProductCategory p = new ProductCategory(c, pr);
        return p;
    }

    //them vao 11/12
    private byte[] getByteArrayFromImage(BufferedImage image) {
        try {
            // Tạo đối tượng ByteArrayOutputStream để lưu trữ dữ liệu
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            // Chuyển đối tượng BufferedImage thành dữ liệu định dạng ảnh và ghi vào baos
            ImageIO.write(image, "png", baos);

            // Trả về mảng byte của dữ liệu ảnh
            return baos.toByteArray();
        } catch (IOException e) {
            //e.printStackTrace();
        }
        return null;
    }

    public com.model.Product formUpdate() {
        String ten = txtName_Product.getText().trim();
        String vatLieu = cbbMaterial.getSelectedItem().toString();
        String gia = txtPrice.getText().trim();
        String doDay = cbbThickness.getSelectedItem().toString();
        String kieuDang = cbbCustom.getSelectedItem().toString();
        String moTa = txtDescribe.getText().trim();
        if (ten.equals("") || gia.equals("")) {
            JOptionPane.showMessageDialog(this, "chua nhap du tt");
            return null;
        }
        if (ten.length() > 50) {
            JOptionPane.showMessageDialog(this, "teen ko ddc qua 50 ky tu.");
            return null;
        }
        try {
            double giaTien = Double.parseDouble(gia);
            BigDecimal tien = new BigDecimal(gia);
            if (giaTien < 0) {
                JOptionPane.showMessageDialog(this, "gia tien phai lon hon 0");
                return null;
            }
            int doDaySo = Integer.parseInt(doDay);
            try {

                byte[] img = getByteArrayFromImage(editedImage);
                com.model.Product sp = new com.model.Product(tien, new Custom(kieuDang), new Material(vatLieu), new Thickness(doDaySo), moTa, ten, img);
                return sp;

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Hãy chọn ảnh khác");
                //e.printStackTrace();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "day ko phai gia tien.");
            //e.printStackTrace();
        }
        return null;
    }

    // them vao 12/12
    private boolean comboBoxContainsItem(JComboBox comboBox, String item) {
        for (int i = 0; i < comboBox.getItemCount(); i++) {
            if (item.equals(comboBox.getItemAt(i))) {
                return true;
            }
        }
        return false;
    }

    public void loadSize_Stop() {
        DefaultTableModel dtm = (DefaultTableModel) this.tblAttribute_Stop.getModel();
        dtm.setRowCount(0);
        int i = 1;
        for (Size d : this.ss.getSize_Stop(minAttribute_stop, maxAttribute_Stop)) {
            Object[] ob = {
                i,
                d.getId(),
                d.getNameSize(),
                d.getCreatedAt(),
                d.getUpdatedAt()
            };
            dtm.addRow(ob);
            i++;
        }
    }

    public void loadColer_Stop() {
        DefaultTableModel dtm = (DefaultTableModel) this.tblAttribute_Stop.getModel();
        dtm.setRowCount(0);
        int i = 1;
        for (Color d : this.cls.getColor_Stop(minAttribute_stop, maxAttribute_Stop)) {
            Object[] ob = {
                i,
                d.getId(),
                d.getNameColor(),
                d.getCreatedAt(),
                d.getUpdatedAt()
            };
            dtm.addRow(ob);
            i++;
        }
    }

    public void loadMaterial_Stop() {
        DefaultTableModel dtm = (DefaultTableModel) this.tblAttribute_Stop.getModel();
        dtm.setRowCount(0);
        int i = 1;
        for (Material d : this.mts.getMaterial_Stop(minAttribute_stop, maxAttribute_Stop)) {
            Object[] ob = {
                i,
                d.getId(),
                d.getNameMaterial(),
                d.getCreatedAt(),
                d.getUpdatedAt()
            };
            dtm.addRow(ob);
            i++;
        }
    }

    public void loadThickness_Stop() {
        DefaultTableModel dtm = (DefaultTableModel) this.tblAttribute_Stop.getModel();
        dtm.setRowCount(0);
        int i = 1;
        for (Thickness d : this.tns.getThickness_Stop(minAttribute_stop, maxAttribute_Stop)) {
            Object[] ob = {
                i,
                d.getId(),
                d.getGsm(),
                d.getCreatedAt(),
                d.getUpdatedAt()
            };
            dtm.addRow(ob);
            i++;
        }
    }

    public void loadCustom_Stop() {
        DefaultTableModel dtm = (DefaultTableModel) this.tblAttribute_Stop.getModel();
        dtm.setRowCount(0);
        int i = 1;
        for (Custom d : this.cts.getCustom_Stop(minAttribute_stop, maxAttribute_Stop)) {
            Object[] ob = {
                i,
                d.getId(),
                d.getNameCustom(),
                d.getCreatedAt(),
                d.getUpdatedAt()
            };
            dtm.addRow(ob);
            i++;
        }
    }

    public void clearBien() {
        minProduct_tab1 = 1;
        maxProduct_tab1 = 10;
        minProduct_detail = 1;
        maxProduct_detail = 10;
        minProduct_tab3 = 1;
        maxProduct_tab3 = 10;
        //them cai nay ngay 28//11

        maxProduct_Stop_Sell = 10;
        minProduct_Stop_Sell = 1;
        maxProduct_Detail_Stop_Sell = 10;
        minProduct_Detail_Stop_Sell = 1;
        //them vao 3/12

        maxProduct_not_Category = 10;
        minProduct_not_Category = 1;
        maxProduct_Category = 10;
        minProduct_Category = 1;

        maxCategory = 10;
        minCategory = 1;
        maxCategory_Delete = 10;
        minCategory_Delete = 1;
        //them 1/12
        maxAttribute = 10;
        minAttribute = 1;
        //het
        //cai nay viet trc r
        idProduct = "";
        idProduct_Extra = "";
        idProduct_Detail = "";
        idTT = "";
        idProduct_Stop_Sell = "";
        idProduct_Detail_Stop_Sell = "";
        nameImage_Product = "";
        nameImage_Product_Detail = "";
        //them vao 3/12
        namePr_Category = "%%";
        nameCategory = "Áo Thun";
        namePr_not_category = "%%";
        id_Category = "";
        id_Category_Delete = "";
        id_Pr_Not_Ctegory = "";
        id_Pr_Category = "";
        //them vao 4/12
        String id_cbbDanhMuc = "";
        //them vao 11/12
        byte[] name_img_mount = null;
        idTT_Stop = "";
        this.clear();
    }

    public ProductDetail formctS() {
        String ten = txtName_Product.getText();
        String size = cbbSize1.getSelectedItem().toString().trim();
        String mau = cbbColor1.getSelectedItem().toString().trim();
        String sl = txtQuantity1.getText().trim();
        if (ten.equals("")) {
            JOptionPane.showMessageDialog(this, "Chưa có sản phẩm để thêm.");
            return null;
        }
        if (lblImage_Detail.getText().equalsIgnoreCase("Ảnh")) {
            JOptionPane.showMessageDialog(this, "Chưa chọn ảnh");
            return null;
        }
        try {
            int soLuong = Integer.parseInt(sl);
            try {
                byte[] img = getByteArrayFromImage(editedImage_Detail);
                ProductDetail spct = new ProductDetail(soLuong, new Color(mau), new com.model.Product(ten), new Size(size), img);
                return spct;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Lỗi ảnh");
                //e.printStackTrace();
            }

        } catch (Exception e) {
            //e.printStackTrace();
        }
        return null;
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

        btgTT = new javax.swing.ButtonGroup();
        jButton2 = new javax.swing.JButton();
        swingTab1 = new com.swing.SwingTabbedPane();
        pnlSanPham = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtName_Product = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        slpProduct = new javax.swing.JScrollPane();
        tblProduct = new javax.swing.JTable();
        btnFix_Produc = new javax.swing.JButton();
        btnStop_Sell_Product = new javax.swing.JButton();
        btnClear_Product = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnPre__Product = new javax.swing.JButton();
        btnNext__Product = new javax.swing.JButton();
        pnlProduct_Detail = new javax.swing.JPanel();
        btnAdd_Product_Detail1 = new javax.swing.JButton();
        btnFix_Product_Detail1 = new javax.swing.JButton();
        btnStop_Sell_Product_Detail1 = new javax.swing.JButton();
        btnClear_Product_Detail1 = new javax.swing.JButton();
        txtQuantity1 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        btnPre_Product_Detail1 = new javax.swing.JButton();
        btnNext_Product_Detail1 = new javax.swing.JButton();
        slpProduct_Detail = new javax.swing.JScrollPane();
        tblProduct_Detail1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        lblImage_Detail = new javax.swing.JLabel();
        cbbColor1 = new com.swing.Combobox();
        cbbSize1 = new com.swing.Combobox();
        jLabel10 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        lblImage = new javax.swing.JLabel();
        btnAdd_Product = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        cbbMaterial = new com.swing.Combobox();
        cbbThickness = new com.swing.Combobox();
        cbbCustom = new com.swing.Combobox();
        jLabel11 = new javax.swing.JLabel();
        textAreaScroll2 = new com.swing.TextAreaScroll();
        txtDescribe = new com.swing.TextArea();
        txtTenTim = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        btnTimSP = new javax.swing.JButton();
        pnlThuocTinh = new javax.swing.JPanel();
        btnStop_Working_Attribute = new javax.swing.JButton();
        btnClear_Attribute = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        slpAttribute = new javax.swing.JScrollPane();
        tblAttribute = new javax.swing.JTable();
        txtName_Attribute = new javax.swing.JTextField();
        rdoSize = new javax.swing.JRadioButton();
        rdoColor = new javax.swing.JRadioButton();
        rdoMaterial = new javax.swing.JRadioButton();
        rdoThickness = new javax.swing.JRadioButton();
        rdoCustom = new javax.swing.JRadioButton();
        btnAdd_Attribute = new javax.swing.JButton();
        btnFix_Attribute = new javax.swing.JButton();
        btnNext_Attribute = new javax.swing.JButton();
        btnPre_Attribute = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        slpAttribute_Stop = new javax.swing.JScrollPane();
        tblAttribute_Stop = new javax.swing.JTable();
        btnPre_Attribute_Stop = new javax.swing.JButton();
        btnKhoiPhuc_Actribute = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        btnNext_Attribute_Stop = new javax.swing.JButton();
        pnlDanhMuc = new javax.swing.JPanel();
        swingTabbedPane2 = new com.swing.SwingTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        swingTabbedPane1 = new com.swing.SwingTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        slpProduct_Has_No_Category_Yet = new javax.swing.JScrollPane();
        tblProduct_Not_Category = new javax.swing.JTable();
        btnPre_Product_Has_No_Category_Yet = new javax.swing.JButton();
        btnNext_Product_Has_No_Category_Yet = new javax.swing.JButton();
        btnThem_pr_DM = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        btnScan_PR_Not_Category = new javax.swing.JButton();
        txtPr_Not_Category = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        slpDanhMuc = new javax.swing.JScrollPane();
        tblProduct_Category = new javax.swing.JTable();
        btnPre_DanhMuc = new javax.swing.JButton();
        btnNext_DanhMuc = new javax.swing.JButton();
        btnXoa_prDM = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtPr_Category = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        btnScan_PR_Category = new javax.swing.JButton();
        cbbCategory = new com.swing.Combobox();
        jLabel20 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        txtDanhMuc = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        btnAdd_DM = new javax.swing.JButton();
        btnSua_DM = new javax.swing.JButton();
        btnXoa_DM = new javax.swing.JButton();
        btnClear_DM = new javax.swing.JButton();
        slpDM = new javax.swing.JScrollPane();
        tblDM = new javax.swing.JTable();
        btnPre_DM = new javax.swing.JButton();
        btnNext_DM = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        slpCategory_Delete = new javax.swing.JScrollPane();
        tblCategory_Delete = new javax.swing.JTable();
        btnPre_Category_Delete = new javax.swing.JButton();
        btnNext_Category_Delete = new javax.swing.JButton();
        btnKhoiPhuc = new javax.swing.JButton();
        pnlSanPhamNgungBan = new javax.swing.JPanel();
        btnPre_Product_Stop_Sell = new javax.swing.JButton();
        btnNext_Product_Stop_Sell = new javax.swing.JButton();
        btnRestore_Product = new javax.swing.JButton();
        slpProduc_Stop_sell = new javax.swing.JScrollPane();
        tblProduc_Stop_sell = new javax.swing.JTable();
        pnlSanPhamCTNgungBan = new javax.swing.JPanel();
        btnRestore_Product_Detail_Stop_Sell = new javax.swing.JButton();
        btnNext_Product_Detail_Stop_Sell = new javax.swing.JButton();
        btnPre_Product_Detail_Stop_Sell = new javax.swing.JButton();
        slpProduct_Detail_Stop_Sell = new javax.swing.JScrollPane();
        tblProduct_Detail_Stop_Sell = new javax.swing.JTable();

        jButton2.setText("jButton2");

        setBackground(new java.awt.Color(255, 255, 255));

        swingTab1.setBackground(new java.awt.Color(0, 0, 0));
        swingTab1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                swingTab1MouseClicked(evt);
            }
        });

        pnlSanPham.setBackground(new java.awt.Color(255, 255, 255));
        pnlSanPham.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        pnlSanPham.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel1.setText("Tên sản phẩm:");
        pnlSanPham.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 20));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("Vật liệu:");
        pnlSanPham.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 84, -1));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("Giá:");
        pnlSanPham.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 84, -1));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setText("Độ dày:");
        pnlSanPham.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 84, -1));

        txtName_Product.setBorder(null);
        pnlSanPham.add(txtName_Product, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 209, 24));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setText("Mô tả:");
        pnlSanPham.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 84, -1));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel7.setText("Kiểu dáng:");
        pnlSanPham.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 84, -1));

        tblProduct.setForeground(new java.awt.Color(255, 255, 255));
        tblProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "stt", "id", "Tên sản phẩm", "Kiểu", "Gía", "Nguyên liệu", "Độ dày (gsm)"
            }
        ));
        tblProduct.setRowHeight(25);
        tblProduct.setSelectionBackground(new java.awt.Color(51, 153, 255));
        tblProduct.setShowHorizontalLines(true);
        tblProduct.getTableHeader().setReorderingAllowed(false);
        tblProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblProductMouseEntered(evt);
            }
        });
        slpProduct.setViewportView(tblProduct);

        pnlSanPham.add(slpProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 413, 1123, 180));

        btnFix_Produc.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnFix_Produc.setText("Sửa");
        btnFix_Produc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFix_ProducActionPerformed(evt);
            }
        });
        pnlSanPham.add(btnFix_Produc, new org.netbeans.lib.awtextra.AbsoluteConstraints(112, 338, 100, -1));

        btnStop_Sell_Product.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnStop_Sell_Product.setText("Ngừng bán");
        btnStop_Sell_Product.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStop_Sell_ProductActionPerformed(evt);
            }
        });
        pnlSanPham.add(btnStop_Sell_Product, new org.netbeans.lib.awtextra.AbsoluteConstraints(218, 338, 100, -1));

        btnClear_Product.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnClear_Product.setText("Làm mới");
        btnClear_Product.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClear_ProductActionPerformed(evt);
            }
        });
        pnlSanPham.add(btnClear_Product, new org.netbeans.lib.awtextra.AbsoluteConstraints(324, 338, 100, -1));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setText("gsm");
        pnlSanPham.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 150, 37, -1));

        btnPre__Product.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnPre__Product.setText("<");
        btnPre__Product.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPre__ProductActionPerformed(evt);
            }
        });
        pnlSanPham.add(btnPre__Product, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 600, 100, -1));

        btnNext__Product.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnNext__Product.setText(">");
        btnNext__Product.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNext__ProductActionPerformed(evt);
            }
        });
        pnlSanPham.add(btnNext__Product, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 600, 100, -1));

        pnlProduct_Detail.setBackground(new java.awt.Color(255, 255, 255));
        pnlProduct_Detail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlProduct_Detail.setForeground(new java.awt.Color(255, 255, 255));

        btnAdd_Product_Detail1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnAdd_Product_Detail1.setText("Thêm");
        btnAdd_Product_Detail1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd_Product_Detail1ActionPerformed(evt);
            }
        });

        btnFix_Product_Detail1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnFix_Product_Detail1.setText("Sửa");
        btnFix_Product_Detail1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFix_Product_Detail1ActionPerformed(evt);
            }
        });

        btnStop_Sell_Product_Detail1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnStop_Sell_Product_Detail1.setText("Ngừng bán");
        btnStop_Sell_Product_Detail1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStop_Sell_Product_Detail1ActionPerformed(evt);
            }
        });

        btnClear_Product_Detail1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnClear_Product_Detail1.setText("Làm mới");
        btnClear_Product_Detail1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClear_Product_Detail1ActionPerformed(evt);
            }
        });

        txtQuantity1.setBorder(null);
        txtQuantity1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQuantity1ActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel14.setText("Kích cỡ:");

        jLabel15.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel15.setText("Màu:");

        jLabel16.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel16.setText("Số  lượng:");

        btnPre_Product_Detail1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnPre_Product_Detail1.setText("<");
        btnPre_Product_Detail1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPre_Product_Detail1ActionPerformed(evt);
            }
        });

        btnNext_Product_Detail1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnNext_Product_Detail1.setText(">");
        btnNext_Product_Detail1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNext_Product_Detail1ActionPerformed(evt);
            }
        });

        tblProduct_Detail1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "stt", "id", "Kích cỡ", "màu sắc", "Số lượng"
            }
        ));
        tblProduct_Detail1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProduct_Detail1MouseClicked(evt);
            }
        });
        slpProduct_Detail.setViewportView(tblProduct_Detail1);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblImage_Detail.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblImage_Detail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImage_Detail.setText("Ảnh");
        lblImage_Detail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblImage_DetailMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblImage_Detail, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblImage_Detail, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cbbColor1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        cbbSize1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel10.setText("____________________________________________________________________");
        jLabel10.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout pnlProduct_DetailLayout = new javax.swing.GroupLayout(pnlProduct_Detail);
        pnlProduct_Detail.setLayout(pnlProduct_DetailLayout);
        pnlProduct_DetailLayout.setHorizontalGroup(
            pnlProduct_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProduct_DetailLayout.createSequentialGroup()
                .addGroup(pnlProduct_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlProduct_DetailLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(pnlProduct_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlProduct_DetailLayout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cbbSize1, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlProduct_DetailLayout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cbbColor1, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlProduct_DetailLayout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addGap(20, 20, 20)
                                .addGroup(pnlProduct_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtQuantity1, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pnlProduct_DetailLayout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(btnAdd_Product_Detail1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addComponent(btnFix_Product_Detail1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(btnStop_Sell_Product_Detail1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(17, 17, 17)
                                .addComponent(btnClear_Product_Detail1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(8, 8, 8)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlProduct_DetailLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(slpProduct_Detail, javax.swing.GroupLayout.PREFERRED_SIZE, 585, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlProduct_DetailLayout.createSequentialGroup()
                        .addGap(373, 373, 373)
                        .addComponent(btnPre_Product_Detail1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnNext_Product_Detail1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6))
        );
        pnlProduct_DetailLayout.setVerticalGroup(
            pnlProduct_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProduct_DetailLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(pnlProduct_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlProduct_DetailLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(pnlProduct_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlProduct_DetailLayout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(jLabel14))
                            .addComponent(cbbSize1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(pnlProduct_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlProduct_DetailLayout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(jLabel15))
                            .addComponent(cbbColor1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(pnlProduct_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlProduct_DetailLayout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jLabel16))
                            .addGroup(pnlProduct_DetailLayout.createSequentialGroup()
                                .addComponent(txtQuantity1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(20, 20, 20)
                        .addGroup(pnlProduct_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAdd_Product_Detail1)
                            .addComponent(btnFix_Product_Detail1)
                            .addComponent(btnStop_Sell_Product_Detail1)
                            .addComponent(btnClear_Product_Detail1)))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(slpProduct_Detail, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlProduct_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPre_Product_Detail1)
                    .addComponent(btnNext_Product_Detail1)))
        );

        pnlSanPham.add(pnlProduct_Detail, new org.netbeans.lib.awtextra.AbsoluteConstraints(524, 5, -1, 360));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblImage.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImage.setText("Ảnh");
        lblImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblImageMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblImage, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlSanPham.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, -1, 174));

        btnAdd_Product.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnAdd_Product.setText("Thêm");
        btnAdd_Product.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd_ProductActionPerformed(evt);
            }
        });
        pnlSanPham.add(btnAdd_Product, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 338, 100, -1));

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel8.setText("__________________________________________");
        jLabel8.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        pnlSanPham.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, 210, 10));

        txtPrice.setBorder(null);
        pnlSanPham.add(txtPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 209, 24));

        cbbMaterial.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        pnlSanPham.add(cbbMaterial, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 92, 210, 30));

        cbbThickness.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        pnlSanPham.add(cbbThickness, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, 210, 30));

        cbbCustom.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cbbCustom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbCustomActionPerformed(evt);
            }
        });
        pnlSanPham.add(cbbCustom, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, 210, -1));

        jLabel11.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel11.setText("__________________________________________");
        jLabel11.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        pnlSanPham.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 210, 10));

        txtDescribe.setColumns(20);
        txtDescribe.setRows(5);
        textAreaScroll2.setViewportView(txtDescribe);

        pnlSanPham.add(textAreaScroll2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 230, 210, 90));

        txtTenTim.setBorder(null);
        pnlSanPham.add(txtTenTim, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 370, 290, 20));

        jLabel23.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel23.setText("_____________________________________________________________");
        jLabel23.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        pnlSanPham.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 390, 320, 10));

        btnTimSP.setText("Tìm");
        btnTimSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimSPActionPerformed(evt);
            }
        });
        pnlSanPham.add(btnTimSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 370, 90, 30));

        swingTab1.addTab("Sản phẩm", pnlSanPham);

        pnlThuocTinh.setBackground(new java.awt.Color(255, 255, 255));
        pnlThuocTinh.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnStop_Working_Attribute.setText("Xóa");
        btnStop_Working_Attribute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStop_Working_AttributeActionPerformed(evt);
            }
        });
        pnlThuocTinh.add(btnStop_Working_Attribute, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 46, 100, -1));

        btnClear_Attribute.setText("Làm mới");
        btnClear_Attribute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClear_AttributeActionPerformed(evt);
            }
        });
        pnlThuocTinh.add(btnClear_Attribute, new org.netbeans.lib.awtextra.AbsoluteConstraints(518, 46, 100, -1));

        jLabel12.setText("Thêm thuộc tính sản phẩm:");
        pnlThuocTinh.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 9, -1, -1));

        tblAttribute.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "stt", "id", "Tên", "Ngày tạo", "Ngày sửa"
            }
        ));
        tblAttribute.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAttributeMouseClicked(evt);
            }
        });
        slpAttribute.setViewportView(tblAttribute);

        pnlThuocTinh.add(slpAttribute, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 1120, 210));

        txtName_Attribute.setBorder(null);
        pnlThuocTinh.add(txtName_Attribute, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 6, 285, -1));

        btgTT.add(rdoSize);
        rdoSize.setSelected(true);
        rdoSize.setText("Kích cỡ");
        rdoSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoSizeActionPerformed(evt);
            }
        });
        pnlThuocTinh.add(rdoSize, new org.netbeans.lib.awtextra.AbsoluteConstraints(473, 7, -1, -1));

        btgTT.add(rdoColor);
        rdoColor.setText("Màu");
        rdoColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoColorActionPerformed(evt);
            }
        });
        pnlThuocTinh.add(rdoColor, new org.netbeans.lib.awtextra.AbsoluteConstraints(547, 7, -1, -1));

        btgTT.add(rdoMaterial);
        rdoMaterial.setText("Vật liệu");
        rdoMaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoMaterialActionPerformed(evt);
            }
        });
        pnlThuocTinh.add(rdoMaterial, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 7, 80, -1));

        btgTT.add(rdoThickness);
        rdoThickness.setText("Độ dày");
        rdoThickness.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoThicknessActionPerformed(evt);
            }
        });
        pnlThuocTinh.add(rdoThickness, new org.netbeans.lib.awtextra.AbsoluteConstraints(693, 7, -1, -1));

        btgTT.add(rdoCustom);
        rdoCustom.setText("Kiểu");
        rdoCustom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoCustomActionPerformed(evt);
            }
        });
        pnlThuocTinh.add(rdoCustom, new org.netbeans.lib.awtextra.AbsoluteConstraints(759, 7, -1, -1));

        btnAdd_Attribute.setText("Thêm");
        btnAdd_Attribute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd_AttributeActionPerformed(evt);
            }
        });
        pnlThuocTinh.add(btnAdd_Attribute, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 46, 100, -1));

        btnFix_Attribute.setText("Sửa");
        btnFix_Attribute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFix_AttributeActionPerformed(evt);
            }
        });
        pnlThuocTinh.add(btnFix_Attribute, new org.netbeans.lib.awtextra.AbsoluteConstraints(282, 46, 100, -1));

        btnNext_Attribute.setText(">");
        btnNext_Attribute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNext_AttributeActionPerformed(evt);
            }
        });
        pnlThuocTinh.add(btnNext_Attribute, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 300, 100, -1));

        btnPre_Attribute.setText("<");
        btnPre_Attribute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPre_AttributeActionPerformed(evt);
            }
        });
        pnlThuocTinh.add(btnPre_Attribute, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 300, 100, -1));

        jLabel17.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel17.setText("_________________________________________________________");
        jLabel17.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        pnlThuocTinh.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 350, -1));

        tblAttribute_Stop.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "stt", "id", "Tên", "Ngày tạo", "Ngày sửa"
            }
        ));
        tblAttribute_Stop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAttribute_StopMouseClicked(evt);
            }
        });
        slpAttribute_Stop.setViewportView(tblAttribute_Stop);

        pnlThuocTinh.add(slpAttribute_Stop, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 1120, 210));

        btnPre_Attribute_Stop.setText("<");
        btnPre_Attribute_Stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPre_Attribute_StopActionPerformed(evt);
            }
        });
        pnlThuocTinh.add(btnPre_Attribute_Stop, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 590, 100, -1));

        btnKhoiPhuc_Actribute.setText("Khôi phục");
        btnKhoiPhuc_Actribute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhoiPhuc_ActributeActionPerformed(evt);
            }
        });
        pnlThuocTinh.add(btnKhoiPhuc_Actribute, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 590, 100, -1));

        jLabel24.setText("Danh sách thuộc tính ngừng sử dụng");
        pnlThuocTinh.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, -1, -1));

        btnNext_Attribute_Stop.setText(">");
        btnNext_Attribute_Stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNext_Attribute_StopActionPerformed(evt);
            }
        });
        pnlThuocTinh.add(btnNext_Attribute_Stop, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 590, 100, -1));

        swingTab1.addTab("Thuộc tính", pnlThuocTinh);

        pnlDanhMuc.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        tblProduct_Not_Category.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "stt", "id", "Tên sản phẩm", "Kiểu", "Giá tiền", "Nguyên liệu", "Độ dày"
            }
        ));
        tblProduct_Not_Category.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProduct_Not_CategoryMouseClicked(evt);
            }
        });
        slpProduct_Has_No_Category_Yet.setViewportView(tblProduct_Not_Category);

        btnPre_Product_Has_No_Category_Yet.setText("<");
        btnPre_Product_Has_No_Category_Yet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPre_Product_Has_No_Category_YetActionPerformed(evt);
            }
        });

        btnNext_Product_Has_No_Category_Yet.setText(">");
        btnNext_Product_Has_No_Category_Yet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNext_Product_Has_No_Category_YetActionPerformed(evt);
            }
        });

        btnThem_pr_DM.setText("Thêm");
        btnThem_pr_DM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem_pr_DMActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel18.setText("Tên sản phẩm: ");

        btnScan_PR_Not_Category.setText("Tìm");
        btnScan_PR_Not_Category.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnScan_PR_Not_CategoryActionPerformed(evt);
            }
        });

        txtPr_Not_Category.setBorder(null);

        jLabel19.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel19.setText("__________________________________________");
        jLabel19.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(slpProduct_Has_No_Category_Yet, javax.swing.GroupLayout.DEFAULT_SIZE, 1107, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnPre_Product_Has_No_Category_Yet, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnNext_Product_Has_No_Category_Yet, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnThem_pr_DM, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(txtPr_Not_Category, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnScan_PR_Not_Category, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtPr_Not_Category, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnScan_PR_Not_Category))
                .addGap(18, 18, 18)
                .addComponent(slpProduct_Has_No_Category_Yet, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPre_Product_Has_No_Category_Yet)
                    .addComponent(btnNext_Product_Has_No_Category_Yet)
                    .addComponent(btnThem_pr_DM))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        swingTabbedPane1.addTab("Sản phẩm chưa thêm vào danh mục", jPanel6);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        tblProduct_Category.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "stt", "id", "Tên sản phẩm", "Kiểu", "Giá tiền", "Nguyên liệu", "Độ dày"
            }
        ));
        tblProduct_Category.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProduct_CategoryMouseClicked(evt);
            }
        });
        slpDanhMuc.setViewportView(tblProduct_Category);

        btnPre_DanhMuc.setText("<");
        btnPre_DanhMuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPre_DanhMucActionPerformed(evt);
            }
        });

        btnNext_DanhMuc.setText(">");
        btnNext_DanhMuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNext_DanhMucActionPerformed(evt);
            }
        });

        btnXoa_prDM.setText("Xóa");
        btnXoa_prDM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa_prDMActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel9.setText("Tên sản phẩm: ");

        txtPr_Category.setBorder(null);

        jLabel13.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel13.setText("__________________________________________");
        jLabel13.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        btnScan_PR_Category.setText("Tìm");
        btnScan_PR_Category.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnScan_PR_CategoryActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(slpDanhMuc, javax.swing.GroupLayout.DEFAULT_SIZE, 1107, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnPre_DanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnNext_DanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoa_prDM, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(txtPr_Category, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnScan_PR_Category, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtPr_Category, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnScan_PR_Category))
                .addGap(18, 18, 18)
                .addComponent(slpDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPre_DanhMuc)
                    .addComponent(btnNext_DanhMuc)
                    .addComponent(btnXoa_prDM))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        swingTabbedPane1.addTab("Sản phẩm đã thêm vào danh mục", jPanel5);

        cbbCategory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbbCategoryMouseClicked(evt);
            }
        });
        cbbCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbCategoryActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel20.setText("Danh mục:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(swingTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addGap(18, 18, 18)
                        .addComponent(cbbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(swingTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
                .addContainerGap())
        );

        swingTabbedPane2.addTab("Danh mục sản phẩm", jPanel2);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel21.setText("Tên Danh mục:");
        jPanel4.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 9, -1, -1));

        txtDanhMuc.setBorder(null);
        jPanel4.add(txtDanhMuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(104, 6, 285, 20));

        jLabel22.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel22.setText("_________________________________________________________");
        jLabel22.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel4.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(104, 16, 290, 20));

        btnAdd_DM.setText("Thêm");
        btnAdd_DM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd_DMActionPerformed(evt);
            }
        });
        jPanel4.add(btnAdd_DM, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 46, 100, -1));

        btnSua_DM.setText("Sửa");
        btnSua_DM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSua_DMActionPerformed(evt);
            }
        });
        jPanel4.add(btnSua_DM, new org.netbeans.lib.awtextra.AbsoluteConstraints(282, 46, 100, -1));

        btnXoa_DM.setText("Xóa");
        btnXoa_DM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa_DMActionPerformed(evt);
            }
        });
        jPanel4.add(btnXoa_DM, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 46, 100, -1));

        btnClear_DM.setText("Làm mới");
        btnClear_DM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClear_DMActionPerformed(evt);
            }
        });
        jPanel4.add(btnClear_DM, new org.netbeans.lib.awtextra.AbsoluteConstraints(518, 46, 100, -1));

        tblDM.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "stt", "id", "Tên"
            }
        ));
        tblDM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDMMouseClicked(evt);
            }
        });
        slpDM.setViewportView(tblDM);

        jPanel4.add(slpDM, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 1129, 390));

        btnPre_DM.setText("<");
        btnPre_DM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPre_DMActionPerformed(evt);
            }
        });
        jPanel4.add(btnPre_DM, new org.netbeans.lib.awtextra.AbsoluteConstraints(917, 485, 100, -1));

        btnNext_DM.setText(">");
        btnNext_DM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNext_DMActionPerformed(evt);
            }
        });
        jPanel4.add(btnNext_DM, new org.netbeans.lib.awtextra.AbsoluteConstraints(1035, 485, 100, -1));

        swingTabbedPane2.addTab("Quản lý danh mục", jPanel4);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        tblCategory_Delete.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "stt", "id", "Name"
            }
        ));
        tblCategory_Delete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCategory_DeleteMouseClicked(evt);
            }
        });
        slpCategory_Delete.setViewportView(tblCategory_Delete);

        btnPre_Category_Delete.setText("<");
        btnPre_Category_Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPre_Category_DeleteActionPerformed(evt);
            }
        });

        btnNext_Category_Delete.setText(">");
        btnNext_Category_Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNext_Category_DeleteActionPerformed(evt);
            }
        });

        btnKhoiPhuc.setText("Khôi phục");
        btnKhoiPhuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhoiPhucActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(slpCategory_Delete, javax.swing.GroupLayout.DEFAULT_SIZE, 1124, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnPre_Category_Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnKhoiPhuc, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNext_Category_Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(118, 118, 118)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(slpCategory_Delete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPre_Category_Delete)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnNext_Category_Delete)
                        .addComponent(btnKhoiPhuc)))
                .addContainerGap(99, Short.MAX_VALUE))
        );

        swingTabbedPane2.addTab("Danh mục ngừng sử dụng", jPanel7);

        javax.swing.GroupLayout pnlDanhMucLayout = new javax.swing.GroupLayout(pnlDanhMuc);
        pnlDanhMuc.setLayout(pnlDanhMucLayout);
        pnlDanhMucLayout.setHorizontalGroup(
            pnlDanhMucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(swingTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1141, Short.MAX_VALUE)
        );
        pnlDanhMucLayout.setVerticalGroup(
            pnlDanhMucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDanhMucLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(swingTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        swingTab1.addTab("Danh mục", pnlDanhMuc);

        pnlSanPhamNgungBan.setBackground(new java.awt.Color(255, 255, 255));

        btnPre_Product_Stop_Sell.setText("<");
        btnPre_Product_Stop_Sell.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPre_Product_Stop_SellActionPerformed(evt);
            }
        });

        btnNext_Product_Stop_Sell.setText(">");
        btnNext_Product_Stop_Sell.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNext_Product_Stop_SellActionPerformed(evt);
            }
        });

        btnRestore_Product.setText("Khôi phục");
        btnRestore_Product.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestore_ProductActionPerformed(evt);
            }
        });

        tblProduc_Stop_sell.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "stt", "id", "Tên sản phẩm", "Kiểu", "Giá tiền", "Nguyên liệu", "Độ dày"
            }
        ));
        tblProduc_Stop_sell.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProduc_Stop_sellMouseClicked(evt);
            }
        });
        slpProduc_Stop_sell.setViewportView(tblProduc_Stop_sell);

        javax.swing.GroupLayout pnlSanPhamNgungBanLayout = new javax.swing.GroupLayout(pnlSanPhamNgungBan);
        pnlSanPhamNgungBan.setLayout(pnlSanPhamNgungBanLayout);
        pnlSanPhamNgungBanLayout.setHorizontalGroup(
            pnlSanPhamNgungBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSanPhamNgungBanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSanPhamNgungBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSanPhamNgungBanLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnPre_Product_Stop_Sell, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnNext_Product_Stop_Sell, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnRestore_Product, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(slpProduc_Stop_sell, javax.swing.GroupLayout.DEFAULT_SIZE, 1129, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlSanPhamNgungBanLayout.setVerticalGroup(
            pnlSanPhamNgungBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSanPhamNgungBanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(slpProduc_Stop_sell, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlSanPhamNgungBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRestore_Product)
                    .addComponent(btnPre_Product_Stop_Sell)
                    .addComponent(btnNext_Product_Stop_Sell))
                .addContainerGap(154, Short.MAX_VALUE))
        );

        swingTab1.addTab("Sản phẩm ngừng bán", pnlSanPhamNgungBan);

        pnlSanPhamCTNgungBan.setBackground(new java.awt.Color(255, 255, 255));

        btnRestore_Product_Detail_Stop_Sell.setText("Khôi phục");
        btnRestore_Product_Detail_Stop_Sell.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestore_Product_Detail_Stop_SellActionPerformed(evt);
            }
        });

        btnNext_Product_Detail_Stop_Sell.setText(">");
        btnNext_Product_Detail_Stop_Sell.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNext_Product_Detail_Stop_SellActionPerformed(evt);
            }
        });

        btnPre_Product_Detail_Stop_Sell.setText("<");
        btnPre_Product_Detail_Stop_Sell.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPre_Product_Detail_Stop_SellActionPerformed(evt);
            }
        });

        tblProduct_Detail_Stop_Sell.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "stt", "id", "Tên sản phẩm", "Kích cỡ", "Màu", "Số lượng"
            }
        ));
        tblProduct_Detail_Stop_Sell.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProduct_Detail_Stop_SellMouseClicked(evt);
            }
        });
        slpProduct_Detail_Stop_Sell.setViewportView(tblProduct_Detail_Stop_Sell);

        javax.swing.GroupLayout pnlSanPhamCTNgungBanLayout = new javax.swing.GroupLayout(pnlSanPhamCTNgungBan);
        pnlSanPhamCTNgungBan.setLayout(pnlSanPhamCTNgungBanLayout);
        pnlSanPhamCTNgungBanLayout.setHorizontalGroup(
            pnlSanPhamCTNgungBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSanPhamCTNgungBanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSanPhamCTNgungBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(slpProduct_Detail_Stop_Sell, javax.swing.GroupLayout.DEFAULT_SIZE, 1129, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSanPhamCTNgungBanLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnPre_Product_Detail_Stop_Sell, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnNext_Product_Detail_Stop_Sell, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnRestore_Product_Detail_Stop_Sell, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlSanPhamCTNgungBanLayout.setVerticalGroup(
            pnlSanPhamCTNgungBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSanPhamCTNgungBanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(slpProduct_Detail_Stop_Sell, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlSanPhamCTNgungBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRestore_Product_Detail_Stop_Sell)
                    .addComponent(btnNext_Product_Detail_Stop_Sell)
                    .addComponent(btnPre_Product_Detail_Stop_Sell))
                .addContainerGap(154, Short.MAX_VALUE))
        );

        swingTab1.addTab("Sản phẩm chi tiết ngừng bán", pnlSanPhamCTNgungBan);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(swingTab1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(swingTab1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductMouseClicked
        // TODO add your handling code here:
        this.loadcbbMaterial();
        this.loadcbbThickness();
        this.loadcbbCustom();

        pnlProduct_Detail.setVisible(true);

        btnAdd_Product.setEnabled(false);
        btnAdd_Product.setBackground(new java.awt.Color(0, 0, 0));
        btnFix_Produc.setEnabled(true);
        bt.Edit(btnFix_Produc);
        btnStop_Sell_Product.setEnabled(true);
        bt.Edit(btnStop_Sell_Product);

        this.pnlProduct_Detail.setVisible(true);
        int row_pr = tblProduct.getSelectedRow();
        com.model.Product pr = this.pds.getNext(txtTenTim.getText().trim(), minProduct_tab1, maxProduct_tab1).get(row_pr);
        idProduct = tblProduct.getValueAt(row_pr, 1).toString();
        txtName_Product.setText(pr.getName_product());
        txtPrice.setText(pr.getProduct_price().toString());
        //material
        if (comboBoxContainsItem(cbbMaterial, tblProduct.getValueAt(row_pr, 5).toString().trim()) == true) {
            this.loadcbbMaterial();
            cbbMaterial.setSelectedItem(pr.getMaterial_id().getNameMaterial());
        } else {
            cbbMaterial.addItem(tblProduct.getValueAt(row_pr, 5).toString().trim());
            cbbMaterial.setSelectedItem(pr.getMaterial_id().getNameMaterial());
        }
        //thickness
        if (comboBoxContainsItem(cbbThickness, tblProduct.getValueAt(row_pr, 6).toString().trim()) == true) {
            this.loadcbbThickness();
            cbbThickness.setSelectedItem(String.valueOf(pr.getThickness_id().getGsm()));
        } else {
            cbbThickness.addItem(tblProduct.getValueAt(row_pr, 6).toString().trim());
            cbbThickness.setSelectedItem(String.valueOf(pr.getThickness_id().getGsm()));
        }
        //custom
        if (comboBoxContainsItem(cbbCustom, tblProduct.getValueAt(row_pr, 3).toString().trim()) == true) {
            this.loadcbbCustom();
            cbbCustom.setSelectedItem(pr.getCustome_id().getNameCustom());
        } else {
            cbbCustom.addItem(tblProduct.getValueAt(row_pr, 3).toString().trim());
            cbbCustom.setSelectedItem(pr.getCustome_id().getNameCustom());
        }

        txtDescribe.setText(pr.getDescription());

        minProduct_detail = 1;
        maxProduct_detail = 10;
        idProduct_Extra = tblProduct.getValueAt(row_pr, 0).toString();
        //xuat anh
        byte[] retrievedImageData = pr.getImage_Type();

        BufferedImage image = getImageFromByteArray(retrievedImageData);
        editedImage = image;
        lblImage.setText("");
        lblImage.setIcon(null);
        if (image != null) {
            lblImage.setText("");
            lblImage.setIcon(null);
            int width = lblImage.getWidth();
            int height = lblImage.getHeight();
            ImageIcon imageIcon = new ImageIcon(image.getScaledInstance(width, height, 0));
            lblImage.setIcon(imageIcon);
        } else {
            lblImage.setIcon(null);
            lblImage.setText("Không thể hiển thị ảnh.");
        }

        this.load_Product_Detail();
    }//GEN-LAST:event_tblProductMouseClicked

    private void btnFix_ProducActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFix_ProducActionPerformed
        // TODO add your handling code here:
        com.model.Product sp = form();
        if (sp == null) {
            sp = formUpdate();
            int check = JOptionPane.showConfirmDialog(this, "Bạn chắc chứ?", "Sửa", JOptionPane.YES_NO_OPTION);
            if (check == JOptionPane.YES_OPTION) {
                if (this.pds.sua(idProduct, sp) == true) {
                    JOptionPane.showMessageDialog(this, "Sửa thành công");
                    this.load_Product_Detail();
                    this.load_Product();
                    this.clearBien();
//            this.loadCatory_Pr();
//            this.loadCatory_not_Pr();
//            this.load_Product_Extra();
//            this.loadcbbProduct();
                } else {
                    JOptionPane.showMessageDialog(this, "Sửa thất bại.");
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(this, "Đã hủy.");
                return;
            }
        } else {
            int check = JOptionPane.showConfirmDialog(this, "Bạn chắc chứ?", "Sửa", JOptionPane.YES_NO_OPTION);
            if (check == JOptionPane.YES_OPTION) {
                if (this.pds.sua(idProduct, sp) == true) {
                    JOptionPane.showMessageDialog(this, "Sửa thành công");
                    this.load_Product_Detail();
                    this.load_Product();
//            this.loadCatory_Pr();
//            this.loadCatory_not_Pr();
//            this.load_Product_Extra();
//            this.loadcbbProduct();
                } else {
                    JOptionPane.showMessageDialog(this, "Sửa thất bại.");
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(this, "Đã hủy.");
                return;
            }
        }


    }//GEN-LAST:event_btnFix_ProducActionPerformed

    private void rdoColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoColorActionPerformed
        // TODO add your handling code here:
        idTT = "";
        idTT_Stop = "";
        minAttribute = 1;
        maxAttribute = 10;
        this.loadColer();
        this.loadColer_Stop();
    }//GEN-LAST:event_rdoColorActionPerformed

    private void rdoSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoSizeActionPerformed
        // TODO add your handling code here:
        idTT = "";
        idTT_Stop = "";
        minAttribute = 1;
        maxAttribute = 10;
        this.loadSize();
        this.loadSize_Stop();
    }//GEN-LAST:event_rdoSizeActionPerformed

    private void rdoMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoMaterialActionPerformed
        // TODO add your handling code here:
        idTT = "";
        idTT_Stop = "";
        minAttribute = 1;
        maxAttribute = 10;
        this.loadMaterial();
        this.loadMaterial_Stop();
    }//GEN-LAST:event_rdoMaterialActionPerformed

    private void rdoThicknessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoThicknessActionPerformed
        // TODO add your handling code here:
        idTT = "";
        idTT_Stop = "";
        minAttribute = 1;
        maxAttribute = 10;
        this.loadThickness();
        this.loadThickness_Stop();
    }//GEN-LAST:event_rdoThicknessActionPerformed

    private void rdoCustomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoCustomActionPerformed
        // TODO add your handling code here:
        idTT = "";
        idTT_Stop = "";
        minAttribute = 1;
        maxAttribute = 10;
        this.loadCustom();
        this.loadCustom_Stop();
    }//GEN-LAST:event_rdoCustomActionPerformed

    private void btnClear_ProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClear_ProductActionPerformed
        // TODO add your handling code here:
        btnAdd_Product.setEnabled(true);
        bt.Edit(btnAdd_Product);
        btnFix_Produc.setEnabled(false);
        btnFix_Produc.setBackground(new java.awt.Color(0, 0, 0));
        btnStop_Sell_Product.setEnabled(false);
        btnStop_Sell_Product.setBackground(new java.awt.Color(0, 0, 0));

        pnlProduct_Detail.setVisible(false);
        this.clearBien();
    }//GEN-LAST:event_btnClear_ProductActionPerformed

    private void btnAdd_AttributeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd_AttributeActionPerformed
        // TODO add your handling code here:
        String ten = txtName_Attribute.getText().trim();
        if (ten.equals("")) {
            return;
        }
        int check = JOptionPane.showConfirmDialog(this, "Bạn chắc chứ?", "Thêm", JOptionPane.YES_NO_OPTION);
        if (check == JOptionPane.YES_OPTION) {
            if (rdoSize.isSelected() == true) {
                Size s = new Size(ten);
                if (this.ss.Insert(s)) {
                    JOptionPane.showMessageDialog(this, "Đã thêm size.");
                    this.loadSize();
                    this.loadcbbCustom();
                    this.loadcbbMaterial();
                    this.loadcbbThickness();
                    this.loadcbbSize();
                    this.loadcbbColor();
                }

            } else if (rdoColor.isSelected() == true) {
                Color c = new Color(ten);

                if (this.cls.them(c)) {
                    JOptionPane.showMessageDialog(this, "Đã thêm màu");
                    this.loadColer();
                    this.loadcbbCustom();
                    this.loadcbbMaterial();
                    this.loadcbbThickness();
                    this.loadcbbSize();
                    this.loadcbbColor();
                }

            } else if (rdoMaterial.isSelected() == true) {
                Material m = new Material(ten);
                if (m == null) {
                    return;
                }
                if (this.mts.them(m)) {
                    JOptionPane.showMessageDialog(this, "Đã thêm Vật liệu.");
                    this.loadMaterial();
                    this.loadcbbCustom();
                    this.loadcbbMaterial();
                    this.loadcbbThickness();
                    this.loadcbbSize();
                    this.loadcbbColor();
                }

            } else if (rdoThickness.isSelected() == true) {
                try {
                    int dd = Integer.parseInt(ten);
                    Thickness t = new Thickness(dd);
                    if (t == null) {
                        return;
                    }
                    if (this.tns.them(t)) {
                        JOptionPane.showMessageDialog(this, "Đã thêm độ dày.");
                        this.loadThickness();
                        this.loadcbbCustom();
                        this.loadcbbMaterial();
                        this.loadcbbThickness();
                        this.loadcbbSize();
                        this.loadcbbColor();
                    }
                } catch (Exception e) {
                    //e.printStackTrace();
                }

            } else if (rdoCustom.isSelected() == true) {
                Custom c = new Custom(ten);
                if (c == null) {
                    return;
                }
                if (this.cts.them(c)) {
                    JOptionPane.showMessageDialog(this, "Đã thêm kiểu.");
                    this.loadCustom();
                    this.loadcbbCustom();
                    this.loadcbbMaterial();
                    this.loadcbbThickness();
                    this.loadcbbSize();
                    this.loadcbbColor();
                }

            }
        } else {
            JOptionPane.showMessageDialog(this, "Đã hủy.");
            return;
        }

    }//GEN-LAST:event_btnAdd_AttributeActionPerformed

    private void btnFix_AttributeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFix_AttributeActionPerformed
        // TODO add your handling code here:
        String ten = txtName_Attribute.getText().trim();
        if (ten.equals("")) {
            return;
        }
        int check = JOptionPane.showConfirmDialog(this, "Bạn chắc chứ?", "Sửa", JOptionPane.YES_NO_OPTION);
        if (check == JOptionPane.YES_OPTION) {
            if (rdoSize.isSelected() == true) {
                Size s = new Size(ten);

                if (this.ss.Update(idTT, s)) {
                    JOptionPane.showMessageDialog(this, "Đã sửa size.");
                    this.loadSize();
                    this.loadcbbCustom();
                    this.loadcbbMaterial();
                    this.loadcbbThickness();
                    this.loadcbbSize();
                    this.loadcbbColor();
                }

            } else if (rdoColor.isSelected() == true) {
                Color c = new Color(ten);

                if (this.cls.sua(idTT, c)) {
                    JOptionPane.showMessageDialog(this, "Đã sửa màu.");
                    this.loadColer();
                    this.loadcbbCustom();
                    this.loadcbbMaterial();
                    this.loadcbbThickness();
                    this.loadcbbSize();
                    this.loadcbbColor();
                }

            } else if (rdoMaterial.isSelected() == true) {
                Material m = new Material(ten);
                if (m == null) {
                    return;
                }
                if (this.mts.sua(idTT, m)) {
                    JOptionPane.showMessageDialog(this, "Đã sửa vật liệu.");
                    this.loadMaterial();
                    this.loadcbbCustom();
                    this.loadcbbMaterial();
                    this.loadcbbThickness();
                    this.loadcbbSize();
                    this.loadcbbColor();
                }

            } else if (rdoThickness.isSelected() == true) {
                try {
                    int dd = Integer.parseInt(ten);
                    Thickness t = new Thickness(dd);
                    if (t == null) {
                        return;
                    }
                    if (this.tns.sua(idTT, t)) {
                        JOptionPane.showMessageDialog(this, "Đã sửa độ dày.");
                        this.loadThickness();
                        this.loadcbbCustom();
                        this.loadcbbMaterial();
                        this.loadcbbThickness();
                        this.loadcbbSize();
                        this.loadcbbColor();
                    }
                } catch (Exception e) {
                    //e.printStackTrace();
                }

            } else if (rdoCustom.isSelected() == true) {
                Custom c = new Custom(ten);
                if (c == null) {
                    return;
                }
                if (this.cts.sua(idTT, c)) {
                    JOptionPane.showMessageDialog(this, "Đã sửa kiểu.");
                    this.loadCustom();
                    this.loadcbbCustom();
                    this.loadcbbMaterial();
                    this.loadcbbThickness();
                    this.loadcbbSize();
                    this.loadcbbColor();
                }

            }

        } else {
            JOptionPane.showMessageDialog(this, "Đã hủy.");
            return;
        }
        if (rdoSize.isSelected() == true) {
            Size s = new Size(ten);

            if (this.ss.Update(idTT, s)) {
                JOptionPane.showMessageDialog(this, "Đã sửa size.");
                this.loadSize();
                this.loadcbbCustom();
                this.loadcbbMaterial();
                this.loadcbbThickness();
                this.loadcbbSize();
                this.loadcbbColor();
            }

        } else if (rdoColor.isSelected() == true) {
            Color c = new Color(ten);

            if (this.cls.sua(idTT, c)) {
                JOptionPane.showMessageDialog(this, "Đã sửa màu.");
                this.loadColer();
                this.loadcbbCustom();
                this.loadcbbMaterial();
                this.loadcbbThickness();
                this.loadcbbSize();
                this.loadcbbColor();
            }

        } else if (rdoMaterial.isSelected() == true) {
            Material m = new Material(ten);
            if (m == null) {
                return;
            }
            if (this.mts.sua(idTT, m)) {
                JOptionPane.showMessageDialog(this, "Đã sửa vật liệu.");
                this.loadMaterial();
                this.loadcbbCustom();
                this.loadcbbMaterial();
                this.loadcbbThickness();
                this.loadcbbSize();
                this.loadcbbColor();
            }

        } else if (rdoThickness.isSelected() == true) {
            try {
                int dd = Integer.parseInt(ten);
                Thickness t = new Thickness(dd);
                if (t == null) {
                    return;
                }
                if (this.tns.sua(idTT, t)) {
                    JOptionPane.showMessageDialog(this, "Đã sửa độ dày.");
                    this.loadThickness();
                    this.loadcbbCustom();
                    this.loadcbbMaterial();
                    this.loadcbbThickness();
                    this.loadcbbSize();
                    this.loadcbbColor();
                }
            } catch (Exception e) {
                //e.printStackTrace();
            }

        } else if (rdoCustom.isSelected() == true) {
            Custom c = new Custom(ten);
            if (c == null) {
                return;
            }
            if (this.cts.sua(idTT, c)) {
                JOptionPane.showMessageDialog(this, "Đã sửa kiểu.");
                this.loadCustom();
                this.loadcbbCustom();
                this.loadcbbMaterial();
                this.loadcbbThickness();
                this.loadcbbSize();
                this.loadcbbColor();
            }

        }
    }//GEN-LAST:event_btnFix_AttributeActionPerformed

    private void btnClear_AttributeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClear_AttributeActionPerformed
        // TODO add your handling code here:
        this.clear();
    }//GEN-LAST:event_btnClear_AttributeActionPerformed

    private void tblAttributeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAttributeMouseClicked
        // TODO add your handling code here:
        int row = tblAttribute.getSelectedRow();
        idTT = tblAttribute.getValueAt(row, 1).toString();
        txtName_Attribute.setText(tblAttribute.getValueAt(row, 2).toString());
    }//GEN-LAST:event_tblAttributeMouseClicked

    private void btnPre__ProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPre__ProductActionPerformed
        // TODO add your handling code here:
        maxProduct_tab1 -= 10;
        minProduct_tab1 = maxProduct_tab1 - 9;
        if (minProduct_tab1 < 1) {
            maxProduct_tab1 = 10;
            minProduct_tab1 = 1;
            return;
        }
        this.load_Product();
    }//GEN-LAST:event_btnPre__ProductActionPerformed

    private void btnNext__ProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNext__ProductActionPerformed
        // TODO add your handling code here:
        minProduct_tab1 = maxProduct_tab1 + 1;
        maxProduct_tab1 += 10;
        boolean checkList = checkNull_Table(this.pds.getNext(txtTenTim.getText().trim(), minProduct_tab1, maxProduct_tab1));
        if (checkList) {
            this.load_Product();
        } else {
            JOptionPane.showMessageDialog(this, "Da het trang.");
            maxProduct_tab1 -= 10;
            minProduct_tab1 = maxProduct_tab1 - 9;
            return;
        }

    }//GEN-LAST:event_btnNext__ProductActionPerformed

    private void btnPre_Product_Stop_SellActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPre_Product_Stop_SellActionPerformed
        // TODO add your handling code here:
        maxProduct_Stop_Sell -= 10;
        minProduct_Detail_Stop_Sell = maxProduct_Detail_Stop_Sell - 9;
        if (maxProduct_Stop_Sell < 1) {
            maxProduct_Stop_Sell = 10;
            minProduct_Detail_Stop_Sell = 1;
            return;
        }
        this.loadProduct_Stop();
    }//GEN-LAST:event_btnPre_Product_Stop_SellActionPerformed

    private void btnNext_Product_Stop_SellActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNext_Product_Stop_SellActionPerformed
        // TODO add your handling code here:
        minProduct_Stop_Sell = maxProduct_Stop_Sell + 1;
        maxProduct_Stop_Sell += 10;
        boolean checkList = checkNull_Table(this.pds.getNext_Product_Stop_selling(minProduct_Stop_Sell, maxProduct_Stop_Sell));
        if (checkList) {
            this.loadProduct_Stop();
        } else {
            JOptionPane.showMessageDialog(this, "Da het trang.");
            maxProduct_Stop_Sell -= 10;
            minProduct_Stop_Sell = maxProduct_Stop_Sell - 9;
            return;
        }

    }//GEN-LAST:event_btnNext_Product_Stop_SellActionPerformed

    private void btnRestore_ProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestore_ProductActionPerformed
        if (idProduct_Stop_Sell.equalsIgnoreCase("")) {
            return;
        }
        int check = JOptionPane.showConfirmDialog(this, "Bạn chắc chứ?", "Khôi phục", JOptionPane.YES_NO_OPTION);
        if (check == JOptionPane.YES_OPTION) {
            if (this.pds.KhoiPhuc(idProduct_Stop_Sell)) {
                JOptionPane.showMessageDialog(this, "Khôi phục thành công.");
                //            product = new Product(f);
                this.loadProduct_Stop();
                this.load_Product();
//            this.loadcbbProduct();
//            this.load_Product_Extra();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Đã hủy.");
            return;
        }

    }//GEN-LAST:event_btnRestore_ProductActionPerformed

    private void tblProduc_Stop_sellMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProduc_Stop_sellMouseClicked
        // TODO add your handling code here:
        int row = tblProduc_Stop_sell.getSelectedRow();
        if (row < 0) {
            return;
        }
        idProduct_Stop_Sell = tblProduc_Stop_sell.getValueAt(row, 1).toString();
    }//GEN-LAST:event_tblProduc_Stop_sellMouseClicked

    private void btnStop_Sell_ProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStop_Sell_ProductActionPerformed
        if (txtName_Product.getText().trim().equalsIgnoreCase("")) {
            return;
        }
        int check = JOptionPane.showConfirmDialog(this, "Bạn chắc chứ?", "Ngừng bán", JOptionPane.YES_NO_OPTION);
        if (check == JOptionPane.YES_OPTION) {
            if (this.pds.xoa(txtName_Product.getText().trim()) == true) {
                JOptionPane.showMessageDialog(this, "Sản phẩm đã ngừng bán.");
                this.load_Product_Detail();
                this.load_Product();
//            this.load_Product_Extra();
//            this.loadcbbProduct();
                this.loadProduct_Stop();
                this.loadProduct_Deteail_Stop_Sell();
//            this.loadCatory_Pr();
//            this.loadCatory_not_Pr();
                this.clearBien();
            } else {
                JOptionPane.showMessageDialog(this, "Thất bại.");
                return;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Đã hủy.");
            return;
        }

    }//GEN-LAST:event_btnStop_Sell_ProductActionPerformed

    private void btnRestore_Product_Detail_Stop_SellActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestore_Product_Detail_Stop_SellActionPerformed
        // TODO add your handling code here:
        if (idProduct_Detail_Stop_Sell == null) {
            return;
        }
        int check = JOptionPane.showConfirmDialog(this, "Bạn chắc chứ?", "Sửa", JOptionPane.YES_NO_OPTION);
        if (check == JOptionPane.YES_OPTION) {
            if (pdds.khoiPhuc(idProduct_Detail_Stop_Sell)) {
                JOptionPane.showMessageDialog(this, "Khôi phục thành công.");
                this.loadProduct_Deteail_Stop_Sell();
                this.load_Product_Detail();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Đã hủy.");
            return;
        }

    }//GEN-LAST:event_btnRestore_Product_Detail_Stop_SellActionPerformed

    private void btnNext_Product_Detail_Stop_SellActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNext_Product_Detail_Stop_SellActionPerformed
        // TODO add your handling code here:
        minProduct_Detail_Stop_Sell = maxProduct_Detail_Stop_Sell + 1;
        maxProduct_Detail_Stop_Sell += 10;
        boolean checkList = checkNull_Table(this.pdds.getRestore_Product_Detail_stop_selling(minProduct_Detail_Stop_Sell, maxProduct_Detail_Stop_Sell));
        if (checkList) {
            this.loadProduct_Deteail_Stop_Sell();
        } else {
            JOptionPane.showMessageDialog(this, "Da het trang.");
            maxProduct_Detail_Stop_Sell -= 10;
            minProduct_Detail_Stop_Sell = maxProduct_Detail_Stop_Sell - 9;
            return;
        }
    }//GEN-LAST:event_btnNext_Product_Detail_Stop_SellActionPerformed

    private void btnPre_Product_Detail_Stop_SellActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPre_Product_Detail_Stop_SellActionPerformed
        // TODO add your handling code here:
        maxProduct_Detail_Stop_Sell -= 10;
        minProduct_Detail_Stop_Sell = maxProduct_Detail_Stop_Sell - 9;
        if (maxProduct_Detail_Stop_Sell < 1) {
            maxProduct_Detail_Stop_Sell = 10;
            minProduct_Detail_Stop_Sell = 1;
            return;
        }
        this.loadProduct_Deteail_Stop_Sell();
    }//GEN-LAST:event_btnPre_Product_Detail_Stop_SellActionPerformed

    private void tblProduct_Detail_Stop_SellMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProduct_Detail_Stop_SellMouseClicked
        // TODO add your handling code here:
        int row = tblProduct_Detail_Stop_Sell.getSelectedRow();
        if (row < 0) {
            return;
        }
        idProduct_Detail_Stop_Sell = tblProduct_Detail_Stop_Sell.getValueAt(row, 1).toString();
    }//GEN-LAST:event_tblProduct_Detail_Stop_SellMouseClicked

    private void btnStop_Working_AttributeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStop_Working_AttributeActionPerformed
        // TODO add your handling code here:
        if (idTT.equals("")) {
            return;
        }
        int check = JOptionPane.showConfirmDialog(this, "Bạn chắc chứ?", "Xóa", JOptionPane.YES_NO_OPTION);
        if (check == JOptionPane.YES_OPTION) {
            if (rdoSize.isSelected() == true) {
                if (this.ss.remove(idTT)) {
                    JOptionPane.showMessageDialog(this, "Đã xóa size.");
                    this.loadSize();
                    this.loadcbbCustom();
                    this.loadcbbMaterial();
                    this.loadcbbThickness();
                    this.loadcbbSize();
                    this.loadcbbColor();
                    this.loadSize_Stop();
                }

            } else if (rdoColor.isSelected() == true) {
                if (this.cls.xoa(idTT)) {
                    JOptionPane.showMessageDialog(this, "Đã xóa màu.");
                    this.loadColer();
                    this.loadcbbCustom();
                    this.loadcbbMaterial();
                    this.loadcbbThickness();
                    this.loadcbbSize();
                    this.loadcbbColor();
                }

            } else if (rdoMaterial.isSelected() == true) {
                if (this.mts.xoa(idTT)) {
                    JOptionPane.showMessageDialog(this, "Đã xóa vật liệu.");
                    this.loadMaterial();
                    this.loadcbbCustom();
                    this.loadcbbMaterial();
                    this.loadcbbThickness();
                    this.loadcbbSize();
                    this.loadcbbColor();
                }

            } else if (rdoThickness.isSelected() == true) {
                try {
                    if (this.tns.xoa(idTT)) {
                        JOptionPane.showMessageDialog(this, "Đã xóa độ dạy.");
                        this.loadThickness();
                        this.loadcbbCustom();
                        this.loadcbbMaterial();
                        this.loadcbbThickness();
                        this.loadcbbSize();
                        this.loadcbbColor();
                    }
                } catch (Exception e) {
                    //e.printStackTrace();
                }

            } else if (rdoCustom.isSelected() == true) {
                if (this.cts.xoa(idTT)) {
                    JOptionPane.showMessageDialog(this, "Đã xóa kiểu.");
                    this.loadCustom();
                    this.loadcbbCustom();
                    this.loadcbbMaterial();
                    this.loadcbbThickness();
                    this.loadcbbSize();
                    this.loadcbbColor();
                }

            }
        } else {
            JOptionPane.showMessageDialog(this, "Đã hủy.");
            return;
        }

    }//GEN-LAST:event_btnStop_Working_AttributeActionPerformed

    private void tblProduct_Not_CategoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProduct_Not_CategoryMouseClicked
        // TODO add your handling code here:
        int row = tblProduct_Not_Category.getSelectedRow();
        if (row < 0) {
            return;
        }
        com.model.Product pr = this.ctg.getProduct_not_Category(namePr_not_category, nameCategory, minProduct_not_Category, maxProduct_not_Category).get(row);
        id_Pr_Not_Ctegory = pr.getId().trim();
        id_cbbDanhMuc = getID_Category(cbbCategory.getSelectedItem().toString());
    }//GEN-LAST:event_tblProduct_Not_CategoryMouseClicked

    private void btnPre_Product_Has_No_Category_YetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPre_Product_Has_No_Category_YetActionPerformed
        // TODO add your handling code here:
        maxProduct_not_Category -= 10;
        minProduct_not_Category = maxProduct_not_Category - 9;
        if (minProduct_not_Category < 1) {
            maxProduct_not_Category = 10;
            minProduct_not_Category = 1;
            return;
        }
        this.loadCatory_not_Pr();
    }//GEN-LAST:event_btnPre_Product_Has_No_Category_YetActionPerformed

    private void btnNext_Product_Has_No_Category_YetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNext_Product_Has_No_Category_YetActionPerformed
        // TODO add your handling code here:
        minProduct_not_Category = maxProduct_not_Category + 1;
        maxProduct_not_Category += 10;
        boolean checkList = checkNull_Table(this.pds.getNext(txtTenTim.getText().trim(), minProduct_not_Category, maxProduct_not_Category));
        if (checkList) {
            this.loadCatory_not_Pr();
        } else {
            JOptionPane.showMessageDialog(this, "Da het trang.");
            maxProduct_not_Category -= 10;
            minProduct_not_Category = maxProduct_not_Category - 9;
            return;
        }
    }//GEN-LAST:event_btnNext_Product_Has_No_Category_YetActionPerformed

    private void btnAdd_Product_Detail1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd_Product_Detail1ActionPerformed
        // TODO add your handling code here:
        ProductDetail prdt = formct();
        if (this.checkSPCTNB(txtName_Product.getText().trim(), cbbSize1.getSelectedItem().toString(), cbbColor1.getSelectedItem().toString()) == false) {
            return;
        }

        if (this.checkSPCTCB(txtName_Product.getText().trim(), cbbSize1.getSelectedItem().toString(), cbbColor1.getSelectedItem().toString()) == false) {
            return;
        }

        if (prdt == null) {
            return;
        }
        int check = JOptionPane.showConfirmDialog(this, "Bạn chắc chứ?", "Thêm", JOptionPane.YES_NO_OPTION);
        if (check == JOptionPane.YES_OPTION) {
            if (pdds.them(prdt) == true) {
                JOptionPane.showMessageDialog(this, "Thêm thành công.");
                this.load_Product_Detail();
//                this.clearBien();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại.");
                return;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Đã hủy.");
            return;
        }

    }//GEN-LAST:event_btnAdd_Product_Detail1ActionPerformed

    private void btnFix_Product_Detail1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFix_Product_Detail1ActionPerformed
        // TODO add your handling code here:
//        int row = tblProduct_Detail1.getSelectedRow();
//        if (row < 0) {
//            return;
//        }
        System.out.println("da an");
        ProductDetail spct = formct();
        System.out.println("da an 2");
        System.out.println("cai" + spct);
        if (spct == null) {
            System.out.println("qua loc 1");
            spct = formctS();
            if(spct == null){
                System.out.println("false ");
                return;
            }
            int check = JOptionPane.showConfirmDialog(this, "Bạn chắc chứ?", "Sửa", JOptionPane.YES_NO_OPTION);
            if (check == JOptionPane.YES_OPTION) {
                if (this.pdds.sua(idProduct_Detail, spct) == true) {
                    JOptionPane.showMessageDialog(this, "Sửa thành công.");
                    this.load_Product_Detail();
//                    this.clearBien();
                } else {
                    JOptionPane.showMessageDialog(this, "Sửa thất bại.");
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(this, "Đã hủy.");
                return;
            }
        }else{
            int check = JOptionPane.showConfirmDialog(this, "Bạn chắc chứ?", "Sửa", JOptionPane.YES_NO_OPTION);
            if (check == JOptionPane.YES_OPTION) {
                if (this.pdds.sua(idProduct_Detail, spct) == true) {
                    JOptionPane.showMessageDialog(this, "Sửa thành công.");
                    this.load_Product_Detail();
                } else {
                    JOptionPane.showMessageDialog(this, "Sửa thất bại.");
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(this, "Đã hủy.");
                return;
            }
        }
    }//GEN-LAST:event_btnFix_Product_Detail1ActionPerformed

    private void btnStop_Sell_Product_Detail1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStop_Sell_Product_Detail1ActionPerformed
        // TODO add your handling code here:
        int row = tblProduct_Detail1.getSelectedRow();
        if (row < 0) {
            return;
        }
        int check = JOptionPane.showConfirmDialog(this, "Bạn chắc chứ?", "Ngừng bán", JOptionPane.YES_NO_OPTION);
        if (check == JOptionPane.YES_OPTION) {
            if (this.pdds.xoa(idProduct_Detail) == true) {
                JOptionPane.showMessageDialog(this, "Sản phẩm đã ngừng bán.");
                this.load_Product_Detail();
                this.loadProduct_Deteail_Stop_Sell();
                this.clearBien();
            } else {
                JOptionPane.showMessageDialog(this, "Thất bại.");
                return;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Đã hủy.");
            return;
        }

    }//GEN-LAST:event_btnStop_Sell_Product_Detail1ActionPerformed

    private void btnClear_Product_Detail1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClear_Product_Detail1ActionPerformed
        // TODO add your handling code here:
        btnAdd_Product_Detail1.setEnabled(true);
        bt.Edit(btnAdd_Product_Detail1);
        btnFix_Product_Detail1.setEnabled(false);
        btnFix_Product_Detail1.setBackground(new java.awt.Color(0, 0, 0));
        btnStop_Sell_Product_Detail1.setEnabled(false);
        btnStop_Sell_Product_Detail1.setBackground(new java.awt.Color(0, 0, 0));

        this.loadcbbColor();
        this.loadSize();
        cbbColor1.setSelectedIndex(0);
        cbbSize1.setSelectedIndex(0);

        txtQuantity1.setText("");
        lblImage_Detail.setIcon(null);
        lblImage_Detail.setText("Ảnh");
//        this.clearBien();

    }//GEN-LAST:event_btnClear_Product_Detail1ActionPerformed

    private void tblProduct_Detail1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProduct_Detail1MouseClicked
        // TODO add your handling code here:
        this.loadcbbSize();
        this.loadcbbColor();

        btnAdd_Product_Detail1.setEnabled(false);
        btnAdd_Product_Detail1.setBackground(new java.awt.Color(0, 0, 0));
        
        btnFix_Product_Detail1.setEnabled(true);
        bt.Edit(btnFix_Product_Detail1);
        
        btnStop_Sell_Product_Detail1.setEnabled(true);
        bt.Edit(btnStop_Sell_Product_Detail1);

        int row = tblProduct_Detail1.getSelectedRow();
        if (row < 0) {
            return;
        }
        ProductDetail prd = this.pdds.getProductDetail_Selling_Next(idProduct, minProduct_detail, maxProduct_detail).get(row);
        idProduct_Detail = tblProduct_Detail1.getValueAt(row, 1).toString().trim();
        cbbColor1.setSelectedItem(tblProduct_Detail1.getValueAt(row, 3).toString().trim());
        cbbSize1.setSelectedItem(tblProduct_Detail1.getValueAt(row, 2).toString().trim());
        txtQuantity1.setText(tblProduct_Detail1.getValueAt(row, 4).toString().trim());
        //xuat anh
        byte[] retrievedImageData = prd.getImage();
        BufferedImage image = getImageFromByteArray(retrievedImageData);

        if (comboBoxContainsItem(cbbSize1, tblProduct_Detail1.getValueAt(row, 2).toString().trim()) == true) {
            this.loadcbbSize();
            cbbSize1.setSelectedItem(tblProduct_Detail1.getValueAt(row, 2).toString().trim());
        } else {
            cbbSize1.addItem(tblProduct_Detail1.getValueAt(row, 2).toString().trim());
            cbbSize1.setSelectedItem(tblProduct_Detail1.getValueAt(row, 2).toString().trim());
        }

        if (comboBoxContainsItem(cbbColor1, tblProduct_Detail1.getValueAt(row, 3).toString().trim()) == true) {
            this.loadcbbColor();
            cbbColor1.setSelectedItem(tblProduct_Detail1.getValueAt(row, 3).toString().trim());
        } else {
            cbbColor1.addItem(tblProduct_Detail1.getValueAt(row, 3).toString().trim());
            cbbColor1.setSelectedItem(tblProduct_Detail1.getValueAt(row, 3).toString().trim());
        }

        editedImage_Detail = image;
        lblImage_Detail.setText("");
        lblImage_Detail.setIcon(null);
        if (image != null) {
            lblImage_Detail.setText("");
            lblImage_Detail.setIcon(null);
            int width = lblImage_Detail.getWidth();
            int height = lblImage_Detail.getHeight();
            ImageIcon imageIcon = new ImageIcon(image.getScaledInstance(width, height, 0));
            lblImage_Detail.setIcon(imageIcon);
        } else {
            lblImage_Detail.setIcon(null);
            lblImage_Detail.setText("Không thể hiển thị ảnh.");
        }
    }//GEN-LAST:event_tblProduct_Detail1MouseClicked

    private void btnPre_Product_Detail1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPre_Product_Detail1ActionPerformed
        // TODO add your handling code here:
        maxProduct_detail -= 10;
        minProduct_detail = maxProduct_detail - 9;
        if (minProduct_detail < 1) {
            maxProduct_detail = 10;
            minProduct_detail = 1;
            return;
        }
        this.load_Product_Detail();
    }//GEN-LAST:event_btnPre_Product_Detail1ActionPerformed

    private void btnNext_Product_Detail1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNext_Product_Detail1ActionPerformed
        // TODO add your handling code here:
        minProduct_detail = maxProduct_detail + 1;
        maxProduct_detail += 10;
        boolean checkList = checkNull_Table(this.pdds.getProductDetail_Selling_Next(idProduct, minProduct_detail, maxProduct_detail));
        if (checkList) {
            this.load_Product_Detail();
        } else {
            JOptionPane.showMessageDialog(this, "Da het trang.");
            maxProduct_detail -= 10;
            minProduct_detail = maxProduct_detail - 9;
            return;
        }
    }//GEN-LAST:event_btnNext_Product_Detail1ActionPerformed

    private void btnPre_AttributeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPre_AttributeActionPerformed
        // TODO add your handling code here:
        if (rdoSize.isSelected() == true) {
            maxAttribute -= 10;
            minAttribute = maxAttribute - 9;
            if (minAttribute < 1) {
                maxAttribute = 10;
                minAttribute = 1;
                return;
            }
            this.loadSize();
        } else if (rdoColor.isSelected() == true) {
            maxAttribute -= 10;
            minAttribute = maxAttribute - 9;
            if (minAttribute < 1) {
                maxAttribute = 10;
                minAttribute = 1;
                return;
            }
            this.loadColer();
        } else if (rdoMaterial.isSelected() == true) {
            maxAttribute -= 10;
            minAttribute = maxAttribute - 9;
            if (minAttribute < 1) {
                maxAttribute = 10;
                minAttribute = 1;
                return;
            }
            this.loadMaterial();
        } else if (rdoThickness.isSelected() == true) {
            maxAttribute -= 10;
            minAttribute = maxAttribute - 9;
            if (minAttribute < 1) {
                maxAttribute = 10;
                minAttribute = 1;
                return;
            }
            this.loadThickness();
        } else if (rdoCustom.isSelected() == true) {
            maxAttribute -= 10;
            minAttribute = maxAttribute - 9;
            if (minAttribute < 1) {
                maxAttribute = 10;
                minAttribute = 1;
                return;
            }
            this.loadCustom();
        }
    }//GEN-LAST:event_btnPre_AttributeActionPerformed

    private void btnNext_AttributeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNext_AttributeActionPerformed
        // TODO add your handling code here:
        if (rdoSize.isSelected() == true) {
            minAttribute = maxAttribute + 1;
            maxAttribute += 10;
            boolean checkList = checkNull_Table(this.ss.getSize_Sell(minAttribute, maxAttribute));
            if (checkList) {
                this.loadSize();
            } else {
                JOptionPane.showMessageDialog(this, "Da het trang.");
                maxAttribute -= 10;
                minAttribute = maxAttribute - 9;
                return;
            }
        } else if (rdoColor.isSelected() == true) {
            minAttribute = maxAttribute + 1;
            maxAttribute += 10;
            boolean checkList = checkNull_Table(this.cls.getColor_Sell(minAttribute, maxAttribute));
            if (checkList) {
                this.loadColer();
            } else {
                JOptionPane.showMessageDialog(this, "Da het trang.");
                maxAttribute -= 10;
                minAttribute = maxAttribute - 9;
                return;
            }
        } else if (rdoMaterial.isSelected() == true) {
            minAttribute = maxAttribute + 1;
            maxAttribute += 10;
            boolean checkList = checkNull_Table(this.mts.getMaterial_Sell(minAttribute, maxAttribute));
            if (checkList) {
                this.loadMaterial();
            } else {
                JOptionPane.showMessageDialog(this, "Da het trang.");
                maxAttribute -= 10;
                minAttribute = maxAttribute - 9;
                return;
            }
        } else if (rdoThickness.isSelected() == true) {
            minAttribute = maxAttribute + 1;
            maxAttribute += 10;
            boolean checkList = checkNull_Table(this.tns.getThickness_sell(minAttribute, maxAttribute));
            if (checkList) {
                this.loadThickness();
            } else {
                JOptionPane.showMessageDialog(this, "Da het trang.");
                maxAttribute -= 10;
                minAttribute = maxAttribute - 9;
                return;
            }
        } else if (rdoCustom.isSelected() == true) {
            minAttribute = maxAttribute + 1;
            maxAttribute += 10;
            boolean checkList = checkNull_Table(this.cts.getCustom_Sell(minAttribute, maxAttribute));
            if (checkList) {
                this.loadCustom();
            } else {
                JOptionPane.showMessageDialog(this, "Da het trang.");
                maxAttribute -= 10;
                minAttribute = maxAttribute - 9;
                return;
            }
        }
    }//GEN-LAST:event_btnNext_AttributeActionPerformed

    private void lblImageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImageMouseClicked
        // TODO add your handling code here:
        try {
            JFileChooser fieChooser = new JFileChooser();
            int option = fieChooser.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                lblImage.setText("");
                file = fieChooser.getSelectedFile();
                Image img = ImageIO.read(file);
                String filePath = file.getAbsolutePath();
                int width = lblImage.getWidth();
                int height = lblImage.getHeight();
                lblImage.setIcon(new ImageIcon(img.getScaledInstance(width, height, 0)));
                nameImage_Product = filePath.trim();

            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }//GEN-LAST:event_lblImageMouseClicked

    private void lblImage_DetailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImage_DetailMouseClicked
        // TODO add your handling code here:
        try {
            JFileChooser fieChooser = new JFileChooser();
            int option = fieChooser.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                lblImage_Detail.setText("");
                file = fieChooser.getSelectedFile();
                Image img = ImageIO.read(file);
                String filePath = file.getAbsolutePath();
                int width = lblImage_Detail.getWidth();
                int height = lblImage_Detail.getHeight();
                lblImage_Detail.setIcon(new ImageIcon(img.getScaledInstance(width, height, 0)));
                nameImage_Product_Detail = filePath.trim();

                System.out.println("ten file: " + nameImage_Product_Detail);

            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }//GEN-LAST:event_lblImage_DetailMouseClicked

    private void btnAdd_ProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd_ProductActionPerformed
        // TODO add your handling code here:
        com.model.Product sp = form();
        if (sp == null) {
            return;
        }
        if (checkSP(txtName_Product.getText().trim()) == false) {
            return;
        }
        if (checkSPKOSD(txtName_Product.getText().trim()) == false) {
            return;
        }
        int check = JOptionPane.showConfirmDialog(this, "Bạn chắc chứ?", "Thêm", JOptionPane.YES_NO_OPTION);
        if (check == JOptionPane.YES_OPTION) {
            if (pds.them(sp) == true) {
                JOptionPane.showMessageDialog(this, "Thêm thành công.");
                this.load_Product();
                this.clearBien();
//            this.loadCatory_Pr();
//            this.loadCatory_not_Pr();
//            this.load_Product_Extra();
//            this.loadcbbProduct();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại.");
                return;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Đã hủy.");
        }

    }//GEN-LAST:event_btnAdd_ProductActionPerformed

    private void cbbCustomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbCustomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbCustomActionPerformed

    private void txtQuantity1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQuantity1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQuantity1ActionPerformed

    private void tblProductMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tblProductMouseEntered

    private void tblProduct_CategoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProduct_CategoryMouseClicked
        int row = tblProduct_Category.getSelectedRow();
        if (row < 0) {
            return;
        }
        com.model.Product pr = this.ctg.getProduct_Category(namePr_Category, nameCategory, minProduct_Category, maxProduct_Category).get(row);
        id_Pr_Category = pr.getId().trim();
        id_cbbDanhMuc = getID_Category(cbbCategory.getSelectedItem().toString());
    }//GEN-LAST:event_tblProduct_CategoryMouseClicked

    private void btnPre_DanhMucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPre_DanhMucActionPerformed
        // TODO add your handling code here:
        maxProduct_Category -= 10;
        minProduct_Category = maxProduct_Category - 9;
        if (minProduct_not_Category < 1) {
            maxProduct_Category = 10;
            minProduct_Category = 1;
            return;
        }
        this.loadCatory_Pr();
    }//GEN-LAST:event_btnPre_DanhMucActionPerformed

    private void btnNext_DanhMucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNext_DanhMucActionPerformed
        // TODO add your handling code here:
        minProduct_Category = maxProduct_Category + 1;
        maxProduct_Category += 10;
        boolean checkList = checkNull_Table(this.pds.getNext(txtTenTim.getText().trim(), minAttribute, maxAttribute));
        if (checkList) {
            this.loadCatory_Pr();
        } else {
            JOptionPane.showMessageDialog(this, "Da het trang.");
            maxProduct_Category -= 10;
            minProduct_Category = maxProduct_Category - 9;
            return;
        }
    }//GEN-LAST:event_btnNext_DanhMucActionPerformed

    private void btnXoa_prDMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa_prDMActionPerformed
        // TODO add your handling code here:
        int row = tblProduct_Category.getSelectedRow();
        if (row < 0) {
            return;

        }
        ProductCategory p = this.getProduct_Category_Form_Remove();
        if (p == null) {
            return;
        }
        int row2 = JOptionPane.showConfirmDialog(this, "Bạn muốn xóa sản phẩm này vào danh mục này sao?", "Danh mục", JOptionPane.YES_NO_OPTION);
        if (row2 == JOptionPane.YES_OPTION) {
            if (this.pcs.Delete(p)) {
                JOptionPane.showMessageDialog(this, "Xóathành công.");
                this.loadCatory_Pr();
                this.loadCatory_not_Pr();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa thất bại");
                return;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Đã hủy.");
            return;
        }
    }//GEN-LAST:event_btnXoa_prDMActionPerformed

    private void btnScan_PR_CategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnScan_PR_CategoryActionPerformed
        // TODO add your handling code here:
        namePr_Category = "%" + txtPr_Category.getText().trim() + "%";
        this.loadCatory_Pr();
    }//GEN-LAST:event_btnScan_PR_CategoryActionPerformed

    private void btnScan_PR_Not_CategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnScan_PR_Not_CategoryActionPerformed
        // TODO add your handling code here:
        namePr_not_category = "%" + txtPr_Not_Category.getText().trim() + "%";
        this.loadCatory_not_Pr();
    }//GEN-LAST:event_btnScan_PR_Not_CategoryActionPerformed

    private void cbbCategoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbbCategoryMouseClicked

    }//GEN-LAST:event_cbbCategoryMouseClicked

    private void cbbCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbCategoryActionPerformed
        // TODO add your handling code here:
        nameCategory = cbbCategory.getSelectedItem().toString();
        this.loadCatory_Pr();
        this.loadCatory_not_Pr();
    }//GEN-LAST:event_cbbCategoryActionPerformed

    private void btnAdd_DMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd_DMActionPerformed
        // TODO add your handling code here:
        Category c = getForm_Category();
        if (c == null) {
            return;
        }
        int row = JOptionPane.showConfirmDialog(this, "Bạn muốn thêm danh mục này sao", "Danh mục", JOptionPane.YES_NO_OPTION);
        if (row == JOptionPane.YES_OPTION) {
            if (this.ctg.Insert(c)) {
                JOptionPane.showMessageDialog(this, "thêm thành công.");
                this.loadCatory();
                this.loadcbbDanhMuc();
                this.loadCatory_Delete();
            } else {
                JOptionPane.showMessageDialog(this, "thêm thất bại");
                this.loadCatory();
                this.loadcbbDanhMuc();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Đã hủy.");
            return;
        }


    }//GEN-LAST:event_btnAdd_DMActionPerformed

    private void btnSua_DMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua_DMActionPerformed
        // TODO add your handling code here:
        Category c = getForm_Category();
        if (c == null) {
            return;
        }
        int row = JOptionPane.showConfirmDialog(this, "Bạn muốn Sửa danh mục này sao", "Danh mục", JOptionPane.YES_NO_OPTION);
        if (row == JOptionPane.YES_OPTION) {
            if (this.ctg.update(id_Category, c)) {
                JOptionPane.showMessageDialog(this, "Sửa thành công.");
                this.loadCatory();
                this.loadcbbDanhMuc();
                this.loadCatory_Delete();
            } else {
                JOptionPane.showMessageDialog(this, "Sửa thất bại");
                this.loadCatory();
                this.loadcbbDanhMuc();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Đã hủy.");
            return;
        }
    }//GEN-LAST:event_btnSua_DMActionPerformed

    private void btnXoa_DMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa_DMActionPerformed
        // TODO add your handling code here:
        int row = tblDM.getSelectedRow();
        if (row < 0) {
            return;
        }
        int row2 = JOptionPane.showConfirmDialog(this, "Bạn muốn Xóa danh mục này sao", "Danh mục", JOptionPane.YES_NO_OPTION);
        if (row2 == JOptionPane.YES_OPTION) {
            if (this.ctg.delete(id_Category)) {
                JOptionPane.showMessageDialog(this, "Xóa thành công.");
                this.loadCatory();
                this.loadcbbDanhMuc();
                this.loadCatory_Delete();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa thất bại");
                this.loadCatory();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Đã hủy.");
            return;
        }
    }//GEN-LAST:event_btnXoa_DMActionPerformed

    private void btnClear_DMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClear_DMActionPerformed
        // TODO add your handling code here:
        this.clear();
    }//GEN-LAST:event_btnClear_DMActionPerformed

    private void tblDMMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDMMouseClicked
        // TODO add your handling code here:
        int row = tblDM.getSelectedRow();
        Category c = this.ctg.getCategory_Action_Next(minCategory, maxCategory).get(row);
        txtDanhMuc.setText(c.getNameCategory());
        id_Category = c.getId().trim();
    }//GEN-LAST:event_tblDMMouseClicked

    private void btnPre_DMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPre_DMActionPerformed
        // TODO add your handling code here:
        maxCategory -= 10;
        minCategory = maxCategory - 9;
        if (minCategory < 1) {
            maxCategory = 10;
            minCategory = 1;
            return;
        }
        this.loadCatory();
    }//GEN-LAST:event_btnPre_DMActionPerformed

    private void btnNext_DMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNext_DMActionPerformed
        // TODO add your handling code here:
        minCategory = maxCategory + 1;
        maxCategory += 10;
        boolean checkList = checkNull_Table(this.pds.getNext(txtTenTim.getText().trim(), minAttribute, maxAttribute));
        if (checkList) {
            this.loadCatory_not_Pr();
        } else {
            JOptionPane.showMessageDialog(this, "Da het trang.");
            maxCategory -= 10;
            minCategory = maxCategory - 9;
            return;
        }
    }//GEN-LAST:event_btnNext_DMActionPerformed

    private void btnPre_Category_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPre_Category_DeleteActionPerformed
        // TODO add your handling code here:
        maxCategory_Delete -= 10;
        minCategory_Delete = maxCategory_Delete - 9;
        if (minCategory_Delete < 1) {
            maxCategory_Delete = 10;
            minCategory_Delete = 1;
            return;
        }
        this.loadCatory_Delete();
    }//GEN-LAST:event_btnPre_Category_DeleteActionPerformed

    private void btnNext_Category_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNext_Category_DeleteActionPerformed
        // TODO add your handling code here:
        minCategory_Delete = maxCategory_Delete + 1;
        maxCategory_Delete += 10;
        boolean checkList = checkNull_Table(this.pds.getNext(txtTenTim.getText().trim(), minAttribute, maxAttribute));
        if (checkList) {
            this.loadCatory_Delete();
        } else {
            JOptionPane.showMessageDialog(this, "Da het trang.");
            maxCategory_Delete -= 10;
            minCategory_Delete = maxCategory_Delete - 9;
            return;
        }
    }//GEN-LAST:event_btnNext_Category_DeleteActionPerformed

    private void btnKhoiPhucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhoiPhucActionPerformed
        // TODO add your handling code here:
        int row = tblCategory_Delete.getSelectedRow();
        if (row < 0) {
            return;
        }
        int row2 = JOptionPane.showConfirmDialog(this, "Bạn muốn khôi phục danh mục này sao?", "Danh mục", JOptionPane.YES_NO_OPTION);
        if (row2 == JOptionPane.YES_OPTION) {
            if (this.ctg.Restore(id_Category_Delete)) {
                JOptionPane.showMessageDialog(this, "Khôi phục thành công.");
                this.loadCatory();
                this.loadcbbDanhMuc();
                this.loadCatory_Delete();
            } else {
                JOptionPane.showMessageDialog(this, "Khôi phục thất bại");
                this.loadCatory();
                this.loadcbbDanhMuc();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Đã hủy.");
            return;
        }
    }//GEN-LAST:event_btnKhoiPhucActionPerformed

    private void tblCategory_DeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCategory_DeleteMouseClicked
        // TODO add your handling code here:
        int row = tblCategory_Delete.getSelectedRow();
        if (row < 0) {
            return;
        }
        Category c = this.ctg.getCategory_not_Action_Next(minCategory_Delete, maxCategory_Delete).get(row);
        id_Category_Delete = c.getId();
    }//GEN-LAST:event_tblCategory_DeleteMouseClicked

    private void btnThem_pr_DMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem_pr_DMActionPerformed
        // TODO add your handling code here:
        int row = tblProduct_Not_Category.getSelectedRow();
        if (row < 0) {
            return;

        }
        ProductCategory p = this.getProduct_Category_Form_Add();
        if (p == null) {
            return;
        }
        int row2 = JOptionPane.showConfirmDialog(this, "Bạn muốn thêm sản phẩm này vào danh mục này sao?", "Danh mục", JOptionPane.YES_NO_OPTION);
        if (row2 == JOptionPane.YES_OPTION) {
            if (this.pcs.Insert(p)) {
                JOptionPane.showMessageDialog(this, "thêm thành công.");
                this.loadCatory_Pr();
                this.loadCatory_not_Pr();
            } else {
                JOptionPane.showMessageDialog(this, "thêm thất bại");
                return;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Đã hủy.");
            return;
        }
    }//GEN-LAST:event_btnThem_pr_DMActionPerformed

    private void btnTimSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimSPActionPerformed
        // TODO add your handling code here:
        this.load_Product();
    }//GEN-LAST:event_btnTimSPActionPerformed

    private void swingTab1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_swingTab1MouseClicked
//        // TODO add your handling code here:
//        this.clearBien();
    }//GEN-LAST:event_swingTab1MouseClicked

    private void tblAttribute_StopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAttribute_StopMouseClicked
        // TODO add your handling code here:
        int row = tblAttribute_Stop.getSelectedRow();
        idTT_Stop = tblAttribute_Stop.getValueAt(row, 1).toString().trim();
    }//GEN-LAST:event_tblAttribute_StopMouseClicked

    private void btnPre_Attribute_StopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPre_Attribute_StopActionPerformed
        // TODO add your handling code here:
        if (rdoSize.isSelected() == true) {
            maxAttribute_Stop -= 10;
            minAttribute_stop = maxAttribute_Stop - 9;
            if (minAttribute_stop < 1) {
                maxAttribute_Stop = 10;
                minAttribute_stop = 1;
                return;
            }
            this.loadSize();
        } else if (rdoColor.isSelected() == true) {
            maxAttribute_Stop -= 10;
            minAttribute_stop = maxAttribute_Stop - 9;
            if (minAttribute_stop < 1) {
                maxAttribute_Stop = 10;
                minAttribute_stop = 1;
                return;
            }
            this.loadColer();
        } else if (rdoMaterial.isSelected() == true) {
            maxAttribute_Stop -= 10;
            minAttribute_stop = maxAttribute_Stop - 9;
            if (minAttribute_stop < 1) {
                maxAttribute_Stop = 10;
                minAttribute_stop = 1;
                return;
            }
            this.loadMaterial();
        } else if (rdoThickness.isSelected() == true) {
            maxAttribute_Stop -= 10;
            minAttribute_stop = maxAttribute_Stop - 9;
            if (minAttribute_stop < 1) {
                maxAttribute_Stop = 10;
                minAttribute_stop = 1;
                return;
            }
            this.loadThickness();
        } else if (rdoCustom.isSelected() == true) {
            maxAttribute_Stop -= 10;
            minAttribute_stop = maxAttribute_Stop - 9;
            if (minAttribute_stop < 1) {
                maxAttribute_Stop = 10;
                minAttribute_stop = 1;
                return;
            }
            this.loadCustom();
        }
    }//GEN-LAST:event_btnPre_Attribute_StopActionPerformed

    private void btnKhoiPhuc_ActributeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhoiPhuc_ActributeActionPerformed
        // TODO add your handling code here:
        if (idTT_Stop.equals("")) {
            return;
        }
        int check = JOptionPane.showConfirmDialog(this, "Bạn chắc chứ?", "Khôi phục", JOptionPane.YES_NO_OPTION);
        if (check == JOptionPane.YES_OPTION) {
            if (rdoSize.isSelected() == true) {
                if (this.ss.getKP(idTT_Stop)) {
                    JOptionPane.showMessageDialog(this, "Đã khôi phục size.");
                    this.loadSize();
                    this.loadcbbCustom();
                    this.loadcbbMaterial();
                    this.loadcbbThickness();
                    this.loadcbbSize();
                    this.loadcbbColor();
                    this.loadSize_Stop();
                    this.loadSize_Stop();
                }

            } else if (rdoColor.isSelected() == true) {
                if (this.cls.getKP(idTT_Stop)) {
                    JOptionPane.showMessageDialog(this, "Đã khôi phục màu.");
                    this.loadColer();
                    this.loadcbbCustom();
                    this.loadcbbMaterial();
                    this.loadcbbThickness();
                    this.loadcbbSize();
                    this.loadcbbColor();
                    this.loadColer_Stop();
                }

            } else if (rdoMaterial.isSelected() == true) {
                if (this.mts.getKP(idTT_Stop)) {
                    JOptionPane.showMessageDialog(this, "Đã khôi phục vật liệu.");
                    this.loadMaterial();
                    this.loadcbbCustom();
                    this.loadcbbMaterial();
                    this.loadcbbThickness();
                    this.loadcbbSize();
                    this.loadcbbColor();
                    this.loadMaterial_Stop();
                }

            } else if (rdoThickness.isSelected() == true) {
                try {
                    if (this.tns.getKP(idTT_Stop)) {
                        JOptionPane.showMessageDialog(this, "Đã khôi phục độ dạy.");
                        this.loadThickness();
                        this.loadcbbCustom();
                        this.loadcbbMaterial();
                        this.loadcbbThickness();
                        this.loadcbbSize();
                        this.loadcbbColor();
                        this.loadThickness_Stop();
                    }
                } catch (Exception e) {
                    //e.printStackTrace();
                }

            } else if (rdoCustom.isSelected() == true) {
                if (this.cts.getKP(idTT_Stop)) {
                    JOptionPane.showMessageDialog(this, "Đã khôi phục kiểu.");
                    this.loadCustom();
                    this.loadcbbCustom();
                    this.loadcbbMaterial();
                    this.loadcbbThickness();
                    this.loadcbbSize();
                    this.loadcbbColor();
                    this.loadCustom_Stop();
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Đã hủy.");
            return;
        }

    }//GEN-LAST:event_btnKhoiPhuc_ActributeActionPerformed

    private void btnNext_Attribute_StopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNext_Attribute_StopActionPerformed
        // TODO add your handling code here:
        if (rdoSize.isSelected() == true) {
            minAttribute_stop = maxAttribute_Stop + 1;
            maxAttribute_Stop += 10;
            boolean checkList = checkNull_Table(this.pds.getNext(txtTenTim.getText().trim(), minAttribute_stop, maxAttribute_Stop));
            if (checkList) {
                this.loadSize();
            } else {
                JOptionPane.showMessageDialog(this, "Da het trang.");
                maxAttribute_Stop -= 10;
                minAttribute_stop = maxAttribute_Stop - 9;
                return;
            }
        } else if (rdoColor.isSelected() == true) {
            minAttribute_stop = maxAttribute_Stop + 1;
            maxAttribute_Stop += 10;
            boolean checkList = checkNull_Table(this.pds.getNext(txtTenTim.getText().trim(), minAttribute_stop, maxAttribute_Stop));
            if (checkList) {
                this.loadColer();
            } else {
                JOptionPane.showMessageDialog(this, "Da het trang.");
                maxAttribute_Stop -= 10;
                minAttribute_stop = maxAttribute_Stop - 9;
                return;
            }
        } else if (rdoMaterial.isSelected() == true) {
            minAttribute_stop = maxAttribute_Stop + 1;
            maxAttribute_Stop += 10;
            boolean checkList = checkNull_Table(this.pds.getNext(txtTenTim.getText().trim(), minAttribute_stop, maxAttribute_Stop));
            if (checkList) {
                this.loadMaterial();
            } else {
                JOptionPane.showMessageDialog(this, "Da het trang.");
                maxAttribute_Stop -= 10;
                minAttribute_stop = maxAttribute_Stop - 9;
                return;
            }
        } else if (rdoThickness.isSelected() == true) {
            minAttribute_stop = maxAttribute_Stop + 1;
            maxAttribute_Stop += 10;
            boolean checkList = checkNull_Table(this.pds.getNext(txtTenTim.getText().trim(), minAttribute_stop, maxAttribute_Stop));
            if (checkList) {
                this.loadThickness();
            } else {
                JOptionPane.showMessageDialog(this, "Da het trang.");
                maxAttribute_Stop -= 10;
                minAttribute_stop = maxAttribute_Stop - 9;
                return;
            }
        } else if (rdoCustom.isSelected() == true) {
            minAttribute_stop = maxAttribute_Stop + 1;
            maxAttribute_Stop += 10;
            boolean checkList = checkNull_Table(this.pds.getNext(txtTenTim.getText().trim(), minAttribute_stop, maxAttribute_Stop));
            if (checkList) {
                this.loadCustom();
            } else {
                JOptionPane.showMessageDialog(this, "Da het trang.");
                maxAttribute_Stop -= 10;
                minAttribute_stop = maxAttribute_Stop - 9;
                return;
            }
        }
    }//GEN-LAST:event_btnNext_Attribute_StopActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgTT;
    private javax.swing.JButton btnAdd_Attribute;
    private javax.swing.JButton btnAdd_DM;
    private javax.swing.JButton btnAdd_Product;
    private javax.swing.JButton btnAdd_Product_Detail1;
    private javax.swing.JButton btnClear_Attribute;
    private javax.swing.JButton btnClear_DM;
    private javax.swing.JButton btnClear_Product;
    private javax.swing.JButton btnClear_Product_Detail1;
    private javax.swing.JButton btnFix_Attribute;
    private javax.swing.JButton btnFix_Produc;
    private javax.swing.JButton btnFix_Product_Detail1;
    private javax.swing.JButton btnKhoiPhuc;
    private javax.swing.JButton btnKhoiPhuc_Actribute;
    private javax.swing.JButton btnNext_Attribute;
    private javax.swing.JButton btnNext_Attribute_Stop;
    private javax.swing.JButton btnNext_Category_Delete;
    private javax.swing.JButton btnNext_DM;
    private javax.swing.JButton btnNext_DanhMuc;
    private javax.swing.JButton btnNext_Product_Detail1;
    private javax.swing.JButton btnNext_Product_Detail_Stop_Sell;
    private javax.swing.JButton btnNext_Product_Has_No_Category_Yet;
    private javax.swing.JButton btnNext_Product_Stop_Sell;
    private javax.swing.JButton btnNext__Product;
    private javax.swing.JButton btnPre_Attribute;
    private javax.swing.JButton btnPre_Attribute_Stop;
    private javax.swing.JButton btnPre_Category_Delete;
    private javax.swing.JButton btnPre_DM;
    private javax.swing.JButton btnPre_DanhMuc;
    private javax.swing.JButton btnPre_Product_Detail1;
    private javax.swing.JButton btnPre_Product_Detail_Stop_Sell;
    private javax.swing.JButton btnPre_Product_Has_No_Category_Yet;
    private javax.swing.JButton btnPre_Product_Stop_Sell;
    private javax.swing.JButton btnPre__Product;
    private javax.swing.JButton btnRestore_Product;
    private javax.swing.JButton btnRestore_Product_Detail_Stop_Sell;
    private javax.swing.JButton btnScan_PR_Category;
    private javax.swing.JButton btnScan_PR_Not_Category;
    private javax.swing.JButton btnStop_Sell_Product;
    private javax.swing.JButton btnStop_Sell_Product_Detail1;
    private javax.swing.JButton btnStop_Working_Attribute;
    private javax.swing.JButton btnSua_DM;
    private javax.swing.JButton btnThem_pr_DM;
    private javax.swing.JButton btnTimSP;
    private javax.swing.JButton btnXoa_DM;
    private javax.swing.JButton btnXoa_prDM;
    private com.swing.Combobox cbbCategory;
    private com.swing.Combobox cbbColor1;
    private com.swing.Combobox cbbCustom;
    private com.swing.Combobox cbbMaterial;
    private com.swing.Combobox cbbSize1;
    private com.swing.Combobox cbbThickness;
    private javax.swing.JButton jButton2;
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
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblImage_Detail;
    private javax.swing.JPanel pnlDanhMuc;
    private javax.swing.JPanel pnlProduct_Detail;
    private javax.swing.JPanel pnlSanPham;
    private javax.swing.JPanel pnlSanPhamCTNgungBan;
    private javax.swing.JPanel pnlSanPhamNgungBan;
    private javax.swing.JPanel pnlThuocTinh;
    private javax.swing.JRadioButton rdoColor;
    private javax.swing.JRadioButton rdoCustom;
    private javax.swing.JRadioButton rdoMaterial;
    private javax.swing.JRadioButton rdoSize;
    private javax.swing.JRadioButton rdoThickness;
    private javax.swing.JScrollPane slpAttribute;
    private javax.swing.JScrollPane slpAttribute_Stop;
    private javax.swing.JScrollPane slpCategory_Delete;
    private javax.swing.JScrollPane slpDM;
    private javax.swing.JScrollPane slpDanhMuc;
    private javax.swing.JScrollPane slpProduc_Stop_sell;
    private javax.swing.JScrollPane slpProduct;
    private javax.swing.JScrollPane slpProduct_Detail;
    private javax.swing.JScrollPane slpProduct_Detail_Stop_Sell;
    private javax.swing.JScrollPane slpProduct_Has_No_Category_Yet;
    private com.swing.SwingTabbedPane swingTab1;
    private com.swing.SwingTabbedPane swingTabbedPane1;
    private com.swing.SwingTabbedPane swingTabbedPane2;
    private javax.swing.JTable tblAttribute;
    private javax.swing.JTable tblAttribute_Stop;
    private javax.swing.JTable tblCategory_Delete;
    private javax.swing.JTable tblDM;
    private javax.swing.JTable tblProduc_Stop_sell;
    private javax.swing.JTable tblProduct;
    private javax.swing.JTable tblProduct_Category;
    private javax.swing.JTable tblProduct_Detail1;
    private javax.swing.JTable tblProduct_Detail_Stop_Sell;
    private javax.swing.JTable tblProduct_Not_Category;
    private com.swing.TextAreaScroll textAreaScroll2;
    private javax.swing.JTextField txtDanhMuc;
    private com.swing.TextArea txtDescribe;
    private javax.swing.JTextField txtName_Attribute;
    private javax.swing.JTextField txtName_Product;
    private javax.swing.JTextField txtPr_Category;
    private javax.swing.JTextField txtPr_Not_Category;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtQuantity1;
    private javax.swing.JTextField txtTenTim;
    // End of variables declaration//GEN-END:variables
}
