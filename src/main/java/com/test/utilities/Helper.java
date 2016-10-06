package com.test.utilities;

import org.drools.core.spi.KnowledgeHelper;

/**
 * Created by korovin on 10/6/2016.
 */
public class Helper {
    public static void help(final KnowledgeHelper drools, final String message){
        System.out.println(message);
        System.out.println("\nrule triggered: " + drools.getRule().getName());
    }

    public static void helper(final KnowledgeHelper drools){
        System.out.println("\nrule triggered: " + drools.getRule().getName());
    }
}
