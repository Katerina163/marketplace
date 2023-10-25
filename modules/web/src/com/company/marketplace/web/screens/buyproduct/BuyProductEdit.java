package com.company.marketplace.web.screens.buyproduct;

import com.company.marketplace.entity.BuyProduct;
import com.company.marketplace.entity.SoldProduct;
import com.company.marketplace.service.BuyProductService;
import com.haulmont.cuba.gui.components.HasValue;
import com.haulmont.cuba.gui.components.PickerField;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.components.ValidationErrors;
import com.haulmont.cuba.gui.screen.*;

import javax.inject.Inject;
import java.util.Objects;

@UiController("marketplace_BuyProduct.edit")
@UiDescriptor("buy-product-edit.xml")
@EditedEntityContainer("buyProductDc")
@LoadDataBeforeShow
public class BuyProductEdit extends StandardEditor<BuyProduct> {
    @Inject
    private BuyProductService buyProductService;
    @Inject
    private PickerField<SoldProduct> productField;
    @Inject
    private TextField<Integer> quantityField;

    @Subscribe
    public void onAfterShow(AfterShowEvent event) {
        if (Objects.nonNull(getEditedEntity().getPrice())
                && !getEditedEntity().getPrice().equals(getEditedEntity().getProduct().getPrice())) {
            productField.setEditable(false);
            quantityField.setEditable(false);
        }
    }

    @Subscribe("productField")
    public void onProductFieldValueChange(HasValue.ValueChangeEvent<SoldProduct> event) {
        if (Objects.isNull(getEditedEntity().getPrice())) {
            getEditedEntity().setPrice(event.getValue().getPrice());
        }
    }

    @Override
    protected void validateAdditionalRules(ValidationErrors errors) {
        if (Objects.nonNull(getEditedEntity().getQuantity())) {
            if (Objects.isNull(getEditedEntity().getVersion()) && buyProductService.checkingDoubleProducts(getEditedEntity())) {
                errors.add("Товар дублируется");
            }
            if (getEditedEntity().getQuantity() == 0 || !buyProductService.checkingQuantityProducts(getEditedEntity())) {
                errors.add("Неправильное количество товара");
            }
        }
        super.validateAdditionalRules(errors);
    }
}