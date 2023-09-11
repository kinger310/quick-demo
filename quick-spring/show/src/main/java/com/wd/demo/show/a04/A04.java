package com.wd.demo.show.a04;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor;
import org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessorRegistrar;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.ContextAnnotationAutowireCandidateResolver;
import org.springframework.context.support.GenericApplicationContext;

import java.util.Arrays;

public class A04 {
    public static void main(String[] args) {
        // ⬇️GenericApplicationContext 是一个【干净】的容器
        GenericApplicationContext context = new GenericApplicationContext();

        // ⬇️用原始方法注册三个 bean
        context.registerBean("bean1", Bean1.class);
        context.registerBean("bean2", Bean2.class);
        context.registerBean("bean3", Bean3.class);
        context.registerBean("bean4", Bean4.class);


        // 需要添加Autowired的解析器
        context.getDefaultListableBeanFactory().setAutowireCandidateResolver(new ContextAnnotationAutowireCandidateResolver());

        // 只有下面这个语句会报错：Caused by: org.springframework.beans.factory.NoSuchBeanDefinitionException:
        // No qualifying bean of type 'java.lang.String' available: expected at least 1 bean which qualifies as autowire candidate.
        // Dependency annotations: {@org.springframework.beans.factory.annotation.Value(value=${JAVA_HOME})}
        context.registerBean(AutowiredAnnotationBeanPostProcessor.class);
        context.registerBean(CommonAnnotationBeanPostProcessor.class);

        // Bean4 SpringBoot
        // ConfigurationPropertiesBindingPostProcessor.

        // ⬇️初始化容器
        context.refresh(); // 执行beanFactory后处理器, 添加bean后处理器, 初始化所有单例


        System.out.println(context.getBean(Bean4.class));

        context.close();

    }
}
