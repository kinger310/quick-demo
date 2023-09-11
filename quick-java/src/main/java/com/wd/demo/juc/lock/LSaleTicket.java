package com.wd.demo.juc.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: wangd
 * @Date: 2023/5/1 19:01
 */
public class LSaleTicket {

    static class LTicket {
        private int number = 30;

        private final ReentrantLock lock = new ReentrantLock(true);

        public synchronized void sale1() {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "sale " + (number--) + "剩下： " + number);
            }
        }

        public void sale() {
            lock.lock();
            try {
                if (number > 0) {
                    System.out.println(Thread.currentThread().getName() + " sale " + (number--) + " 剩下： " + number);
                }
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        LTicket ticket = new LTicket();

        new Thread(() -> {
            for (int i = 0 ; i < 40; i++) {
                ticket.sale();
            }
        }, "AA").start();

        new Thread(() -> {
            for (int i = 0 ; i < 40; i++) {
                ticket.sale();
            }
        }, "BB").start();
        new Thread(() -> {
            for (int i = 0 ; i < 40; i++) {
                ticket.sale();
            }
        }, "CC").start();

    }





}
