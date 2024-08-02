package com.samoyed.framework.data.permission.mybatis_test;

import com.baomidou.mybatisplus.extension.plugins.handler.DataPermissionHandler;
import java.util.HashMap;
import java.util.Map;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;

/**
 * @Author wyz
 * @Date 2024/8/2 17:18
 * @Description
 */
public class MyDataPermissionHandler implements DataPermissionHandler {

    private static String TEST_1 = "com.baomidou.userMapper.selectByUsername";
    private static String TEST_2 = "com.baomidou.userMapper.selectById";
    private static String TEST_3 = "com.baomidou.roleMapper.selectByCompanyId";
    private static String TEST_4 = "com.baomidou.roleMapper.selectById";
    private static String TEST_5 = "com.baomidou.roleMapper.selectByRoleId";

    /**
     * 这里可以理解为数据库配置的数据权限规则 SQL
     */
    private static Map<String, String> sqlSegmentMap = new HashMap<String, String>() {
        {
            put(TEST_1, "username='123' or userId IN (1,2,3)");
            put(TEST_2, "u.state=1 and u.amount > 1000");
            put(TEST_3, "companyId in (1,2,3)");
            put(TEST_4, "username like 'abc%'");
            put(TEST_5, "id=1 and role_id in (select id from sys_role)");
        }
    };
    
    @Override
    public Expression getSqlSegment(Expression where, String mappedStatementId) {
        
        try {
            String sqlSegment = sqlSegmentMap.get(mappedStatementId);
            Expression sqlSegmentExpression = CCJSqlParserUtil.parseCondExpression(sqlSegment);
            if (null != where) {
                System.out.println("原 where: " + where);
                if (mappedStatementId.equals(TEST_4)) {
                    return new OrExpression(where, sqlSegmentExpression);
                }
                return new AndExpression(where, sqlSegmentExpression);
            }
        } catch (JSQLParserException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
