package com.wd.demo.reflect2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author: wangd
 * @Date: 2023/4/30 10:29
 */
public class Review {

    static class User {
        public void show(String nation) {
            System.out.println("我爱你"+nation);
        }
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException {
        Class<?> clazz = Class.forName("com.wd.demo.reflect2.Review.User");
        User user = (User) clazz.newInstance();
        Method show = clazz.getDeclaredMethod("show", String.class);
        show.setAccessible(true);
        show.invoke(user,"China");
    }
}
