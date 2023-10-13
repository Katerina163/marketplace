package com.company.marketplace.web.screens.buyproduct;

import com.company.marketplace.service.BuyProductService;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.ValidationErrors;
import com.haulmont.cuba.gui.screen.*;
import com.company.marketplace.entity.BuyProduct;

import javax.inject.Inject;

@UiController("marketplace_BuyProductForSale.edit")
@UiDescriptor("buy-product-edit-for-sale.xml")
@EditedEntityContainer("buyProductDc")
@LoadDataBeforeShow
public class BuyProductForSaleEdit extends StandardEditor<BuyProduct> {
    @Inject
    private BuyProductService buyProductService;
    @Inject
    private Button commitAndCloseBtn;


    @Override
    protected void validateAdditionalRules(ValidationErrors errors) {
        if (buyProductService.checkAnotherProductWithSale(getEditedEntity())) {
            errors.add("Товар дублируется");
            commitAndCloseBtn.setEnabled(false);
        }
        super.validateAdditionalRules(errors);
    }
}