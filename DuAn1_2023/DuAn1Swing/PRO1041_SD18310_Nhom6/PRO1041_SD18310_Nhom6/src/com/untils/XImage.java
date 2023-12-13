/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.untils;

import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author thiet
 */
public class XImage {
    
    public void setImag(JLabel imgIn, String linkImg){
        try {
            imgIn.setText("");
            ImageIcon imgIcon = new ImageIcon(getClass().getResource(linkImg));
            Image img = imgIcon.getImage();
            int rong = imgIn.getWidth();
            int dai = imgIn.getHeight();
            imgIn.setIcon(new ImageIcon(img.getScaledInstance(rong, dai, 0)));
        } catch (Exception e) {
            e.printStackTrace();
        }  
    }
    
    public static Image getAppIcon(){
        URL url = XImage.class.getResource("/util.User2_1.png");
        return new ImageIcon(url).getImage();  
    }
    
    public static  boolean  getSave(File src){
        File dst = new File("src\\Icon", src.getName());
        if(!dst.getParentFile().exists()){
            dst.getParentFile().mkdirs();//tao thu muc neu chua co
        }
        try {
            Path from = Paths.get(src.getAbsolutePath());
            Path to = Paths.get(dst.getAbsolutePath());
            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
            
        }
    }
//    
    public static  ImageIcon read(String fileName){
        File path = new  File("src\\Icon",fileName);
        return new ImageIcon(path.getAbsolutePath());
    }
}
