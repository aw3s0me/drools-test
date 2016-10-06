package com.test.facts;

/**
 * Created by korovin on 10/6/2016.
 */
public class Account {
    private Integer balance;

    public Account(Integer balance) {
        this.balance = balance;
    }

    public Account() {}

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public void withdraw(int money) {
        balance -= money;
    }
}
