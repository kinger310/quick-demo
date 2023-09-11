package com.wd.demo.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: wangd
 * @Date: 2023/6/29 10:16
 */
@Component
public class B {
    @Autowired
    A a;

    public void run() {
        System.out.println("run B");
    }
}
