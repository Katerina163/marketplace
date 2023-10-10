package com.company.marketplace.web.screens.basket;

import com.company.marketplace.entity.Basket;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.security.global.UserSession;

import javax.inject.Inject;
import java.time.LocalDateTime;

@UiController("marketplace_Basket.edit")
@UiDescriptor("basket-edit.xml")
@EditedEntityContainer("basketDc")
@LoadDataBeforeShow
public class BasketEdit extends StandardEditor<Basket> {
    @Inject
    private UserSession userSession;

    @Subscribe
    public void onInitEntity(InitEntityEvent<Basket> event) {
        event.getEntity().setData(LocalDateTime.now());
        event.getEntity().setUser(userSession.getUser());
    }
}