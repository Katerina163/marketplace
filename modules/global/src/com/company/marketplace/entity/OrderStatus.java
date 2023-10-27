package com.company.marketplace.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;

public enum OrderStatus implements EnumClass<String> {

    PROCESSING("In processing"),
    DELIVERY("In delivery"),
    CANCELED("Canceled"),
    DELIVERED("Delivered");

    private final String id;

    OrderStatus(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static OrderStatus fromId(String id) {
        for (OrderStatus at : OrderStatus.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}