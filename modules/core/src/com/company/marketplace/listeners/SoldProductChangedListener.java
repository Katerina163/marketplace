package com.company.marketplace.listeners;

import com.company.marketplace.entity.PriceHistory;
import com.company.marketplace.entity.SoldProduct;
import com.haulmont.cuba.core.TransactionalDataManager;
import com.haulmont.cuba.core.app.events.EntityChangedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.UUID;

@Component("marketplace_SoldProductChangedListener")
public class SoldProductChangedListener {
    @Inject
    private TransactionalDataManager tdm;

    @EventListener
    public void onSoldProductAfterCommit1(EntityChangedEvent<SoldProduct, UUID> event) {
        if ((event.getType().equals(EntityChangedEvent.Type.UPDATED) && event.getChanges().isChanged("price"))
                || event.getType().equals(EntityChangedEvent.Type.CREATED)) {
            SoldProduct sp = tdm.load(event.getEntityId()).view("view-soldProduct-with-shop").one();
            PriceHistory ph = tdm.create(PriceHistory.class);
            ph.setProduct(sp.getProduct());
            ph.setShop(sp.getShop());
            ph.setDatePriceChange(LocalDateTime.now());
            ph.setPrice(sp.getPrice());
            tdm.save(ph);
        }
    }
}