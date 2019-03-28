package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.GetUserListCase;
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

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class GetUserInfoListTest {
    @Test(dependsOnGroups = "loginTrue",description = "获取性别为女的用户的信息")
    public void getUserInfoList() throws IOException, InterruptedException {
        SqlSession session= DatabaseUtil.getSqlSession();
        GetUserListCase getUserListCase=session.selectOne("getUserListCase",1);
        System.out.println(getUserListCase.toString());
        System.out.println(TestConfig.getUserInfoUrl);
//        下边为写完接口的代码,这是接口返回的user
        JSONArray resultJson=getJsonResult(getUserListCase);
        Thread.sleep(2000);
//        这是查询数据库的user
        List <User> userList=session.selectList(getUserListCase.getExpected(),getUserListCase);
        for(User u:userList){
            System.out.println("list获取的user:"+u.toString());
        }
        JSONArray userListJson=new JSONArray(userList);
        Assert.assertEquals(resultJson.length(),userListJson.length());
        for(int i=0;i<resultJson.length();i++){
            JSONObject expected=(JSONObject)userListJson.get(i);
            JSONObject actual=(JSONObject)resultJson.get(i);
            Assert.assertEquals(actual.toString(),expected.toString());
        }


    }
    private JSONArray getJsonResult(GetUserListCase getUserListCase) throws IOException {
        HttpPost post =new HttpPost(TestConfig.getUserInfoUrl);
        JSONObject param=new JSONObject();
        param.put("sex",getUserListCase.getSex());
        post.setHeader("Content-Type","application/json");
        StringEntity entity=new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);
        String result;
        HttpResponse response=TestConfig.defaultHttpClient.execute(post);
        result= EntityUtils.toString(response.getEntity(),"utf-8");
//        将响应实体result转成JsonArray
        JSONArray jsonArray=new JSONArray(result);
        System.out.println("调用接口list result:"+result);
        return jsonArray;
    }
}
