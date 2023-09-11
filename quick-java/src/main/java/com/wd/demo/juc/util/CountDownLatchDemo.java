package com.wd.demo.juc.util;

import java.util.concurrent.CountDownLatch;

/**
 * @Author: wangd
 * @Date: 2023/5/4 11:41
 */
public class CountDownLatchDemo {
    // 六个同学陆续离开，班长最后锁门
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    startSignal.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + "号同学进入教室");
                System.out.println(Thread.currentThread().getName() + "号同学离开教室");
                doneSignal.countDown();
            }, String.valueOf(i+1)).start();
        }

        System.out.println("班长开门");
        startSignal.countDown();
        doneSignal.await();
        System.out.println("班长锁门");
    }
}
