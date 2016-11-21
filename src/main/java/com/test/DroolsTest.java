package com.test;

import com.test.facts.SensorReading;
import com.test.utilities.RuleRunner;
import org.drools.core.event.DebugRuleRuntimeEventListener;
import org.drools.core.event.DefaultAgendaEventListener;
import org.drools.core.time.SessionPseudoClock;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.event.rule.*;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.conf.ClockTypeOption;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.definition.KnowledgePackage;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by korovin on 10/6/2016.
 */
public class DroolsTest {
    public static void main(String[] args) {
        try {
            DroolsTest drools = new DroolsTest();
            // Switching to Stream mode
            // config.setOption( EventProcessingOption.STREAM );
            ArrayList<SensorReading> readings = drools.createTestData();

            // Run it to see what are event listeners
            // drools.testEventListener();

            // Run it to check inference of new facts (inference of alerts and applying rules on them)
            // drools.testNewInferredFacts(readings);

            // Run it to see window feature event from drools
            // @see https://docs.jboss.org/drools/release/6.5.0.Final/drools-docs/html_single/#DroolsComplexEventProcessingChapter
            drools.testWindowEvent(readings);
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
    }

    /**
     * Test inference of new facts (common.drl)
     */
    private void testNewInferredFacts(ArrayList<SensorReading> readings) {
        KieBaseConfiguration config = KieServices.Factory.get().newKieBaseConfiguration();
        config.setOption( EventProcessingOption.CLOUD );

        new RuleRunner().runRules( new String[] { "rules/common.drl" }, readings.toArray() );
    }

    /**
     * Test time window event feature
     * @param readings
     */
    private void testWindowEvent(ArrayList<SensorReading> readings) {
        executeWindow(new String[] { "rules/time.drl" }, readings.toArray());
    }

    /**
     * Run it to see what is event listener
     */
    private void testEventListener(ArrayList<SensorReading> readings) {
        KieBaseConfiguration config = KieServices.Factory.get().newKieBaseConfiguration();
        config.setOption( EventProcessingOption.CLOUD );

        KieSession kSession = getSession("ksession-rules");

        kSession.addEventListener( new DefaultAgendaEventListener() {
            public void afterMatchFired(AfterMatchFiredEvent event) {
                super.afterMatchFired( event );
                System.out.println("AfterMatchFired");
                System.out.println( event );
            }

            @Override
            public void afterRuleFlowGroupActivated(RuleFlowGroupActivatedEvent event) {
                super.afterRuleFlowGroupActivated(event);
                System.out.println("afterRuleFlowGroupActivated");
                System.out.println(event);
            }

            @Override
            public void matchCreated(MatchCreatedEvent event) {
                super.matchCreated(event);
                System.out.println("matchCreated");
                System.out.println(event);
            }

            @Override
            public void agendaGroupPushed(AgendaGroupPushedEvent event) {
                super.agendaGroupPushed(event);
                System.out.println("agendaGroupPushed");
                System.out.println(event);
            }

            @Override
            public void matchCancelled(MatchCancelledEvent event) {
                super.matchCancelled(event);
                System.out.println("matchCancelled");
                System.out.println(event);
            }
        });

        kSession.addEventListener( new DebugRuleRuntimeEventListener() );

        executeTest(kSession, readings.toArray());
    }

    private void executeTest(KieSession ksession, Object[] readings) {
        for ( int i = 0; i < readings.length; i++ ) {
            Object fact = readings[i];
            System.out.println( "Inserting fact: " + fact );
            ksession.insert( fact );
        }

        ksession.fireAllRules();
    }

    private void executeWindow(String[] rules, Object[] readings) {
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();

        for ( int i = 0; i < rules.length; i++ ) {
            String ruleFile = rules[i];
            System.out.println( "Loading file: " + ruleFile );
            kbuilder.add( ResourceFactory.newClassPathResource( ruleFile,
                    RuleRunner.class ),
                    ResourceType.DRL );
        }

        Collection<KnowledgePackage> pkgs = kbuilder.getKnowledgePackages();
        kbase.addKnowledgePackages( pkgs );
        //StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();

        KieBaseConfiguration config = KieServices.Factory.get().newKieBaseConfiguration();
        config.setOption( EventProcessingOption.STREAM );
        // This clock is specially useful for unit testing temporal rules since it can be controlled by the application and so the results become deterministic.
        KieSessionConfiguration sessConfig = KieServices.Factory.get().newKieSessionConfiguration();
        sessConfig.setOption( ClockTypeOption.get("pseudo"));
        // KieSession session = kbase.newKieSession( sessConfig, null );

        StatefulKnowledgeSession session = kbase.newStatefulKnowledgeSession(sessConfig, null);
        SessionPseudoClock clock = session.getSessionClock();
        // session.setGlobal("max", 6.0);

        for ( int i = 0; i < readings.length; i++ ) {
            Object fact = readings[i];
            System.out.println( "Inserting fact: " + fact );

            session.insert(fact);
            clock.advanceTime( 1, TimeUnit.SECONDS );
        }

        session.fireAllRules();
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
            add(new SensorReading("0", (new Date()).getTime(), "1", "TemperatureSensorReading", new HashMap<String, Double>(){{
               put("temperature", 20.0);
            }}));
            add(new SensorReading("1", (new Date()).getTime(), "1", "TemperatureSensorReading", new HashMap<String, Double>(){{
                put("temperature", 22.0);
            }}));
            add(new SensorReading("2", (new Date()).getTime(), "1", "TemperatureSensorReading", new HashMap<String, Double>(){{
                put("temperature", 5.0);
            }}));
            add(new SensorReading("3", (new Date()).getTime(), "1", "TemperatureSensorReading", new HashMap<String, Double>(){{
                put("temperature", 15.0);
            }}));
            add(new SensorReading("4", (new Date()).getTime(), "1", "HumiditySensorReading", new HashMap<String, Double>(){{
                put("humidity", 33.0);
            }}));
            add(new SensorReading("5", (new Date()).getTime(), "1", "HumiditySensorReading", new HashMap<String, Double>(){{
                put("humidity", 45.0);
            }}));
            add(new SensorReading("6", (new Date()).getTime(), "1", "HumiditySensorReading", new HashMap<String, Double>(){{
                put("humidity", 42.0);
            }}));
            add(new SensorReading("7", (new Date()).getTime(), "1", "HumiditySensorReading", new HashMap<String, Double>(){{
                put("humidity", 35.0);
            }}));
            add(new SensorReading("8", (new Date()).getTime(), "1", "AccelerometerSensorReading", new HashMap<String, Double>(){{
                put("x", 0.5);
                put("y", 0.6);
                put("z", 0.7);
            }}));
            add(new SensorReading("9", (new Date()).getTime(), "1", "AccelerometerSensorReading", new HashMap<String, Double>(){{
                put("x", 1.2);
                put("y", 1.5);
                put("z", 1.7);
            }}));
            add(new SensorReading("10", (new Date()).getTime(), "1", "AccelerometerSensorReading", new HashMap<String, Double>(){{
                put("x", 0.2);
                put("y", 1.0);
                put("z", 2.0);
            }}));
        }};
    }
}
