package com.wd.demo.show.a05;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.*;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Author: wangd
 * @Date: 2023/8/19 19:07
 */
public class ComponentScanPostProcessor implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        // ComponentScan componentScan = AnnotationUtils.findAnnotation(Config.class, ComponentScan.class);
        // if (componentScan != null) {
        //     for (String p : componentScan.basePackages()) {
        //         String path = "classpath*:" + p.replace(".", "/") + "/**/*.class";
        //         System.out.println("path = " + path);
        //         CachingMetadataReaderFactory factory = new CachingMetadataReaderFactory();
        //         AnnotationBeanNameGenerator generator = new AnnotationBeanNameGenerator();
        //         try {
        //             Resource[] resources = new PathMatchingResourcePatternResolver().getResources(path);
        //             for (Resource resource : resources) {
        //                 System.out.println("resource = " + resource);
        //                 MetadataReader reader = factory.getMetadataReader(resource);
        //                 AnnotationMetadata annotationMetadata = reader.getAnnotationMetadata();
        //                 System.out.println("是否加了 @Component:" + annotationMetadata.hasAnnotation(Component.class.getName()));
        //                 System.out.println("是否加了 @Component 派生:" + annotationMetadata.hasMetaAnnotation(Component.class.getName()));
        //                 if (annotationMetadata.hasAnnotation(Component.class.getName()) ||
        //                         annotationMetadata.hasMetaAnnotation(Component.class.getName()) ) {
        //                     AbstractBeanDefinition beanDefinition =
        //                             BeanDefinitionBuilder
        //                                     .genericBeanDefinition(reader.getClassMetadata().getClassName())
        //                                     .getBeanDefinition();
        //                     String name = generator.generateBeanName(beanDefinition, registry);
        //                     registry.registerBeanDefinition(name, beanDefinition);
        //                 }
        //             }
        //         } catch (IOException e) {
        //             throw new RuntimeException(e);
        //         }
        //     }
        // }
    }

    @Override // context.refresh
    public void postProcessBeanFactory(ConfigurableListableBeanFactory clbf) throws BeansException {
        ComponentScan componentScan = AnnotationUtils.findAnnotation(Config.class, ComponentScan.class);
        if (componentScan != null) {
            for (String p : componentScan.basePackages()) {
                String path = "classpath*:" + p.replace(".", "/") + "/**/*.class";
                System.out.println("path = " + path);
                CachingMetadataReaderFactory factory = new CachingMetadataReaderFactory();
                AnnotationBeanNameGenerator generator = new AnnotationBeanNameGenerator();
                try {
                    Resource[] resources = new PathMatchingResourcePatternResolver().getResources(path);
                    for (Resource resource : resources) {
                        System.out.println("resource = " + resource);
                        MetadataReader reader = factory.getMetadataReader(resource);
                        AnnotationMetadata annotationMetadata = reader.getAnnotationMetadata();
                        System.out.println("是否加了 @Component:" + annotationMetadata.hasAnnotation(Component.class.getName()));
                        System.out.println("是否加了 @Component 派生:" + annotationMetadata.hasMetaAnnotation(Component.class.getName()));
                        if (annotationMetadata.hasAnnotation(Component.class.getName()) ||
                                annotationMetadata.hasMetaAnnotation(Component.class.getName()) ) {
                            AbstractBeanDefinition beanDefinition =
                                    BeanDefinitionBuilder
                                            .genericBeanDefinition(reader.getClassMetadata().getClassName())
                                            .getBeanDefinition();

                            if (clbf instanceof DefaultListableBeanFactory) {
                                DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) clbf;
                                String name = generator.generateBeanName(beanDefinition, beanFactory);
                                beanFactory.registerBeanDefinition(name, beanDefinition);
                            }

                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

}
