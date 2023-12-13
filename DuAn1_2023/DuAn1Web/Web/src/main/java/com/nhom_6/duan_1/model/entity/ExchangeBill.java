package com.nhom_6.duan_1.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="ExchangeBill")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeBill extends BaseEntity {
    @Column
    private String describeReason;

    @OneToOne
    @JoinColumn(name="bill_id")
    private Bill bill;

    @OneToMany(mappedBy = "exchangeBill")
    @JsonManagedReference
    private List<ExchangeBillDetail> exchangeBillDetails;
}
