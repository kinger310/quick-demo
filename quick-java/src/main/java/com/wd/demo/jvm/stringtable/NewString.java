package com.wd.demo.jvm.stringtable;

/**
 * @Author: wangd
 * @Date: 2023/6/2 17:48
 */
public class NewString {
    static Object o;
    public static void main(String[] args) {
        o = new Object();
        String a = "abc";
        String s = new String("abc");

    }
}
