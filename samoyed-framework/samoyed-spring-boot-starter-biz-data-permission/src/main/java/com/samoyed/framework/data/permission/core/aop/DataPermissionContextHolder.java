package com.samoyed.framework.data.permission.core.aop;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.samoyed.framework.data.permission.core.annotation.DataPermission;
import java.util.LinkedList;

/**
 * @Author wyz
 * @Date 2024/8/2 14:29
 * @Description
 */
public class DataPermissionContextHolder {

    /**
     * 使用 List 的原因，可能存在方法的嵌套调用
     */
    private static final ThreadLocal<LinkedList<DataPermission>> DATA_PERMISSIONS =
        TransmittableThreadLocal.withInitial(LinkedList::new);

    /**
     * 获得当前的 DataPermission 注解
     *
     * @return DataPermission 注解
     */
    public static DataPermission get() {
        return DATA_PERMISSIONS.get().peekLast();
    }

    /**
     * 入栈 DataPermission 注解
     *
     * @param dataPermission DataPermission 注解
     */
    public static void add(DataPermission dataPermission) {
        DATA_PERMISSIONS.get().addLast(dataPermission);
    }
}
