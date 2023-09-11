package com.wd.demo.juc2.threadlocal;

import java.lang.ref.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author: wangd
 * @Date: 2023/5/7 18:43
 */
public class ReferenceDemo {

    static class MyObject {
        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("finalize invoked!");
        }
    }


    public static void main(String[] args) throws InterruptedException {
        phantomReference();
    }

    private static void phantomReference() throws InterruptedException {
        // MyObject myObject = new MyObject();  //这里引入了一个强引用。所以加入引用队列会有问题
        ReferenceQueue<MyObject>  referenceQueue = new ReferenceQueue<>();
        PhantomReference<MyObject> phantomReference = new PhantomReference<>(new MyObject(), referenceQueue);

        System.out.println("phantomReference.get() = " + phantomReference.get());

        System.gc();
        Thread.sleep(50);

        List<byte[]> list = new ArrayList<>();

        new Thread(() -> {
            while (true) {
                list.add(new byte[1*1024*1024]);
                System.out.println("list add ok " + phantomReference.get());
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }).start();

        new Thread(() -> {
            while (true) {
                Reference<? extends MyObject> reference = referenceQueue.poll();
                if (reference != null) {
                    System.out.println("有虚对象加入队列中");
                    break;
                }
            }
        }).start();
    }

    private static void weakReference() throws InterruptedException {
        WeakReference<MyObject> weakRef = new WeakReference<>(new MyObject());
        System.gc();
        TimeUnit.MILLISECONDS.sleep(50);
        System.out.println("内存够用，也回收。 weakRef.get() = " + weakRef.get());
    }

    private static void softReference() throws InterruptedException {
        SoftReference<MyObject> softReference = new SoftReference<>(new MyObject());
        System.gc();
        TimeUnit.MILLISECONDS.sleep(50);
        System.out.println("内存够用，不回收。 softReference.get() = " + softReference.get());

        // run with VM options:  -Xms10m -Xmx10m
        try {
            byte[] bytes = new byte[20 * 1024 * 1024]; // 20MB
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            TimeUnit.MILLISECONDS.sleep(50);
            System.out.println("内存不够用，回收。 softReference.get() = " + softReference.get());
        }
    }

    private static void strongReference() throws InterruptedException {
        MyObject o = new MyObject();
        System.out.println("gc before. o = " + o);
        o = null;

        System.gc();
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println("gc after. o = " + o);
    }
}
