package com.company.marketplace.entity;

import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.OnDeleteInverse;
import com.haulmont.cuba.core.global.DeletePolicy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Table(name = "MARKETPLACE_PURCHASE")
@Entity(name = "marketplace_Purchase")
@NamePattern("%s|shop")
public class Purchase extends StandardEntity {
    private static final long serialVersionUID = -4352460935069378744L;

    @NotNull
    @OnDeleteInverse(DeletePolicy.CASCADE)
    @JoinColumn(name = "SHOP_ID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Shop shop;

    @ManyToMany
    @JoinTable(name = "MARKETPLACE_PURCHASE_PRODUCT_LINK",
            joinColumns = @JoinColumn(name = "PURCHASE_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID"))
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    @Transient
    @MetaProperty(related = {"products"})
    public int getNumberProducts() {
        return products.size();
    }
}