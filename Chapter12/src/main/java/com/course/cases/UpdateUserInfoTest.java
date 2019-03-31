package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.UpdateUserInfoCase;
import com.course.model.User;
import com.course.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.course.config.TestConfig.store;

public class UpdateUserInfoTest {
    @Test(dependsOnGroups = "loginTrue",description="更改用户信息")
    public void updateUserInfo() throws IOException, InterruptedException {
        SqlSession session= DatabaseUtil.getSqlSession();
        UpdateUserInfoCase updateUserInfoCase=session.selectOne("updateUserInfoCase",1);
        System.out.println(updateUserInfoCase.toString());
        System.out.println(TestConfig.updateUserInfoUrl);

//        下边为写完接口的代码
        int result=getResult(updateUserInfoCase);

//        获取更新后的结果
        Thread.sleep(2000);
        User user=session.selectOne(updateUserInfoCase.getExpected(),updateUserInfoCase);
        System.out.println(user.toString());


        Assert.assertNotNull(user);
        Assert.assertNotNull(result);
    }


    @Test(dependsOnGroups = "loginTrue",description = "删除用户")
    public void deleteUser() throws IOException, InterruptedException {
        SqlSession session=DatabaseUtil.getSqlSession();
        UpdateUserInfoCase updateUserInfoCase=session.selectOne("updateUserInfoCase",2);
        System.out.println(updateUserInfoCase);
        System.out.println(TestConfig.updateUserInfoUrl);

//        下边为写完接口的代码
        int result=getResult(updateUserInfoCase);

        Thread.sleep(2000);
        User user=session.selectOne(updateUserInfoCase.getExpected(),updateUserInfoCase);
        System.out.println(user.toString());
        Assert.assertNotNull(user);
        Assert.assertNotNull(result);
    }



    private int getResult(UpdateUserInfoCase updateUserInfoCase) throws IOException {
        HttpPost post=new HttpPost(TestConfig.updateUserInfoUrl);
        JSONObject param=new JSONObject();
        param.put("userName",updateUserInfoCase.getUserName());
        param.put("id",updateUserInfoCase.getUserId());
        param.put("isDelete",updateUserInfoCase.getIsDelete());
        post.setHeader("Content-Type","application/json");
        StringEntity entity=new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        TestConfig.defaultHttpClient.setCookieStore(store);
        String result;
        HttpResponse response=TestConfig.defaultHttpClient.execute(post);
        result= EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
        return Integer.parseInt(result);
    }
}
