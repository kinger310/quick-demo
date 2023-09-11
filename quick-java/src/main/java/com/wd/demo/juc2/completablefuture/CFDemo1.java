package com.wd.demo.juc2.completablefuture;

import java.util.concurrent.*;

public class CFDemo1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(3);

        // CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
        //     System.out.println(Thread.currentThread().getName() + " come");
        //     try {
        //         TimeUnit.MILLISECONDS.sleep(1000);
        //     } catch (InterruptedException e) {
        //         e.printStackTrace();
        //     }
        // }, pool);

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " come");
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hi";
        }, pool);

        System.out.println("future = " + future.get());
        pool.shutdown();
    }
}
