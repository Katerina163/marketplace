package com.company.marketplace.web.screens.product;

import com.haulmont.cuba.gui.screen.*;
import com.company.marketplace.entity.Product;

@UiController("marketplace_Product.edit")
@UiDescriptor("product-edit.xml")
@EditedEntityContainer("productDc")
@LoadDataBeforeShow
public class ProductEdit extends StandardEditor<Product> {
}