package com.company.marketplace.listeners;

import com.company.marketplace.entity.PurchasedProducts;
import com.company.marketplace.entity.SoldProduct;
import com.haulmont.cuba.core.TransactionalDataManager;
import com.haulmont.cuba.core.app.events.EntityChangedEvent;
import com.haulmont.cuba.core.app.events.EntityPersistingEvent;
import com.haulmont.cuba.core.entity.contracts.Id;
import com.haulmont.cuba.core.global.DataManager;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.inject.Inject;
import java.util.Objects;
import java.util.UUID;

@Component("marketplace_PurchasedProductsChangedListener")
public class PurchasedProductsChangedListener {
    @Inject
    private DataManager dataManager;
    @Inject
    private TransactionalDataManager tdm;

    @EventListener
    public void beforePersist(EntityPersistingEvent<PurchasedProducts> event) {
        SoldProduct soldProduct = dataManager.reload(event.getEntity().getProduct(), "_local");
        Integer quantitySp = soldProduct.getQuantity();
        soldProduct.setQuantity(quantitySp - event.getEntity().getQuantity());
        dataManager.commit(soldProduct);
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onPurchasedProductsBeforeCommit(EntityChangedEvent<PurchasedProducts, UUID> event) {
        if (event.getType().equals(EntityChangedEvent.Type.DELETED)) {
            Id<SoldProduct, UUID> id = event.getChanges().getOldValue("product");
            SoldProduct product = tdm.load(id).one();
            Integer quantity = event.getChanges().getOldValue("quantity");
            product.setQuantity(product.getQuantity() + quantity);
            tdm.save(product);
        }
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onPurchasedProductsBeforeCommit1(EntityChangedEvent<PurchasedProducts, UUID> event) {
        if (event.getType().equals(EntityChangedEvent.Type.UPDATED)) {
            PurchasedProducts pp = tdm.load(event.getEntityId()).view("view-purchasedProducts-with-product").one();
            if (event.getChanges().isChanged("quantity")) {
                Integer oldQuantity = event.getChanges().getOldValue("quantity");
                Integer diff = oldQuantity - pp.getQuantity();
                SoldProduct sp = pp.getProduct();
                sp.setQuantity(sp.getQuantity() + diff);
                tdm.save(sp);
            } else if (event.getChanges().isChanged("product")) {
                Id<SoldProduct, UUID> id = event.getChanges().getOldValue("product");
                SoldProduct productOld = tdm.load(id).view("view-soldProduct-with-shop").one();
                Integer quantityNew;
                if (Objects.isNull(event.getChanges().getOldValue("quantity"))) {
                    quantityNew = pp.getQuantity();
                } else {
                    quantityNew = event.getChanges().getOldValue("quantity");
                }
                productOld.setQuantity(productOld.getQuantity() + quantityNew);
                SoldProduct spNew = pp.getProduct();
                spNew.setQuantity(spNew.getQuantity() - pp.getQuantity());
                tdm.save(spNew, productOld);
            }
        }
    }
}