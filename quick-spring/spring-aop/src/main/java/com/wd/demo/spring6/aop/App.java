package com.wd.demo.spring6.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        Calculator calculator = context.getBean(Calculator.class);
        calculator.add(4,5);

        calculator.div(5, 0);
    }
}
