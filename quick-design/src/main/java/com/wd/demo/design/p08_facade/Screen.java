package com.wd.demo.design.p08_facade;

/**
 * @Author: wangd
 * @Date: 2023/4/28 17:20
 */
public class Screen {
    private static Screen instance = new Screen();

    public Screen() {
    }

    public static Screen getInstance() {
        return instance;
    }

    public void up() {
        System.out.println(" Screen up ");
    }

    public void down() {
        System.out.println(" Screen down ");
    }
}
