package com.wd.demo.juc.util;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Author: wangd
 * @Date: 2023/5/4 15:22
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "号抢占了车位");
                    TimeUnit.SECONDS.sleep(new Random().nextInt(2));
                    System.out.println(Thread.currentThread().getName() + "号离开了车位");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    semaphore.release();
                }

            }, String.valueOf(i+1)).start();
        }
    }
}
