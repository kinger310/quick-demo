package com.wd.demo.spring6.self.bean;

/**
 * @Author: wangd
 * @Date: 2023/6/2 10:14
 */
public interface WangApplicationContext {
    <T> T getBean(Class<T> requiredType);
}
