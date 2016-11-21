package com.test.facts;

/**
 * Created by korovin on 11/21/2016.
*/
public class HumidityAlert extends Alert {
    public HumidityAlert(String message, SensorReading reading) {
        super(message);
        setReading(reading);
    }

    public void execute() {
        System.out.println("Humidity Alert: " + this.getMessage());
    }
}
