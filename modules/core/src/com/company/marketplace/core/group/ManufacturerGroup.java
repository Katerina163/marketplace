package com.company.marketplace.core.group;

import com.company.marketplace.entity.Manufacturer;
import com.company.marketplace.entity.Product;
import com.haulmont.cuba.security.app.group.AnnotatedAccessGroupDefinition;
import com.haulmont.cuba.security.app.group.annotation.AccessGroup;
import com.haulmont.cuba.security.app.group.annotation.JpqlConstraint;
import com.haulmont.cuba.security.group.ConstraintsContainer;

@AccessGroup(name = ManufacturerGroup.NAME)
public class ManufacturerGroup extends AnnotatedAccessGroupDefinition {
    public final static String NAME = "Производитель";

    @JpqlConstraint(target = Manufacturer.class, where = "{E}.user.id = :session$userId")
    @JpqlConstraint(target = Product.class, where = "{E}.manufacturer.user.id = :session$userId")
    @Override
    public ConstraintsContainer accessConstraints() {
        return super.accessConstraints();
    }
}
