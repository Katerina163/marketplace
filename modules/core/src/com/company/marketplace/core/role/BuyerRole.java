package com.company.marketplace.core.role;

import com.company.marketplace.entity.Basket;
import com.company.marketplace.entity.PurchasedProducts;
import com.company.marketplace.entity.SoldProduct;
import com.haulmont.cuba.security.app.role.AnnotatedRoleDefinition;
import com.haulmont.cuba.security.app.role.annotation.EntityAccess;
import com.haulmont.cuba.security.app.role.annotation.EntityAttributeAccess;
import com.haulmont.cuba.security.app.role.annotation.Role;
import com.haulmont.cuba.security.app.role.annotation.ScreenAccess;
import com.haulmont.cuba.security.entity.EntityOp;
import com.haulmont.cuba.security.role.EntityAttributePermissionsContainer;
import com.haulmont.cuba.security.role.EntityPermissionsContainer;
import com.haulmont.cuba.security.role.ScreenPermissionsContainer;

@Role(name = BuyerRole.NAME)
public class BuyerRole extends AnnotatedRoleDefinition {
    public final static String NAME = "Покупатель";

    @EntityAccess(entityClass = Basket.class,
            operations = {EntityOp.CREATE, EntityOp.READ, EntityOp.UPDATE, EntityOp.DELETE})
    @EntityAccess(entityClass = PurchasedProducts.class,
            operations = {EntityOp.CREATE, EntityOp.READ, EntityOp.UPDATE, EntityOp.DELETE})
    @EntityAccess(entityClass = SoldProduct.class,
            operations = {EntityOp.READ})
    @Override
    public EntityPermissionsContainer entityPermissions() {
        return super.entityPermissions();
    }

    @EntityAttributeAccess(entityClass = Basket.class, modify = "*")
    @EntityAttributeAccess(entityClass = PurchasedProducts.class, modify = "*")
    @EntityAttributeAccess(entityClass = SoldProduct.class, view = "*")
    @Override
    public EntityAttributePermissionsContainer entityAttributePermissions() {
        return super.entityAttributePermissions();
    }

    @ScreenAccess(screenIds = {"application-marketplace3", "marketplace_Basket.browse", "marketplace_Basket.edit",
            "marketplace_SoldProduct.browse", "marketplace_PurchasedProducts.browse", "marketplace_PurchasedProducts.edit",
            "settings"})
    @Override
    public ScreenPermissionsContainer screenPermissions() {
        return super.screenPermissions();
    }
}
