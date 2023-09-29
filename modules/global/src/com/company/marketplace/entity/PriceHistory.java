package com.company.marketplace.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.OnDeleteInverse;
import com.haulmont.cuba.core.global.DeletePolicy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Table(name = "MARKETPLACE_PRICE_HISTORY")
@Entity(name = "marketplace_PriceHistory")
@NamePattern("%s %s|shop,product")
public class PriceHistory extends StandardEntity {
    private static final long serialVersionUID = -4929317396043400859L;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @NotNull
    @OnDeleteInverse(DeletePolicy.CASCADE)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "SHOP_ID")
    private Shop shop;

    @NotNull
    @Column(name = "DATE_PRICE_CHANGE")
    private LocalDateTime datePriceChange;

    public LocalDateTime getDatePriceChange() {
        return datePriceChange;
    }

    public void setDatePriceChange(LocalDateTime datePriceChange) {
        this.datePriceChange = datePriceChange;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}