package com.test;

import com.test.facts.SensorReading;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by korovin on 10/6/2016.
 */
public class DroolsTest {
    public static void main(String[] args) {
        try {
            DroolsTest drools = new DroolsTest();
            KieSession kSession = drools.getSession("ksession-rules");

            ArrayList<SensorReading> readings = drools.createTestData();

//            acc.withdraw(150);
//            kSession.insert(acc);
//            kSession.fireAllRules();
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
    }

    private void executeBySensorType(ArrayList<SensorReading> readings) {
        for (SensorReading reading: readings) {

        }
    }

    /**
     * Load up knowledge base
     * @param sessionName
     * @return
     */
    private KieSession getSession(String sessionName) {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        return kContainer.newKieSession(sessionName);
    }

    /**
     * String id, Date timestamp, String sensorId, String type
     * @return
     */
    private ArrayList<SensorReading> createTestData() {
        return new ArrayList<SensorReading>() {{
            add(new SensorReading("0", new Date(), "1", "TemperatureSensorReading", new HashMap<String, Double>(){{
               put("temperature", 24.0);
            }}));
            add(new SensorReading("1", new Date(), "1", "TemperatureSensorReading", new HashMap<String, Double>(){{
                put("temperature", 24.0);
            }}));
            add(new SensorReading("2", new Date(), "1", "TemperatureSensorReading", new HashMap<String, Double>(){{
                put("temperature", 24.0);
            }}));
            add(new SensorReading("3", new Date(), "1", "TemperatureSensorReading", new HashMap<String, Double>(){{
                put("temperature", 24.0);
            }}));
            add(new SensorReading("4", new Date(), "1", "AccelerometerSensorReading", new HashMap<String, Double>(){{
                put("temperature", 24.0);
            }}));
            add(new SensorReading("5", new Date(), "1", "AccelerometerSensorReading", new HashMap<String, Double>(){{
                put("x", 24.0);
                put("y", 21.0);
                put("z", 32.0);
            }}));
            add(new SensorReading("6", new Date(), "1", "AccelerometerSensorReading", new HashMap<String, Double>(){{
                put("x", 24.0);
                put("y", 21.0);
                put("z", 32.0);
            }}));
            add(new SensorReading("7", new Date(), "1", "AccelerometerSensorReading", new HashMap<String, Double>(){{
                put("x", 24.0);
                put("y", 21.0);
                put("z", 32.0);
            }}));
        }};
    }
}
