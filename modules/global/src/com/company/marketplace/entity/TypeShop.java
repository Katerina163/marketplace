package com.company.marketplace.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;

public enum TypeShop implements EnumClass<String> {

    NEAR_THE_HOUSE("Near"),
    SUPERMARKET("Supermarket"),
    HYPERMARKET("Hypermarket");

    private final String id;

    TypeShop(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static TypeShop fromId(String id) {
        for (TypeShop at : TypeShop.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}