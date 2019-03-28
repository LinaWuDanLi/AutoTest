package com.course.testng;

import org.testng.annotations.Test;

//超时测试
//快捷键alt + Enter， 处理错误
//快捷键ctrl + 点击，查看类或方法的具体实现
public class TimeOutTest {
    @Test(timeOut = 3000)//单位为毫秒值
    public void testSuccess() throws InterruptedException {
        Thread.sleep(2000);
    }
    @Test(timeOut = 2000)
    public void testFailed() throws InterruptedException {
        Thread.sleep(3000);
    }
}

