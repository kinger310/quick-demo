package com.wd.demo.juc2.cas;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @Author: wangd
 * @Date: 2023/5/7 11:20
 */
public class ABADemo {

    static AtomicInteger atomicInteger = new AtomicInteger(100);

    static AtomicStampedReference<Integer> stampedReference = new AtomicStampedReference<>(100, 1);

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            stampedReference.compareAndSet(100, 101,
                    stampedReference.getStamp(), stampedReference.getStamp()+1);
            stampedReference.compareAndSet(101, 100,
                    stampedReference.getStamp(), stampedReference.getStamp()+1);

        }).start();
        new Thread(() -> {
            int i = stampedReference.getReference(), stamp = stampedReference.getStamp();
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(stampedReference.compareAndSet(i, 2022, stamp, stamp+1)+"\t"+stampedReference.getReference());

        }).start();

    }

    public static void abaHappens(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            atomicInteger.compareAndSet(100, 101);
            atomicInteger.compareAndSet(101, 100);

        }).start();
        new Thread(() -> {
            int i = atomicInteger.get();
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(atomicInteger.compareAndSet(i, 2022)+"\t"+atomicInteger.get());

        }).start();


    }


}
