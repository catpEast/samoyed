package com.samoyed.framework.data.permission.mybatis_test;

import com.samoyed.framework.data.permission.core.annotation.DataPermission;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;

import javax.xml.crypto.Data;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

//@DataPermission
public class MyTest {

    //执行总次数
    private static final int EXECUTE_COUNT = 1000;
    //同时运行的线程数量
    private static final int THREAD_COUNT = 20;

    private static final ThreadLocal<SimpleDateFormat> THREAD_LOCAL = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));

    @Test
    public void test() {
        Semaphore semaphore = new Semaphore(THREAD_COUNT);
        CountDownLatch countDownLatch = new CountDownLatch(EXECUTE_COUNT);

        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < EXECUTE_COUNT; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    try {
                        THREAD_LOCAL.get().parse("2021-01-01");
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    semaphore.release();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                countDownLatch.countDown();
            });
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        executorService.shutdown();
    }


    @DataPermission
    public void car() {

    }
    
    public static void main(String[] args) {
        // 获取Class上面的注解
        DataPermission dataPermission1 = AnnotationUtils.findAnnotation(MyTest.class, DataPermission.class);
        System.out.println(dataPermission1);

        // 获取Method上面的注解
        Method carMethod = ReflectionUtils.findMethod(MyTest.class, "car");
        DataPermission dataPermission2 = AnnotationUtils.findAnnotation(carMethod, DataPermission.class);
        System.out.println(dataPermission2);
    }
    
    @Test
    public void test2() {
        AnnotationMatchingPointcut classPointcut = new AnnotationMatchingPointcut(DataPermission.class, true);
        AnnotationMatchingPointcut methodPointcut = new AnnotationMatchingPointcut(null, DataPermission.class, true);

        ComposablePointcut intersection = new ComposablePointcut(classPointcut).union(methodPointcut);
        Method carMethod = ReflectionUtils.findMethod(MyTest.class, "car");
        boolean matches = intersection.getMethodMatcher().matches(carMethod, MyTest.class);
        boolean matches1 = intersection.getClassFilter().matches(MyTest.class);
        System.out.println(matches);
        System.out.println(matches1);

    }
}
