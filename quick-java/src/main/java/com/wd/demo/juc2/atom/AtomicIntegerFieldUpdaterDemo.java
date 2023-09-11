package com.wd.demo.juc2.atom;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @Author: wangd
 * @Date: 2023/5/7 0:28
 */
public class AtomicIntegerFieldUpdaterDemo {
    static class BankAccount {
        public volatile int money = 0;

        public void add() {
            money++;
        }

    }

    static class BankAccountAtom extends BankAccount {
        static final AtomicIntegerFieldUpdater<BankAccount> updater =
                AtomicIntegerFieldUpdater.newUpdater(BankAccount.class, "money");

        @Override
        public void add() {
            updater.incrementAndGet(this);
        }
    }



    public static void main(String[] args) throws InterruptedException {
        BankAccount bankAccount = new BankAccountAtom();
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    bankAccount.add();
                }
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        System.out.println("bankAccount.money = " + bankAccount.money);
    }
}


