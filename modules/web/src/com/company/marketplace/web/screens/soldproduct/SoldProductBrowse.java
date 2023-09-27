package com.company.marketplace.web.screens.soldproduct;

import com.haulmont.cuba.gui.screen.*;
import com.company.marketplace.entity.SoldProduct;

@UiController("marketplace_SoldProduct.browse")
@UiDescriptor("sold-product-browse.xml")
@LookupComponent("soldProductsTable")
@LoadDataBeforeShow
public class SoldProductBrowse extends StandardLookup<SoldProduct> {
}