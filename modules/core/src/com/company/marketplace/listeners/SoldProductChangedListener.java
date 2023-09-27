package com.company.marketplace.listeners;

import com.company.marketplace.entity.PriceHistory;
import com.company.marketplace.entity.SoldProduct;
import com.haulmont.cuba.core.app.events.EntityChangedEvent;
import com.haulmont.cuba.core.app.events.EntityPersistingEvent;
import com.haulmont.cuba.core.entity.contracts.Id;
import com.haulmont.cuba.core.global.DataManager;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.UUID;

@Component("marketplace_SoldProductChangedListener")
public class SoldProductChangedListener {
    @Inject
    private DataManager dataManager;

    @EventListener
    public void beforePersist(EntityPersistingEvent<SoldProduct> event) {
        createPriceHistory(event.getEntity());
    }

    @EventListener
    public void onSoldProductAfterCommit(EntityChangedEvent<SoldProduct, UUID> event) {
        Id<SoldProduct, UUID> entityId = event.getEntityId();
        if (event.getType().equals(EntityChangedEvent.Type.UPDATED) && event.getChanges().isChanged("price")) {
            SoldProduct sp = dataManager.load(entityId).one();
            createPriceHistory(sp);
        }
    }

    private void createPriceHistory(SoldProduct sp) {
        PriceHistory ph = dataManager.create(PriceHistory.class);
        ph.setProduct(sp.getProduct());
        ph.setShop(sp.getShop());
        ph.setDatePriceChange(LocalDateTime.now());
        dataManager.commit(ph);
    }
}