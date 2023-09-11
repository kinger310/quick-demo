package com.wd.demo.spring6.self.dao;

import com.wd.demo.spring6.self.anno.WangBean;

/**
 * @Author: wangd
 * @Date: 2023/6/1 21:15
 */
@WangBean
public class UserDaoImpl implements UserDao {

    @Override
    public void run() {
        System.out.println("UserDao run");
    }

}
