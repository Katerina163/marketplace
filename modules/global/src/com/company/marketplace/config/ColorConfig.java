package com.company.marketplace.config;

import com.haulmont.cuba.core.config.Config;
import com.haulmont.cuba.core.config.Property;
import com.haulmont.cuba.core.config.Source;
import com.haulmont.cuba.core.config.SourceType;
import com.haulmont.cuba.core.config.defaults.Default;

import java.math.BigDecimal;

@Source(type = SourceType.DATABASE)
public interface ColorConfig extends Config {
    @Property("color.price")
    @Default("100")
    BigDecimal getProductPrice();

    void setProductPrice(BigDecimal price);
}