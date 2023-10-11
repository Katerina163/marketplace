package com.company.marketplace.web.screens.onlineorder;

import com.haulmont.cuba.gui.screen.*;
import com.company.marketplace.entity.OnlineOrder;

@UiController("marketplace_OnlineOrder.browse")
@UiDescriptor("online-order-browse.xml")
@LookupComponent("onlineOrdersTable")
@LoadDataBeforeShow
public class OnlineOrderBrowse extends StandardLookup<OnlineOrder> {
}