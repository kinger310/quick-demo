package com.wd.demo.design.p01_singleton;

/**
 * 饿汉式
 * 线程安全，缺点，程序启动时即加载
 */
public class Singleton01 {

    private static final Singleton01 INSTANCE = new Singleton01();

    private Singleton01() {}

    public static Singleton01 getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        Singleton01 m1 = Singleton01.getInstance();
        Singleton01 m2 = Singleton01.getInstance();
        System.out.println(m1 == m2);
    }


}
