package com.wd.demo.design.p08_facade;

/**
 * @Author: wangd
 * @Date: 2023/4/28 17:20
 */
public class TheaterLight {
    private static TheaterLight instance = new TheaterLight();

    public TheaterLight() {
    }

    public static TheaterLight getInstance() {
        return instance;
    }

    public void on() {
        System.out.println(" TheaterLight on ");
    }

    public void off() {
        System.out.println(" TheaterLight off ");
    }

    public void dim() {
        System.out.println(" TheaterLight dim.. ");
    }

    public void bright() {
        System.out.println(" TheaterLight bright.. ");
    }
}
