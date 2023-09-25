package com.company.marketplace.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.OnDeleteInverse;
import com.haulmont.cuba.core.global.DeletePolicy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NamePattern("%s|name")
@Table(name = "MARKETPLACE_SHOP")
@Entity(name = "marketplace_Shop")
public class Shop extends StandardEntity {
    private static final long serialVersionUID = 5434045714175144L;

    @NotNull
    @Column(name = "NUMBER", nullable = false, unique = true)
    private String number;

    @NotNull
    @Column(name = "NAME", nullable = false)
    private String name;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "TRADING_NETWORK_ID")
    @OnDeleteInverse(DeletePolicy.DENY)
    private TradingNetwork tradingNetwork;

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