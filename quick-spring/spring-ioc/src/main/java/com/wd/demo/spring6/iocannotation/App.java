package com.wd.demo.spring6.iocannotation;

import com.wd.demo.spring6.iocannotation.autowired.controller.UserController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.wd.demo.spring6.iocannotation.bean.User;

/**
 * @Author: wangd
 * @Date: 2023/6/1 22:49
 */
public class App {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-anno.xml");
        User user = context.getBean(User.class);
        System.out.println("user = " + user);;


        UserController controller = context.getBean(UserController.class);
        System.out.println("controller = " + controller);
        controller.run();
    }
}
