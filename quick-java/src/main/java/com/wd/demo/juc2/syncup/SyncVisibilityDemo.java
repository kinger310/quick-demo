package com.wd.demo.juc2.syncup;

/**
 * @Author: wangd
 * @Date: 2023/5/5 20:15
 */
public class SyncVisibilityDemo {
    public static boolean sign = false;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            int i = 0;
            while (!sign) {
                i = add(i);
            }
            System.out.println("你坏" + i);
        });
        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignore) {
            }
            sign = true;
            System.out.println("vt.sign = true  while (!sign)");
        });
        t1.start();
        t2.start();
    }

    public static synchronized int add(int i) {
        return i + 1;
    }

}
