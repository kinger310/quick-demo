package com.wd.demo.design.p05_decorator.interceptor;

/**
 * @Author: wangd
 * @Date: 2023/4/28 15:21
 */
public class SSOInterceptor implements HandleInterceptor{


    @Override
    public boolean preHandle(String request, String response, Object handler) {
        // 模拟获取cookie
        String ticket = request.substring(1, 8);
        // 模拟校验
        return ticket.equals("success");
    }
}
