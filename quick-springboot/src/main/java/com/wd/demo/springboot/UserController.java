package com.wd.demo.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wangd
 * @Date: 2023/6/18 11:58
 */
@RestController
public class UserController {

    @Autowired B b;


    @GetMapping("hi")
    public User hi() {
        b.run();
        System.out.println("hello");
        return new User("wd");
    }
}
