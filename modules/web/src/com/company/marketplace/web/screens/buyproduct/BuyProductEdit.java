package com.company.marketplace.web.screens.buyproduct;

import com.company.marketplace.entity.BuyProduct;
import com.company.marketplace.entity.SoldProduct;
import com.haulmont.cuba.gui.components.HasValue;
import com.haulmont.cuba.gui.screen.*;

import java.util.Objects;

@UiController("marketplace_BuyProduct.edit")
@UiDescriptor("buy-product-edit.xml")
@EditedEntityContainer("buyProductDc")
@LoadDataBeforeShow
public class BuyProductEdit extends StandardEditor<BuyProduct> {

    @Subscribe("productField")
    public void onProductFieldValueChange(HasValue.ValueChangeEvent<SoldProduct> event) {
        if (Objects.isNull(getEditedEntity().getPrice())) {
            getEditedEntity().setPrice(event.getValue().getPrice());
        }
    }
}