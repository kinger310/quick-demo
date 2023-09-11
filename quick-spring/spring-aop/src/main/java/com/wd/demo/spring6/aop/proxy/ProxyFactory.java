package com.wd.demo.spring6.aop.proxy;

import com.wd.demo.spring6.aop.Calculator;
import com.wd.demo.spring6.aop.CalculatorImpl;

import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @Author: wangd
 * @Date: 2023/6/2 21:06
 */
public class ProxyFactory {
    // 被代理的目标对象
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    public Object getProxy() {
        ClassLoader classLoader = target.getClass().getClassLoader();
        Class<?>[] interfaces = target.getClass().getInterfaces();
        return Proxy.newProxyInstance(classLoader, interfaces, (proxy, method, args) -> {
            System.out.println("[动态代理][日志] "+method.getName()+"，参数："+ Arrays.toString(args));
            Object result = method.invoke(target, args);
            System.out.println("[动态代理][日志] "+method.getName()+"，结果："+ result);
            return result;
        });
    }

    public static void main(String[] args) {
        System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");

        ProxyFactory proxyFactory = new ProxyFactory(new CalculatorImpl());
        // JDK动态代理只能基于接口代理
        // CalculatorImpl calculator = (CalculatorImpl) proxyFactory.getProxy();
        Calculator calculator = (Calculator) proxyFactory.getProxy();
        calculator.add(4,7);
    }
}
