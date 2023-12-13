package com.nhom_6.duan_1.model.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

@Entity
@Table(name="Size")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Size extends BaseEntity {
    @Column
    private String nameSize;

    @OneToMany(mappedBy = "size")
    private List<ProductDetail> productDetail;
}
