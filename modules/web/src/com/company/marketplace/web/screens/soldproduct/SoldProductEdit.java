package com.company.marketplace.web.screens.soldproduct;

import com.company.marketplace.entity.SoldProduct;
import com.haulmont.cuba.gui.screen.*;

@UiController("marketplace_SoldProduct.edit")
@UiDescriptor("sold-product-edit.xml")
@EditedEntityContainer("soldProductDc")
@LoadDataBeforeShow
public class SoldProductEdit extends StandardEditor<SoldProduct> {
}