package com.wd.demo.design.p08_facade;

/**
 * @Author: wangd
 * @Date: 2023/4/28 17:20
 */
public class Stereo {
    private static Stereo instance = new Stereo();

    public Stereo() {
    }

    public static Stereo getInstance() {
        return instance;
    }

    public void on() {
        System.out.println(" Stereo on ");
    }

    public void off() {
        System.out.println(" Screen off ");
    }

    public void up() {
        System.out.println(" Screen up.. ");
    }
}
