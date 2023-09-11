package com.wd.demo.base;

/**
 * @Author: wangd
 * @Date: 2023/5/5 18:49
 */
public class StringConcatDemo {

    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();
        String str = "hollis";
        for (int i = 0; i < 50000; i++) {
            String s = String.valueOf(i);
            str += s;
        }
        long t2 = System.currentTimeMillis();
        System.out.println("+ cost:" + (t2 - t1));
    }
}
