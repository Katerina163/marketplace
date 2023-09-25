package com.company.marketplace.web.screens.tradingnetwork;

import com.haulmont.cuba.gui.screen.*;
import com.company.marketplace.entity.TradingNetwork;

@UiController("marketplace_TradingNetwork.browse")
@UiDescriptor("trading-network-browse.xml")
@LookupComponent("table")
@LoadDataBeforeShow
public class TradingNetworkBrowse extends MasterDetailScreen<TradingNetwork> {
}