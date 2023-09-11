package com.wd.demo.juc2.locksupport;

/**
 * @Author: wangd
 * @Date: 2023/5/10 11:27
 */
public class ThreadBlockedDemo {
    public static void main(String[] args) throws InterruptedException {

        Object obj = new Object();
        new Thread(() -> {
            synchronized (obj) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Thread thread = new Thread(() -> {
            synchronized (obj) {
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        while (true) {
            Thread.sleep(1000);
            System.out.println(thread.getState());
        }

    }
}
