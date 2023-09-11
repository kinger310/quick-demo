package com.wd.demo.design.p01_singleton;

/**
 * @Author: wangd
 * @Date: 2023/4/27 20:45
 */
public class Singleton07 {


    static class Singleton07Holder {
        private static Singleton07 INSTANCE = new Singleton07();
    }

    public static Singleton07 getInstance() {
        return Singleton07Holder.INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            Thread t = new Thread(() -> System.out.println(Singleton07.getInstance().hashCode()));
            t.start();
        }
    }
}
