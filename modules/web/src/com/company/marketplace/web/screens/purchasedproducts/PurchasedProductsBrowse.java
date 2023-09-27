package com.company.marketplace.web.screens.purchasedproducts;

import com.haulmont.cuba.gui.screen.*;
import com.company.marketplace.entity.PurchasedProducts;

@UiController("marketplace_PurchasedProducts.browse")
@UiDescriptor("purchased-products-browse.xml")
@LookupComponent("purchasedProductsesTable")
@LoadDataBeforeShow
public class PurchasedProductsBrowse extends StandardLookup<PurchasedProducts> {
}