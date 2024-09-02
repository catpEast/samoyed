package com.samoyed.framework.data.permission2.core.aop;

import static org.junit.jupiter.api.Assertions.*;

import com.samoyed.framework.data.permission2.core.aop.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author wyz
 * @Date 2024/8/19 9:30
 * @Description
 */
@SpringBootTest(classes = Application.class)
class DataPermissionAspectTest {
    
    @Autowired
    private User user;

    @Test
    void test() {
        user.method1();
    }
}