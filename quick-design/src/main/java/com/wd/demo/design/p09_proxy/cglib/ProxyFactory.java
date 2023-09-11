package com.wd.demo.design.p09_proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author: wangd
 * @Date: 2023/5/1 11:30
 */
public class ProxyFactory implements MethodInterceptor {
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    public Object getProxyInstance() {
        // 1. 创建工具类
        Enhancer enhancer = new Enhancer();
        // 2. 设置目标类为父类
        enhancer.setSuperclass(target.getClass());
        // 3. 设置回调函数
        enhancer.setCallback(this);
        // 4. 创建子类对象，即代理对象
        return enhancer.create();
    }





    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib 代理模式开始。。。");
        Object res = method.invoke(target, args);
        System.out.println("cglib 代理模式结束。。。");
        return res;
    }


}
