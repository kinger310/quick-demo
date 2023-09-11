package com.wd.demo.design.p01_singleton;

/**
 * @Author: wangd
 * @Date: 2023/4/27 20:45
 */
public enum Singleton08 {
    INSTANCE;
    public static Singleton08 getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            Thread t = new Thread(() -> System.out.println(Singleton08.getInstance().hashCode()));
            t.start();
        }
    }
}
