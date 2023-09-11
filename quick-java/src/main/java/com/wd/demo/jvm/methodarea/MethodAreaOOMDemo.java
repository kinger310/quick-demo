package com.wd.demo.jvm.methodarea;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author: wangd
 * @Date: 2023/5/12 14:03
 *
 *  -XX:MetaspaceSize=10m -XX:MaxMetaspaceSize=10m
 */
public class MethodAreaOOMDemo {
    public static void main(String[] args) {
        for (;;) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) ->
                    methodProxy.invokeSuper(o, objects));
            enhancer.create();
        }
    }

    static class OOMObject {
    }
}
