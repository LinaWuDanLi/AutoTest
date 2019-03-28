package com.course.httpclient.cookies;

import netscape.javascript.JSObject;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

public class MyCookiesForGet {
    private String url;
    private ResourceBundle bundle;
    private CookieStore store;
    @BeforeTest
    public void beforeTest(){
//        读取配置文件
        bundle=ResourceBundle.getBundle("application");
        url=bundle.getString("dev.url");
    }
    @Test
    public void testGetCookies() throws IOException {
        String result;
//        从配置文件中拼接测试的url
        String uri=bundle.getString("getCookies.uri");
        String testUrl=this.url+uri;
//        测试逻辑代码书写
        HttpGet get=new HttpGet(testUrl);
        DefaultHttpClient client=new DefaultHttpClient();
        HttpResponse response=client.execute(get);
        result= EntityUtils.toString(response.getEntity());
//        System.out.println(result);
//        获取cookies信息
       this.store=client.getCookieStore();
        List<Cookie> cookieList=store.getCookies();
//        输出cookies
        for(Cookie cookie:cookieList){
            String name=cookie.getName();
            String value=cookie.getValue();
            System.out.println("cookie name ="+name+"; cookie value ="+value);
        }
    }
    @Test(dependsOnMethods = {"testGetCookies"})
    public void testGetWithCookies() throws IOException {
        String uri=bundle.getString("test.get.with.cookies");
        String testUrl=this.url+uri;
        System.out.println("testUrl="+testUrl);
        HttpGet get=new HttpGet(testUrl);
        DefaultHttpClient client=new DefaultHttpClient();
//        设置cookie 信息
        client.setCookieStore(this.store);
        HttpResponse response=client.execute(get);
//        获取响应状态码
        int statusCode=response.getStatusLine().getStatusCode();
        ProtocolVersion version=response.getStatusLine().getProtocolVersion();
        String reasonPhrase=response.getStatusLine().getReasonPhrase();
        System.out.println("status code ="+statusCode+";ProtocolVersion="+version+";ReasonPhrase"+reasonPhrase);
        if(statusCode==200){
             String result=EntityUtils.toString(response.getEntity());
            System.out.println(result);
        }
    }
}
