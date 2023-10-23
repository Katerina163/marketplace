package com.company.marketplace.service;

import com.company.marketplace.entity.PurchasedProducts;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service(PurchasedProductsService.NAME)
public class PurchasedProductsServiceBean implements PurchasedProductsService {

    @Override
    public boolean checkingQuantityProducts(PurchasedProducts product) {
        if (Objects.isNull(product.getProduct())) {
            return false;
        }
        return product.getProduct().getQuantity() >= product.getQuantity();
    }

    @Override
    public boolean checkingDoubleProducts(PurchasedProducts product) {
        if (Objects.isNull(product.getBasket().getProducts())) {
            return false;
        }
        for (PurchasedProducts sp : product.getBasket().getProducts()) {
            if (sp.getProduct().equals(product.getProduct())) {
                return !sp.equals(product);
            }
        }
        return false;
    }
}