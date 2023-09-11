package com.wd.demo.spring6.iocannotation.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: wangd
 * @Date: 2023/6/1 18:07
 */
public class User2Test {
    @Test
    public void testSetter() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean-anno.xml");
        User2 user = context.getBean(User2.class);
        System.out.println("user = " + user);
        user.run();

        context.close();
    }
}
