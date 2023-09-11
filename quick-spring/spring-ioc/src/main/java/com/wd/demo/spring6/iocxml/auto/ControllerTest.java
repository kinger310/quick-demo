package com.wd.demo.spring6.iocxml.auto;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: wangd
 * @Date: 2023/6/1 21:19
 */
public class ControllerTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-auto.xml");
        UserController controller = context.getBean("userController", UserController.class);
        controller.run();

    }
}
