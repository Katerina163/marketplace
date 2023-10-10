package com.company.marketplace.core.group;

import com.company.marketplace.entity.Basket;
import com.haulmont.cuba.security.app.group.AnnotatedAccessGroupDefinition;
import com.haulmont.cuba.security.app.group.annotation.AccessGroup;
import com.haulmont.cuba.security.app.group.annotation.JpqlConstraint;
import com.haulmont.cuba.security.group.ConstraintsContainer;

@AccessGroup(name = BuyerGroup.NAME)
public class BuyerGroup extends AnnotatedAccessGroupDefinition {
    public final static String NAME = "Покупатель";

    @JpqlConstraint(target = Basket.class, where = "{E}.user.id = :session$userId")
    @Override
    public ConstraintsContainer accessConstraints() {
        return super.accessConstraints();
    }
}
