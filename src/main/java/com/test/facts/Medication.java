package com.test.facts;

import javax.persistence.*;

/**
 * Created by akorovin on 02.12.2016.
 */
@Entity
public class Medication implements java.io.Serializable {
    protected Long id;

    private String name;

    private Double dose;

    private Double periodicity;

    private Patient patient;

    public Medication(String name, Double dose, Double periodicity, Patient patient) {
        this.name = name;
        this.dose = dose;
        this.periodicity = periodicity;
        this.patient = patient;
    }

    public Medication(String name, Double dose, Double periodicity) {
        this.name = name;
        this.dose = dose;
        this.periodicity = periodicity;
    }

    public Medication(String name) {
        this.name = name;
    }

    public Medication() {
        super();
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    // @Column(name = "medication_id")
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

    @ManyToOne(cascade = CascadeType.ALL)
    // @JoinColumn(name = "patient_id", insertable=false, updatable=false)
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
