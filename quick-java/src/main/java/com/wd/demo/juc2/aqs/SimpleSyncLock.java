package com.wd.demo.juc2.aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @Author: wangd
 * @Date: 2023/5/9 9:30
 */
public class SimpleSyncLock {

    private final Sync sync;

    public SimpleSyncLock() {
        this.sync = new Sync();
    }

    public void lock() {
        sync.acquire(1);
    }

    public void unlock() {
        sync.release(1);
    }

    private static class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected boolean tryAcquire(int arg) {
            return compareAndSetState(0, 1);
        }

        @Override
        protected boolean tryRelease(int arg) {
            setState(0);
            return true;
        }

        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }
    }

    static class Data {
        public int val = 0;
        public void add() {
            val++;
        }
    }

    public static void main(String[] args) {
        Data data = new Data();
        SimpleSyncLock lock = new SimpleSyncLock();
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                lock.lock();
                try {
                    for (int j = 0; j < 10; j++) {
                        // 这是一把简单的非重入锁
                        // lock.lock();
                        // try {
                            data.add();
                        // } finally {
                        //     lock.unlock();
                        // }
                    }
                    System.out.println(Thread.currentThread().getName() + " data.val = " + data.val);
                } finally {
                    lock.unlock();
                }
            }, String.valueOf(i+1)).start();
        }
    }
}
