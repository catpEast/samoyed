package com.samoyed.framework.data.permission.mybatis_test.test1;

import com.samoyed.framework.data.permission.core.annotation.DataPermission;

@DataPermission
public class Waiter {

    @DataPermission
    public void greetTo(String name) {
        System.out.println("Waiter Greet To " + name);
    }

    public void serverTo(String name) {
        System.out.println("Waiter Server To " + name);
    }
}