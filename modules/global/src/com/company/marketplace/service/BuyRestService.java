package com.company.marketplace.service;

import com.company.marketplace.entity.*;

import java.util.List;
import java.util.UUID;

public interface BuyRestService {
    String NAME = "marketplace_BuyRestService";

    List<SoldProductDTO> getAvailableProduct();

    Buyer createBuyer(Buyer buyer);

    OnlineOrder createOrder(Buyer buyer, List<BuyProductDTO> products, Integer discount);

    String cancelOrder(UUID orderId);
}