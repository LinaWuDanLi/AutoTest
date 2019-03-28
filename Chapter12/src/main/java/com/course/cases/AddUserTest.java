package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.AddUserCase;
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

public class AddUserTest {

    @Test(description = "添加用户接口")
    public void  addUser() throws IOException, InterruptedException {
//        查询数据库
        SqlSession session= DatabaseUtil.getSqlSession();
        AddUserCase addUserCase=session.selectOne("addUserCase",10);
        System.out.println(addUserCase.toString());
        System.out.println(TestConfig.addUserUrl);
        String result=getResult(addUserCase);
//        查询用户是否添加成功
        Thread.sleep(4000);
        User user=session.selectOne("addUser",addUserCase);
        System.out.println(user.toString());
//        删除上面添加的用户
        session.delete("deleteAddedUser",addUserCase.getId());
//        进行增删改时需要提交事务
        session.commit();
//        判断返回的结果是否符合预期
        Assert.assertEquals(addUserCase.getExpected(),result);
    }
   private String getResult(AddUserCase addUserCase) throws IOException {
//        下面的代码为写完mock接口的测试代码
        HttpPost post=new HttpPost(TestConfig.addUserUrl);
        JSONObject param=new JSONObject();
        param.put("id",addUserCase.getId());
        param.put("userName",addUserCase.getUserName());
        param.put("password",addUserCase.getPassword());
        param.put("sex",addUserCase.getSex());
        param.put("age",addUserCase.getAge());
        param.put("permission",addUserCase.getPermission());
        param.put("isDelete",addUserCase.getIsDelete());
       System.out.println("addUserCase.getExpected()="+addUserCase.getExpected());
        post.setHeader("Content-Type","application/json");
        StringEntity entity= new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
//        设置cookie信息
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);
        String result;
        HttpResponse response=TestConfig.defaultHttpClient.execute(post);
        result= EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println("result="+result);
        return result;
    }
}
