package com.wd.demo.design.p05_decorator.bad;

import com.wd.demo.design.p05_decorator.interceptor.SSOInterceptor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: wangd
 * @Date: 2023/4/28 15:28
 */
public class BadLoginSsoDecorator extends SSOInterceptor {
    private static Map<String, String> authMap = new ConcurrentHashMap<String, String>();

    static {
        authMap.put("huahua", "queryUserInfo");
        authMap.put("doudou", "queryUserInfo");
    }

    @Override
    public boolean preHandle(String request, String response, Object handler) {

        // 模拟获取cookie
        String ticket = request.substring(1, 8);
        // 模拟校验
        boolean success = ticket.equals("success");

        if (!success) return false;

        String userId = request.substring(8);
        String method = authMap.get(userId);

        // 模拟方法校验
        return "queryUserInfo".equals(method);
    }

    public static void main(String[] args) {
        BadLoginSsoDecorator ssoDecorator = new BadLoginSsoDecorator();
        String request = "1successhuahua";
        boolean success = ssoDecorator.preHandle(request, "ewcdqwt40liuiu", "t");
        System.out.println("登录校验：" + request + (success ? " 放行" : " 拦截"));
    }
}
