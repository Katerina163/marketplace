package com.company.marketplace.web.screens.buyproduct;

import com.company.marketplace.entity.BuyProduct;
import com.company.marketplace.entity.SoldProduct;
import com.haulmont.cuba.gui.components.HasValue;
import com.haulmont.cuba.gui.screen.*;

@UiController("marketplace_BuyProduct.edit")
@UiDescriptor("buy-product-edit.xml")
@EditedEntityContainer("buyProductDc")
@LoadDataBeforeShow
public class BuyProductEdit extends StandardEditor<BuyProduct> {

    @Subscribe("productField")
    public void onProductFieldValueChange(HasValue.ValueChangeEvent<SoldProduct> event) {
        getEditedEntity().setPrice(event.getValue().getPrice());
    }
}