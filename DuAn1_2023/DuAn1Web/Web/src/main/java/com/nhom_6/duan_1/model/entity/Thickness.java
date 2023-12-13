package com.nhom_6.duan_1.model.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="Thickness")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Thickness extends BaseEntity{
    @Column
    private int GSM;

    @Column
    private Boolean status;

    @OneToMany(mappedBy = "thickness")
    private List<Product> products;

}
