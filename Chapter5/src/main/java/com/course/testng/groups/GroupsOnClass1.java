package com.course.testng.groups;

import org.testng.annotations.Test;
//组测试中的类分组测试
@Test(groups = "Teacher")
public class GroupsOnClass1 {
    public void teacher1(){
        System.out.println("GroupsOnClass1中的teacher1方法运行");
    }
    public void teacher2(){
        System.out.println("GroupsOnClass1中的teacher2方法运行");
    }

}
