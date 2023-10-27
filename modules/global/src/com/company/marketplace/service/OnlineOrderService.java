package com.company.marketplace.service;

import com.company.marketplace.entity.OnlineOrder;

import java.math.BigDecimal;

public interface OnlineOrderService {
    String NAME = "marketplace_OnlineOrderService";

    BigDecimal calculateAmount(OnlineOrder order);
}