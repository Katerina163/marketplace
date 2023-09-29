package com.company.marketplace.web.screens.basket;

import com.haulmont.cuba.gui.screen.*;
import com.company.marketplace.entity.Basket;

@UiController("marketplace_Basket.browse")
@UiDescriptor("basket-browse.xml")
@LookupComponent("basketsTable")
@LoadDataBeforeShow
public class BasketBrowse extends StandardLookup<Basket> {
}