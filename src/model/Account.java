package model;

import java.util.concurrent.locks.ReentrantLock;

public class Account {
    private final String name;
    private int balance;
    private final ReentrantLock lock = new ReentrantLock();

    public Account(String name, int balance) {
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public void withdraw(int amount) {
        balance -= amount;
    }

    public ReentrantLock getLock() {
        return lock;
    }
}