package com.test.facts;

/**
 * Created by korovin on 11/21/2016.
 */
public class TemperatureAlert extends Alert {
    private Double value;

    public TemperatureAlert(String message, Double value) {
        super(message);
        this.value = value;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public void execute() {
        System.out.println("Humidity Alert");
    }
}
