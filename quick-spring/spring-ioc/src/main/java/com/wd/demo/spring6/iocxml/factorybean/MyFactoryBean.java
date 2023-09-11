package com.wd.demo.spring6.iocxml.factorybean;

import org.springframework.beans.factory.FactoryBean;

/**
 * @Author: wangd
 * @Date: 2023/6/1 18:53
 */
public class MyFactoryBean implements FactoryBean<User> {

    @Override
    public User getObject() throws Exception {
        return new User();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
