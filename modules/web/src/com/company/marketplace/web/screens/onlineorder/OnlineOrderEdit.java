package com.company.marketplace.web.screens.onlineorder;

import com.company.marketplace.entity.*;
import com.haulmont.cuba.core.app.UniqueNumbersService;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.gui.RemoveOperation;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;

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
    @Inject
    private CollectionLoader<SoldProduct> soldProductsDl;
    @Inject
    private CollectionContainer<SoldProduct> soldProductsDc;
    @Inject
    private DataManager dataManager;
    @Inject
    private Table<BuyProduct> productsTable;

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
        BigDecimal amount = calculateAmount(buyProduct -> buyProduct.getPrice().equals(buyProduct.getProduct().getPrice()));
        if (sale != 0) {
            amount = amount.multiply(BigDecimal.valueOf(100 - sale))
                    .divide(BigDecimal.valueOf(100), RoundingMode.DOWN);
        }
        BigDecimal amountWithSale = calculateAmount(buyProduct -> !buyProduct.getPrice().equals(buyProduct.getProduct().getPrice()));
        getEditedEntity().setAmount(amount.add(amountWithSale));
        amountField.setValue(amount.add(amountWithSale));
    }

    private BigDecimal calculateAmount(Predicate<BuyProduct> predicate) {
        return getEditedEntity().getProducts().stream()
                .filter(predicate)
                .map(buyProduct -> buyProduct.getPrice()
                        .multiply(BigDecimal.valueOf(buyProduct.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Install(to = "saleProductsDl", target = Target.DATA_LOADER)
    private List<SaleProduct> saleProductsDlLoadDelegate(LoadContext<SaleProduct> loadContext) {
        soldProductsDl.load();
        List<SoldProduct> soldProducts = soldProductsDc.getItems();
        int sizeSaleProducts = ThreadLocalRandom.current().nextInt(soldProducts.size());
        int[] indexSoldProduct = ThreadLocalRandom.current().ints(0, soldProducts.size()).distinct().limit(sizeSaleProducts).toArray();
        int sale = LocalDate.now().getDayOfMonth();
        List<SaleProduct> collection = new ArrayList<>(sizeSaleProducts);
        for (int i = 0; i < sizeSaleProducts; i++) {
            SoldProduct product = soldProducts.get(indexSoldProduct[i]);
            SaleProduct ap = new SaleProduct();
            ap.setProduct(product);
            ap.setPrice(BigDecimal.valueOf(product.getPrice().longValue() * (100 - sale) / 100));
            collection.add(ap);
        }
        collection.sort(Comparator.comparing(SaleProduct::getPrice));
        return collection;
    }

    @Subscribe("saleProductsTable")
    public void onSaleProductsTableSelection(Table.SelectionEvent<SaleProduct> event) {
        SoldProduct product = event.getSelected().stream().findFirst().get().getProduct();
//        BuyProduct buyProduct = dataManager.create(BuyProduct.class);
//        buyProduct.setProduct(product);
//        buyProduct.setOnlineOrder(getEditedEntity());
//        buyProduct.setQuantity(1L);
//        int sale = LocalDate.now().getDayOfMonth();
//        buyProduct.setPrice(BigDecimal.valueOf(product.getPrice().longValue() * (100 - sale) / 100));
//        dataManager.commit(getEditedEntity(), buyProduct);
        productsTable.repaint();
    }
}