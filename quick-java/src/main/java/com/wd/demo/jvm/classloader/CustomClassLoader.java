package com.wd.demo.jvm.classloader;

import java.io.FileNotFoundException;
import java.net.URL;

/**
 * @Author: wangd
 * @Date: 2023/5/11 14:52
 */
public class CustomClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] result = getClassFromCustomPath(name);
            if (result == null) {
                throw new FileNotFoundException();
            } else {
                return defineClass(name, result, 0, result.length);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    private byte[] getClassFromCustomPath(String name){
        //从自定义路径中加载指定类:细节略
        //如果指定路径的字节码文件进行了加密，则需要在此方法中进行解密操作。
        return null;
    }

    public static void main(String[] args) {
        MyJarClassLoader classLoader = new MyJarClassLoader(new URL[]{}, MyJarClassLoader.class.getClassLoader());
        try {
            Class<?> clazz = Class.forName("One", true, classLoader);
            Object o = clazz.newInstance();
            System.out.println(o.getClass().getClassLoader());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
