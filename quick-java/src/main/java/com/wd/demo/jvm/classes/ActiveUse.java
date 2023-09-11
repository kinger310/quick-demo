package com.wd.demo.jvm.classes;

/**
 * @Author: wangd
 * @Date: 2023/5/30 18:15
 */
public class ActiveUse {
    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println(User.num);
        System.out.println(User.STRING);

        // Class<?> aClass = Class.forName("com.wd.demo.jvm.classes.User");
    }
}

class User {
    static {
        System.out.println("User类的初始化");
    }
    public static final int num = 2 / 15;

    public static final String STRING = "abc";
    public static final String STRING2 = new StringBuilder("ja").append("va").toString();
}