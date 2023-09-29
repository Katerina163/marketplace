package com.company.marketplace.entity;

import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.EmbeddedParameters;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.entity.annotation.OnDeleteInverse;
import com.haulmont.cuba.core.global.DeletePolicy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@NamePattern("%s|name")
@Table(name = "MARKETPLACE_SHOP")
@Entity(name = "marketplace_Shop")
public class Shop extends StandardEntity {
    private static final long serialVersionUID = 5434045714175144L;

    @NotNull
    @Column(name = "NUMBER", nullable = false, unique = true)
    private String number;

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "shop")
    private List<SoldProduct> products = new ArrayList<>();

    @NotNull
    @Column(name = "TYPE_", nullable = false)
    private String type;

    @Embedded
    @EmbeddedParameters(nullAllowed = false)
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "ADDRESS_CITY")),
            @AttributeOverride(name = "street", column = @Column(name = "ADDRESS_STREET")),
            @AttributeOverride(name = "house", column = @Column(name = "ADDRESS_HOUSE"))
    })
    private Address address;

    @NotNull
    @Column(name = "NAME", nullable = false)
    private String name;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "TRADING_NETWORK_ID")
    @OnDeleteInverse(DeletePolicy.DENY)
    private TradingNetwork tradingNetwork;

    public List<SoldProduct> getProducts() {
        return products;
    }

    public void setProducts(List<SoldProduct> products) {
        this.products = products;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public TypeShop getType() {
        return type == null ? null : TypeShop.fromId(type);
    }

    public void setType(TypeShop type) {
        this.type = type == null ? null : type.getId();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TradingNetwork getTradingNetwork() {
        return tradingNetwork;
    }

    public void setTradingNetwork(TradingNetwork tradingNetwork) {
        this.tradingNetwork = tradingNetwork;
    }
}