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

@Role(name = ShopRole.NAME)
public class ShopRole extends AnnotatedRoleDefinition {
    public final static String NAME = "Сотрудник магазина";

    @EntityAccess(entityClass = TradingNetwork.class,
            operations = {EntityOp.CREATE, EntityOp.READ, EntityOp.UPDATE, EntityOp.DELETE})
    @EntityAccess(entityClass = Address.class,
            operations = {EntityOp.CREATE, EntityOp.READ, EntityOp.UPDATE, EntityOp.DELETE})
    @EntityAccess(entityClass = SoldProduct.class,
            operations = {EntityOp.CREATE, EntityOp.READ, EntityOp.UPDATE, EntityOp.DELETE})
    @EntityAccess(entityClass = Shop.class,
            operations = {EntityOp.CREATE, EntityOp.READ, EntityOp.UPDATE, EntityOp.DELETE})
    @EntityAccess(entityClass = StatisticShop.class,
            operations = {EntityOp.CREATE, EntityOp.READ, EntityOp.UPDATE, EntityOp.DELETE})
    @EntityAccess(entityClass = PriceHistory.class,
            operations = {EntityOp.READ})
    @EntityAccess(entityClass = Product.class,
            operations = {EntityOp.READ})
    @EntityAccess(entityClass = StatisticTradingNetwork.class,
            operations = {EntityOp.CREATE, EntityOp.READ, EntityOp.UPDATE, EntityOp.DELETE})
    @EntityAccess(entityClass = Basket.class,
            operations = {EntityOp.CREATE, EntityOp.READ, EntityOp.UPDATE, EntityOp.DELETE})
    @EntityAccess(entityClass = PurchasedProducts.class,
            operations = {EntityOp.CREATE, EntityOp.READ, EntityOp.UPDATE, EntityOp.DELETE})
    @Override
    public EntityPermissionsContainer entityPermissions() {
        return super.entityPermissions();
    }

    @EntityAttributeAccess(entityClass = TradingNetwork.class, modify = "*")
    @EntityAttributeAccess(entityClass = Address.class, view = "*")
    @EntityAttributeAccess(entityClass = Address.class, modify = "*")
    @EntityAttributeAccess(entityClass = SoldProduct.class, modify = "*")
    @EntityAttributeAccess(entityClass = StatisticShop.class, modify = "*")
    @EntityAttributeAccess(entityClass = Shop.class, modify = "*")
    @EntityAttributeAccess(entityClass = Product.class, view = "*")
    @EntityAttributeAccess(entityClass = PriceHistory.class, view = "*")
    @EntityAttributeAccess(entityClass = StatisticTradingNetwork.class, modify = "*")
    @EntityAttributeAccess(entityClass = Basket.class, modify = "*")
    @EntityAttributeAccess(entityClass = PurchasedProducts.class, modify = "*")
    @Override
    public EntityAttributePermissionsContainer entityAttributePermissions() {
        return super.entityAttributePermissions();
    }

    @ScreenAccess(screenIds = {"application-marketplace", "application-marketplace3", "settings",
            "marketplace_TradingNetwork.browse", "marketplace_TradingNetwork.edit",
            "marketplace_Shop.browse", "marketplace_Shop.edit",
            "marketplace_SoldProduct.browse", "marketplace_SoldProduct.edit",
            "marketplace_PriceHistory.browse", "marketplace_StatisticShop.edit",
            "marketplace_Basket.browse", "marketplace_Basket.edit",
            "marketplace_PurchasedProducts.browse", "marketplace_PurchasedProducts.edit",
            "marketplace_Product.browse"})
    @Override
    public ScreenPermissionsContainer screenPermissions() {
        return super.screenPermissions();
    }
}
