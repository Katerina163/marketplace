package com.company.marketplace.web.screens.purchasedproducts;

import com.company.marketplace.entity.PurchasedProducts;
import com.company.marketplace.entity.SoldProduct;
import com.company.marketplace.service.PurchasedProductsService;
import com.haulmont.cuba.gui.components.HasValue;
import com.haulmont.cuba.gui.components.ValidationErrors;
import com.haulmont.cuba.gui.screen.*;

import javax.inject.Inject;
import java.util.Objects;

@UiController("marketplace_PurchasedProducts.edit")
@UiDescriptor("purchased-products-edit.xml")
@EditedEntityContainer("purchasedProductsDc")
@LoadDataBeforeShow
public class PurchasedProductsEdit extends StandardEditor<PurchasedProducts> {
    @Inject
    private PurchasedProductsService service;

    @Subscribe("productField")
    public void onProductFieldValueChange(HasValue.ValueChangeEvent<SoldProduct> event) {
        if (event.isUserOriginated()) {
            getEditedEntity().setShop(event.getValue().getShop());
            getEditedEntity().setPrice(event.getValue().getPrice());
        }
    }

    @Override
    protected void validateAdditionalRules(ValidationErrors errors) {
        if (Objects.nonNull(getEditedEntity().getProduct()) && Objects.nonNull(getEditedEntity().getQuantity())) {
            if (Objects.isNull(getEditedEntity().getVersion()) && service.checkingDoubleProducts(getEditedEntity())) {
                errors.add("Товар дублируется");
            }
            if (getEditedEntity().getQuantity() == 0 || !service.checkingQuantityProducts(getEditedEntity())) {
                errors.add("Неправильное количество товара");
            }
        }
        super.validateAdditionalRules(errors);
    }
}