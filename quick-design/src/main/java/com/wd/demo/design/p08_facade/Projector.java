package com.wd.demo.design.p08_facade;

/**
 * @Author: wangd
 * @Date: 2023/4/28 17:20
 */
public class Projector {
    private static Projector instance = new Projector();

    public Projector() {
    }

    public static Projector getInstance() {
        return instance;
    }

    public void on() {
        System.out.println(" Projector on ");
    }

    public void off() {
        System.out.println(" Projector ff ");
    }

    public void focus() {
        System.out.println(" Projector is Projector  ");
    }
}
