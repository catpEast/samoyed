package com.samoyed.framework.data.permission.core.aop;

import com.samoyed.framework.data.permission.core.annotation.DataPermission;
import lombok.Getter;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.core.MethodClassKey;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@DataPermission
public class DataPermissionAnnotationInterceptor implements MethodInterceptor {

    /**
     * DataPermission 空对象，用于方法无 {@link DataPermission} 注解时，使用 DATA_PERMISSION_NULL 进行占位
     */
    static final DataPermission DATA_PERMISSION_NULL = DataPermissionAnnotationInterceptor.class.getAnnotation(DataPermission.class);

    @Getter
    private final Map<MethodClassKey, DataPermission> dataPermissionCache = new ConcurrentHashMap<>();
    
    
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("方法执行前......");
        DataPermission dataPermission = this.findAnnotation(invocation);
        if (dataPermission != null) {
            DataPermissionContextHolder.add(dataPermission);
        }
        try {
            return invocation.proceed();
        } finally {
            if (dataPermission != null) {
                DataPermissionContextHolder.remove();
            }
            System.out.println("方法执行后......");
        }
    }

    private DataPermission findAnnotation(MethodInvocation methodInvocation) {
        // 获取 method 和 target
        Method method = methodInvocation.getMethod();
        Object targetObject = methodInvocation.getThis();
        // 可能是 static 方法
        Class<?> clazz = targetObject != null ? targetObject.getClass() : method.getDeclaringClass();
        MethodClassKey methodClassKey = new MethodClassKey(method, clazz);
        DataPermission dataPermission = dataPermissionCache.get(methodClassKey);
        if (dataPermission != null) {
            return dataPermission != DATA_PERMISSION_NULL ? dataPermission : null;
        }
        
        // 从方法中获取
        dataPermission = AnnotationUtils.findAnnotation(method, DataPermission.class);
        if (dataPermission == null) {
            // 从类上获取
            AnnotationUtils.findAnnotation(clazz, DataPermission.class);
        }
        dataPermissionCache.put(methodClassKey, dataPermission == null ? DATA_PERMISSION_NULL : dataPermission);
        return dataPermission;
    }
}
