package com.wd.demo.spring6.self;

import com.wd.demo.spring6.self.bean.DiAnnotationApplicationContext;
import com.wd.demo.spring6.self.bean.WangApplicationContext;
import com.wd.demo.spring6.self.service.UserService;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        System.out.println( "Hello World!" );

        WangApplicationContext context = new DiAnnotationApplicationContext("com.wd.demo.spring6.self");
        UserService userService = context.getBean(UserService.class);
        userService.service();

    }
}
