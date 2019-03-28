package com.tester.extent.demo;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
//ctrl+d 复制鼠标所在的一整行，ctrl+y 删除鼠标所在的一整行
//注释快捷键 ctrl+/
public class TestMethodDemo {
    @Test
    public void test1(){
        Assert.assertEquals(1,3);
    }
    @Test
    public void test2(){
        Assert.assertEquals(1,1);
    }
    @Test
    public void test3(){
        Assert.assertEquals(2,2);
    }
    @Test
    public void logDemo(){
        Reporter.log("这是我们自己写的日志");
        throw new RuntimeException("这是我自己的运行时异常");
    }


}
