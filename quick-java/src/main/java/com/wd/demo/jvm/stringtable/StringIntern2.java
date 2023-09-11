package com.wd.demo.jvm.stringtable;

/**
 * @Author: wangd
 * @Date: 2023/5/17 16:41
 */
public class StringIntern2 {
    public static void main(String[] args) {
        String s1 = new StringBuilder("58").append("tongcheng").toString();
        System.out.println(s1 == s1.intern());

        String s2 = new StringBuilder("ja").append("va").toString();
        System.out.println(s2 == s2.intern());


    }
}
