package com.wd.demo.juc.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: wangd
 * @Date: 2023/5/5 17:43
 */
public class BadLockDemo {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        lock.lock();
        int i = 1/0;
        try {
            // int i = 1/0;
            // lock.lock();

            System.out.println("lock.lock() is in try{}");
        } finally {
            lock.unlock();
        }
    }
}
