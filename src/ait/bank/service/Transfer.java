package ait.bank.service;

import ait.bank.model.Account;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Transfer implements Runnable {
    private Account accFrom;
    private Account accTo;
    private int sum;
    private static Lock mutex = new ReentrantLock();

    public Transfer(Account accFrom, Account accTo, int sum) {
        this.accFrom = accFrom;
        this.accTo = accTo;
        this.sum = sum;
    }


    @Override
    public void run() {
        mutex.lock();
        try {
            Thread.sleep(1000);
            if (accFrom.getBalance() >= sum) {
                accFrom.credit(sum);
                accTo.debit(sum);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            mutex.unlock();
        }
    }
}
