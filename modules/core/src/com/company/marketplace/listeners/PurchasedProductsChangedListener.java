package com.company.marketplace.listeners;

import com.company.marketplace.entity.BuyProduct;
import com.company.marketplace.entity.PurchasedProducts;
import com.company.marketplace.entity.SoldProduct;
import com.haulmont.cuba.core.TransactionalDataManager;
import com.haulmont.cuba.core.app.events.AttributeChanges;
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
    public void beforePersistPurchased(EntityPersistingEvent<PurchasedProducts> event) {
        SoldProduct soldProduct = dataManager.reload(event.getEntity().getProduct(), "_local");
        reduceQuantityPersist(soldProduct, event.getEntity().getQuantity());
    }

    @EventListener
    public void beforePersistBuy(EntityPersistingEvent<BuyProduct> event) {
        SoldProduct soldProduct = dataManager.reload(event.getEntity().getProduct(), "_local");
        reduceQuantityPersist(soldProduct, event.getEntity().getQuantity());
    }

    private void reduceQuantityPersist(SoldProduct soldProduct, Integer quantity) {
        Integer quantitySp = soldProduct.getQuantity();
        soldProduct.setQuantity(quantitySp - quantity);
        dataManager.commit(soldProduct);
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onBuyProductBeforeCommitDeleted(EntityChangedEvent<BuyProduct, UUID> event) {
        if (event.getType().equals(EntityChangedEvent.Type.DELETED)) {
            changeQuantityDeleted(event.getChanges());
        }
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onPurchasedProductsBeforeCommitDeleted(EntityChangedEvent<PurchasedProducts, UUID> event) {
        if (event.getType().equals(EntityChangedEvent.Type.DELETED)) {
            changeQuantityDeleted(event.getChanges());
        }
    }

    private void changeQuantityDeleted(AttributeChanges changes) {
        Id<SoldProduct, UUID> id = changes.getOldValue("product");
        SoldProduct product = tdm.load(id).one();
        Integer quantity = changes.getOldValue("quantity");
        product.setQuantity(product.getQuantity() + quantity);
        tdm.save(product);
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onBuyProductBeforeCommitUpdated(EntityChangedEvent<BuyProduct, UUID> event) {
        if (event.getType().equals(EntityChangedEvent.Type.UPDATED)) {
            BuyProduct bp = tdm.load(event.getEntityId()).view("view-buyProduct-with-product").one();
            if (event.getChanges().isChanged("quantity")) {
                changeAndRevertQuantity(event.getChanges(), bp.getQuantity(), bp.getProduct());
            } else if (event.getChanges().isChanged("product")) {
                changeAndRevertProduct(event.getChanges(), bp.getProduct(), bp.getQuantity());
            }
        }
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onPurchasedProductsBeforeCommitUpdated(EntityChangedEvent<PurchasedProducts, UUID> event) {
        if (event.getType().equals(EntityChangedEvent.Type.UPDATED)) {
            PurchasedProducts pp = tdm.load(event.getEntityId()).view("view-purchasedProducts-with-product").one();
            if (event.getChanges().isChanged("quantity")) {
                changeAndRevertQuantity(event.getChanges(), pp.getQuantity(), pp.getProduct());
            } else if (event.getChanges().isChanged("product")) {
                changeAndRevertProduct(event.getChanges(), pp.getProduct(), pp.getQuantity());
            }
        }
    }

    private void changeAndRevertQuantity(AttributeChanges changes, Integer quantity, SoldProduct sp) {
        Integer oldQuantity = changes.getOldValue("quantity");
        Integer diff = oldQuantity - quantity;
        sp.setQuantity(sp.getQuantity() + diff);
        tdm.save(sp);
    }

    private void changeAndRevertProduct(AttributeChanges changes, SoldProduct spNew, Integer quantityProduct) {
        Id<SoldProduct, UUID> id = changes.getOldValue("product");
        SoldProduct productOld = tdm.load(id).view("view-soldProduct-with-shop").one();
        Integer quantityNew;
        if (Objects.isNull(changes.getOldValue("quantity"))) {
            quantityNew = quantityProduct;
        } else {
            quantityNew = changes.getOldValue("quantity");
        }
        productOld.setQuantity(productOld.getQuantity() + quantityNew);
        spNew.setQuantity(spNew.getQuantity() - quantityProduct);
        tdm.save(spNew, productOld);
    }
}