package com.test;

import com.test.facts.Account;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * Created by korovin on 10/6/2016.
 */
public class DroolsTest {
    public static void main(String[] args) {
        try {
            DroolsTest drools = new DroolsTest();
            KieSession kSession = drools.getSession("ksession-rules");

            Account acc = new Account(200);
            acc.withdraw(150);
            kSession.insert(acc);
            kSession.fireAllRules();
        }
        catch (Throwable t) {
            t.printStackTrace();
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
}
