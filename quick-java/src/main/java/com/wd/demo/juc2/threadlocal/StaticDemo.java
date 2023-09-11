package com.wd.demo.juc2.threadlocal;

import java.math.BigDecimal;

/**
 * @Author: wangd
 * @Date: 2023/5/7 22:11
 */
public class StaticDemo {

    static String a = "haha";


    public static void main(String[] args) {
        StaticDemo sd1 = new StaticDemo();
        StaticDemo sd2 = new StaticDemo();

        System.out.println("sd2.a.hashCode() = " + sd2.a.hashCode());
        System.out.println("sd1.a.hashCode() = " + sd1.a.hashCode());

        int x = BigDecimal.valueOf(Math.pow(2, 32) * 0.6180339887).intValue();
        System.out.println(x + " " + Integer.toHexString(-x));

        final int HASH_INCREMENT = 0x61c88647;

        System.out.println(HASH_INCREMENT);

        Object o = new Object();
        System.out.println(System.identityHashCode(o));
        System.out.println(o.hashCode());


    }
}
