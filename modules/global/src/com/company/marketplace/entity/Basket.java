package com.company.marketplace.entity;

import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.entity.annotation.PublishEntityChangedEvents;
import com.haulmont.cuba.core.global.DeletePolicy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@PublishEntityChangedEvents
@Table(name = "MARKETPLACE_BASKET")
@Entity(name = "marketplace_Basket")
@NamePattern("%s|products")
public class Basket extends StandardEntity {
    private static final long serialVersionUID = -4352460935069378744L;

    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "basket")
    private List<PurchasedProducts> products = new ArrayList<>();

    @NotNull
    @Column(name = "DATA_", nullable = false)
    private LocalDateTime data;

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public List<PurchasedProducts> getProducts() {
        return products;
    }

    @MetaProperty(related = {"products"})
    public Long getNumberProducts() {
        long result = 0;
        for (PurchasedProducts pp : products) {
            result += pp.getQuantity();
        }
        return result;
    }
}