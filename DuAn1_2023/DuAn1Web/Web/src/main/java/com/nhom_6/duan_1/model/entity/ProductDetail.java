package com.nhom_6.duan_1.model.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="ProductDetail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetail extends BaseEntity {
    @Column
    private int quantity;
    @Column
    private String status;

    @ManyToOne
    @JoinColumn(name="product_id")
    @JsonBackReference
    private Product product;


    @ManyToOne
    @JoinColumn(name="size_id")
    private Size size;

    @ManyToOne
    @JoinColumn(name="color_id")
    private Color color;

    @OneToMany(mappedBy = "productDetail")
    @JsonManagedReference
    private List<BillDetails> billDetails;

    @OneToMany(mappedBy = "productDetail")
    private List<ReturnBillDetail> returnBillDetails;

    @OneToMany(mappedBy = "productDetail")
    private List<ExchangeBillDetail> exchangeBillDetails;

}
