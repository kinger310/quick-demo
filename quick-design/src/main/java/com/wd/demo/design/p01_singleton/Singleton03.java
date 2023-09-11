package com.wd.demo.design.p01_singleton;

/**
 * 懒汉式  加锁
 * 缺点：锁粒度太大，创建与读取都加了锁，效率低
 */
public class Singleton03 {

    private static Singleton03 INSTANCE;

    private Singleton03() {}

    public static synchronized Singleton03 getInstance() {
        if (INSTANCE == null) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            INSTANCE = new Singleton03();
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            Thread t = new Thread(() -> System.out.println(Singleton03.getInstance().hashCode()));
            t.start();
        }
    }


}
