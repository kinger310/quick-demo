package com.wd.demo.juc2.locksupport;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: wangd
 * @Date: 2023/5/8 21:17
 */
public class LockSupportDemo3 {


    public static void main(String[] args) {

        Object o = new Object();

        Thread t1 = new Thread(() -> {
            // try {Thread.sleep(200);} catch (InterruptedException e) {throw new RuntimeException(e);}

            System.out.println(Thread.currentThread().getName() + " come in");
            LockSupport.park(o);
            // System.out.println("t1中断状态：" + Thread.currentThread().isInterrupted());
            System.out.println("t1中断状态：" + Thread.interrupted());
            System.out.println(Thread.currentThread().getName() + "被唤醒");

            LockSupport.park(o);
            System.out.println("t1中断状态：" + Thread.interrupted());
            System.out.println(Thread.currentThread().getName() + "被二次唤醒");

            // LockSupport.park(o);
            // System.out.println("t1中断状态：" + Thread.interrupted());
            // System.out.println(Thread.currentThread().getName() + "被三次唤醒");
        }, "t1");
        t1.start();

        new Thread(() ->{
            // try {Thread.sleep(100);} catch (InterruptedException e) {throw new RuntimeException(e);}
            // 中断也可以使park继续执行
            // t1.interrupt();
            LockSupport.unpark(t1);
            System.out.println(Thread.currentThread().getName() + "通知");

            // try {Thread.sleep(100);} catch (InterruptedException e) {throw new RuntimeException(e);}
            LockSupport.unpark(t1);

            // try {Thread.sleep(100);} catch (InterruptedException e) {throw new RuntimeException(e);}
            LockSupport.unpark(t1);
        }, "t2").start();


    }
}
