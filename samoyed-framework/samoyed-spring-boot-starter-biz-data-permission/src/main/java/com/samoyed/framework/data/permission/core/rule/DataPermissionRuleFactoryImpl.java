package com.samoyed.framework.data.permission.core.rule;

import cn.hutool.core.collection.CollUtil;
import com.samoyed.framework.data.permission.core.annotation.DataPermission;
import com.samoyed.framework.data.permission.core.aop.DataPermissionContextHolder;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 * @Author wyz
 * @Date 2024/8/2 14:26
 * @Description
 */
@RequiredArgsConstructor
public class DataPermissionRuleFactoryImpl implements DataPermissionRuleFactory{

    /**
     * 数据权限规则数组
     */
    private final List<DataPermissionRule> rules;

    @Override
    public List<DataPermissionRule> getDataPermissionRules() {
        return rules;
    }
    
    @Override
    public List<DataPermissionRule> getDataPermissionRule(String mappedStatementId) {
        // 1. 无数据权限
        if (CollUtil.isEmpty(rules)) {
            return Collections.emptyList();
        }

        // 2. 未配置，则默认开启
        DataPermission dataPermission = DataPermissionContextHolder.get();
        if (dataPermission == null) {
            return rules;
        }
        
        return Collections.emptyList();
    }
}
