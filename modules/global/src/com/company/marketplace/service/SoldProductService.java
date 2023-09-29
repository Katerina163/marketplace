package com.company.marketplace.service;

import com.company.marketplace.entity.SoldProduct;

public interface SoldProductService {
    String NAME = "marketplace_SoldProductService";

    boolean checkingAvailabilityProducts(SoldProduct product);
}