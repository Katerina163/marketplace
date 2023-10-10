package com.company.marketplace.core.role;

import com.company.marketplace.entity.*;
import com.haulmont.cuba.security.app.role.AnnotatedRoleDefinition;
import com.haulmont.cuba.security.app.role.annotation.EntityAccess;
import com.haulmont.cuba.security.app.role.annotation.EntityAttributeAccess;
import com.haulmont.cuba.security.app.role.annotation.Role;
import com.haulmont.cuba.security.app.role.annotation.ScreenAccess;
import com.haulmont.cuba.security.entity.EntityOp;
import com.haulmont.cuba.security.role.EntityAttributePermissionsContainer;
import com.haulmont.cuba.security.role.EntityPermissionsContainer;
import com.haulmont.cuba.security.role.ScreenPermissionsContainer;

@Role(name = CashierRole.NAME)
public class CashierRole extends AnnotatedRoleDefinition {
    public final static String NAME = "Кассир";

    @EntityAccess(entityClass = Address.class,
            operations = {EntityOp.READ})
    @EntityAccess(entityClass = SoldProduct.class,
            operations = {EntityOp.CREATE, EntityOp.READ, EntityOp.UPDATE, EntityOp.DELETE})
    @EntityAccess(entityClass = Shop.class,
            operations = {EntityOp.READ, EntityOp.UPDATE})
    @EntityAccess(entityClass = PriceHistory.class,
            operations = {EntityOp.READ})
    @EntityAccess(entityClass = Product.class,
            operations = {EntityOp.READ})
    @Override
    public EntityPermissionsContainer entityPermissions() {
        return super.entityPermissions();
    }

    @EntityAttributeAccess(entityClass = Address.class, view = "*")
    @EntityAttributeAccess(entityClass = SoldProduct.class, modify = "*")
    @EntityAttributeAccess(entityClass = Shop.class, view = "*")
    @EntityAttributeAccess(entityClass = Product.class, view = "*")
    @EntityAttributeAccess(entityClass = PriceHistory.class, view = "*")
    @Override
    public EntityAttributePermissionsContainer entityAttributePermissions() {
        return super.entityAttributePermissions();
    }

    @ScreenAccess(screenIds = {"application-marketplace", "settings",
            "marketplace_Shop.browse", "marketplace_Shop.edit",
            "marketplace_SoldProduct.browse", "marketplace_SoldProduct.edit",
            "marketplace_PriceHistory.browse", "marketplace_Product.browse"})
    @Override
    public ScreenPermissionsContainer screenPermissions() {
        return super.screenPermissions();
    }
}
