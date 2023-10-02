package com.company.marketplace.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.PublishEntityChangedEvents;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@PublishEntityChangedEvents
@Table(name = "MARKETPLACE_PURCHASED_PRODUCTS")
@Entity(name = "marketplace_PurchasedProducts")
@NamePattern("%s %s|product,quantity")
public class PurchasedProducts extends StandardEntity {
    private static final long serialVersionUID = -3228353320184570900L;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "SHOP_ID")
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BASKET_ID")
    private Basket basket;

    @NotNull
    @Column(name = "PRICE", nullable = false)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SOLD_PRODUCT_ID")
    private SoldProduct product;

    @NotNull
    @Positive
    @Column(name = "QUANTITY")
    private Integer quantity;

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public SoldProduct getProduct() {
        return product;
    }

    public void setProduct(SoldProduct product) {
        this.product = product;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public BigDecimal getPrice() {
        return price;
    }
}