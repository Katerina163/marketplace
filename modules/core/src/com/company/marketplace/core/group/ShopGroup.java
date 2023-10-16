package com.company.marketplace.core.group;

import com.haulmont.cuba.security.app.group.AnnotatedAccessGroupDefinition;
import com.haulmont.cuba.security.app.group.annotation.AccessGroup;

@AccessGroup(name = ShopGroup.NAME)
public class ShopGroup extends AnnotatedAccessGroupDefinition {
    public final static String NAME = "Магазин";
}