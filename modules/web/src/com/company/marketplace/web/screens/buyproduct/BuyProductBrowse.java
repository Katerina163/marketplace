package com.company.marketplace.web.screens.buyproduct;

import com.company.marketplace.entity.BuyProduct;
import com.haulmont.cuba.gui.screen.*;

@UiController("marketplace_BuyProduct.browse")
@UiDescriptor("buy-product-browse.xml")
@LookupComponent("buyProductsTable")
@LoadDataBeforeShow
public class BuyProductBrowse extends StandardLookup<BuyProduct> {
}