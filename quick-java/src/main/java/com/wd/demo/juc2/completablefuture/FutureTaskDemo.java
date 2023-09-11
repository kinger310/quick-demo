package com.wd.demo.juc2.completablefuture;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class FutureTaskDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> task = new FutureTask<>(new MyThread());
        new Thread(task, "t1").start();
        String s = task.get();
        System.out.println("s = " + s);

        try { TimeUnit.MILLISECONDS.sleep(100); } catch (InterruptedException e) { e.printStackTrace(); }
    }

    static class MyThread implements Callable<String> {

        @Override
        public String call() throws Exception {
            System.out.println("----- come in call()");
            return "hello";
        }
    }
}
