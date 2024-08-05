package com.samoyed.framework.data.permission.config;

import com.samoyed.framework.data.permission.core.rule.dept.DeptDataPermissionRuleCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class DataPermissionConfiguration {
    
    @Bean
    public DeptDataPermissionRuleCustomizer deptDataPermissionRuleCustomizer() {
        return null;
    }
}
