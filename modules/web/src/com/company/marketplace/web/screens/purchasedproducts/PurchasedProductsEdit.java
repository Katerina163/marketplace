package com.company.marketplace.web.screens.purchasedproducts;

import com.company.marketplace.entity.PurchasedProducts;
import com.company.marketplace.entity.Shop;
import com.company.marketplace.entity.SoldProduct;
import com.haulmont.cuba.gui.components.HasValue;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;

import javax.inject.Inject;

@UiController("marketplace_PurchasedProducts.edit")
@UiDescriptor("purchased-products-edit.xml")
@EditedEntityContainer("purchasedProductsDc")
@LoadDataBeforeShow
public class PurchasedProductsEdit extends StandardEditor<PurchasedProducts> {
    @Inject
    private CollectionLoader<SoldProduct> productsDc;

    @Subscribe("shopField")
    public void onShopFieldValueChange(HasValue.ValueChangeEvent<Shop> event) {
        if (event.getValue() != null) {
            productsDc.setParameter("shop", event.getValue());
        } else {
            productsDc.removeParameter("shop");
        }
        productsDc.load();
    }
}