package com.wd.demo.juc.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @Author: wangd
 * @Date: 2023/5/2 10:59
 */
public class ThreadDemo3 {


    static class Share {

        private Lock lock = new ReentrantLock();
        private Condition condition = lock.newCondition();

        private int number;

        public void incr() throws InterruptedException {
            lock.lock();
            try {
                while (number == 1) {
                    condition.await();
                }
                number++;
                System.out.println(Thread.currentThread().getName() + " number = " + number);
                condition.signalAll();

            } finally {
                lock.unlock();
            }
        }

        public synchronized void decr() throws InterruptedException {
            lock.lock();
            try {
                while (number == 0) {
                    condition.await();
                }
                number--;
                System.out.println(Thread.currentThread().getName() + " number = " + number);
                condition.signalAll();

            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        Share share = new Share();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "AA").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "BB").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "CC").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "DD").start();
    }
}
