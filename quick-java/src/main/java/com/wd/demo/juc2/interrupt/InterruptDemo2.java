package com.wd.demo.juc2.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * @Author: wangd
 * @Date: 2023/5/8 19:21
 */
public class InterruptDemo2 {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.MILLISECONDS.sleep(2);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                    // System.out.println("isInterrupted = " + Thread.currentThread().isInterrupted());
                    // throw new RuntimeException(e);
                }
                if (Thread.currentThread().isInterrupted()) {
                    break;
                }
                System.out.println("i am running...");
            }
            System.out.println("running is interrupted! ");
        }, "t1");
        t1.start();
        System.out.println("t1.isInterrupted() = " + t1.isInterrupted());

        TimeUnit.MILLISECONDS.sleep(1);

        t1.interrupt();
    }
}
