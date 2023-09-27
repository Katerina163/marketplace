package com.company.marketplace.web.screens.purchase;

import com.haulmont.cuba.gui.screen.*;
import com.company.marketplace.entity.Purchase;

@UiController("marketplace_Purchase.edit")
@UiDescriptor("purchase-edit.xml")
@EditedEntityContainer("purchaseDc")
@LoadDataBeforeShow
public class PurchaseEdit extends StandardEditor<Purchase> {
}