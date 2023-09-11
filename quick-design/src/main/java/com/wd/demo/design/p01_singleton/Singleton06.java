package com.wd.demo.design.p01_singleton;

/**
 * 懒汉式  双重检查锁
 * 线程安全
 */
public class Singleton06 {

    private static volatile Singleton06 INSTANCE;

    private Singleton06() {}

    public static Singleton06 getInstance() {
        if (INSTANCE == null) {
            synchronized (Singleton06.class) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (INSTANCE == null) {
                    INSTANCE = new Singleton06();
                }
            }
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            Thread t = new Thread(() -> System.out.println(Singleton06.getInstance().hashCode()));
            t.start();
        }
    }


}
