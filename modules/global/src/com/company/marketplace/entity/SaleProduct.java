package com.company.marketplace.entity;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.BaseUuidEntity;

import java.math.BigDecimal;

@MetaClass(name = "marketplace_SaleProduct")
public class SaleProduct extends BaseUuidEntity {
    private static final long serialVersionUID = 4847237033451484297L;

    @MetaProperty
    private SoldProduct product;

    @MetaProperty
    private BigDecimal price;

    public BigDecimal getPrice() {
        return price;
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
}