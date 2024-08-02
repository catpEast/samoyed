package com.samoyed.framework.data.permission.core.annotation;

import com.samoyed.framework.data.permission.core.rule.DataPermissionRule;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author wyz
 * @Date 2024/8/1 18:52
 * @Description
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataPermission {

    /**
     * 是否开启数据权限
     */
    boolean enable() default true;

    /**
     * 生效的数据权限规则数组，优先级高于 {@link #excludeRules()}
     */
    Class<? extends DataPermissionRule>[] includeRules() default {};

    /**
     * 排除的数据权限规则数组，优先级最低
     */
    Class<? extends DataPermissionRule>[] excludeRules() default {};
}
