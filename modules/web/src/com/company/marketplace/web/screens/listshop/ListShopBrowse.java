package com.company.marketplace.web.screens.listshop;

import com.company.marketplace.entity.*;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.PickerField;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;

import javax.inject.Inject;
import java.util.Objects;

@UiController("marketplace_ListShopWithoutProducts.browse")
@UiDescriptor("list-for-manufacturer.xml")
@LookupComponent("soldProductsTable")
@LoadDataBeforeShow
public class ListShopBrowse extends StandardLookup<ListShop> {
    @Inject
    private CollectionLoader<SoldProduct> soldProductsDl;
    @Inject
    private PickerField<Manufacturer> manufacturerField;
    @Inject
    private PickerField<Product> productField;
    @Inject
    private TextField<String> quantityField;
    @Inject
    private PickerField<Shop> shopField;
    @Inject
    private CollectionLoader<Shop> shopsDl;

    @Subscribe
    public void onInit(InitEvent event) {
        soldProductsDl.setParameter("quantity", 0);
        soldProductsDl.setParameter("shop", null);
        soldProductsDl.setParameter("manufacturer", null);
        soldProductsDl.load();
        shopsDl.setQuery("select e from marketplace_Shop e where e.number = :null");
        shopsDl.setParameter("null", null);
        shopsDl.load();
    }

    @Subscribe("calculateWithNumberBtn")
    public void onCalculateWithNumberBtnClick(Button.ClickEvent event) {
        if (Objects.nonNull(manufacturerField.getValue())
                && Objects.nonNull(shopField.getValue())
                && Objects.nonNull(quantityField.getValue())) {
            soldProductsDl.setParameter("quantity", Integer.valueOf(quantityField.getValue()));
            soldProductsDl.setParameter("shop", shopField.getValue());
            soldProductsDl.setParameter("manufacturer", manufacturerField.getValue());
            soldProductsDl.load();
        }
    }

    @Subscribe("calculateWithoutProductBtn")
    public void onCalculateWithoutProductBtnClick(Button.ClickEvent event) {
        if (Objects.nonNull(productField.getValue())) {
            shopsDl.setQuery("select e from marketplace_Shop e where e.products <> ALL "
                    + "(select s from marketplace_SoldProduct s where s.product = :product)");
            shopsDl.setParameter("product", productField.getValue());
            shopsDl.load();
        }
    }
}