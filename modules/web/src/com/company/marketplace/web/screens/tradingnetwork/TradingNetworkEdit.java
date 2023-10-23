package com.company.marketplace.web.screens.tradingnetwork;

import com.company.marketplace.entity.*;
import com.haulmont.addon.maps.gis.utils.GeometryUtils;
import com.haulmont.addon.maps.web.gui.components.CanvasLayer;
import com.haulmont.addon.maps.web.gui.components.GeoMap;
import com.haulmont.charts.gui.components.charts.PieChart;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.gui.components.HasValue;
import com.haulmont.cuba.gui.components.PickerField;
import com.haulmont.cuba.gui.components.VBoxLayout;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
import org.locationtech.jts.geom.Point;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@UiController("marketplace_TradingNetwork.edit")
@UiDescriptor("trading-network-edit.xml")
@EditedEntityContainer("tradingNetworkDc")
@LoadDataBeforeShow
public class TradingNetworkEdit extends StandardEditor<TradingNetwork> {
    @Inject
    private GeoMap map;
    @Inject
    private DataManager dataManager;
    @Inject
    private CollectionLoader<ChartShop> chartShopsDl;
    @Inject
    private PieChart pieChart;
    @Inject
    private PickerField<Product> productField;
    @Inject
    private VBoxLayout tabWithPieChart;
    @Inject
    private VBoxLayout tabWithMap;

    @Subscribe
    public void onAfterShow(AfterShowEvent event) {
        if (Objects.nonNull(getEditedEntity().getName())) {
            List<Shop> list = dataManager.reload(getEditedEntity(), "view-tradingNetwork-with-shop-address-lat-lng").getShops();
            CanvasLayer canvas = map.getCanvas();
            for (Shop shop : list) {
                Address address = shop.getAddress();
                if (Objects.nonNull(address.getLat()) && Objects.nonNull(address.getLng())) {
                    Point point = GeometryUtils.createPoint(address.getLng(), address.getLat());
                    canvas.addPoint(point);
                }
            }
        } else {
            tabWithMap.setVisible(false);
            tabWithPieChart.setVisible(false);
        }
    }

    @Subscribe("productField")
    public void onProductFieldFieldValueChange(PickerField.FieldValueChangeEvent<Product> event) {
        chartShopsDl.load();
    }

    @Install(to = "chartShopsDl", target = Target.DATA_LOADER)
    private List<ChartShop> chartShopsDlLoadDelegate(LoadContext<ChartShop> loadContext) {
        List<ChartShop> chartShops = new ArrayList<>();
        if (Objects.nonNull(getEditedEntity().getName()) && Objects.nonNull(productField.getValue())) {
            pieChart.setVisible(true);
            List<Shop> list = dataManager.reload(getEditedEntity(), "view-tradingNetwork-shop-with-products").getShops();
            for (Shop shop : list) {
                ChartShop cs = new ChartShop();
                cs.setName(shop.getName());
                for (SoldProduct product : shop.getProducts()) {
                    if (productField.getValue().equals(product.getProduct())) {
                        cs.setCount(product.getQuantity());
                    }
                }
                if (Objects.isNull(cs.getCount())) {
                    cs.setCount(0);
                }
                chartShops.add(cs);
            }
        } else {
            pieChart.setVisible(false);
        }
        return chartShops;
    }

    @Subscribe("productField")
    public void onProductFieldValueChange(HasValue.ValueChangeEvent<Product> event) {
        chartShopsDl.load();
    }
}