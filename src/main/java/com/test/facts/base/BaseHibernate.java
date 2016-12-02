package com.test.facts.base;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created by akorovin on 02.12.2016.
 */
@MappedSuperclass
public class BaseHibernate {
    @Id
    @GeneratedValue
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
