package com.wd.demo.juc2.completablefuture;

import java.util.concurrent.*;

public class CFDemo2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(3);

        CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " come");
            int result = ThreadLocalRandom.current().nextInt(10);
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (result > 5) {
                int i = 10/0;
            }

            return result;
        }, pool).whenComplete((v, e) -> {
            if (e == null) {
                System.out.println("v = " + v);
            }
        }).exceptionally(e -> {
            e.printStackTrace();
            System.out.println("e.getCause() = " + e.getCause() + "\t e.getMessage = " + e.getMessage());
            return null;
        });
        System.out.println(Thread.currentThread().getName() + "线程去忙别的任务");

        // System.out.println("future = " + future.get());
        pool.shutdown();
    }
}
