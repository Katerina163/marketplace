package com.company.marketplace.service;

import com.company.marketplace.entity.BuyProduct;
import com.company.marketplace.entity.OnlineOrder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import java.util.function.Predicate;

@Service(OnlineOrderService.NAME)
public class OnlineOrderServiceBean implements OnlineOrderService {
    @Override
    public BigDecimal calculateAmount(OnlineOrder order) {
        if (Objects.nonNull(order.getProducts())) {
            Integer sale = order.getDiscount();
            BigDecimal amount = calculateAmount(order,
                    buyProduct -> buyProduct.getPrice().equals(buyProduct.getProduct().getPrice()));
            if (sale != 0) {
                amount = amount.multiply(BigDecimal.valueOf(100 - sale))
                        .divide(BigDecimal.valueOf(100), RoundingMode.DOWN);
            }
            BigDecimal amountWithSale = calculateAmount(order,
                    buyProduct -> !buyProduct.getPrice().equals(buyProduct.getProduct().getPrice()));
            return amount.add(amountWithSale);
        }
        return BigDecimal.ZERO;
    }

    private BigDecimal calculateAmount(OnlineOrder order, Predicate<BuyProduct> predicate) {
        return order.getProducts().stream()
                .filter(predicate)
                .map(buyProduct -> buyProduct.getPrice()
                        .multiply(BigDecimal.valueOf(buyProduct.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}