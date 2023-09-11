package com.wd.demo.jvm.classes;

/**
 * @Author: wangd
 * @Date: 2023/5/29 11:29
 */
public class GenManyFileds {

    public static void main(String[] args) {
        // LocalVariableTable
        String s = new StringBuilder("ja").append("va").toString();
        System.out.println(s == s.intern());

        // for (int i = 1; i <= 65536; i++) {
        //     System.out.println(String.format("private int i_%d;",  i));
        // }
    }
}
