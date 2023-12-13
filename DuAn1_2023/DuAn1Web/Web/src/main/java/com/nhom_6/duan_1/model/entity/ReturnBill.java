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
@Table(name="ReturnBill")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReturnBill extends BaseEntity{
    @Column
    private double totalCost;
    @Column
    private String reasonDescription;

    @OneToOne
    @JoinColumn(name="bill_id")
    private Bill bill;

    @OneToMany(mappedBy = "returnBill")
    @JsonManagedReference
    private List<ReturnBillDetail> returnBillDetails;

}
