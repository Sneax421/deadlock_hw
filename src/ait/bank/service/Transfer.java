package ait.bank.service;

import ait.bank.model.Account;


public class Transfer implements Runnable {
    private Account accFrom;
    private Account accTo;
    private int sum;


    public Transfer(Account accFrom, Account accTo, int sum) {
        this.accFrom = accFrom;
        this.accTo = accTo;
        this.sum = sum;
    }


    @Override
    public void run() {
        accFrom.lock();
        try {
            Thread.sleep(1000);
            accTo.lock();
            try {
                if (accFrom.getBalance() >= sum) {
                    accFrom.credit(sum);
                    accTo.debit(sum);
                }
            } finally {
                accTo.unlock();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            accFrom.unlock();
        }
    }
}



