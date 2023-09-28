package com.company.marketplace.service;

import com.company.marketplace.entity.Shop;
import com.company.marketplace.entity.SoldProduct;
import org.springframework.stereotype.Service;

@Service(SoldProductService.NAME)
public class SoldProductServiceBean implements SoldProductService {

    @Override
    public boolean checkSoldProduct(SoldProduct product) {
        Shop shop = product.getShop();
        for (SoldProduct sp : shop.getProducts()) {
            if (sp.getProduct().equals(product.getProduct())) {
                return true;
            }
        }
        return false;
    }
}