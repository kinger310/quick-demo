package com.wd.demo.base;

/**
 * @Author: wangd
 * @Date: 2023/6/10 1:06
 */
public class StaticTest {
    public static void main(String[] args) {
        Father p = new Son();
        p.eat();

        Son p2 = null;
        p2.eat();
    }
}

class Father {

    Father () {
        System.out.println("Father init");
    }
    public static void eat() {
        System.out.println("Father eat");
    }

    public void eat2() {
        System.out.println("Father eat");
    }
}

class Son extends Father {
    Son() {
        // super.eat2();
        System.out.println("Son init");
    }

    // 不是重写，静态方法不能被重写，因为静态方法不存在多态性
    public static void eat() {
        System.out.println("Son eat");
    }
}