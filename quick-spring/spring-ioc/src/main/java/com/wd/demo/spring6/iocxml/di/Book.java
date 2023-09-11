package com.wd.demo.spring6.iocxml.di;

/**
 * @Author: wangd
 * @Date: 2023/6/1 14:56
 */
public class Book {
    private String name;
    private String author;

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    // setter需要默认构造器 No default constructor found
    public Book() {
        System.out.println("无参构造器执行");
    }

    public Book(String name, String author) {
        System.out.println("有参构造器执行");
        this.name = name;
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
