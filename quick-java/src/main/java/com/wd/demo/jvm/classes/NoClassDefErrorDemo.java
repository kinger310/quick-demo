package com.wd.demo.jvm.classes;

/**
 * @Author: wangd
 * @Date: 2023/5/31 16:12
 */
public class NoClassDefErrorDemo {
    public static void main(String[] args) {
        try {
            int t = App2.i;
        } catch (Exception e) {

        }

        // App2 app2 = new App2();
    }
}

class App2 {
    static {
        System.out.println("App2 初始化");
    }
    public static final int i = 1/0;
}
