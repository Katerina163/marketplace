package com.company.marketplace.service;

import com.company.marketplace.entity.PurchasedProducts;

public interface PurchasedProductsService {
    String NAME = "marketplace_PurchasedProductsService";

    boolean checkingQuantityProducts(PurchasedProducts product);

    boolean checkingDoubleProducts(PurchasedProducts product);
}