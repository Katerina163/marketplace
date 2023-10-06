package com.company.marketplace.web.screens.tradingnetwork;

import com.company.marketplace.entity.Address;
import com.company.marketplace.entity.Shop;
import com.company.marketplace.entity.TradingNetwork;
import com.haulmont.addon.maps.gis.utils.GeometryUtils;
import com.haulmont.addon.maps.web.gui.components.CanvasLayer;
import com.haulmont.addon.maps.web.gui.components.GeoMap;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.screen.*;
import org.locationtech.jts.geom.Point;

import javax.inject.Inject;
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
            map.setVisible(false);
        }
    }
}