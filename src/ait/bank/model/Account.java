package ait.bank.model;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
    private final Lock mutex = new ReentrantLock();
    private volatile int accNumber;
    private volatile int balance;

    public Account(int accNumber) {
        this.accNumber = accNumber;
    }

    public int getAccNumber() {
        return accNumber;
    }

    public int getBalance() {
        return balance;
    }

    public void debit(int sum){
        balance = balance + sum;
    }

    public void credit(int sum){
        balance = balance - sum;
    }

    public void lock(){
        mutex.lock();
    }

    public void unlock(){
        mutex.unlock();
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Account account)) return false;

        return accNumber == account.accNumber;
    }

    @Override
    public int hashCode() {
        return accNumber;
    }
}
