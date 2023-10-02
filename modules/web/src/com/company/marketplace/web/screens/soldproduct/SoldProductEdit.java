package com.company.marketplace.web.screens.soldproduct;

import com.company.marketplace.entity.Product;
import com.company.marketplace.entity.SoldProduct;
import com.company.marketplace.service.SoldProductService;
import com.haulmont.cuba.gui.components.HasValue;
import com.haulmont.cuba.gui.components.ValidationErrors;
import com.haulmont.cuba.gui.screen.*;

import javax.inject.Inject;
import java.util.Objects;

@UiController("marketplace_SoldProduct.edit")
@UiDescriptor("sold-product-edit.xml")
@EditedEntityContainer("soldProductDc")
@LoadDataBeforeShow
public class SoldProductEdit extends StandardEditor<SoldProduct> {
    @Inject
    private SoldProductService service;

    @Override
    protected void validateAdditionalRules(ValidationErrors errors) {
        if (Objects.isNull(getEditedEntity().getVersion()) && service.checkingAvailabilityProducts(getEditedEntity())) {
            errors.add("В магазине уже продается такой товар");
        }
        super.validateAdditionalRules(errors);
    }

    @Subscribe("productField")
    public void onProductFieldValueChange(HasValue.ValueChangeEvent<Product> event) {
        if (event.isUserOriginated()) {
            getEditedEntity().setPrice(event.getValue().getManufacturerPrice());
        }
    }
}