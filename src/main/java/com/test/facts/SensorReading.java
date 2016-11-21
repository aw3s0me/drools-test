package com.test.facts;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by korovin on 11/21/2016.
 */
public class SensorReading {
    private String id;
    private Date timestamp;
    private String sensorId;
    private String type;
    private HashMap<String, Double> values;

    public SensorReading(String id, Date timestamp, String sensorId, String type, HashMap<String, Double> values) {
        this.id = id;
        this.timestamp = timestamp;
        this.sensorId = sensorId;
        this.type = type;
        this.values = values;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public HashMap<String, Double> getValues() {
        return values;
    }

    public Double getOneValue() {
        Map.Entry<String,Double> entry = getValues().entrySet().iterator().next();
        String key = entry.getKey();
        return entry.getValue();
    }

    public void setValues(HashMap<String, Double> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "SensorReading{" +
                "id='" + id + '\'' +
                ", timestamp=" + timestamp +
                ", sensorId='" + sensorId + '\'' +
                ", type='" + type + '\'' +
                ", values=" + values +
                '}';
    }
}
