package com.wd.demo.spring6.iocxml.di;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: wangd
 * @Date: 2023/6/1 14:58
 */
public class DITest {

    @Test
    public void testSetter() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-di.xml");
        Book book = context.getBean("book", Book.class);
        System.out.println(book);
    }

    @Test
    public void testConstructor() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-di.xml");
        Book book = context.getBean("bookCon", Book.class);
        System.out.println(book);
    }
}
