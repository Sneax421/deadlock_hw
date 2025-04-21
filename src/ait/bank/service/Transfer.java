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
        Account first = accFrom.getAccNumber() < accTo.getAccNumber() ? accFrom : accTo;
        Account second = accFrom.getAccNumber() < accTo.getAccNumber() ? accTo : accFrom;

        synchronized (first) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (second) {
                if (accFrom.getBalance() >= sum) {
                    accFrom.credit(sum);
                    accTo.debit(sum);
                }
            }
        }

    }
}
