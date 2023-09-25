package com.company.marketplace.web.screens.shop;

import com.haulmont.cuba.gui.screen.*;
import com.company.marketplace.entity.Shop;

@UiController("marketplace_Shop.browse")
@UiDescriptor("shop-browse.xml")
@LookupComponent("table")
@LoadDataBeforeShow
public class ShopBrowse extends MasterDetailScreen<Shop> {
}