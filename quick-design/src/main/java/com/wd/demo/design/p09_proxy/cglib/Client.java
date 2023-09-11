package com.wd.demo.design.p09_proxy.cglib;

/**
 * @Author: wangd
 * @Date: 2023/5/1 11:37
 */
public class Client {
    public static void main(String[] args) {
        SuperMan superMan = new SuperMan();
        SuperMan proxyInstance = (SuperMan) new ProxyFactory(superMan).getProxyInstance();
        proxyInstance.eat("sandwich");
    }
}
