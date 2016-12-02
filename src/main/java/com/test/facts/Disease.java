package com.test.facts;

import com.test.facts.base.BaseHibernate;

import javax.persistence.ManyToOne;

/**
 * Created by akorovin on 02.12.2016.
 */
public class Disease extends BaseHibernate {
    private String name;

    private String type;

    @ManyToOne
    private Patient patient;

    public Disease() {
        super();
    }

    public Disease(String name) {
        this.name = name;
    }

    public Disease(String name, String type, Patient patient) {
        this.name = name;
        this.type = type;
        this.patient = patient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
