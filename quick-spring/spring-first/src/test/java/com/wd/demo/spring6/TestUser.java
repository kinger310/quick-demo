package com.wd.demo.spring6;

import org.junit.jupiter.api.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.InvocationTargetException;


/**
 * @Author: wangd
 * @Date: 2023/5/31 21:59
 */
public class TestUser {

    private static final Logger log = LoggerFactory.getLogger(TestUser.class);

    @Test
    public void testUserObject() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        User user1 = (User) context.getBean("user");
        System.out.println(user1);

        User user2 = (User) context.getBean("user");
        System.out.println(user2);

        user1.add();

        log.info("hi");
    }

    // 反射创建对象
    @Test
    public void testUserObj2() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> clazz = Class.forName("com.wd.demo.spring6.User");
        User user = (User) clazz.getDeclaredConstructor().newInstance();
        System.out.println(user);

    }
}
