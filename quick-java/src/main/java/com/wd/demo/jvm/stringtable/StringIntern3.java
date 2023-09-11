package com.wd.demo.jvm.stringtable;

/**
 * @Author: wangd
 * @Date: 2023/5/17 16:41
 */
public class StringIntern3 {
    public static void main(String[] args) {
        String s = "hello";
        // String s1 = s.substring(1, 3);
        // s1.intern();
        // String s2 = "el";
        // System.out.println(s1 == s2);

        String s3 = s.substring(1, 3).intern();
        System.out.println("el" == s3);
    }
}
