package com.test.facts;

/**
 * Created by korovin on 11/21/2016.
 */
public class AccelerometerAlert extends Alert {
    public AccelerometerAlert(String message, SensorReading reading) {
        super(message);
        setReading(reading);
    }

    public void execute() {
        System.out.println("Accelerometer Alert: " + this.getMessage());
    }
}
