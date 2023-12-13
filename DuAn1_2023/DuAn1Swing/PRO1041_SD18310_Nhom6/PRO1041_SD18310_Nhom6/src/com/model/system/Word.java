package com.model.system;

import com.model.response.BillRes;
import com.model.response.ProductRes;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;

public class Word {

    public static void createFile(BillRes bill) {
        try {
            // Kiểm tra và tạo thư mục "HoaDon" trong ổ C nếu nó chưa tồn tại
            String directoryPath = "C:\\HoaDon";
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdir();
            }

            // Tạo tệp Word mới
            XWPFDocument document = new XWPFDocument();

            // Tiêu đề hóa đơn
            createHeading(document, "HÓA ĐƠN BÁN HÀNG");

            // Thông tin khách hàng
            createLineText(document, "Mã hóa đơn: " + bill.getCode(), ParagraphAlignment.CENTER);
            createLineText(document, "Khách Hàng: " + bill.getCustomeName(), ParagraphAlignment.LEFT);
            createLineText(document, "Địa chỉ: " + bill.getCustomeAddress(), ParagraphAlignment.LEFT);
            createLineText(document, "Số điện thoại: " + bill.getCustomePhone(), ParagraphAlignment.LEFT);

            // Bảng sản phẩm
            createProductTable(document, bill.getProducts());

            addBreak(document);

            // Tổng cộng
            createBoldText(document, "GIẢM GIÁ: " + formatCurrency(bill.getSaleOfMoney()), ParagraphAlignment.LEFT);
            createBoldText(document, "Tổng cộng: " + formatCurrency(bill.getTotal()), ParagraphAlignment.LEFT);

            createBoldText(document, "NGƯỜI TẠO", ParagraphAlignment.RIGHT);
            createLineText(document, "Admin", ParagraphAlignment.RIGHT);

            // Lưu tệp Word
            // Lấy đối tượng Class của class chứa hàm main
            FileOutputStream fos = new FileOutputStream("C:\\HoaDon\\Hóa Đơn " + bill.getCustomeName() + " mã " + bill.getCode() + ".docx");
            document.write(fos);
            fos.close();

            System.out.println("Đã tạo hóa đơn thành công.");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void createHeading(XWPFDocument document, String text) {
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = paragraph.createRun();
        run.setFontSize(16);
        run.setBold(true);
        run.setText(text);
    }

    private static void createLineText(XWPFDocument document, String text, ParagraphAlignment index) {
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setAlignment(index);
        XWPFRun run = paragraph.createRun();
        run.setText(text);
//        run.addBreak();
    }

    private static void addBreak(XWPFDocument document) {
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.addBreak();
    }

    private static void createProductTable(XWPFDocument document, List<ProductRes> list) {
        // Tạo bảng với 5 cột
        XWPFTable table = document.createTable(1, 5);

        // Đặt thuộc tính cho bảng
        CTTblWidth tblWidth = table.getCTTbl().addNewTblPr().addNewTblW();
        tblWidth.setType(STTblWidth.PCT);
        tblWidth.setW(BigInteger.valueOf(100 * 50)); // 50% của đơn vị PCT

        // Đặt tiêu đề cho từng cột
        String[] headers = {"Mã Sản Phẩm", "Tên Sản Phẩm", "Số lượng", "Đơn giá", "Thành tiền"};
        for (int i = 0; i < headers.length; i++) {
            XWPFTableCell cell = table.getRow(0).getCell(i);
            cell.setText(headers[i]);

            // Đặt kiểu chữ và căn giữ
            XWPFParagraph paragraph = cell.getParagraphs().get(0);
            paragraph.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun run = paragraph.createRun();
            run.setBold(true);
        }

        // Thêm các dòng sản phẩm (có thể thêm dòng từ cơ sở dữ liệu ở đây)
        list.forEach(i -> {
            createProductRow(table,
                    i.getCodeProduct(),
                    i.getName(),
                    i.getQuantity().toString(),
                    i.getUnitPrice(),
                    i.getTotal());
        });
    }

    private static void createProductRow(XWPFTable table, String code, String name, String quantity, BigDecimal unitPrice, BigDecimal total) {
        XWPFTableRow row = table.createRow();
        row.getCell(0).setText(code);
        row.getCell(1).setText(name);
        row.getCell(2).setText(quantity);
        row.getCell(3).setText(formatCurrency(unitPrice));
        row.getCell(4).setText(formatCurrency(total));

        // Căn giữ tất cả các ô trong dòng
        for (XWPFTableCell cell : row.getTableCells()) {
            XWPFParagraph paragraph = cell.getParagraphs().get(0);
            paragraph.setAlignment(ParagraphAlignment.CENTER);
        }
    }

    private static void createBoldText(XWPFDocument document, String text, ParagraphAlignment index) {
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setAlignment(index);
        XWPFRun run = paragraph.createRun();
        run.setBold(true);
        run.setText(text);
    }

    public static String formatCurrency(BigDecimal amount) {
        // Kiểu tiền tệ Việt Nam
        Locale vietnameseLocale = new Locale("vi", "VN");

        // Định dạng số tiền
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(vietnameseLocale);

        // Sử dụng phương thức format để định dạng số tiền từ BigDecimal thành chuỗi
        return currencyFormatter.format(amount);
    }

}
