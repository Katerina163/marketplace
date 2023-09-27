package com.company.marketplace.web.screens.purchase;

import com.haulmont.cuba.gui.screen.*;
import com.company.marketplace.entity.Purchase;

@UiController("marketplace_Purchase.browse")
@UiDescriptor("purchase-browse.xml")
@LookupComponent("purchasesTable")
@LoadDataBeforeShow
public class PurchaseBrowse extends StandardLookup<Purchase> {
}