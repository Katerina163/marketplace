package com.company.marketplace.service;

import com.company.marketplace.entity.Shop;
import com.company.marketplace.entity.TradingNetwork;
import com.haulmont.cuba.core.global.DataManager;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Objects;

@Service(StatisticService.NAME)
public class StatisticServiceBean implements StatisticService {
    @Inject
    private DataManager dataManager;

    @Override
    public Long calculateSalesShop(Shop shop) {
        String sql = "select sum(p.quantity) from marketplace_PurchasedProducts p where p.shop = :shop";
        return calculate(sql, "shop", shop);
    }

    @Override
    public Long calculateOnlineSalesShop(Shop shop) {
        String sql = "select sum(bp.quantity) from marketplace_BuyProduct bp "
                + "join bp.product p "
                + "where p.shop = :shop";
        return calculate(sql, "shop", shop);
    }

    @Override
    public Long calculateSalesTradingNetwork(TradingNetwork network) {
        String sql = "select sum(p.quantity) from marketplace_PurchasedProducts p "
                + "join p.shop s "
                + "where s.tradingNetwork = :network";
        return calculate(sql, "network", network);
    }

    @Override
    public Long calculateOnlineSalesTradingNetwork(TradingNetwork network) {
        String sql = "select sum(bp.quantity) from marketplace_BuyProduct bp "
                + "join bp.product p "
                + "join p.shop s "
                + "where s.tradingNetwork = :network";
        return calculate(sql, "network", network);
    }

    private Long calculate(String sql, String nameParameter, Object parameter) {
        Long count = dataManager.loadValue(sql, Long.class)
                .parameter(nameParameter, parameter)
                .one();
        if (Objects.isNull(count)) {
            return -1L;
        }
        return count;
    }
}