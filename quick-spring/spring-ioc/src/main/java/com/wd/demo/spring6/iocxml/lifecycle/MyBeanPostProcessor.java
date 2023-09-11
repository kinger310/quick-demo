package com.wd.demo.spring6.iocxml.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @Author: wangd
 * @Date: 2023/6/1 18:15
 */
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("3 Bean后置处理器  初始化之前执行");
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("5 Bean后置处理器  初始化之后执行");
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
