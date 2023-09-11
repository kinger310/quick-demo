package com.wd.demo.spring6.iocannotation.autowired.dao;

import org.springframework.stereotype.Repository;

/**
 * @Author: wangd
 * @Date: 2023/6/1 21:15
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Override
    public void run() {
        System.out.println("UserDao run");
    }

}
