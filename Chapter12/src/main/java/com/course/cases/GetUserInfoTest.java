package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.GetUserInfoCase;
import com.course.model.User;
import com.course.utils.DatabaseUtil;
import com.mysql.cj.xdevapi.JsonArray;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.course.config.TestConfig.store;

public class GetUserInfoTest {
    @Test(dependsOnGroups = "loginTrue",description = "获取userID为1的用户信息")
     public void getUserInfo() throws IOException, InterruptedException {
        SqlSession session= DatabaseUtil.getSqlSession();
        GetUserInfoCase getUserInfoCase=session.selectOne("getUserInfoCase",1);
        System.out.println(getUserInfoCase.toString());
        System.out.println(TestConfig.getUserInfoUrl);

//        下边为写完接口的代码
       JSONArray resultJson=getJsonResult(getUserInfoCase);
//        List resultList=getJsonResult(getUserInfoCase);

        Thread.sleep(2000);
        User user=session.selectOne(getUserInfoCase.getExpected(),getUserInfoCase);
        System.out.println("自己查库获取用户信息:"+user.toString());

        List userList=new ArrayList();
        userList.add(user);
        JSONArray jsonArray=new JSONArray(userList);
        System.out.println("查询数据库获取用户信息："+jsonArray.toString());
        System.out.println("调用接口获取用户信息resultJson:"+resultJson.toString());
        JSONArray jsonArray1=new JSONArray(resultJson.getString(0));
        System.out.println("调用接口获取用户信息jsonArray1:"+jsonArray1.toString());
        Assert.assertEquals(jsonArray.toString(),jsonArray1.toString());
//        System.out.println("查询数据库获取用户信息："+userList.toString());
//        System.out.println("调用接口获取用户信息:"+resultList.toString());
//        Assert.assertEquals(userList,resultList);

    }
    private JSONArray getJsonResult(GetUserInfoCase getUserInfoCase) throws IOException {
        HttpPost post=new HttpPost(TestConfig.getUserInfoUrl);
        JSONObject param =new JSONObject();
        param.put("id",getUserInfoCase.getUserId());
        post.setHeader("Content-Type","application/json");
        StringEntity entity=new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        TestConfig.defaultHttpClient.setCookieStore(store);
        String result;
        HttpResponse response=TestConfig.defaultHttpClient.execute(post);
        result= EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println("调用接口result:"+result);
        List resultList= Arrays.asList(result);
        System.out.println("resultList="+resultList);
        JSONArray array=new JSONArray(resultList);
        System.out.println("JSONArray="+array.toString());
        return array;
//        return resultList;

    }
}
