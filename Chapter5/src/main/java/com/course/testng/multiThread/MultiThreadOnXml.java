package com.course.testng.multiThread;

import org.testng.annotations.Test;

public class MultiThreadOnXml {
    @Test
    public void test1(){
        //%s代表当前位置输出字符串,%n是换行的格式字符串,只能用在print输出语句中, 而\n是回车字符, 可以用在所有的字符串中
        System.out.printf("test 1 Thread ID: %s%n",Thread.currentThread().getId());
    }
    @Test
    public void test2(){
        System.out.printf("test 2 Thread ID: %s%n",Thread.currentThread().getId());
    }

    @Test
    public void test3(){
        System.out.printf("test 3 Thread ID: %s%n",Thread.currentThread().getId());
    }


}
