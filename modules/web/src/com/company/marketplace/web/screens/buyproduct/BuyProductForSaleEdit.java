package com.company.marketplace.web.screens.buyproduct;

import com.haulmont.cuba.gui.screen.*;
import com.company.marketplace.entity.BuyProduct;

@UiController("marketplace_BuyProductForSale.edit")
@UiDescriptor("buy-product-edit-for-sale.xml")
@EditedEntityContainer("buyProductDc")
@LoadDataBeforeShow
public class BuyProductForSaleEdit extends StandardEditor<BuyProduct> {
}