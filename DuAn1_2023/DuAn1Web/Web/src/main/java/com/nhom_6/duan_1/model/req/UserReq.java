package com.nhom_6.duan_1.model.req;

import lombok.Data;

@Data
public class UserReq {
    String email;
    String password;
    String name;
    String address;
    String phone;
}
