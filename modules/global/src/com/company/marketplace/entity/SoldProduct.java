package com.company.marketplace.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.OnDeleteInverse;
import com.haulmont.cuba.core.global.DeletePolicy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Table(name = "MARKETPLACE_SOLD_PRODUCT")
@Entity(name = "marketplace_SoldProduct")
@NamePattern("%s|product")
public class SoldProduct extends StandardEntity {
    private static final long serialVersionUID = -8284431251793090384L;

    @NotNull
    @OnDeleteInverse(DeletePolicy.CASCADE)
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @NotNull
    @Column(name = "PRICE", nullable = false)
    @Positive
    private BigDecimal price;

    @NotNull
    @Column(name = "QUANTITY", nullable = false)
    @Positive
    private Integer quantity;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "SHOP_ID")
    private Shop shop;

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }
}