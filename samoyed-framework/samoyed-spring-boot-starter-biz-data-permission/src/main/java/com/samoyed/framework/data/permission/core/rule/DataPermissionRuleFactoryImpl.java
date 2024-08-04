package com.samoyed.framework.data.permission.core.rule;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import com.samoyed.framework.data.permission.core.annotation.DataPermission;
import com.samoyed.framework.data.permission.core.aop.DataPermissionContextHolder;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
        
        // 3. 配置，但禁用
        if (!dataPermission.enable()) {
            return Collections.emptyList();
        }

        // 4. 已配置，只选择部分规则
        if (ArrayUtil.isNotEmpty(dataPermission.includeRules())) {
            return rules.stream().filter(rule -> ArrayUtil.contains(dataPermission.includeRules(), rule.getClass()))
                    .collect(Collectors.toList()); // 一般规则不会太多，所以不采用 HashSet 查询
        }
        // 5. 已配置，只排除部分规则
        if (ArrayUtil.isNotEmpty(dataPermission.excludeRules())) {
            return rules.stream().filter(rule -> !ArrayUtil.contains(dataPermission.excludeRules(), rule.getClass()))
                    .collect(Collectors.toList()); // 一般规则不会太多，所以不采用 HashSet 查询
        }
        // 6. 已配置，全部规则
        return rules;
    }
}
