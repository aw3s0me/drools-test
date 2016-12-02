package com.test.facts;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by akorovin on 02.12.2016.
 */
public class Medication {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Double dose;

    private Double periodicity;

    @ManyToOne
    private Patient patient;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
