package com.company.marketplace.web.screens.manufacturer;

import com.company.marketplace.entity.Manufacturer;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.security.global.UserSession;

import javax.inject.Inject;

@UiController("marketplace_Manufacturer.browse")
@UiDescriptor("manufacturer-browse.xml")
@LookupComponent("table")
@LoadDataBeforeShow
public class ManufacturerBrowse extends MasterDetailScreen<Manufacturer> {
    @Inject
    private UserSession userSession;

    @Subscribe
    public void onInitEntity(InitEntityEvent<Manufacturer> event) {
        event.getEntity().setUser(userSession.getUser());
    }
}