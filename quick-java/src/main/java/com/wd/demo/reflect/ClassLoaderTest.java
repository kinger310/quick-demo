package com.wd.demo.reflect;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Author: wangd
 * @Date: 2023/4/29 11:45
 */
public class ClassLoaderTest {

    @Test
    public void testClassLoaderType() {
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        System.out.println(classLoader);

        ClassLoader classLoader1 = classLoader.getParent();
        System.out.println(classLoader1);

        System.out.println(classLoader1.getParent());

        System.out.println(String.class.getClassLoader());
    }


    @Test
    public void testLoadProperties() throws IOException {
        Properties properties = new Properties();

        // 方式一 文件流。 此时文件默认在当前module下
        // FileInputStream fis = new FileInputStream("src/main/resources/jdbc.properties");
        // properties.load(fis);

        // 方式二 使用classLoader，配置文件默认识别为在当前module的src目录中（会自动去findResource）
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        InputStream resourceAsStream = classLoader.getResourceAsStream("jdbc.properties");
        properties.load(resourceAsStream);

        String user = properties.getProperty("user");
        System.out.println("user = " + user);

    }
}
