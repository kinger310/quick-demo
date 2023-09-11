package com.wd.demo.jvm.classloader;

/**
 * @Author: wangd
 * @Date: 2023/5/11 13:18
 */
public class ClassInitDemo2 {

    // 没有类变量，字节码中没有clinit
    private int i = 1;

    public static void main(String[] args) {
    }
}
