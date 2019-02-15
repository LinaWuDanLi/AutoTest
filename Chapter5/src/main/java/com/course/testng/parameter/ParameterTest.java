package com.course.testng.parameter;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParameterTest {
    @Test
    @Parameters({"name","age","height"})
    public void paramTest1(String name,int age,int height){
        System.out.println("name is "+name+", age is "+age+", height is "+height);
    }
}
