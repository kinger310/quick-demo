package com.wd.demo.juc;

/**
 * @Author: wangd
 * @Date: 2023/5/1 18:37
 */
public class DaemonTest {

    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "::" + Thread.currentThread().isDaemon());

            while (true) {}

        });
        // 设置守护线程
        t.setDaemon(true);

        t.start();

    }
}
