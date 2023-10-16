package com.company.marketplace.service;

import com.company.marketplace.entity.Shop;

public interface ShopService {
    String NAME = "marketplace_ShopService";

    boolean checkGeocoordinates(Shop shop);
}