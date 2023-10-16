package com.company.marketplace.entity;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.BaseUuidEntity;

@MetaClass(name = "marketplace_ChartShop")
@NamePattern("%s|name")
public class ChartShop extends BaseUuidEntity {
    private static final long serialVersionUID = -5013383651510661624L;

    @MetaProperty
    private String name;

    @MetaProperty
    private Integer count;

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getCount() {
        return count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}