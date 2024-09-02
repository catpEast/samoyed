package com.samoyed.framework.data.permission2.core.aop;

import com.samoyed.framework.data.permission2.core.annotation.DataPermission;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodClassKey;

/**
 * @Author wyz
 * @Date 2024/8/16 9:17
 * @Description
 */
@Aspect
@DataPermission
public class DataPermissionAspect {

    // public static final ThreadLocal<>

    // DataPermission 空对象
    static final DataPermission DATA_PERMISSION_NULL = DataPermissionAspect.class.getAnnotation(DataPermission.class);

    private final Map<MethodClassKey, DataPermission> dataPermissionCache = new ConcurrentHashMap<>();


    @Pointcut("@annotation(com.samoyed.framework.data.permission2.core.annotation.DataPermission) " +
        "|| @within(com.samoyed.framework.data.permission2.core.annotation.DataPermission)")
    public void dataPermissionPointcut() {}

    @Around("dataPermissionPointcut()")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Before proceeding: " + joinPoint.getSignature().getName());
        Object result = joinPoint.proceed();
        System.out.println("After proceeding: " + joinPoint.getSignature().getName());
        return result;
    }
}

