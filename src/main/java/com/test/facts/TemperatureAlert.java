package com.test.facts;

/**
 * Created by korovin on 11/21/2016.
 */
public class TemperatureAlert extends Alert {
    public TemperatureAlert(String message, SensorReading reading) {
        super(message);
        setReading(reading);
    }

    public void execute() {
        System.out.println("Temperature Alert: " + this.getMessage());
    }
}
