package com.wd.demo.spring6.self.bean;

import com.wd.demo.spring6.self.anno.Di;
import com.wd.demo.spring6.self.anno.WangBean;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: wangd
 * @Date: 2023/6/2 10:21
 */
public class DiAnnotationApplicationContext implements WangApplicationContext {

    // private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);
    // org.springframework.beans.factory.support.DefaultListableBeanFactory
    private Map<String, Object> beanMap = new HashMap<>();
    private String packageAbsolutePath;
    private String basePackage;

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getBean(Class<T> clazz) {
        return (T) beanMap.get(clazz.getName());
    }

    // 通过构造扫描包
    // 将带@Bean注解的加入BeanMap中
    public DiAnnotationApplicationContext(String basePackage) {
        this.basePackage = basePackage;
        String packagePath = basePackage.replace('.', '/');
        URL packageUrl = Thread.currentThread().getContextClassLoader().getResource(packagePath);
        if (packageUrl == null) {
            throw new IllegalArgumentException("cannot find " + basePackage);
        }
        String packageAbsoluteUrl = packageUrl.getPath();
        File packageDir = new File(packageAbsoluteUrl);
        packageAbsolutePath = packageDir.getAbsolutePath();
        // 包扫描
        loadBean(packageDir);
        // 属性注入
        loadDi();

    }

    private void loadBean(File file) {
        // 如果是文件，就看能不能加载bean
        if (file.isFile()) {
            System.out.println(file.getPath());
            String allClassName = basePackage +
                    file.getAbsolutePath().substring(packageAbsolutePath.length()).replace('\\', '.');
            System.out.println(allClassName);
            if (!allClassName.contains(".class")) return;
            allClassName = allClassName.substring(0, allClassName.length() - 6);
            try {
                // 反射获取类
                Class<?> clazz = Class.forName(allClassName);
                // 检查类是否是接口，是否有@Bean注解，是否继承了接口
                if (clazz.isInterface()) return;
                Annotation annotation = clazz.getAnnotation(WangBean.class);
                if (annotation == null) return;
                Object instance = clazz.getDeclaredConstructor().newInstance();
                if (clazz.getInterfaces().length > 0) {
                    beanMap.put(clazz.getInterfaces()[0].getName(), instance);
                } else {
                    beanMap.put(allClassName, instance);
                }

            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
        // 如果是文件夹， 递归
        else if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files == null) return;
            for (File f : files) {
                loadBean(f);
            }
        }
    }

    private void loadDi() {
        beanMap.forEach((name, instance) -> {
            try {
                Class<?> clazz = instance.getClass();
                for (Field field : clazz.getDeclaredFields()) {
                    Di di = field.getAnnotation(Di.class);
                    if (di == null) continue;
                    // 反射set field
                    field.setAccessible(true);
                    field.set(instance, beanMap.get(field.getType().getName()));
                }
            }  catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
    }


}
