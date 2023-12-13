/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

/**
 *
 * @author TgNam
 */
public class Validate {
    private static final String REGEX_TXT = "^(?=\\s*\\S)(.{1,100})$";
    private static final String REGEX_NAME = "^(?=\\s*\\S)(.{1,40})$";
    private static final String REGEX_DATE = "^(19\\d{2}|20\\d{2}|2023)-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$";
    private static final String REGEX_PhoneNumble = "^0[0-9]{9}$" ;
    private static final String REGEX_Voucher = "^[0-9]{10}$" ;
    public static boolean isCheckName(String name) {
        return name.trim().matches(REGEX_NAME);
    }
    public static boolean isCheckTXT(String txt) {
        return txt.trim().matches(REGEX_TXT);
    }
    public static boolean isCheckDate(String date) {
        return date.trim().matches(REGEX_DATE);
    }
    public static boolean isCheckPhone(String phone) {
        return phone.trim().matches(REGEX_PhoneNumble);
    }
        public static boolean isCheckVoucher(String voucher) {
        return voucher.trim().matches(REGEX_Voucher);
    }
}
