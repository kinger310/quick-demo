package com.wd.demo.juc2.locksupport;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: wangd
 * @Date: 2023/5/8 21:17
 */
public class LockSupportDemo2 {


    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(() ->{
            System.out.println(Thread.currentThread().getName() + " come in");
            // try {Thread.sleep(100);} catch (InterruptedException e) {throw new RuntimeException(e);}
            lock.lock();
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    lock.unlock();
                }
            System.out.println(Thread.currentThread().getName() + "被唤醒");
            // }
        }, "t1").start();

        try {Thread.sleep(100);} catch (InterruptedException e) {throw new RuntimeException(e);}

        new Thread(() ->{
            lock.lock();
            try {
                condition.signal();
                System.out.println(Thread.currentThread().getName() + "通知");
            } finally {
                lock.unlock();
            }
        }, "t2").start();


    }
}
