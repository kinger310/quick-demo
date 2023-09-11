package com.wd.demo.reflect;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author: wangd
 * @Date: 2023/4/28 23:08
 */
public class ReflectionDemo02Test {

    @Test
    public void testWithReflection() throws InstantiationException, IllegalAccessException {
        Class<Person> clazz = Person.class;
        Person person = clazz.newInstance();
        System.out.println("person = " + person);
    }

}
