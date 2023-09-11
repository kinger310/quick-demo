package com.wd.demo.juc2.syncup;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: wangd
 * @Date: 2023/5/5 20:06
 */
public class SyncAtomicityDemo {
    // private static volatile int count = 0;

    private static final AtomicInteger count = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            new Thread(() ->{
                for (int j = 0; j < 10000; j++) {
                    add();
                }
            }).start();
        }
        Thread.sleep(1000);
        System.out.println(count.get());
        System.out.println("costTime = " + (System.currentTimeMillis() - start));
    }

    private static void add() {
        // synchronized (SyncAtomicityDemo.class) {
        //     count++;
        // }
        count.incrementAndGet();
    }

}
