package com.wd.demo.juc.sync;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: wangd
 * @Date: 2023/5/3 22:20
 */
public class SyncLockDemo {

    public synchronized void m1() {
        m1();
    }

    public static void main(String[] args) {
        Object obj = new Object();

        Lock lock = new ReentrantLock();

        new Thread(() -> {
            synchronized (obj) {
                System.out.println(Thread.currentThread().getName() + " 外层");
                synchronized (obj) {
                    System.out.println(Thread.currentThread().getName() + " 内层");
                }
            }
        }, "t1").start();

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " 外层");
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + " 内层");
                } finally {
                    lock.unlock();
                }
            } finally {
                lock.unlock();
            }
        }, "t2").start();
    }
}
