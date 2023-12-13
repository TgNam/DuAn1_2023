/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author TgNam
 */
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.sql.*;

public class ImageDisplayExample {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Hiển thị ảnh");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JLabel imageLabel = new JLabel();
            frame.getContentPane().add(imageLabel);

            String url = "jdbc:mysql://localhost:3306/db_levents";
            String username = "root";
            String password = "root";

            try (Connection connection = DriverManager.getConnection(url, username, password)) {
                String description = "1";

                String selectQuery = "SELECT image_data FROM product WHERE id = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                    preparedStatement.setString(1, description);

                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if (resultSet.next()) {
                            byte[] retrievedImageData = resultSet.getBytes("image_data");
                            // Hiển thị ảnh trên JLabel
                            BufferedImage image = getImageFromByteArray(retrievedImageData);
                            if (image != null) {
                                ImageIcon imageIcon = new ImageIcon(image);
                                imageLabel.setIcon(imageIcon);
                            } else {
                                imageLabel.setText("Không thể hiển thị ảnh.");
                            }
                        } else {
                            System.out.println("Không tìm thấy ảnh với mô tả đã cho.");
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            frame.pack();
            frame.setVisible(true);
        });
    }

    private static BufferedImage getImageFromByteArray(byte[] imageData) {
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(imageData);
            return ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
