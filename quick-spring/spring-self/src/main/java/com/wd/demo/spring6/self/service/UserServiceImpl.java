package com.wd.demo.spring6.self.service;


import com.wd.demo.spring6.self.anno.Di;
import com.wd.demo.spring6.self.anno.WangBean;
import com.wd.demo.spring6.self.dao.UserDao;

/**
 * @Author: wangd
 * @Date: 2023/6/1 21:14
 */
@WangBean
public class UserServiceImpl implements UserService {
    @Di
    private UserDao userDao;

    @Override
    public void service() {
        System.out.println("UserService run ");
        userDao.run();
    }
}
