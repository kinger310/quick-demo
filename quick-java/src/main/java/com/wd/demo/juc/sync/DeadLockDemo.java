package com.wd.demo.juc.sync;

/**
 * @Author: wangd
 * @Date: 2023/5/3 22:37
 */
public class DeadLockDemo {

    public static void main(String[] args) {
        Object a = new Object();
        Object b = new Object();
        new Thread(() -> {
            synchronized (a) {
                System.out.println(Thread.currentThread().getName() + "持有锁a，试图获取锁b");
                synchronized (b) {
                    System.out.println("持有锁b");
                }
            }
        }, "aa").start();

        new Thread(() -> {
            synchronized (b) {
                System.out.println(Thread.currentThread().getName() + "持有锁b，试图获取锁a");
                synchronized (a) {
                    System.out.println("持有锁b");
                }
            }
        }, "bb").start();
    }
}
