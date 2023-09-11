package com.wd.demo.juc.lock;

/**
 *
 * @Author: wangd
 * @Date: 2023/5/2 10:59
 */
public class ThreadDemo2 {


    static class Share {

        private static int number;
        public synchronized void incr() throws InterruptedException {
            // if (number == 1) {
            while (number == 1) {
                this.wait();  // 在哪里睡去，就在哪里唤醒
            }
            number++;
            System.out.println(Thread.currentThread().getName() + " number = " + number);
            this.notifyAll();
        }

        public synchronized void decr() throws InterruptedException {
            // if (number == 0)
            while (number == 0) {
                this.wait();
            }
            number--;
            System.out.println(Thread.currentThread().getName() + " number = " + number);
            this.notifyAll();
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
