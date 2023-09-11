package com.wd.demo.juc2.locksupport;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

/**
 * @Author: wangd
 * @Date: 2023/5/8 21:42
 */
public class FIFODemo {
    static class FIFOMutex {
        private final AtomicBoolean locked = new AtomicBoolean(false);
        private final Queue<Thread> waiters
                = new ConcurrentLinkedQueue<Thread>();

        public void lock() {
            boolean wasInterrupted = false;
            Thread current = Thread.currentThread();
            waiters.add(current);

            // Block while not first in queue or cannot acquire lock
            while (waiters.peek() != current ||
                    !locked.compareAndSet(false, true)) {
                System.out.println(waiters.size());
                LockSupport.park(this);
                if (Thread.interrupted()) // ignore interrupts while waiting
                    wasInterrupted = true;
            }

            waiters.remove();
            if (wasInterrupted)          // reassert interrupt status on exit
                current.interrupt();
        }

        public void unlock() {
            locked.set(false);
            LockSupport.unpark(waiters.peek());
        }
    }

    static class Data {
        public int val = 0;

        public void add() {
            val++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Data data = new Data();
        FIFOMutex lock = new FIFOMutex();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                lock.lock();
                try {
                    for (int j = 0; j < 100000; j++) {
                        data.add();
                    }
                    System.out.println(Thread.currentThread().getName() + " " + data.val);
                } finally {
                    lock.unlock();
                }
            }, String.valueOf(i+1)).start();
        }

        Thread.sleep(10);
    }

}
