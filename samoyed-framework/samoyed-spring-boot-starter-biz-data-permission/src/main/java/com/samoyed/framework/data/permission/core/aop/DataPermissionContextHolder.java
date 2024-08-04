package com.samoyed.framework.data.permission.core.aop;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.samoyed.framework.data.permission.core.annotation.DataPermission;
import java.util.LinkedList;
import java.util.List;

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
     *
     *  入栈 DataPermission 注解
     *
     * @param dataPermission DataPermission 注解
     */
    public static void add(DataPermission dataPermission) {
        DATA_PERMISSIONS.get().addLast(dataPermission);
    }
    
    public static DataPermission remove() {
        DataPermission dataPermission = DATA_PERMISSIONS.get().removeLast();
        if (DATA_PERMISSIONS.get().isEmpty()) {
            DATA_PERMISSIONS.remove();
        }
        return dataPermission;
    }

    /**
     * 获得所有 DataPermission
     *
     * @return DataPermission 队列
     */
    public static List<DataPermission> getAll() {
        return DATA_PERMISSIONS.get();
    }

    /**
     * 清空上下文
     *
     * 目前仅仅用于单测
     */
    public static void clear() {
        DATA_PERMISSIONS.remove();
    }
}
