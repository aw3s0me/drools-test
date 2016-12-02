package com.test.facts;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by akorovin on 02.12.2016.
 */
@Entity
@Table
public class Patient implements java.io.Serializable {
    protected Long id;

    private String name;

    private Double height;

    private Double weight;

    private Integer age;

    private Set<Disease> diseases = new HashSet<Disease>();

    private Set<Medication> medications = new HashSet<Medication>();

    public Patient() {
        super();
    }

    public Patient(String name) {
        this.name = name;
    }

    public Patient(String name, Double height, Double weight, Integer age) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.age = age;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    // @Column(name = "patient_id")
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

//    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Disease.class)
//    @Fetch(value = FetchMode.SUBSELECT)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patient")
    // @LazyCollection(LazyCollectionOption.FALSE)
    // @JoinTable(joinColumns = { @JoinColumn(name = "patient_id") }, inverseJoinColumns = { @JoinColumn(name = "disease_id")})
    public Set<Disease> getDiseases() {
        return diseases;
    }

    public void setDiseases(Set<Disease> diseases) {
        this.diseases = diseases;
    }

    // @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Medication.class)
    // @Fetch(value = FetchMode.SUBSELECT)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patient")
    // @LazyCollection(LazyCollectionOption.FALSE)
    // @JoinTable(joinColumns = { @JoinColumn(name = "patient_id") }, inverseJoinColumns = { @JoinColumn(name = "medication_id")})
    public Set<Medication> getMedications() {
        return medications;
    }

    public void setMedications(Set<Medication> medications) {
        this.medications = medications;
    }

    public void addMedication(Medication medication) {
        medication.setPatient(this);
        this.medications.add(medication);
    }

    public void addDisease(Disease disease) {
        disease.setPatient(this);
        this.diseases.add(disease);
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", age=" + age +
                '}';
    }
}
