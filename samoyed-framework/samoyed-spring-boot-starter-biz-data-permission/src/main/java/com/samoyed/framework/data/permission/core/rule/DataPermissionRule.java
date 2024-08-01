package com.samoyed.framework.data.permission.core.rule;

import java.util.Set;

/**
 * @Author wyz
 * @Date 2024/8/1 18:54
 * @Description
 */
public interface DataPermissionRule {

    /**
     * 返回需要生效的表名数组
     * 为什么需要该方法？Data Permission 数组基于 SQL 重写，通过 Where 返回只有权限的数据
     *
     * 如果需要基于实体名获得表名，可调用 {@link TableInfoHelper#getTableInfo(Class)} 获得
     *
     * @return 表名数组
     */
    Set<String> getTableNames();


    // Expression
}
