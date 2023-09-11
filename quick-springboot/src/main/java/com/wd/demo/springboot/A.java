package com.wd.demo.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: wangd
 * @Date: 2023/6/29 10:15
 */
@Component
public class A {
    @Autowired
    B b;
}
