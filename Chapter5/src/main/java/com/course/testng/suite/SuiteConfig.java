package com.course.testng.suite;

import org.testng.annotations.*;

public class SuiteConfig {
    @BeforeSuite
    public void BeforeSuite(){
        System.out.println("BeforeSuite测试套件运行啦");
    }
    @AfterSuite
    public void AfterSuite(){
        System.out.println("AfterSuite测试套件运行啦");
    }
    @BeforeTest
    public void BeforeTest(){
        System.out.println("在SuitConfig中的BeforeTest");
    }
    @AfterTest
    public void AfterTest(){
        System.out.println("在SuitConfig中的AfterTest");
    }
}
