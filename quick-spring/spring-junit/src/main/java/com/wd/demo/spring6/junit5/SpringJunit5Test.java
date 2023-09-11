package com.wd.demo.spring6.junit5;

import com.wd.demo.spring6.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * @Author: wangd
 * @Date: 2023/6/3 12:06
 */
@SpringJUnitConfig(locations = "classpath:bean.xml")
public class SpringJunit5Test {
    @Autowired
    User user;
    
    @Test
    public void testUser() {
        System.out.println("user = " + user);
    }
}
