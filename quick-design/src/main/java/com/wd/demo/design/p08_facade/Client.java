package com.wd.demo.design.p08_facade;

/**
 * @Author: wangd
 * @Date: 2023/4/28 17:22
 */
public class Client {

    public static void main(String[] args) {
        HomeTheaterFacade facade = new HomeTheaterFacade();
        System.out.println("================start===========");
        facade.ready();
        System.out.println("================play===========");
        facade.play();
        System.out.println("================pause===========");
        facade.pause();
        System.out.println("================end===========");
        facade.end();
    }
}
