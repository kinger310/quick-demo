package com.wd.demo.spring6.iocxml.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: wangd
 * @Date: 2023/6/1 18:07
 */
public class UserTest {
    @Test
    public void testSetter() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean-lifecycle.xml");
        User user = context.getBean("user", User.class);
        System.out.println(user);
        user.run();

        context.close();
    }
}
