package com.wd.demo.juc2.threadlocal;

import org.checkerframework.checker.units.qual.C;

import java.lang.reflect.Field;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @Author: wangd
 * @Date: 2023/5/7 15:19
 *
 */
public class ThreadLocalDemo {

    static class House {
        int saleCount = 0;
        public synchronized void sale() {
            saleCount ++;
        }

        ThreadLocal<Integer> saleLocal = ThreadLocal.withInitial(() -> 0);

        public void saleThreadLocal() {
            saleLocal.set(saleLocal.get() + 1);
        }

    }

    public static void main(String[] args) throws InterruptedException {
        House house = new House();
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                int size = new Random().nextInt(5) + 1;
                System.out.println("size = " + size);
                try {
                    for (int j = 0; j < size; j++) {
                        house.sale();
                        house.saleThreadLocal();
                    }
                    ThreadLocal<Integer> val = house.saleLocal;

                    // Field threadLocalHashCode = val.getClass().getSuperclass().getDeclaredField("threadLocalHashCode");
                    // threadLocalHashCode.setAccessible(true);
                    // System.out.println("threadLocals.get() = " + threadLocalHashCode.get(val));
                    //
                    // Thread thread = Thread.currentThread();
                    // Field threadLocals = thread.getClass().getDeclaredField("threadLocals");
                    // threadLocals.setAccessible(true);
                    // Object o = threadLocals.get(thread);
                    //
                    // System.out.println(Thread.currentThread().getName() +" 号线程的threadLocals HashCode = " +  o.hashCode());

                    Thread.sleep(100);

                    System.out.println(Thread.currentThread().getName() + "号销售" + house.saleLocal.get());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    house.saleLocal.remove();
                }
                countDownLatch.countDown();
            }, String.valueOf(i)).start();

        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "销售共卖出 " + house.saleCount);
    }

}
