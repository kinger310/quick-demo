package com.wd.demo.spring6.iocxml.factorybean;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: wangd
 * @Date: 2023/6/1 18:07
 */
public class UserTest {
    @Test
    public void testSetter() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-factorybean.xml");
        User user = context.getBean("user", User.class);
        System.out.println(user);

    }
}
