package com.nhom_6.duan_1.model.req;

import lombok.Data;

import java.util.List;

@Data
public class CartReq {

    Long cartId;
    List<ProductReq> data;
}
