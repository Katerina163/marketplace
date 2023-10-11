package com.company.marketplace.web.screens.buyproduct;

import com.haulmont.cuba.gui.screen.*;
import com.company.marketplace.entity.BuyProduct;

@UiController("marketplace_BuyProduct.edit")
@UiDescriptor("buy-product-edit.xml")
@EditedEntityContainer("buyProductDc")
@LoadDataBeforeShow
public class BuyProductEdit extends StandardEditor<BuyProduct> {
}