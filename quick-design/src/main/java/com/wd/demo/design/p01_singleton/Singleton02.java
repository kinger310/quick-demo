package com.wd.demo.design.p01_singleton;

/**
 * 懒汉式
 * 线程不安全
 */
public class Singleton02 {

    private static Singleton02 INSTANCE;

    private Singleton02() {}

    public static Singleton02 getInstance() {
        if (INSTANCE == null) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            INSTANCE = new Singleton02();
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            Thread t = new Thread(() -> System.out.println(Singleton02.getInstance().hashCode()));
            t.start();
        }
    }


}
