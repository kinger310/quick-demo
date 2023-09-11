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
public class ReflectionDemo01Test {

    @Test
    public void testWithNoReflection() {
        // 反射之前，对Person的操作
        Person p1 = new Person("wd", 30);
        p1.age = 18;
        System.out.println(p1);
        p1.show();
        // Person类外部，不可以通过Person类的对象调用其内部私有结构
        // 比如name, showNation以及私有的构造器
    }

    @Test
    public void testWithReflection() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        // 反射之后，对Person的操作
        Class<Person> clazz = Person.class;
        // 1. 通过反射创建Person类的对象
        Constructor<Person> constructor = clazz.getConstructor(String.class, int.class);
        Person p = constructor.newInstance("hr", 18);
        System.out.println(p);

        // 2. 通过反射，调用对象指定的属性
        Field age = clazz.getDeclaredField("age");
        age.set(p, 10);

        // 3. 通过反射，调用方法
        Method show = clazz.getDeclaredMethod("show");
        show.invoke(p);
        
        // 4. 通过反射，调用私有构造器，私有属性和私有方法
        Constructor<Person> declaredConstructor = clazz.getDeclaredConstructor(String.class);
        declaredConstructor.setAccessible(true);
        Person p2 = declaredConstructor.newInstance("jerry");
        System.out.println(p2);

        Field name = clazz.getDeclaredField("name");
        name.setAccessible(true);
        name.set(p2, "tom");
        System.out.println(p2);

        Method showNation = clazz.getDeclaredMethod("showNation", String.class);
        showNation.setAccessible(true);
        String nation = (String) showNation.invoke(p2, "中国");
        System.out.println(nation);
    }

    @Test
    public void testGetClass() throws ClassNotFoundException {
        // 方式一 调用运行时类的属性 .class
        Class<Person> clazz1 = Person.class;
        System.out.println(clazz1);

        // 方式二 通过运行时类的对象调用getClass()
        // Person p = new Person();
        // Class clazz2 = p.getClass();
        // System.out.println(clazz2);

        // 方式三， 调用Class的静态方法Class.forName
        Class clazz3 = Class.forName("com.wd.demo.reflect.Person");
        System.out.println(clazz3);

        // 方式四  使用类加载器  ClassLoader
        ClassLoader classLoader = ReflectionDemo01Test.class.getClassLoader();
        Class<?> clazz4 = classLoader.loadClass("com.wd.demo.reflect.Person");
        System.out.println(clazz4);

        // System.out.println(clazz1 == clazz2);
        System.out.println(clazz1 == clazz3);
        System.out.println(clazz1 == clazz4);

    }

}
