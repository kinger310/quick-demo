package com.wd.demo.juc.future;

import java.util.concurrent.*;

/**
 * @Author: wangd
 * @Date: 2023/5/4 21:49
 */

public class CompletableFutureDemo2 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "---- come in");
            int result = ThreadLocalRandom.current().nextInt(10);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return result;
        }, executorService).thenApply(r -> r+10).whenComplete((o, t) -> {
            if (t == null) {
                System.out.println("complete");
            }
        }).exceptionally(e -> {
            e.printStackTrace();
            System.out.println("异常情况："+e.getCause()+"\t"+e.getMessage());
            return null;
        });
        System.out.println("main thread");
        executorService.shutdown();
    }

}
