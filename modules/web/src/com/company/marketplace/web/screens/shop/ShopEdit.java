package com.company.marketplace.web.screens.shop;

import com.company.marketplace.config.ColorConfig;
import com.company.marketplace.entity.Shop;
import com.company.marketplace.entity.SoldProduct;
import com.company.marketplace.service.ShopService;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.screen.*;

import javax.inject.Inject;
import java.util.Objects;

@UiController("marketplace_Shop.edit")
@UiDescriptor("shop-edit.xml")
@EditedEntityContainer("shopDc")
@LoadDataBeforeShow
public class ShopEdit extends StandardEditor<Shop> {
    @Inject
    private ColorConfig colorConfig;
    @Inject
    private TextField<Integer> colorPrice;
    @Inject
    private Table<SoldProduct> productsTable;
    @Inject
    private ShopService shopService;

    @Subscribe
    public void onInit(InitEvent event) {
        colorPrice.setValue(colorConfig.getProductPrice());
    }

    @Subscribe("colorPriceBtn")
    public void onColorPriceBtnClick(Button.ClickEvent event) {
        if (Objects.nonNull(colorPrice.getValue())) {
            colorConfig.setProductPrice(colorPrice.getValue());
            productsTable.repaint();
        }
    }

    @Install(to = "productsTable", subject = "styleProvider")
    private String productsTableStyleProvider(SoldProduct entity, String property) {
        if (entity.getQuantity() < colorConfig.getProductPrice()) {
            return "colored-grade";
        }
        return null;
    }

    @Override
    protected void validateAdditionalRules(ValidationErrors errors) {
        if (!shopService.checkGeocoordinates(getEditedEntity())) {
            errors.add("Неправильный адрес");
        }
        super.validateAdditionalRules(errors);
    }
}