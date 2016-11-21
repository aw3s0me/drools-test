package com.test.facts;

/**
 * Created by korovin on 10/6/2016.
 */
public abstract class Alert {
    private String message;
    private int eventId;
    private long eventDestinationTimestamp;
    private SensorReading reading;

    public Alert(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public long getEventDestinationTimestamp() {
        return eventDestinationTimestamp;
    }

    public void setEventDestinationTimestamp(long eventDestinationTimestamp) {
        this.eventDestinationTimestamp = eventDestinationTimestamp;
    }

    public SensorReading getReading() {
        return reading;
    }

    public void setReading(SensorReading reading) {
        this.reading = reading;
    }

    public abstract void execute();
}
