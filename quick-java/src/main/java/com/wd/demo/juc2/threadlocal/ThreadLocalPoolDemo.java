package com.wd.demo.juc2.threadlocal;

import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: wangd
 * @Date: 2023/5/7 15:48
 */
public class ThreadLocalPoolDemo {

    static class Data {

        static ThreadLocal<Integer> val = ThreadLocal.withInitial(() -> 0);

        public void add() {
            val.set(val.get() + 1);
        }

    }

    public static void main(String[] args) {
        Data data = new Data();
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        try {
            for (int i = 0; i < 10; i++) {
                threadPool.submit(() -> {
                    try {
                        Integer before = Data.val.get();
                        data.add();
                        Integer after = Data.val.get();
                        System.out.println(Thread.currentThread().getName() + " before = " + before + ", after = " + after);
                    } finally {
                        Data.val.remove();
                    }

                });
            }
        } finally {
            threadPool.shutdown();
        }

    }


}
