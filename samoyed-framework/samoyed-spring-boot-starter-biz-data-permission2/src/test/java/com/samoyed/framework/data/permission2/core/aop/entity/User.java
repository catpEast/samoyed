package com.samoyed.framework.data.permission2.core.aop.entity;

import com.samoyed.framework.data.permission2.core.annotation.DataPermission;
import org.springframework.stereotype.Component;

/**
 * @Author wyz
 * @Date 2024/8/19 9:32
 * @Description
 */
@Component
public class User {
    
    private String name;
    
    private Integer age;
    
    
    @DataPermission
    public void method1() {
        System.out.println("执行method1111");
    }
}
