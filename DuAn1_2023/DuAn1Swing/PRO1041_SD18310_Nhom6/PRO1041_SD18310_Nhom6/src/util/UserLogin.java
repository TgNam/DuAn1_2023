/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

public class UserLogin {

    private static UserLogin userLogin;
    private  String chucVu;
    
    public static UserLogin getUserLogin() {
        if (userLogin == null) {
            userLogin = new UserLogin();
        }
        return userLogin;
    }

    public UserLogin() {
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

   
}
