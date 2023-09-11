package com.wd.demo.juc.future;

import java.util.concurrent.CompletableFuture;

/**
 * @Author: wangd
 * @Date: 2023/5/4 21:49
 */

public class CompletableFutureDemo {

    public static void main(String[] args) {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " completableFuture");
            return 1024;
        });
        completableFuture.whenComplete((i, t) -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("i = " + i);
            System.out.println("t = " + t);;
        });


    }

}
