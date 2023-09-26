package com.company.marketplace.web.screens.tradingnetwork;

import com.haulmont.cuba.gui.screen.*;
import com.company.marketplace.entity.TradingNetwork;

@UiController("marketplace_TradingNetwork.browse")
@UiDescriptor("trading-network-browse.xml")
@LookupComponent("tradingNetworksTable")
@LoadDataBeforeShow
public class TradingNetworkBrowse extends StandardLookup<TradingNetwork> {
}