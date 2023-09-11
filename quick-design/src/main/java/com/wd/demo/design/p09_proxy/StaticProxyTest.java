package com.wd.demo.design.p09_proxy;

/**
 * 静态代理：代理类和被代理类在编译期间就确定下来
 * @Author: wangd
 * @Date: 2023/5/1 0:05
 */


interface ClothFactory {
    void produceCloth();
}

class ProxyClothFactory implements ClothFactory{

    private ClothFactory factory; // 用被代理类对象进行实例化

    public ProxyClothFactory(ClothFactory factory) {
        this.factory = factory;
    }

    @Override
    public void produceCloth() {
        System.out.println("代理工厂准备");
        factory.produceCloth();
        System.out.println("代理工厂做一些收尾工作");
    }
}

class NikeClothFactory implements ClothFactory {

    @Override
    public void produceCloth() {
        System.out.println("Nike工厂生产一批服装");
    }
}

public class StaticProxyTest {
    public static void main(String[] args) {
        ClothFactory nike = new NikeClothFactory();
        ProxyClothFactory proxyClothFactory = new ProxyClothFactory(nike);
        proxyClothFactory.produceCloth();
    }
}
