package com.samoyed.framework.data.permission.config;

import com.samoyed.framework.data.permission.core.rule.DataPermissionRule;
import com.samoyed.framework.data.permission.core.rule.DataPermissionRuleFactory;
import com.samoyed.framework.data.permission.core.rule.DataPermissionRuleFactoryImpl;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.List;

@AutoConfiguration
public class DataPermissionAutoConfiguration {
    
    @Bean
    public DataPermissionRuleFactory dataPermissionRuleFactory(List<DataPermissionRule> rules) {
        return new DataPermissionRuleFactoryImpl(rules);
    }
    
    
}
