package com.wd.demo.juc.executors;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: wangd
 * @Date: 2023/5/4 20:16
 */
public class ThreadPoolDemo3 {

    public static void main(String[] args) {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                5,
                2L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );

        try {
            for (int i = 1; i < 200; i++) {
                int finalI = i;
                threadPoolExecutor.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "办理业务" + finalI);
                });
                // System.out.println(Thread.currentThread().getName() + " poolsize = " + threadPoolExecutor.getLargestPoolSize());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPoolExecutor.shutdown();
        }

    }

}
