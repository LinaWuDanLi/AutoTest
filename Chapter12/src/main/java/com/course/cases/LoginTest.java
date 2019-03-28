package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.model.LoginCase;
import com.course.utils.ConfigFile;
import com.course.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.CookieList;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
;


public class LoginTest {
    @BeforeTest(groups ="loginTrue", description = "测试准备工作，获取httpclient对象")
    public void beforeTest() {
        TestConfig.loginUrl = ConfigFile.getUrl(InterfaceName.LOGIN);
        TestConfig.addUserUrl = ConfigFile.getUrl(InterfaceName.ADDUSERINFO);
        TestConfig.getUserInfoUrl = ConfigFile.getUrl(InterfaceName.GETUSERINFO);
        TestConfig.getUserListUrl = ConfigFile.getUrl(InterfaceName.GETUSERLIST);
        TestConfig.updateUserInfoUrl = ConfigFile.getUrl(InterfaceName.UPDATEUSERINFO);
        TestConfig.defaultHttpClient = new DefaultHttpClient();
    }

    @Test(groups ="loginTrue", description = "用户成功登陆接口")
    public void loginTrue() throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();
//        o:1 表示 “parameterType="Integer"”的值为1， 即id=1
        LoginCase loginCase = session.selectOne("loginCase", 1);
        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginUrl);

//        下边的代码为写完接口的测试代码
//        将从数据库中查询的结果loginCase作为getResult的参数
        String result = getResult(loginCase);
//        处理结果，判断返回的结果是否符合预期
        Assert.assertEquals(loginCase.getExpected(), result);
    }

    @Test(description = "用户登录失败接口")
    public void loginFalse() throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();
//       o:2 表示 “parameterType="Integer"”的值为2， 即id=2,id为2不是有效用户
        LoginCase loginCase = session.selectOne("loginCase", 2);
        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginUrl);
        //        下边的代码为写完接口的测试代码
        String result = getResult(loginCase);
//        处理结果，判断返回的结果是否符合预期
        Assert.assertEquals(loginCase.getExpected(), result);
    }

    private String getResult(LoginCase loginCase) throws IOException {
//       下边的代码为写完接口的测试代码
        HttpPost post = new HttpPost(TestConfig.loginUrl);
        JSONObject param = new JSONObject();
        param.put("userName", loginCase.getUserName());
        param.put("password", loginCase.getPassword());
//        设置请求头信息header
        post.setHeader("Content-Type", "application/json");
//        将参数信息添加到方法中
        String bodyJson=param.toString();
        StringEntity entity = new StringEntity(bodyJson,"utf-8");
        System.out.println(entity.toString());
        post.setEntity(entity);
//        声明一个对象来进行相应结果的存储
        String result;
//        执行post方法
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);

//       获取响应结果
        result = EntityUtils.toString(response.getEntity());
        System.out.println(result);
//        存储返回的cookie信息
        TestConfig.store = TestConfig.defaultHttpClient.getCookieStore();
        System.out.println(TestConfig.store);
        return result;
    }
}