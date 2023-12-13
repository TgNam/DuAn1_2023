/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author TgNam
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

public class ImageDatabaseExample {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/db_levents";
            String username = "root";
            String password = "root";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            // Lưu ảnh vào cơ sở dữ liệu
            String imagePath = "C:\\Users\\lenovo\\Desktop\\image\\6.jpg"; // Đường dẫn đến file ảnh
            byte[] imageData = readImageFile(imagePath);

            String description = "";

            String insertQuery = "update db_levents.product set image_type = ?, image_data = ? where id = 1;";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setBytes(2, imageData);
                preparedStatement.setString(1, description);
                preparedStatement.executeUpdate();
                System.out.println("Lưu ảnh vào cơ sở dữ liệu thành công.");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    private static byte[] readImageFile(String imagePath) throws IOException {
        File file = new File(imagePath);
        byte[] imageData = new byte[(int) file.length()];
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            fileInputStream.read(imageData);
        }
        return imageData;
    }
}
