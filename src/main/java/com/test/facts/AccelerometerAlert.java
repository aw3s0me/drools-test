package com.test.facts;

import java.util.ArrayList;

/**
 * Created by korovin on 11/21/2016.
 */
public class AccelerometerAlert extends Alert {
    private ArrayList<Double> values;

    public AccelerometerAlert(String message, ArrayList<Double> values) {
        super(message);
        this.values = values;
    }

    public ArrayList<Double> getValues() {
        return values;
    }

    public void setValues(ArrayList<Double> values) {
        this.values = values;
    }

    public void execute() {
        System.out.println("Accelerometer Alert");
    }

    @Override
    public String toString() {
        return "AccelerometerAlert{" +
                "values=" + values +
                '}';
    }
}
