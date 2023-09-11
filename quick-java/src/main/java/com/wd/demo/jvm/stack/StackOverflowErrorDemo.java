package com.wd.demo.jvm.stack;

/**
 * @Author: wangd
 * @Date: 2023/5/10 16:28
 */
public class StackOverflowErrorDemo {

    private static int count = 0;
    public static void foo() {
        // stopped at  6456 when default
        // when set -Xss256k   stopped at 1593
        // then we know the default is -Xss1024k
        System.out.println("count = " + count);

        count++;
        foo();
    }

    public static void main(String[] args) {
        foo();
    }

}
