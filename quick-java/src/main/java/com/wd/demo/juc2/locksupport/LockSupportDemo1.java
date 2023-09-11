package com.wd.demo.juc2.locksupport;

/**
 * @Author: wangd
 * @Date: 2023/5/8 21:17
 */
public class LockSupportDemo1 {


    public static void main(String[] args) {
        Object lock = new Object();
        new Thread(() ->{
            System.out.println(Thread.currentThread().getName() + " come in");
            // try {Thread.sleep(100);} catch (InterruptedException e) {throw new RuntimeException(e);}
            // synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + "被唤醒");
            // }
        }, "t1").start();

        try {Thread.sleep(100);} catch (InterruptedException e) {throw new RuntimeException(e);}

        new Thread(() ->{
            // synchronized (lock) {
                lock.notify();
                System.out.println(Thread.currentThread().getName() + "通知");
            // }
        }, "t2").start();


    }
}
