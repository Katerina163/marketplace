package com.company.marketplace.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.PublishEntityChangedEvents;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@PublishEntityChangedEvents
@Table(name = "MARKETPLACE_BUY_PRODUCT")
@Entity(name = "marketplace_BuyProduct")
@NamePattern("%s %s|product,quantity")
public class BuyProduct extends StandardEntity {
    private static final long serialVersionUID = -615511566769271173L;

    @NotNull
    @Column(name = "QUANTITY", nullable = false)
    @Positive
    private Integer quantity;

    @Column(name = "PRICE")
    @NotNull
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SOLD_PRODUCT_ID")
    private SoldProduct product;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ONLINE_ORDER_ID")
    @NotNull
    private OnlineOrder onlineOrder;

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public OnlineOrder getOnlineOrder() {
        return onlineOrder;
    }

    public void setOnlineOrder(OnlineOrder onlineOrder) {
        this.onlineOrder = onlineOrder;
    }

    public SoldProduct getProduct() {
        return product;
    }

    public void setProduct(SoldProduct product) {
        this.product = product;
    }

}