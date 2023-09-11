package com.wd.demo.design.p08_facade;

/**
 * @Author: wangd
 * @Date: 2023/4/28 17:21
 */
public class DVDPlayer {
    private static DVDPlayer instance = new DVDPlayer();

    public DVDPlayer() {
    }

    public static DVDPlayer getInstanc() {
        return instance;
    }

    public void on() {
        System.out.println(" dvd on ");
    }

    public void off() {
        System.out.println(" dvd off ");
    }

    public void play() {
        System.out.println(" dvd is playing ");
    }

    public void pause() {
        System.out.println(" dvd pause ..");
    }
}
