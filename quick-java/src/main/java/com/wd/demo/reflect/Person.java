package com.wd.demo.reflect;

/**
 * @Author: wangd
 * @Date: 2023/4/28 23:04
 */
public class Person {

    private String name;
    public int age;

    public Person() {

    }

    private Person(String name) {
        this.name = name;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public void show() {
        System.out.println("hi, " + name);
    }

    private String showNation(String nation) {
        System.out.println("nation:" + nation);
        return nation;
    }
}
