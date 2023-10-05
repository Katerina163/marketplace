package com.company.marketplace.web.screens.product;

import com.company.marketplace.config.ColorConfig;
import com.company.marketplace.entity.Product;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.screen.*;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Objects;

@UiController("marketplace_Product.browse")
@UiDescriptor("product-browse.xml")
@LookupComponent("productsTable")
@LoadDataBeforeShow
public class ProductBrowse extends StandardLookup<Product> {
    @Inject
    private ColorConfig colorConfig;
    @Inject
    private TextField<BigDecimal> colorPrice;
    @Inject
    private GroupTable<Product> productsTable;

    @Subscribe
    public void onInit(InitEvent event) {
        colorPrice.setValue(colorConfig.getProductPrice());
    }

    @Install(to = "productsTable", subject = "styleProvider")
    private String productsTableStyleProvider(Product entity, String property) {
        if (entity.getManufacturerPrice().compareTo(colorConfig.getProductPrice()) < 0) {
            return "colored-grade";
        }
        return null;
    }

    @Subscribe("colorPriceBtn")
    public void onColorPriceBtnClick(Button.ClickEvent event) {
        if (Objects.nonNull(colorPrice.getValue())) {
            colorConfig.setProductPrice(colorPrice.getValue());
            productsTable.repaint();
        }
    }
}