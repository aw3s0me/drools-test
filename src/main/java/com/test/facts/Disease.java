package com.test.facts;

import com.test.facts.base.BaseHibernate;

/**
 * Created by akorovin on 02.12.2016.
 */
public class Disease extends BaseHibernate {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
