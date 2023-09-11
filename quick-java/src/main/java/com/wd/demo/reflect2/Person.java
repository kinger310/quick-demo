package com.wd.demo.reflect2;

import java.util.List;

/**
 * @Author: wangd
 * @Date: 2023/4/29 15:56
 */
@MyAnnotation(value = "hi")
public class Person extends Creature<String>
        implements Comparable<String>, MyInterface {
    String[] presents;
    private String name;
    int age;
    public int id;


    @Override
    public int compareTo(String o) {
        return 0;
    }

    @MyAnnotation(value="abc")
    private Person(String name){
        this.name = name;
    }

    Person(String name,int age){
        this.name = name;
        this.age = age;
    }
    public Person() {}

    @Override
    public void info() {
        System.out.println("我是一个人");
    }

    @MyAnnotation
    private String show(String nation){
        System.out.println("我的国籍是：" + nation);
        return nation;
    }

    public String display(String interests,int age) throws NullPointerException,ClassCastException{
        return interests + age;
    }

    private static void showDesc(){
        System.out.println("我是一个可爱的人");
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id=" + id +
                '}';
    }


}
