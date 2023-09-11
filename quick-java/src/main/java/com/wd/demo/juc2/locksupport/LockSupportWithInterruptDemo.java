package com.wd.demo.juc2.locksupport;

import java.util.concurrent.locks.LockSupport;

/**
 * @Author: wangd
 * @Date: 2023/5/9 17:22
 */
public class LockSupportWithInterruptDemo {
    static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            long start = System.currentTimeMillis();
            long end = 0;
            while ((end - start) <= 1000) {
                count++;
                end = System.currentTimeMillis();
            }
            System.out.println("after 1 second.count=" + count);
            //等待或许许可
            LockSupport.park();
            System.out.println("thread over." + Thread.currentThread().isInterrupted());

        });

        t.start();

        Thread.sleep(2000);

        // 中断线程
        t.interrupt();


        System.out.println("main over");
    }

}
