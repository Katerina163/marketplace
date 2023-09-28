package com.company.marketplace.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Table(name = "MARKETPLACE_BUY_PRODUCT")
@Entity(name = "marketplace_BuyProduct")
@NamePattern("%s %s|product,quantity")
public class BuyProduct extends StandardEntity {
    private static final long serialVersionUID = -615511566769271173L;

    @NotNull
    @Column(name = "QUANTITY", nullable = false)
    @Positive
    private Long quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID", unique = true)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ONLINE_ORDER_ID")
    private OnlineOrder onlineOrder;

    public OnlineOrder getOnlineOrder() {
        return onlineOrder;
    }

    public void setOnlineOrder(OnlineOrder onlineOrder) {
        this.onlineOrder = onlineOrder;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}