package com.test.facts;

import javax.persistence.*;

/**
 * Created by akorovin on 02.12.2016.
 */
@Entity
public class Disease implements java.io.Serializable {
    protected Long id;

    private String name;

    private String type;

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

    public Disease(String type, String name) {
        this.type = type;
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    // @Column(name = "disease_id")
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

    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "patient_id", insertable=false, updatable=false)
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
