package com.wd.demo.design.p05_decorator;

import com.wd.demo.design.p05_decorator.interceptor.HandleInterceptor;
import com.wd.demo.design.p05_decorator.interceptor.SSOInterceptor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: wangd
 * @Date: 2023/4/28 15:28
 */
public class LoginSsoDecorator extends SsoDeractor {
    private static Map<String, String> authMap = new ConcurrentHashMap<String, String>();

    static {
        authMap.put("huahua", "queryUserInfo");
        authMap.put("doudou", "queryUserInfo");
    }

    public LoginSsoDecorator(HandleInterceptor handleInterceptor) {
        super(handleInterceptor);
    }

    @Override
    public boolean preHandle(String request, String response, Object handler) {
        boolean success = super.preHandle(request, response, handler);

        if (!success) return false;

        String userId = request.substring(8);
        String method = authMap.get(userId);

        // 模拟方法校验
        return "queryUserInfo".equals(method);
    }

    public static void main(String[] args) {
        // 过将原有单点登录类new SsoInterceptor()传递给装饰器，让装饰器可以执行扩充的功能
        LoginSsoDecorator ssoDecorator = new LoginSsoDecorator(new SSOInterceptor());
        String request = "1successhuahua";
        boolean success = ssoDecorator.preHandle(request, "ewcdqwt40liuiu", "t");
        System.out.println("登录校验：" + request + (success ? " 放行" : " 拦截"));
    }
}
