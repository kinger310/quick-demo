package com.wd.demo.base;

/**
 * @Author: wangd
 * @Date: 2023/6/10 0:22
 */
public class FinallyTest {
    public static void main(String[] args) {
        int result = test("a");
        System.out.println("result = " + result);
        System.out.println(test("123"));
    }

    private static int test(String s) {
        int i = 0;
        try {
            i = Integer.parseInt(s);
            return i;
        } catch (NumberFormatException e) {
            return i;
        } finally {
            System.out.println("test end");

        }
    }
}
