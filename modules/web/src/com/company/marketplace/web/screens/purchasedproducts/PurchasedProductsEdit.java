package com.company.marketplace.web.screens.purchasedproducts;

import com.haulmont.cuba.gui.screen.*;
import com.company.marketplace.entity.PurchasedProducts;

@UiController("marketplace_PurchasedProducts.edit")
@UiDescriptor("purchased-products-edit.xml")
@EditedEntityContainer("purchasedProductsDc")
@LoadDataBeforeShow
public class PurchasedProductsEdit extends StandardEditor<PurchasedProducts> {
}