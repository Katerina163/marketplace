package com.company.marketplace.entity;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.BaseUuidEntity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.UUID;

@MetaClass(name = "marketplace_BuyProductDTO")
public class BuyProductDTO extends BaseUuidEntity {
    private static final long serialVersionUID = 8709734444226799575L;

    @NotNull
    @MetaProperty
    private UUID id;

    @NotNull
    @Positive
    @MetaProperty
    private Integer quantity;

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}