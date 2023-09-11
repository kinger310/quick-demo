package com.wd.demo.design.p05_decorator.interceptor;

/**
 * @Author: wangd
 * @Date: 2023/4/28 15:20
 */
public interface HandleInterceptor {

    boolean preHandle(String request , String response, Object handler);

}
