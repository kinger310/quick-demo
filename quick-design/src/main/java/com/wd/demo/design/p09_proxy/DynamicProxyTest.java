package com.wd.demo.design.p09_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 * @Author: wangd
 * @Date: 2023/5/1 0:12
 */


interface Human {
    String getBelief();

    void eat(String food);
}

class SuperMan implements Human {

    @Override
    public String getBelief() {
        return "I believe I can fly";
    }

    @Override
    public void eat(String food) {
        System.out.println("food = " + food);
    }
}

// 要想实现动态代理，要解决两个问题：
// 一，如何根据加载内存中的被代理类，动态创建一个代理类及其对象
// 二、当通过代理类的对象调用方法时，如何动态调用被代理类中的同名方法
class ProxyFactory {
    public static Object getProxyInstance(Object obj) {
        // MyInvocationHandler handler = new MyInvocationHandler();
        // handler.bind(obj);
        return Proxy.newProxyInstance(
                obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    HumanUtil.m1();
                    Object invoke = method.invoke(obj, args);
                    HumanUtil.m2();
                    return invoke;
                }
        );
    }
}

class HumanUtil {
    public static void m1() {
        System.out.println("**********通用方法一**********");
    }

    public static void m2() {
        System.out.println("**********通用方法二**********");
    }
}

class MyInvocationHandler implements InvocationHandler {

    private Object obj; // 需要使用被代理类的对象进行赋值

    public void bind(Object obj) {
        this.obj = obj;
    }

    // 当被代理类要执行方法method的功能就声明于此
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        HumanUtil.m1();
        Object invoke = method.invoke(obj, args);
        HumanUtil.m2();
        return invoke;
    }
}

public class DynamicProxyTest {

    public static void main(String[] args) {
        SuperMan superMan = new SuperMan();
        Human proxyInstance = (Human)ProxyFactory.getProxyInstance(superMan);
        System.out.println(proxyInstance.getBelief());
        proxyInstance.eat("麻辣烫");

        System.out.println("========================");
        NikeClothFactory nikeClothFactory = new NikeClothFactory();
        ClothFactory proxyInstance1 = (ClothFactory) ProxyFactory.getProxyInstance(nikeClothFactory);
        proxyInstance1.produceCloth();
    }

}
