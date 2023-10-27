package com.company.marketplace.service;

import com.company.marketplace.core.Geocoding;
import com.company.marketplace.entity.Address;
import com.company.marketplace.entity.Shop;
import com.company.marketplace.entity.SoldProduct;
import com.opencagedata.jopencage.model.JOpenCageLatLng;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Objects;

@Service(ShopService.NAME)
public class ShopServiceBean implements ShopService {

    @Inject
    private Geocoding geocoding;

    @Override
    public boolean checkGeocoordinates(Shop shop) {
        Address address = shop.getAddress();
        JOpenCageLatLng jOpenCage = geocoding.getResponse(address.getCity(), address.getStreet(), address.getHouse());
        if (Objects.nonNull(jOpenCage)) {
            shop.getAddress().setLat(jOpenCage.getLat());
            shop.getAddress().setLng(jOpenCage.getLng());
            return true;
        }
        return false;
    }

    @Override
    public boolean checkContainsMinQuantityProduct(Shop shop) {
        if (shop.getProducts().isEmpty()) {
            return false;
        }
        for (SoldProduct product : shop.getProducts()) {
            if (product.getMin() > product.getQuantity()) {
                return true;
            }
        }
        return false;
    }
}