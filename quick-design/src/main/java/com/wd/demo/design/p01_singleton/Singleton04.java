package com.wd.demo.design.p01_singleton;

/**
 * 懒汉式  错误的加锁
 * 缺点：锁粒度小，不能保护线程竞争
 */
public class Singleton04 {

    private static Singleton04 INSTANCE;

    private Singleton04() {}

    public static Singleton04 getInstance() {
        if (INSTANCE == null) {
            synchronized (Singleton04.class) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                INSTANCE = new Singleton04();
            }
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            Thread t = new Thread(() -> System.out.println(Singleton04.getInstance().hashCode()));
            t.start();
        }
    }


}
