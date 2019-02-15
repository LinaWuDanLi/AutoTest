package com.course.testng.groups;

import org.testng.annotations.Test;
//组测试中类分组测试
@Test(groups="Student")
public class GroupsOnClass2 {
    public void stu1(){
        System.out.println("GroupsOnClass2中stu1方法运行");
    }
    public void stu2(){
        System.out.println("GroupsOnClass2中stu2方法运行");
    }
}
