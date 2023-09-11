package com.wd.demo.juc2.rwlock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author: wangd
 * @Date: 2023/5/10 10:17
 */
public class LockDowngradingDemo {

    class CachedData {
        int data;
        volatile boolean cacheValid;
        final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
        void processCachedData() {
            rwl.readLock().lock();
            if (!cacheValid) {
                // Must release read lock before acquiring write lock
                rwl.readLock().unlock();
                rwl.writeLock().lock();
                try {
                    // Recheck state because another thread might have
                    // acquired write lock and changed state before we did.
                    if (!cacheValid) {
                        data = 2023;
                        cacheValid = true;
                    }
                    // Downgrade by acquiring read lock before releasing write lock
                    rwl.readLock().lock();
                } finally {
                    rwl.writeLock().unlock(); // Unlock write, still hold read
                }
            }
            try {
                System.out.println(data);;
            } finally {
                rwl.readLock().unlock();
            }
        }
    }
}
