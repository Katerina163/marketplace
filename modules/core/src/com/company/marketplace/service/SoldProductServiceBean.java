package com.company.marketplace.service;

import com.company.marketplace.entity.SoldProduct;
import org.springframework.stereotype.Service;

@Service(SoldProductService.NAME)
public class SoldProductServiceBean implements SoldProductService {

    @Override
    public boolean checkingAvailabilityProducts(SoldProduct product) {
        for (SoldProduct sp : product.getShop().getProducts()) {
            if (sp.getProduct().equals(product.getProduct())) {
                return true;
            }
        }
        return false;
    }
}