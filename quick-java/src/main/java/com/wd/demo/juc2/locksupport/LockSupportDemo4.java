package com.wd.demo.juc2.locksupport;

import java.util.concurrent.locks.LockSupport;

/**
 * @Author: wangd
 * @Date: 2023/5/8 21:17
 */
public class LockSupportDemo4 {


    public static void main(String[] args) throws InterruptedException {

        Object o = new Object();

        Thread t1 = new Thread(() -> {
            try {Thread.sleep(102);} catch (InterruptedException e) {throw new RuntimeException(e);}

            System.out.println(Thread.currentThread().getName() + " come in");
            LockSupport.park(o);
            // System.out.println("t1中断状态：" + Thread.currentThread().isInterrupted());
            System.out.println(Thread.currentThread().getName() + "被唤醒， AQS赶紧去抢锁");

            System.out.println("非公平锁，t1没抢到锁 又回队列里park了");

            LockSupport.park(o);
            System.out.println(Thread.currentThread().getName() + "被二次唤醒, AQS赶紧去抢锁");

            LockSupport.park(o);
        }, "t1");
        t1.start();

        new Thread(() ->{
            try {Thread.sleep(100);} catch (InterruptedException e) {throw new RuntimeException(e);}
            // 中断也可以使park继续执行
            // t1.interrupt();
            LockSupport.unpark(t1);
            System.out.println(Thread.currentThread().getName() + "一次通知，队头的t1去抢锁啦");

            try {Thread.sleep(3);} catch (InterruptedException e) {throw new RuntimeException(e);}
            System.out.println("去队列里一看队头还是t1. 原来别的线程抢到锁了， t1没抢过人家, 还在队头park着呢");

            LockSupport.unpark(t1);
            System.out.println(Thread.currentThread().getName() + "二次通知");
        }, "t2").start();

        for (;;) {
            System.out.println(t1.getState());
            Thread.sleep(2);
        }


    }
}
