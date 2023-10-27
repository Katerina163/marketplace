package com.company.marketplace.entity;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.BaseUuidEntity;

import java.math.BigDecimal;
import java.util.UUID;

@MetaClass(name = "marketplace_SoldProductDTO")
@NamePattern("%s|name")
public class SoldProductDTO extends BaseUuidEntity {
    private static final long serialVersionUID = 6229679100916887933L;
    @MetaProperty
    private UUID id;

    @MetaProperty
    private String name;

    @MetaProperty
    private BigDecimal price;

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}