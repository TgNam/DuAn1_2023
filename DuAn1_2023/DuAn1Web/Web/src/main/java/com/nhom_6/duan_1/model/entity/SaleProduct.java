package com.nhom_6.duan_1.model.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name="SaleProduct")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaleProduct extends BaseEntity {
    @Column
    private double sale;
    @Column
    private String status;
    @Column
    private Date startAt;
    @Column
    private Date endAt;

    @OneToOne(mappedBy = "saleProduct")
    private Product product;
}
