package com.wd.demo.spring6.iocannotation.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @Author: wangd
 * @Date: 2023/6/1 18:15
 */
@Component
public class MyBeanPostProcessor1 implements InstantiationAwareBeanPostProcessor {
    // @Override
    // public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
    //     System.out.println("3 Bean后置处理器  初始化之前执行");
    //     return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    // }
    //
    // @Override
    // public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    //     System.out.println("5 Bean后置处理器  初始化之后执行");
    //     return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    // }


    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        System.out.println("1-0 调用无参构造器之前");
        return InstantiationAwareBeanPostProcessor.super.postProcessBeforeInstantiation(beanClass, beanName);
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        System.out.println("1-2 调用无参构造器之后");
        return InstantiationAwareBeanPostProcessor.super.postProcessAfterInstantiation(bean, beanName);
    }
}
