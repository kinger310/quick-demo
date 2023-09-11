package com.wd.demo.spring6.junit4;

import com.wd.demo.spring6.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: wangd
 * @Date: 2023/6/3 12:06
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:bean.xml")
public class SpringJunit4Test {
    @Autowired
    User user;
    
    @Test
    public void testUser() {
        System.out.println("user = " + user);
    }
}
