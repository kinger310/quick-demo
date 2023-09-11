package com.wd.demo.design.p08_facade;

/**
 * @Author: wangd
 * @Date: 2023/4/28 17:20
 */
public class Popcorn {
    private static Popcorn instance = new Popcorn();

    public Popcorn() {
    }

    public static Popcorn getInstance() {
        return instance;
    }

    public void on() {
        System.out.println(" popcorn on ");
    }

    public void off() {
        System.out.println(" popcorn ff ");
    }

    public void pop() {
        System.out.println(" popcorn is poping  ");
    }
}
