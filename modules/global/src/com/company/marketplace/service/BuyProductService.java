package com.company.marketplace.service;

import com.company.marketplace.entity.BuyProduct;

public interface BuyProductService {
    String NAME = "marketplace_BuyProductService";

    boolean checkAnotherProductWithSale(BuyProduct product);
}