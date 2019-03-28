package com.course.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.ResourceBundle;

public class MyCookiesForJsonPost {
    private String url;
    private ResourceBundle bundle;
    private CookieStore store;
    @BeforeTest
    public void beforeTest(){
//        读取配置文件
        this.bundle=ResourceBundle.getBundle("application");
        this.url=this.bundle.getString("dev.url");
    }
    @Test
    public void testGetCookies() throws IOException {
        String result;
//       从配置文件中拼接测试的url
        String uri=this.bundle.getString("getCookies.uri");
        String testUrl=this.url+uri;
//      声明一个get方法
        HttpGet get= new HttpGet(testUrl);
//        声明一个client来执行get方法
        DefaultHttpClient client=new DefaultHttpClient();
//        执行get方法并获取响应信息
        HttpResponse response=client.execute(get);
//       获取cookie信息
        this.store=client.getCookieStore();
        List<Cookie> cookieList=store.getCookies();
//        输出cookie信息
        for (Cookie cookie:cookieList){
            String name=cookie.getName();
            String value=cookie.getValue();
            System.out.println("cookie name is "+name+"; cookie value is "+value);
        }
    }
    @Test(dependsOnMethods = {"testGetCookies"})
      public void testPostWithCookies() throws IOException {
//  从配置文件中拼接测试的url
        String uri=this.bundle.getString("test.post.with.cookies");
        String testUrl=this.url+uri;
//      声明一个client对象，用来进行方法的执行
        DefaultHttpClient client=new DefaultHttpClient();
//       声明一个post方法
        HttpPost post=new HttpPost(testUrl);
//        添加json参数
        JSONObject param=new JSONObject();
        param.put("name","zhangsan");
        param.put("age","19");
//       设置参数header
        post.setHeader("Content-Type","application/json");
//        将json参数信息添加到方法中
        StringEntity entity=new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
//        设置cookie信息
        client.setCookieStore(this.store);
//        声明一个对象来进行响应结果的存储
        String result;
//        执行post方法
        HttpResponse response=client.execute(post);
//        获取响应结果
        result= EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
//        处理结果，就是判断返回结果是否符合预期
//        将返回的响应结果字符串转化为json对象
        JSONObject resultJson=new JSONObject(result);
//        获取结果值
        String status=resultJson.getString("status");
        String text=resultJson.getString("text");
//      具体判断返回结果的值
        Assert.assertEquals(status,"1");
        Assert.assertEquals(text,"success");
    }
}
