package com.wd.demo.spring6;

import org.springframework.stereotype.Component;

/**
 * @Author: wangd
 * @Date: 2023/6/3 11:55
 */
@Component
public class User {

    public User() {
        System.out.println("run User");
    }
}
