package com.company.marketplace.service;

import com.company.marketplace.entity.BuyProduct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service(BuyProductService.NAME)
public class BuyProductServiceBean implements BuyProductService {
    @Override
    public boolean checkAnotherProductWithSale(BuyProduct product) {
        List<BuyProduct> products = product.getOnlineOrder().getProducts();
        if (Objects.isNull(products)) {
            return false;
        }
        for (BuyProduct buyProduct : products) {
            if (check(product, buyProduct)) {
                return true;
            }
        }
        return false;
    }

    private boolean check(BuyProduct p1, BuyProduct p2) {
        return Objects.equals(p1.getQuantity(), p2.getQuantity())
                && Objects.equals(p1.getPrice(), p2.getPrice())
                && Objects.equals(p1.getProduct(), p2.getProduct())
                && Objects.equals(p1.getOnlineOrder(), p2.getOnlineOrder());
    }
}