package com.nhom_6.duan_1.model.entity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="Voucher")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Voucher extends BaseEntity{
    @Column
    private double saleOf;
    @Column
    private Date startAt;
    @Column
    private Date endAt;
    @Column
    private String status;

    @ManyToMany
    @JoinTable(name = "user_voucher",
              joinColumns = @JoinColumn(name = "voucher_id"),
    inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;

    @OneToMany(mappedBy ="voucher")
    private List<Bill> bills;
}
