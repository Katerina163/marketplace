package com.company.marketplace.web.screens.statisticshop;

import com.company.marketplace.entity.Shop;
import com.company.marketplace.entity.StatisticShop;
import com.company.marketplace.entity.TradingNetwork;
import com.company.marketplace.service.StatisticService;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.PickerField;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.screen.*;

import javax.inject.Inject;
import java.util.Objects;

@UiController("marketplace_StatisticShop.edit")
@UiDescriptor("statistic-shop-edit.xml")
@EditedEntityContainer("statisticShopDc")
@LoadDataBeforeShow
public class StatisticShopEdit extends StandardLookup<StatisticShop> {
    @Inject
    private TextField<String> textShopField;
    @Inject
    private PickerField<Shop> shopField;
    @Inject
    private StatisticService statisticService;
    @Inject
    private TextField<String> textNetworkField;
    @Inject
    private PickerField<TradingNetwork> networkField;
    @Inject
    private TextField<String> textOnlineShopField;
    @Inject
    private TextField<String> textOnlineNetworkField;

    @Subscribe("calculateShopBtn")
    public void onCalculateShopBtnClick(Button.ClickEvent event) {
        String str;
        String online;
        if (Objects.nonNull(shopField.getValue())) {
            Long result = statisticService.calculateSalesShop(shopField.getValue());
            str = result != -1 ? result.toString() : "Продажи отсутствуют";

            Long resultOnl = statisticService.calculateOnlineSalesShop(shopField.getValue());
            online = resultOnl != -1 ? resultOnl.toString() : "Онлайн продажи отсутствуют";
        } else {
            str = "Необходимо выбрать магазин";
            online = str;
        }
        textShopField.setValue(str);
        textOnlineShopField.setValue(online);
    }

    @Subscribe("calculateNetworkBtn")
    public void onCalculateNetworkBtnClick(Button.ClickEvent event) {
        String str;
        String online;
        if (Objects.nonNull(networkField.getValue())) {
            Long result = statisticService.calculateSalesTradingNetwork(networkField.getValue());
            str = result != -1 ? result.toString() : "Продажи отсутствуют";

            Long resultOnl = statisticService.calculateOnlineSalesTradingNetwork(networkField.getValue());
            online = resultOnl != -1 ? resultOnl.toString() : "Онлайн продажи отсутствуют";
        } else {
            str = "Необходимо выбрать сеть";
            online = str;
        }
        textNetworkField.setValue(str);
        textOnlineNetworkField.setValue(online);
    }
}