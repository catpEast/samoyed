package com.samoyed.framework.data.permission.core.aop;

import com.samoyed.framework.data.permission.core.annotation.DataPermission;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;

public class DataPermissionAnnotationAdvisor {
    
    private final Advice advice;
    
    private final Pointcut pointcut;
    
    public DataPermissionAnnotationAdvisor() {
        this.advice = new DataPermissionAnnotationInterceptor();
        this.pointcut = this.buildPointcut();
    }

    /**
     * 切入点：在 类 父类 接口 方法 父类方法 接口方法，只要加了 DataPermission 注解，就可以标记到
     * 
     */
    protected Pointcut buildPointcut() {
        // 限定类的切入点
        Pointcut classPointcut = new AnnotationMatchingPointcut(DataPermission.class, true);
        // 限定方法的切入点
        Pointcut methodPointcut = new AnnotationMatchingPointcut(null, DataPermission.class, true);
        // 组合切面 只要满足一个条件就返回true 即类(包括父类/接口中的方法)或方法上有该注解就返回true
        return new ComposablePointcut(classPointcut).union(methodPointcut);
    }
}
