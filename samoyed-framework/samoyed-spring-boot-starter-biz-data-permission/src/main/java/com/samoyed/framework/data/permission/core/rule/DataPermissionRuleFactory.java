package com.samoyed.framework.data.permission.core.rule;

import java.util.List;

/**
 * @Author wyz
 * @Date 2024/8/2 14:25
 * @Description
 */
public interface DataPermissionRuleFactory {
    /**
     * 获得所有数据权限规则数组
     *
     * @return 数据权限规则数组
     */
    List<DataPermissionRule> getDataPermissionRules();

    /**
     * 获得指定 Mapper 的数据权限规则数组
     *
     * @param mappedStatementId 指定 Mapper 的编号
     * @return 数据权限规则数组
     */
    List<DataPermissionRule> getDataPermissionRule(String mappedStatementId);

}
