package com.company.marketplace.service;

import com.company.marketplace.entity.Shop;
import com.company.marketplace.entity.TradingNetwork;

public interface StatisticService {
    String NAME = "marketplace_StatisticService";

    Long calculateSalesShop(Shop shop);

    Long calculateSalesTradingNetwork(TradingNetwork network);
}