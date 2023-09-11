package com.wd.demo.juc2.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * @Author: wangd
 * @Date: 2023/5/8 19:21
 */
public class InterruptDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    break;
                }
                System.out.println("i am running...");
            }
            System.out.println("running is interrupted!");
        }, "t1");
        t1.start();
        System.out.println("t1.isInterrupted() = " + t1.isInterrupted());

        TimeUnit.MILLISECONDS.sleep(1);
        t1.interrupt();

        TimeUnit.MILLISECONDS.sleep(500);

        t1.interrupt();
        System.out.println("t1.isInterrupted() = " + t1.isInterrupted());
    }
}
