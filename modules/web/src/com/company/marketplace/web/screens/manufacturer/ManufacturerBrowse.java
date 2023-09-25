package com.company.marketplace.web.screens.manufacturer;

import com.haulmont.cuba.gui.screen.*;
import com.company.marketplace.entity.Manufacturer;

@UiController("marketplace_Manufacturer.browse")
@UiDescriptor("manufacturer-browse.xml")
@LookupComponent("table")
@LoadDataBeforeShow
public class ManufacturerBrowse extends MasterDetailScreen<Manufacturer> {
}