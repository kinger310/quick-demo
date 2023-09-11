package com.wd.demo.jvm.classloader;

/**
 * @Author: wangd
 * @Date: 2023/5/11 13:18
 */
public class ClassInitDemo {

    private static int i = 1;

    static {
        i = 2;
        j = 20;
        System.out.println(i);
        // System.out.println(j);
        // Illegal forward reference
    }

    private static int j = 10;
    // linking之prepare: number = 0, -> initial: 20 --> 10

    public static void main(String[] args) {
        System.out.println("num = " + i);
        System.out.println("j = " + j);
    }
}
