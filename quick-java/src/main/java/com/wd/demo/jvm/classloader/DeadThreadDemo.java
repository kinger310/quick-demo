package com.wd.demo.jvm.classloader;

/**
 * @Author: wangd
 * @Date: 2023/5/11 13:36
 */
public class DeadThreadDemo {

    static class DeadThread {
        static {
            if (true){
                System.out.println(Thread.currentThread().getName() + " initialize class");
                // for (;;) {}
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " start");
                DeadThread dt = new DeadThread();
                System.out.println(Thread.currentThread().getName() + " end");
            }, String.valueOf(i+1)).start();
        }
    }

}
