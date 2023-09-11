package com.wd.demo.juc.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @Author: wangd
 * @Date: 2023/5/5 13:32
 */
public class FutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            System.out.println(Thread.currentThread().getName()+ "-----come in");
            TimeUnit.SECONDS.sleep(3);
            return "task over";
        });
        new Thread(futureTask, "t1").start();
        System.out.println(Thread.currentThread().getName() + "干其他事情");

        while (true) {
            if (futureTask.isDone()) {
                System.out.println("futureTask.get() = " + futureTask.get());
                break;
            } else {
                try { TimeUnit.MILLISECONDS.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println("正在处理中。。。");
            }
        }
    }
}
