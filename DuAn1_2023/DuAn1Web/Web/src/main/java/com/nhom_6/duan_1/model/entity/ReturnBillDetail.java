package com.nhom_6.duan_1.model.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="ReturnBillDetail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReturnBillDetail extends BaseEntity {
    @Column
    private int quantityOfProductsReturned;
    @Column
    private double priceAtTheTimeOfPurchase;
    @Column
    private String status;

    @ManyToOne
    @JoinColumn(name="returnBill_id")
    @JsonBackReference
    private ReturnBill returnBill;

    @ManyToOne
    @JoinColumn(name="product_detail_id")
    private ProductDetail productDetail;
}
