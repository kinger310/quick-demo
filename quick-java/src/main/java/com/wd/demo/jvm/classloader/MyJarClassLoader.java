package com.wd.demo.jvm.classloader;

/**
 * @Author: wangd
 * @Date: 2023/7/7 18:04
 */
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class MyJarClassLoader extends URLClassLoader {

    public MyJarClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    public void addJarFile(String jarFilePath) throws Exception {
        File jarFile = new File(jarFilePath);
        if (jarFile.exists()) {
            URL jarUrl = jarFile.toURI().toURL();
            addURL(jarUrl);
        } else {
            throw new IllegalArgumentException("Jar file does not exist: " + jarFilePath);
        }
    }

    public static void main(String[] args) throws Exception {
        // 创建自定义ClassLoader实例
        MyJarClassLoader classLoader = new MyJarClassLoader(new URL[]{}, MyJarClassLoader.class.getClassLoader());

        // 加载指定的jar文件
        String jarFilePath = "D:\\codes\\quick-demo\\quick-flink\\target\\quick-flink-1.0-SNAPSHOT.jar";
        classLoader.addJarFile(jarFilePath);

        // 使用ClassLoader加载指定的类
        String className = "com.wd.demo.Main";
        Class<?> loadedClass = classLoader.loadClass(className);

        // 创建实例并调用方法
        Object instance = loadedClass.newInstance();
        loadedClass.getMethod("hello").invoke(instance);
    }
}

