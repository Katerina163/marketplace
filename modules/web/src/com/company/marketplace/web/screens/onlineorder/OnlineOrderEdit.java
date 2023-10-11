package com.company.marketplace.web.screens.onlineorder;

import com.company.marketplace.entity.BuyProduct;
import com.company.marketplace.entity.OnlineOrder;
import com.company.marketplace.entity.OrderStatus;
import com.haulmont.cuba.core.app.UniqueNumbersService;
import com.haulmont.cuba.gui.RemoveOperation;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.screen.*;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.math.RoundingMode;

@UiController("marketplace_OnlineOrder.edit")
@UiDescriptor("online-order-edit.xml")
@EditedEntityContainer("onlineOrderDc")
@LoadDataBeforeShow
public class OnlineOrderEdit extends StandardEditor<OnlineOrder> {
    @Inject
    private UniqueNumbersService uniqueNumbersService;
    @Inject
    private TextField<BigDecimal> amountField;
    @Inject
    private TextField<Integer> discountField;

    @Subscribe
    public void onInitEntity(InitEntityEvent<OnlineOrder> event) {
        OnlineOrder order = event.getEntity();
//        event.getEntity().setBuyer(user);
        order.setNumber(String.valueOf(uniqueNumbersService.getNextNumber("sequenceForOnlineOrder")));
        order.setStatus(OrderStatus.PROCESSING);
        order.setAmount(new BigDecimal(0));
        order.setDiscount(0);
        discountField.setValue(0);
    }

    @Install(to = "productsTable.add", subject = "afterCloseHandler")
    protected void productsTableCreateAfterCloseHandler(AfterCloseEvent event) {
        calculateAmountWithSale();
    }

    @Install(to = "productsTable.edit", subject = "afterCloseHandler")
    protected void productsTableEditAfterCloseHandler(AfterCloseEvent event) {
        calculateAmountWithSale();
    }

    @Install(to = "productsTable.remove", subject = "afterActionPerformedHandler")
    protected void productsTableRemoveAfterActionPerformedHandler(RemoveOperation.AfterActionPerformedEvent<BuyProduct> event) {
        calculateAmountWithSale();
    }

    private void calculateAmountWithSale() {
        Integer sale = discountField.getValue();
        BigDecimal amount = getEditedEntity().getProducts().stream()
                .map(buyProduct -> buyProduct.getProduct().getPrice()
                        .multiply(BigDecimal.valueOf(buyProduct.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        if (sale != 0) {
            amount = amount.multiply(BigDecimal.valueOf(sale))
                    .divide(BigDecimal.valueOf(100), RoundingMode.DOWN);
        }
        getEditedEntity().setAmount(amount);
        amountField.setValue(amount);
    }
}