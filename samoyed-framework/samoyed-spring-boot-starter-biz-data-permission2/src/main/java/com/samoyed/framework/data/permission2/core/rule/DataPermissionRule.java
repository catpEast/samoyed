package com.samoyed.framework.data.permission2.core.rule;

import java.util.Set;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;

/**
 * @Author wyz
 * @Date 2024/8/16 9:03
 * @Description 数据权限规则
 */
public interface DataPermissionRule {

    /**
     * @return 对那些表进行数据权限限制
     */
    Set<String> getTableNames();

    /**
     * @param tableName 表的名称
     * @param tableAlias 表的别名
     * @return SQL语句表达式
     */
    Expression getExpression(String tableName, Alias tableAlias);
}
