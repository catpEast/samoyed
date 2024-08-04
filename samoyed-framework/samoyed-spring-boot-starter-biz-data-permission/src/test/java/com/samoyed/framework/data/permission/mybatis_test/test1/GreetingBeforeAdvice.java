package com.samoyed.framework.data.permission.mybatis_test.test1;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class GreetingBeforeAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        // 输出切点
        System.out.println("Pointcut:" + target.getClass().getName() + "." + method.getName());
        String clientName = (String) args[0];
        System.out.println("How are you " + clientName + " ?");
    }
}