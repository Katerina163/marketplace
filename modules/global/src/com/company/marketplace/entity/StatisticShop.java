package com.company.marketplace.entity;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.BaseUuidEntity;

@MetaClass(name = "marketplace_StatisticShop")
@NamePattern("%s|shop")
public class StatisticShop extends BaseUuidEntity {
    private static final long serialVersionUID = 1869118561309039750L;

    @MetaProperty
    private Shop shop;

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}