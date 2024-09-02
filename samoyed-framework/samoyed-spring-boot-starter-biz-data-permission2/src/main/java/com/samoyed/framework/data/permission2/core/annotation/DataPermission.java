package com.samoyed.framework.data.permission2.core.annotation;

import com.samoyed.framework.data.permission2.core.rule.DataPermissionRule;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author wyz
 * @Date 2024/8/16 9:02
 * @Description
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataPermission {
    
    boolean enable() default true;
    
    Class<? extends DataPermissionRule>[] includeRules() default {};

    Class<? extends DataPermissionRule>[] excludeRules() default {};
}
