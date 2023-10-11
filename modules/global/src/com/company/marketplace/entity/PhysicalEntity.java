package com.company.marketplace.entity;

import com.haulmont.chile.core.annotations.NamePattern;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "MARKETPLACE_PHYSICAL_ENTITY")
@Entity(name = "marketplace_PhysicalEntity")
@PrimaryKeyJoinColumn(name = "ID")
@DiscriminatorValue("PhysicalEntity")
@NamePattern("%s %s|name,surname")
public class PhysicalEntity extends Buyer {
    private static final long serialVersionUID = -8468760714748049275L;

    @Column(name = "NAME")
    private String name;

    @NotNull
    @Column(name = "SURNAME", nullable = false)
    private String surname;

    @Column(name = "PATRONYMIC")
    private String patronymic;

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}