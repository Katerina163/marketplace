package com.company.marketplace.core;

import com.opencagedata.jopencage.JOpenCageGeocoder;
import com.opencagedata.jopencage.model.JOpenCageForwardRequest;
import com.opencagedata.jopencage.model.JOpenCageLatLng;
import org.springframework.stereotype.Component;

@Component(Geocoding.NAME)
public class Geocoding {
    public static final String NAME = "marketplace_Geocoding";
    private final JOpenCageGeocoder geocoder = new JOpenCageGeocoder("ab2c3874d0004bc99a07c9ecfa05507f");

    public JOpenCageLatLng getResponse(String city, String street, String house) {
        JOpenCageForwardRequest request = new JOpenCageForwardRequest("Россия, " + city + ", " + street + ", " + house);
        return geocoder.forward(request).getFirstPosition();
    }
}