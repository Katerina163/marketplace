package com.company.marketplace.listeners;

import com.company.marketplace.core.Geocoding;
import com.company.marketplace.entity.Address;
import com.company.marketplace.entity.Shop;
import com.haulmont.cuba.core.TransactionalDataManager;
import com.haulmont.cuba.core.app.events.EntityChangedEvent;
import com.haulmont.cuba.core.entity.contracts.Id;
import com.opencagedata.jopencage.model.JOpenCageLatLng;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.UUID;

@Component("marketplace_ShopChangedListener")
public class ShopChangedListener {
    @Inject
    private TransactionalDataManager tdm;
    @Inject
    private Geocoding geocoding;

    @EventListener
    public void afterCommit(EntityChangedEvent<Shop, UUID> event) {
        if (event.getType().equals(EntityChangedEvent.Type.CREATED)) {
            setCoordinates(event.getEntityId());
        } else if (event.getType().equals(EntityChangedEvent.Type.UPDATED)
                && (event.getChanges().isChanged("address.city")
                || event.getChanges().isChanged("address.street")
                || event.getChanges().isChanged("address.house"))) {
            setCoordinates(event.getEntityId());
        }
    }

    private void setCoordinates(Id<Shop, UUID> id) {
        Shop shop = tdm.load(id).view("view-shop-with-address").one();
        Address address = shop.getAddress();
        JOpenCageLatLng jOpenCage = geocoding.getResponse(address.getCity(), address.getStreet(), address.getHouse());
        shop.getAddress().setLat(jOpenCage.getLat());
        shop.getAddress().setLng(jOpenCage.getLng());
        tdm.save(shop);
    }
}