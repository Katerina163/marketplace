package com.company.marketplace.web.screens.pricehistory;

import com.haulmont.cuba.gui.screen.*;
import com.company.marketplace.entity.PriceHistory;

@UiController("marketplace_PriceHistory.browse")
@UiDescriptor("price-history-browse.xml")
@LookupComponent("priceHistoriesTable")
@LoadDataBeforeShow
public class PriceHistoryBrowse extends StandardLookup<PriceHistory> {
}