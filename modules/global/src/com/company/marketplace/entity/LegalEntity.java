package com.company.marketplace.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.annotation.Extends;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "MARKETPLACE_LEGAL_ENTITY")
@Entity(name = "marketplace_LegalEntity")
@Extends(Buyer.class)
@NamePattern("%s|designation")
public class LegalEntity extends Buyer {
    private static final long serialVersionUID = -6776648809981010884L;

    @Column(name = "DESIGNATION")
    private String designation;

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}