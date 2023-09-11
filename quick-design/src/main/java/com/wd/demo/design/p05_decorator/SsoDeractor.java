package com.wd.demo.design.p05_decorator;

import com.wd.demo.design.p05_decorator.interceptor.HandleInterceptor;

/**
 * @Author: wangd
 * @Date: 2023/4/28 15:32
 */
public abstract class SsoDeractor implements HandleInterceptor {

    private HandleInterceptor handleInterceptor;

    public SsoDeractor(HandleInterceptor handleInterceptor) {
        this.handleInterceptor = handleInterceptor;
    }

    @Override
    public boolean preHandle(String request, String response, Object handler) {
        return handleInterceptor.preHandle(request, response, handler);
    }
}
