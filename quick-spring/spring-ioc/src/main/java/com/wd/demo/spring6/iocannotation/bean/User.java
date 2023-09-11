package com.wd.demo.spring6.iocannotation.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Author: wangd
 * @Date: 2023/6/1 22:47
 */
@Component
public class User {

    public User() {
        System.out.println("User无参构造");
    }


}
