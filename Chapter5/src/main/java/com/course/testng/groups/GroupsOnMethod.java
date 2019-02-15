package com.course.testng.groups;
//组测试中的方法分组测试
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

public class GroupsOnMethod {
    @Test(groups = "Client")
    public void ClientTest1(){
        System.out.println("这是客户端的测试方法1");
    }
    @Test(groups = "Client")
    public void ClientTest2(){
        System.out.println("这是客户端的测试方法2");
    }
    @Test(groups = "Server")
    public void ServerTest1(){
        System.out.println("这是服务端的测试方法1");
    }
    @Test(groups = "Server")
    public void ServerTest2(){
        System.out.println("这是服务端的测试方法2");
    }
    @BeforeGroups("Client")
    public void beforeGroupsTest1(){
        System.out.println("这是在客户端组运行之前运行的方法");
    }
    @AfterGroups("Client")
    public void afterGroupsTest1(){
        System.out.println("这是在客户端组运行之后运行的方法");
    }
    @BeforeGroups("Server")
    public void beforeGroupsTest2(){
        System.out.println("这是在服务端组运行之前运行的方法");
    }
    @AfterGroups("Server")
    public void afterGroupsTest2(){
        System.out.println("这是在服务端组运行之后运行的方法");
    }
}
