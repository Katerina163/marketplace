package com.company.marketplace.web.screens.buyproduct;

import com.haulmont.cuba.gui.screen.*;
import com.company.marketplace.entity.BuyProduct;

@UiController("marketplace_BuyProduct.browse")
@UiDescriptor("buy-product-browse.xml")
@LookupComponent("buyProductsTable")
@LoadDataBeforeShow
public class BuyProductBrowse extends StandardLookup<BuyProduct> {
}