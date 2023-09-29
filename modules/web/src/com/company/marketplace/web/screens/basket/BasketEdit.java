package com.company.marketplace.web.screens.basket;

import com.company.marketplace.entity.Basket;
import com.haulmont.cuba.gui.screen.*;

import java.time.LocalDateTime;

@UiController("marketplace_Basket.edit")
@UiDescriptor("basket-edit.xml")
@EditedEntityContainer("basketDc")
@LoadDataBeforeShow
public class BasketEdit extends StandardEditor<Basket> {
    @Subscribe
    public void onInitEntity(InitEntityEvent<Basket> event) {
        event.getEntity().setData(LocalDateTime.now());
    }
}