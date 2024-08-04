package com.samoyed.framework.data.permission.core.db;

import com.baomidou.mybatisplus.extension.plugins.handler.MultiDataPermissionHandler;
import com.samoyed.framework.data.permission.core.rule.DataPermissionRule;
import com.samoyed.framework.data.permission.core.rule.DataPermissionRuleFactory;
import lombok.RequiredArgsConstructor;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.schema.Table;

import java.util.List;

@RequiredArgsConstructor
public class DataPermissionRuleHandler implements MultiDataPermissionHandler {

    private final DataPermissionRuleFactory ruleFactory;
    
    @Override
    public Expression getSqlSegment(Table table, Expression where, String mappedStatementId) {
        try {
            List<DataPermissionRule> rules = ruleFactory.getDataPermissionRule(mappedStatementId);
            if (rules == null || rules.isEmpty()) {
                return null;
            }
            
    }
}
