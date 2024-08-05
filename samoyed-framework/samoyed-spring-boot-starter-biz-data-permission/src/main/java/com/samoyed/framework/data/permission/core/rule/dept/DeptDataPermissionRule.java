package com.samoyed.framework.data.permission.core.rule.dept;

import com.samoyed.framework.data.permission.core.rule.DataPermissionRule;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import lombok.AllArgsConstructor;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.NullValue;

/**
 * @Author wyz
 * @Date 2024/8/5 17:20
 * @Description
 */
@AllArgsConstructor
public class DeptDataPermissionRule implements DataPermissionRule {

    private static final String DEPT_COLUMN_NAME = "depart_no";
    
    private static final String USER_COLUMN_NAME = "user_name";

    static final Expression EXPRESSION_NULL = new NullValue();

    /**
     * 基于部门的表字段配置
     * 每个表的部门编号字段是 depart_no，通过该配置自定义。
     */
    private final Map<String, String> deptColumns = new HashMap<>();

    /**
     * 基于用户的表字段配置
     */
    private final Map<String, String> userColumns = new HashMap<>();

    /**
     * 所有表名，是 {@link #deptColumns} 和 {@link #userColumns} 的合集
     */
    private final Set<String> tableNames = new HashSet<>();
    
    @Override
    public Set<String> getTableNames() {
        return tableNames;
    }

    @Override
    public Expression getExpression(String tableName, Alias tableAlias) {
        return null;
    }
}
