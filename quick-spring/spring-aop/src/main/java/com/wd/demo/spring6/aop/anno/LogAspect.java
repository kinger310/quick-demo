package com.wd.demo.spring6.aop.anno;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @Author: wangd
 * @Date: 2023/6/3 0:27
 */
@Aspect
@Component
public class LogAspect {
    //设置切入点和通知类型
    //切入点表达式: execution(访问修饰符 增强方法返回类型 增强方法所在类全路径.方法名称(方法参数))
    //通知类型：
    // 前置 @Before(value="切入点表达式配置切入点")
    //@Before(value = "execution(* com.atguigu.spring6.aop.annoaop.CalculatorImpl.*(..))")
    @Before(value = "execution(public int com.wd.demo.spring6.aop.*.*(..))")
    public void beforeMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.println("Logger-->前置通知，方法名称："+methodName+"，参数："+ Arrays.toString(args));
    }

    @After(value = "pointcut()")
    public void afterMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.println("Logger-->后置通知，方法名称："+methodName+"，参数："+ Arrays.toString(args));
    }

    @AfterReturning(value = "execution(* com.wd.demo.spring6.aop.*.*(..))", returning = "result")
    public void afterThrowingMethod(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("Logger-->返回结果通知，方法名称："+methodName+"，结果："+ result);
    }

    @AfterThrowing(value = "execution(* com.wd.demo.spring6.aop.*.*(..))", throwing = "ex")
    public void afterThrowingMethod(JoinPoint joinPoint, Throwable ex) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.println("Logger-->异常通知，方法名称："+methodName+"，异常："+ ex);
    }

    @Around(value = "pointcut()")
    public Object aroundMethod(ProceedingJoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        Object result = null;
        try {
            System.out.println("Logger-->Around前置通知，方法名称："+methodName+"，参数："+ Arrays.toString(args));
            result = joinPoint.proceed();
            System.out.println("Logger-->Around返回结果通知，方法名称："+methodName+"，结果："+ result);
        }  catch (Throwable e) {
            System.out.println("Logger-->Around异常通知，方法名称："+methodName+"，异常："+ e);
            throw new RuntimeException(e);
        } finally {
            System.out.println("Logger-->Around后置通知，方法名称："+methodName+"，参数："+ Arrays.toString(args));
        }
        return result;
    }

    @Pointcut(value = "execution(* com.wd.demo.spring6.aop.*.*(..))")
    public void pointcut() {
    }
}
