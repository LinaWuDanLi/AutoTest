package com.course.testng;

import org.testng.annotations.*;

public class BasicAnnotation {
    @Test //最基本的注解，用来把方法标记为测试的一部分
    public void testCase1(){
        System.out.println("Test这是测试用例1");
        System.out.printf("BasicAnnotation Thread ID: %s%n",Thread.currentThread().getId());
    }
    @Test
    public void testCase2(){
        System.out.println("Test这是测试用例2");
    }
    @BeforeMethod   //有几个@Test测试方法就运行几次
    public void BeforeMethod(){
        System.out.println("BeforeMethod这是在测试方法之前运行的");
    }
    @AfterMethod   //有几个@Test测试方法就运行几次
    public void AfterMethod(){
        System.out.println("AfterMethod这是在测试方法之后运行的");
    }
   @BeforeClass
    public void BeforeClass(){
       System.out.println("BeforeClass这是在类运行之前运行的");
   }
   @AfterClass
    public void AfterClass(){
       System.out.println("AfterClass这是在类运行之后运行的");
   }
   @BeforeSuite
    public void BeforeSuite(){
       System.out.println("BeforeSuite测试套件");
   }
   @AfterSuite
    public void AfterSuite(){
       System.out.println("AfterSuite测试套件");
   }
}
