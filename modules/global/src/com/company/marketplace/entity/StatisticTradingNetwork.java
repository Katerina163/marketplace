package com.company.marketplace.entity;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.BaseUuidEntity;

@MetaClass(name = "marketplace_StatistictradingNetwork")
public class StatisticTradingNetwork extends BaseUuidEntity {
    private static final long serialVersionUID = -8060209727341336739L;

    @MetaProperty
    private TradingNetwork network;

    public TradingNetwork getNetwork() {
        return network;
    }

    public void setNetwork(TradingNetwork network) {
        this.network = network;
    }
}