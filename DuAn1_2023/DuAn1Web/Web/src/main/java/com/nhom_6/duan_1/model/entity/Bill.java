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
@Table(name="Bill")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bill extends BaseEntity{
    @Column
    private Date deliveryDate;
    @Column
    private Double totalCost;
    @Column
    private Double intoMoney;
    @Column
    private String status;

    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy="bill")
    @JsonManagedReference
    private List<BillDetails> billDetails;

    @ManyToOne
    @JoinColumn(name="voucher_id")
    private Voucher voucher;

    @OneToOne(mappedBy = "bill")
    private ReturnBill returnBill;

    @OneToOne(mappedBy = "bill")
    private ExchangeBill exchangeBill;

    @OneToOne
    private Address address;
}
