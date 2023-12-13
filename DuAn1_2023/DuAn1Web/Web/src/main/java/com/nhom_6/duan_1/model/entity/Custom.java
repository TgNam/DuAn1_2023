package com.nhom_6.duan_1.model.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.security.PrivateKey;
import java.util.List;

@Entity
@Table(name="Custom")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Custom extends BaseEntity {
    @Column
    private String nameCustom;
    @Column
    private Boolean status;

    @OneToMany(mappedBy = "custom", cascade = CascadeType.ALL)
    private List<Product> products;

}
