package com.wd.demo.jvm.classes;

import java.util.Random;

/**
 * @Author: wangd
 * @Date: 2023/5/30 22:10
 */
public class InitializationTest {
    public static final int INT_CONSTANT = 10;                                // 在链接阶段的准备环节赋值
    public static final int NUM1 = new Random().nextInt(10);                  // 在初始化阶段clinit>()中赋值
    public static int a = 1;                                                  // 在初始化阶段<clinit>()中赋值
    public static final Integer I = 10000;

    public static final Integer INTEGER_CONSTANT1 = Integer.valueOf(100);     // 在初始化阶段<clinit>()中赋值
    public static Integer INTEGER_CONSTANT2 = Integer.valueOf(100);           // 在初始化阶段<clinit>()中概值

    public static final String s0 = "helloworld0";                            // 在链接阶段的准备环节赋值
    public static final String s1 = new String("helloworld1");                // 在初始化阶段<clinit>()中赋值
    public static String s2 = "hellowrold2";                                  // 在初始化阶段<clinit>()中赋值
}
