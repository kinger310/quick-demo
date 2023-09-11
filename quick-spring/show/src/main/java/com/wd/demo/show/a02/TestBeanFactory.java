package com.wd.demo.show.a02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

public class TestBeanFactory {
    private static final Logger log = LoggerFactory.getLogger(TestBeanFactory.class);

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // bean 的定义  <class, scope, 初始化, 销毁>
        AbstractBeanDefinition beanDefinition =
                BeanDefinitionBuilder.genericBeanDefinition(Config.class).setScope("singleton").getBeanDefinition();
        beanFactory.registerBeanDefinition("config", beanDefinition);
        printBeanNames(beanFactory);

        // 给BeanFactory 添加一些常用的后处理器
        AnnotationConfigUtils.registerAnnotationConfigProcessors(beanFactory);
        printBeanNames(beanFactory);

        // BeanFactory 后处理器主要功能，补充了一些 bean 定义
        beanFactory.getBeansOfType(BeanFactoryPostProcessor.class).values()
                .forEach(beanFactoryPostProcessor -> {
                    beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
                });
        printBeanNames(beanFactory);

        // Bean后处理器, 针对 bean 的生命周期的各个阶段提供扩展, 例如 @Autowired @Resource ...
        // 后处理器加载的顺序决定了那个注解先被执行
        beanFactory.getBeansOfType(BeanPostProcessor.class).values().stream()
                .sorted(beanFactory.getDependencyComparator())
                .forEach(beanPostProcessor -> {
                    System.out.println(">>>>beanPostProcessor = " + beanPostProcessor);
                    // log.info("{}", beanPostProcessor);
                    beanFactory.addBeanPostProcessor(beanPostProcessor);
                });
        // printBeanNames(beanFactory);

        // System.out.println(beanFactory.getBean(Bean1.class).getBean2());
        beanFactory.preInstantiateSingletons(); // 准备好所有单例
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ");
        System.out.println(beanFactory.getBean(Bean1.class).getInter());
    }

    private static void printBeanNames(DefaultListableBeanFactory beanFactory) {
        for (String name : beanFactory.getBeanDefinitionNames()) {
            System.out.println("name = " + name);
        }
        System.out.println("=========");
    }


    @Configuration
    static class Config {
        @Bean
        public Bean1 bean1() {
            return new Bean1();
        }

        @Bean
        public Bean2 bean2() {
            return new Bean2();
        }

        @Bean
        public Bean3 bean3() {
            return new Bean3();
        }

        @Bean
        public Bean4 bean4() {
            return new Bean4();
        }
    }

    interface Inter {

    }

    static class Bean3 implements Inter {

    }

    static class Bean4 implements Inter {

    }

    static class Bean1 {
        private static final Logger log = LoggerFactory.getLogger(Bean1.class);

        public Bean1() {
            log.debug("构造 Bean1()");
        }

        @Autowired
        private Bean2 bean2;

        public Bean2 getBean2() {
            return bean2;
        }

        // @Autowired
        // private Inter inter;
        // 上面例子是错误的 ，因为Spring无法分辨是Bean3还是Bean4
        // 可以改成 private Inter bean3;


        @Resource(name = "bean4")
        @Autowired
        private Inter bean3;

        public Inter getInter() {
            return bean3;
        }
    }

    static class Bean2 {
        private static final Logger log = LoggerFactory.getLogger(Bean2.class);

        public Bean2() {
            log.debug("构造 Bean2()");
        }
    }
}
