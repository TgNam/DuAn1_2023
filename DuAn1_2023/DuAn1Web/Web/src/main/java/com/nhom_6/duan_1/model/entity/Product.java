package com.nhom_6.duan_1.model.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="Product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product extends BaseEntity {
    @Column
    private String nameProduct;
    @Column
    private double productPrice;
    @Column
    private String description;
    @Column
    private String status;

    @Lob
    @Column(name = "image_data",columnDefinition = "LONGBLOB")
    private byte[] imageByte;


    @OneToMany(mappedBy="product")
    @JsonManagedReference
    private List<ProductDetail> productDetails;

    @ManyToMany
    @JoinTable(name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    @JsonManagedReference
    private List<Category> categories;

    @ManyToOne
    @JoinColumn(name="custome_id")
    private Custom custom;

    @ManyToOne
    @JoinColumn(name = "material_id")
    private Material material;

    @ManyToOne
    @JoinColumn(name = "thickness_id")
    private Thickness thickness;

    @OneToOne
    @JoinColumn(name ="sale_id")
    private SaleProduct saleProduct;

    @ManyToMany
    @JoinTable( name = "product_productFovorite",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "productFavorite_id"))
    private List<ProductFavorite> productFavorites;

}
