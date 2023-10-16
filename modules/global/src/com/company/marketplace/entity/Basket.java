package com.company.marketplace.entity;

import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.entity.annotation.PublishEntityChangedEvents;
import com.haulmont.cuba.core.global.DeletePolicy;
import com.haulmont.cuba.security.entity.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@PublishEntityChangedEvents
@Table(name = "MARKETPLACE_BASKET")
@Entity(name = "marketplace_Basket")
@NamePattern("%s|products")
public class Basket extends StandardEntity {
    private static final long serialVersionUID = -4352460935069378744L;

    @NotNull
    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "basket")
    private List<PurchasedProducts> products;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USER_ID")
    @NotNull
    private User user;

    @NotNull
    @Column(name = "DATA_", nullable = false)
    private LocalDateTime data;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public List<PurchasedProducts> getProducts() {
        return products;
    }

    public void setProducts(List<PurchasedProducts> products) {
        this.products = products;
    }

    @MetaProperty(related = {"products"})
    public Long getNumberProducts() {
        if (Objects.isNull(products)) {
            return 0L;
        }
        long result = 0;
        for (PurchasedProducts pp : products) {
            result += pp.getQuantity();
        }
        return result;
    }
}