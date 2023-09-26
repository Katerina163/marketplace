package com.company.marketplace.web.screens.tradingnetwork;

import com.haulmont.cuba.gui.screen.*;
import com.company.marketplace.entity.TradingNetwork;

@UiController("marketplace_TradingNetwork.edit")
@UiDescriptor("trading-network-edit.xml")
@EditedEntityContainer("tradingNetworkDc")
@LoadDataBeforeShow
public class TradingNetworkEdit extends StandardEditor<TradingNetwork> {
}