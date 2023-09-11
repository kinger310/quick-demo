package com.wd.demo.juc2.cas;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author: wangd
 * @Date: 2023/5/6 18:24
 *  * 题目：实现一个自旋锁,复习CAS思想
 *  * 自旋锁好处：循环比较获取 没有类似wait的阻塞。
 *  *
 *  * 通过CAS操作完成自旋锁，A线程先进来调用myLock方法自己持有锁5秒钟，B随后进来后发现
 *  * 当前有线程持有锁，所以只能通过自旋等待，直到A释放锁后B随后抢到。
 */
public class SpinLockDemo {
    private AtomicReference<Thread> ar = new AtomicReference<>();

    public void lock() {
        Thread thread = Thread.currentThread();
        for(;;) {
            if (ar.compareAndSet(null, thread)) break;
        }
    }

    public void unlock() {
        Thread thread = Thread.currentThread();
        ar.compareAndSet(thread, null);
    }

    public static void main(String[] args) throws InterruptedException {
        SpinLockDemo spinLock = new SpinLockDemo();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " come in");
            spinLock.lock();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " task over");
            spinLock.unlock();

        }, "AA").start();
        Thread.sleep(500);
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " come in");
            spinLock.lock();
            System.out.println(Thread.currentThread().getName() + " task over");
            spinLock.unlock();
        }, "BB").start();


    }
}

