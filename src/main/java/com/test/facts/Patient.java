package com.test.facts;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by akorovin on 02.12.2016.
 */
@Entity
@Table
public class Patient {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Double height;

    private Double weight;

    private Integer age;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.PERSIST)
    private List<Disease> diseases = new ArrayList<Disease>();

    @OneToMany(mappedBy = "medication", cascade = CascadeType.PERSIST)
    private List<Medication> medications = new ArrayList<Medication>();

    public Patient() {
        super();
    }

    public Patient(String name) {
        this.name = name;
    }

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

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Disease> getDiseases() {
        return diseases;
    }

    public void setDiseases(List<Disease> diseases) {
        this.diseases = diseases;
    }

    public List<Medication> getMedications() {
        return medications;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }
}
