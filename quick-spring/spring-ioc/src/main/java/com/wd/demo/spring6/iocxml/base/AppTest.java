package com.wd.demo.spring6.iocxml.base;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: wangd
 * @Date: 2023/6/1 13:49
 */
public class AppTest {

    @Test
    public void testId() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        User user = (User) context.getBean("user");
        System.out.println("user = " + user);
    }

    @Test
    public void testClass() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        User user = context.getBean(User.class);
        System.out.println("user = " + user);
    }

    @Test
    public void testIdAndClass() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        User user = context.getBean("user", User.class);
        System.out.println("user = " + user);
    }

    @Test
    public void testUserDao() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        UserDao userDao = context.getBean(UserDao.class);
        System.out.println("userDao = " + userDao);
    }

}
