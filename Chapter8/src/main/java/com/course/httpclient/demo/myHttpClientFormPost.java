package com.course.httpclient.demo;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import sun.net.www.http.HttpClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class myHttpClientFormPost {
    private ResourceBundle bundle;
    private String url;
    @BeforeTest
    public void beforeTest(){
//        读取配置文件
        this.bundle= ResourceBundle.getBundle("application");
        this.url=this.bundle.getString("dev.url");
    }
    @Test
    public void testFormPost() throws IOException {
//        拼接测试的url
        String uri=this.bundle.getString("form.post");
        String testUrl=this.url+uri;
//        声明一个client对象
        DefaultHttpClient client=new DefaultHttpClient();
//        声明一个post方法
        HttpPost post=new HttpPost(testUrl);
//        设置表单参数
        List<NameValuePair> form=new ArrayList<>();
        form.add(new BasicNameValuePair("name","lisi"));
        form.add(new BasicNameValuePair("age","19"));
        UrlEncodedFormEntity entity=new UrlEncodedFormEntity(form, Consts.UTF_8);
//        将表单参数添加到方法中
        post.setEntity(entity);
//       将header信息添加到方法中
        post.setHeader("Content-Type","x-www-form-urlencoded");
//        执行post方法
        HttpResponse response=client.execute(post);
//        输出响应结果
        String result= EntityUtils.toString(response.getEntity());
        System.out.println(result);
//        获取响应码
        int statusCode=response.getStatusLine().getStatusCode();
//        将响应结果转化为json格式
        JSONObject jsonResult=new JSONObject(result);
        String status=jsonResult.getString("status");
        String message=jsonResult.getString("message");
//        判断响应结果是否正确
        Assert.assertEquals(status,"1");
        Assert.assertEquals(message,"success");
        Assert.assertEquals(statusCode,200);



    }
}
