package com.wd.demo.jvm.stack;

/**
 * @Author: wangd
 * @Date: 2023/5/10 16:28
 */
public class StackRefDemo {

    public static void swap(int x, int y) {
        int tmp = x;
        x = y;
        y = tmp;
    }

    public static void main(String[] args) {
        int x = 1, y = 2;
        swap(x, y);
        System.out.println("x = " + x + " y = " + y);
    }

}
