package com.company.marketplace.web.screens.product;

import com.haulmont.cuba.gui.screen.*;
import com.company.marketplace.entity.Product;

@UiController("marketplace_Product.browse")
@UiDescriptor("product-browse.xml")
@LookupComponent("productsTable")
@LoadDataBeforeShow
public class ProductBrowse extends StandardLookup<Product> {
}