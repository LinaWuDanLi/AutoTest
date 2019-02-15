package com.course.testng.parameter;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;


public class DataProviderTest {
    @Test(dataProvider = "data")
    public void testDataProvider(String name,int age ){
        System.out.println("name ="+name+";"+"age ="+age);
    }
    @DataProvider(name="data")
    public Object[][] providerData(){
        Object [][] o =new Object[][]{
                {"张三",10},
                {"李四",20},
                {"王五",30}
        };
        return o;
    }
    @Test(dataProvider = "methodData")
    public void test1(String name,int age){
        System.out.println("test1111 name ="+name+"; age="+age);
    }
    @Test(dataProvider = "methodData")
    public void test2(String name,int age){
        System.out.println("test2222 name="+name+"; age="+age);
    }
    @DataProvider(name="methodData")
    public Object[][] methodDataTest(Method method){
        Object[][] result=null;
        if(method.getName().equals("test1")){
            result=new Object[][]{
                    {"zhangsan",20},
                    {"lisi",30}
            };
        }else if(method.getName().equals("test2")){
            result=new Object[][]{
                    {"wangwu",60},
                    {"zhaoliu",70}
            };
        }
        return result;
    }


}
