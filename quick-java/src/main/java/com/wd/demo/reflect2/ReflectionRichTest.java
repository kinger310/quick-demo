package com.wd.demo.reflect2;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @Author: wangd
 * @Date: 2023/4/29 16:04
 */
public class ReflectionRichTest {

    @Test
    public void testRichFields() {
        Class<Person> clazz = Person.class;
        for (Field f : clazz.getFields()) {
            // 只能打印出public field
            System.out.println(f);
        }
        System.out.println("===========");
        for (Field f: clazz.getDeclaredFields()) {
            System.out.println(f.toString());
            System.out.println("field modifier = " + Modifier.toString(f.getModifiers()));
            System.out.println("f.getType() = " + f.getType().getTypeName());
            System.out.println("f.getDeclaringClass() = " + f.getDeclaringClass());
            System.out.println("f.getName() = " + f.getName());

            //         int mod = getModifiers();
            //         return (((mod == 0) ? "" : (Modifier.toString(mod) + " "))
            //             + getType().getTypeName() + " "
            //             + getDeclaringClass().getTypeName() + "."
            //             + getName());
        }

        // TODO 获取方法的注解， 形参

        // 注解
        for(Annotation annotation: clazz.getAnnotations()) {
            System.out.println(annotation);
        }



    }


}
