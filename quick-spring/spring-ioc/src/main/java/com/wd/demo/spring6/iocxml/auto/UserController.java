package com.wd.demo.spring6.iocxml.auto;

/**
 * @Author: wangd
 * @Date: 2023/6/1 21:14
 */
public class UserController {
    UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void run() {
        System.out.println("UserController run  ");
        userService.service();
    }

}
