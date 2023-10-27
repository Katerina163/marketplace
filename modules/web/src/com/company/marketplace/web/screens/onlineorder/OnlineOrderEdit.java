package com.company.marketplace.web.screens.onlineorder;

import com.company.marketplace.entity.*;
import com.company.marketplace.service.OnlineOrderService;
import com.haulmont.cuba.core.app.UniqueNumbersService;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.RemoveOperation;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.components.HasValue;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.components.ValidationErrors;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.security.entity.User;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

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
    private Table<BuyProduct> productsTable;
    @Inject
    private ScreenBuilders screenBuilders;
    @Inject
    private UserSessionSource userSessionSource;
    @Inject
    private DataManager dataManager;
    @Inject
    private Notifications notifications;
    @Inject
    private OnlineOrderService onlineOrderService;

    @Subscribe
    public void onInitEntity(InitEntityEvent<OnlineOrder> event) {
        OnlineOrder order = event.getEntity();
        User user = userSessionSource.getUserSession().getUser();
        ExtUser u = (ExtUser) dataManager.reload(user, "user.edit");
        order.setBuyer(u.getBuyer());
        order.setNumber(String.valueOf(uniqueNumbersService.getNextNumber("sequenceForOnlineOrder")));
        order.setStatus(OrderStatus.PROCESSING);
        order.setAmount(new BigDecimal(0));
        order.setDiscount(0);
        discountField.setValue(0);
    }

    @Subscribe("discountField")
    public void onDiscountFieldValueChange(HasValue.ValueChangeEvent<Integer> event) {
        if (Objects.nonNull(event.getPrevValue()) && Objects.nonNull(getEditedEntity().getProducts())) {
            calculateAmountWithSale();
        }
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
        if (Objects.nonNull(getEditedEntity().getProducts())) {
            BigDecimal amount = onlineOrderService.calculateAmount(getEditedEntity());
            getEditedEntity().setAmount(amount);
            amountField.setValue(amount);
        }
    }

    @Install(to = "saleProductsDl", target = Target.DATA_LOADER)
    private List<SaleProduct> saleProductsDlLoadDelegate(LoadContext<SaleProduct> loadContext) {
        soldProductsDl.load();
        List<SoldProduct> soldProducts = soldProductsDc.getItems();
        if (!soldProducts.isEmpty()) {
            int sizeSaleProducts = ThreadLocalRandom.current().nextInt(soldProducts.size());
            int[] indexSoldProduct = ThreadLocalRandom.current()
                    .ints(0, soldProducts.size())
                    .distinct()
                    .limit(sizeSaleProducts)
                    .toArray();
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
        return Collections.EMPTY_LIST;
    }

    @Subscribe
    public void onAfterShow(AfterShowEvent event) {
        User user = userSessionSource.getUserSession().getUser();
        ExtUser u = (ExtUser) dataManager.reload(user, "user.edit");
        if (Objects.isNull(u.getBuyer())) {
            notifications.create(Notifications.NotificationType.WARNING)
                    .withDescription("Отсутствует покупатель")
                    .withPosition(Notifications.Position.MIDDLE_CENTER)
                    .show();
            this.closeWithDiscard();
        } else if (soldProductsDc.getItems().isEmpty()) {
            notifications.create(Notifications.NotificationType.WARNING)
                    .withDescription("Отсутствуют товары")
                    .withPosition(Notifications.Position.MIDDLE_CENTER)
                    .show();
            this.closeWithDiscard();
        }
    }

    @Subscribe("saleProductsTable")
    public void onSaleProductsTableSelection(Table.SelectionEvent<SaleProduct> event) {
        SoldProduct product = event.getSelected().stream().findFirst().get().getProduct();
        Screen screen = screenBuilders.editor(productsTable)
                .withScreenId("marketplace_BuyProductForSale.edit")
                .newEntity()
                .withInitializer(buyProduct -> {
                    buyProduct.setOnlineOrder(getEditedEntity());
                    buyProduct.setQuantity(1);
                    buyProduct.setProduct(product);
                    int sale = LocalDate.now().getDayOfMonth();
                    buyProduct.setPrice(BigDecimal.valueOf(product.getPrice().longValue() * (100 - sale) / 100));
                })
                .withLaunchMode(OpenMode.DIALOG)
                .build();
        screen.addAfterCloseListener(afterCloseEvent -> {
            if (afterCloseEvent.closedWith(StandardOutcome.COMMIT)) {
                calculateAmountWithSale();
            }
        });
        screen.show();
    }

    @Subscribe
    public void onBeforeCommitChanges(BeforeCommitChangesEvent event) {
        if (Objects.isNull(getEditedEntity().getProducts()) || getEditedEntity().getProducts().isEmpty()) {
            event.preventCommit();
            this.closeWithDiscard();
        }
    }

    @Override
    protected void validateAdditionalRules(ValidationErrors errors) {
        if (amountField.getValue().compareTo(BigDecimal.valueOf(99_999_999_999_999_999L)) > 0) {
            errors.add("Слишком большая итоговая сумма");
        }
        super.validateAdditionalRules(errors);
    }
}