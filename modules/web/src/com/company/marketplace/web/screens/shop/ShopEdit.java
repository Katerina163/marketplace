package com.company.marketplace.web.screens.shop;

import com.haulmont.cuba.gui.screen.*;
import com.company.marketplace.entity.Shop;

@UiController("marketplace_Shop.edit")
@UiDescriptor("shop-edit.xml")
@EditedEntityContainer("shopDc")
@LoadDataBeforeShow
public class ShopEdit extends StandardEditor<Shop> {
}