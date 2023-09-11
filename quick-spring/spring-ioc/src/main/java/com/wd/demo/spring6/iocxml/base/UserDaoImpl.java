package com.wd.demo.spring6.iocxml.base;

/**
 * @Author: wangd
 * @Date: 2023/6/1 14:12
 */
public class UserDaoImpl implements UserDao{
    @Override
    public void run() {
        System.out.println("UserDaoImpl");
    }
}
