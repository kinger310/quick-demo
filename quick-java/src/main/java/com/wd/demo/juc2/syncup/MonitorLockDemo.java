package com.wd.demo.juc2.syncup;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * @Author: wangd
 * @Date: 2023/5/8 15:19
 */
public class MonitorLockDemo {

    //  java -XX:BiasedLockingStartupDelay=0 关闭延迟开启

    // -XX:-UseBiasedLocking

    static Object o = new Object();

    public static void main(String[] args) {


        new Thread(() ->{
            synchronized (o) {
                System.out.println(ClassLayout.parseInstance(o).toPrintable());
            }
        }).start();

        new Thread(() ->{
            synchronized (o) {
                System.out.println(ClassLayout.parseInstance(o).toPrintable());
            }
        }).start();
    }

}
