package com.wd.demo.juc2.threadlocal;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: wangd
 * @Date: 2023/6/13 21:37
 */
public class ThreadLocalOutOfMemoryTest {
    static class LocalVariable {
        // 总共有5M
        private byte[] local = new byte[1024 * 1024 * 5];
    }

    // (1)创建了一个核心线程数和最大线程数为 6 的线程池，这个保证了线程池里面随时都有 6 个线程在运行
    final static ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(6, 6, 1, TimeUnit.MINUTES,
            new LinkedBlockingQueue<>());
    // (2)创建了一个 ThreadLocal 的变量，泛型参数为 LocalVariable，LocalVariable 内部是一个 Long 数组
    static ThreadLocal<LocalVariable> localVariable = new ThreadLocal<LocalVariable>();

    public static void main(String[] args) throws InterruptedException {
        // (3)向线程池里面放入 50 个任务
        for (int i = 0; i < 50; ++i) {
            poolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    // (4) 往threadLocal变量设置值
                    LocalVariable localVariable = new LocalVariable();
                    // 会覆盖
                    ThreadLocalOutOfMemoryTest.localVariable.set(localVariable);
                    // (5) 手动清理ThreadLocal
                    System.out.println("thread name end：" + Thread.currentThread().getName() + ", value:" + ThreadLocalOutOfMemoryTest.localVariable.get());
                    // 不remove会有30M，
                    ThreadLocalOutOfMemoryTest.localVariable.remove();

                }
            });

            Thread.sleep(1000);
        }

        // (6)是否让key失效，都不影响。只要持有的线程存在，都无法回收。
        // ThreadLocalOutOfMemoryTest.localVariable = null;
        System.out.println("pool execute over");
    }
}

