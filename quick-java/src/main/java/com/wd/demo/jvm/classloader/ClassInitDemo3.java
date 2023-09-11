package com.wd.demo.jvm.classloader;

/**
 * @Author: wangd
 * @Date: 2023/5/11 13:18
 */
public class ClassInitDemo3 {

    static class Father {
        public static int a = 1;
        static {
            a = 2;
        }
    }
    
    static class Son extends Father {
        static {
            b = 4;
        }
        public static int b = a;
        
    }

    public static void main(String[] args) {
        System.out.println("Son.b = " + Son.b);
    }
}
