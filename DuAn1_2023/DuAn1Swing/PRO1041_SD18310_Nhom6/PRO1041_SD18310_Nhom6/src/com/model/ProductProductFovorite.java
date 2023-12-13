/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model;

/**
 *
 * @author TgNam
 */
public class ProductProductFovorite {
    private ProductFavorite productFavoriteId;
    private Product productId;

    public ProductProductFovorite(ProductFavorite productFavoriteId, Product productId) {
        this.productFavoriteId = productFavoriteId;
        this.productId = productId;
    }

    public ProductProductFovorite() {
    }

    public ProductFavorite getProductFavoriteId() {
        return productFavoriteId;
    }

    public void setProductFavoriteId(ProductFavorite productFavoriteId) {
        this.productFavoriteId = productFavoriteId;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

}
