package com.wd.demo.spring6.iocannotation.autowired.controller;

import com.wd.demo.spring6.iocannotation.autowired.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @Author: wangd
 * @Date: 2023/6/1 21:14
 */
@Controller
public class UserController {
    @Autowired
    UserService userService;

    public void run() {
        System.out.println("UserController run  ");
        userService.service();
    }

}
