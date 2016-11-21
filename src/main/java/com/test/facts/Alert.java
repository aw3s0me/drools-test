package com.test.facts;

/**
 * Created by korovin on 10/6/2016.
 */
public abstract class Alert {
    private String message;

    public Alert(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public abstract void execute();
}
