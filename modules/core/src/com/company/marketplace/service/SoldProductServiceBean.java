package com.company.marketplace.service;

import com.company.marketplace.entity.SoldProduct;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service(SoldProductService.NAME)
public class SoldProductServiceBean implements SoldProductService {

    @Override
    public boolean checkingAvailabilityProducts(SoldProduct product) {
        if (Objects.isNull(product.getShop().getProducts())) {
            return false;
        }
        for (SoldProduct sp : product.getShop().getProducts()) {
            if (sp.getProduct().equals(product.getProduct())) {
                return !sp.equals(product);
            }
        }
        return false;
    }
}