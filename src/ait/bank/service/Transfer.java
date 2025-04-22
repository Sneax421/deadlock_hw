package ait.bank.service;

import ait.bank.model.Account;


public class Transfer implements Runnable {
    private static Object mutex = new Object();
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
        synchronized (mutex) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (accFrom.getBalance() >= sum) {
                accFrom.credit(sum);
                accTo.debit(sum);
            }
        }
    }
}
