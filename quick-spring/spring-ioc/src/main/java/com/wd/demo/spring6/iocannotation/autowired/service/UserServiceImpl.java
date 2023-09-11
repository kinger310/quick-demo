package com.wd.demo.spring6.iocannotation.autowired.service;

import com.wd.demo.spring6.iocannotation.autowired.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: wangd
 * @Date: 2023/6/1 21:14
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public void service() {
        System.out.println("UserService run ");
        userDao.run();
    }
}
