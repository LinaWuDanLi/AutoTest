package com.course.testng;

import org.testng.annotations.Test;

public class IgnoreTest{
    @Test(enabled=true)
    public void ignoreTest1(){
        System.out.println("ingoreTest1执行");
    }
    //忽略测试，false代表不执行这个测试方法
    @Test(enabled=false)
    public void ignoreTest2(){
        System.out.println("ignoreTest2执行");
    }
}
