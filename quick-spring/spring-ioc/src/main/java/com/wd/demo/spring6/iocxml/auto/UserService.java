package com.wd.demo.spring6.iocxml.auto;

/**
 * @Author: wangd
 * @Date: 2023/6/1 21:14
 */
public class UserService {
    UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void service() {
        System.out.println("UserService run ");
        userDao.run();
    }
}
