package com.wd.demo.spring6.aop.proxy;

import com.wd.demo.spring6.aop.CalculatorImpl;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @Author: wangd
 * @Date: 2023/6/2 21:18
 */
public class CglibProxyFactory implements MethodInterceptor {

    private Object target;

    public CglibProxyFactory(Object target) {
        this.target = target;
    }

    public Object getProxyInstance() {
        return Enhancer.create(target.getClass(), this);
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("[CGLIB动态代理][日志] " + method.getName() + "，参数：" + Arrays.toString(args));
        Object result = method.invoke(target, args);
        System.out.println("[CGLIB动态代理][日志] " + method.getName() + "，结果：" + result);
        return result;
    }

    public static void main(String[] args) {
        CglibProxyFactory cglibProxyFactory = new CglibProxyFactory(new CalculatorImpl());
        CalculatorImpl calculator = (CalculatorImpl) cglibProxyFactory.getProxyInstance();
        calculator.add(4,5);
    }
}
