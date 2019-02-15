package com.course.testng;

import org.testng.annotations.Test;

/** 什么是依赖测试？
 * 当方法2依赖方法1时，只有方法1执行成功后，方法2才执行
 * 如果方法1执行失败，方法2 就不执行
 * */
public class DependTest {
    @Test
    public void test1(){
        System.out.println("test1 run");
        //throw new RuntimeException();
        System.out.printf("dependtest 1 Thread ID: %s%n",Thread.currentThread().getId());
    }
    @Test(dependsOnMethods = {"test1"})
    public void test2(){
        System.out.println("test2 run");
        System.out.printf("dependtest 2 Thread ID: %s%n",Thread.currentThread().getId());
    }
}
