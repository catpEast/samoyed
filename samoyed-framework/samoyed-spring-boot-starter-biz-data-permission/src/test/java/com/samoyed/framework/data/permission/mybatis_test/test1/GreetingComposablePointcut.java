package com.samoyed.framework.data.permission.mybatis_test.test1;

import com.samoyed.framework.data.permission.core.annotation.DataPermission;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;

public class GreetingComposablePointcut {

    public Pointcut getPointcut() {
        ComposablePointcut composablePointcut = new ComposablePointcut();

        AnnotationMatchingPointcut classPointcut = new AnnotationMatchingPointcut(DataPermission.class, true);

        AnnotationMatchingPointcut methodPointcut = new AnnotationMatchingPointcut(null, DataPermission.class, true);

        return composablePointcut.intersection(classPointcut).intersection(methodPointcut);
    }
}
