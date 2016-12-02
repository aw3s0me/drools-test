package com.test.facts;

import com.test.facts.base.BaseHibernate;

import javax.persistence.*;

/**
 * Created by akorovin on 02.12.2016.
 */
@Entity
@Table
public class Medication extends BaseHibernate {
    private String name;

    private Double dose;

    private Double periodicity;

    @ManyToOne
    private Patient patient;

    public Medication(String name, Double dose, Double periodicity, Patient patient) {
        this.name = name;
        this.dose = dose;
        this.periodicity = periodicity;
        this.patient = patient;
    }

    public Medication(String name) {
        this.name = name;
    }

    public Medication() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getDose() {
        return dose;
    }

    public void setDose(Double dose) {
        this.dose = dose;
    }

    public Double getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(Double periodicity) {
        this.periodicity = periodicity;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "Medication{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dose=" + dose +
                ", periodicity=" + periodicity +
                ", patient=" + patient +
                '}';
    }
}
