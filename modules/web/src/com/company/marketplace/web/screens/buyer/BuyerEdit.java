package com.company.marketplace.web.screens.buyer;

import com.haulmont.cuba.gui.screen.*;
import com.company.marketplace.entity.Buyer;

@UiController("marketplace_Buyer.edit")
@UiDescriptor("buyer-edit.xml")
@EditedEntityContainer("buyerDc")
@LoadDataBeforeShow
public class BuyerEdit extends StandardEditor<Buyer> {
}