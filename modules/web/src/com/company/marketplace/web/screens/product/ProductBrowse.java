package com.company.marketplace.web.screens.product;

import com.company.marketplace.entity.Product;
import com.haulmont.cuba.gui.screen.*;

@UiController("marketplace_Product.browse")
@UiDescriptor("product-browse.xml")
@LookupComponent("productsTable")
@LoadDataBeforeShow
public class ProductBrowse extends StandardLookup<Product> {
}