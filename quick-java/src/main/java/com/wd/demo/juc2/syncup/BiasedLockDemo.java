package com.wd.demo.juc2.syncup;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * @Author: wangd
 * @Date: 2023/5/8 15:19
 */
public class BiasedLockDemo {

    //  java -XX:BiasedLockingStartupDelay=0 关闭延迟开启

    // -XX:-UseBiasedLocking
    byte b;
    boolean flag;

    char c;
    short s;

    int i;
    float f;

    long l;
    double d;

    static final Object o = new Object();


    public static void main(String[] args) {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // 当一个对象已经计算过一致性hashcode，它就再也无法进入到偏向锁状态了。
        // System.out.println(o.hashCode());
        synchronized (o) {
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
        // BiasedLockDemo lockDemo = new BiasedLockDemo();
        // System.out.println(ClassLayout.parseInstance(lockDemo).toPrintable());
    }

}
