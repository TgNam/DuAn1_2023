package com.nhom_6.duan_1.model.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="BillDetail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BillDetails extends BaseEntity{
    @Column
    private String quantityUrchased;
    @Column
    private double priceNow;

    @ManyToOne
    @JoinColumn(name="bill_id")
    @JsonBackReference
    private Bill bill;

    @ManyToOne
    @JoinColumn(name="productDetail_id")
    @JsonBackReference
    private ProductDetail productDetail;

}
