package com.wd.demo.jvm.stringtable;

/**
 * @Author: wangd
 * @Date: 2023/5/13 13:38
 */
public class StringIntern1 {
    public static void main(String[] args) {
        String s1 = new String("1") + new String("2");

        // String s2 = "12";
        // s1.intern();

        s1.intern();
        String s2 = "12";
        System.out.println(s1 == s2);
    }

}
