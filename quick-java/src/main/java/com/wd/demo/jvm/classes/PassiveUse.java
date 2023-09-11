package com.wd.demo.jvm.classes;

/**
 * @Author: wangd
 * @Date: 2023/5/30 18:07
 */
public class PassiveUse {
    public static void main(String[] args) {
        // System.out.println(Child.num);


        Parent[] parents= new Parent[10];
        System.out.println(parents.getClass());
        // new的话才会初始化
        parents[0] = new Parent();
    }

}

class Child extends Parent {
    static {
        System.out.println("Child类的初始化");
    }
}

class Parent {
    static {
        System.out.println("Parent类的初始化");
    }

    public static int num = 1;
}
