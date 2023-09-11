package com.wd.demo.design.p01_singleton;

/**
 * 懒汉式  双重
 * 缺点：锁粒度小，不能保护线程竞争
 */
public class Singleton05 {

    private static Singleton05 INSTANCE;

    private Singleton05() {}

    public static Singleton05 getInstance() {
        if (INSTANCE == null) {
            synchronized (Singleton05.class) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                INSTANCE = new Singleton05();
            }
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            Thread t = new Thread(() -> System.out.println(Singleton05.getInstance().hashCode()));
            t.start();
        }
    }


}
