package com.course.server;

import com.course.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value="/",description = "这是我全部的post请求")
@RequestMapping(value="/v1")
public class MyPostMethod {
//   这个变量是来装我们的cookie信息的
    private static Cookie cookies;
//   用户登录成功获取到cookies，然后再访问其他接口获取到列表
    @RequestMapping(value="/login",method = RequestMethod.POST)
    @ApiOperation(value="登录接口，成功后获取cookie信息",httpMethod = "POST")
//    这个方法的请求数据是以Parameters的形式
    public String login(HttpServletResponse response,
                        @RequestParam(value="userName",required =true) String userName,
                        @RequestParam(value="password",required=true) String password){
        if(userName.equals("zhangsan")&&password.equals("123456")){
            cookies=new Cookie("status","1000");
            response.addCookie(cookies);
            return "恭喜你登录成功！";
        }
        return "登录失败,用户名或者密码错误";
    }
//    验证cookie信息正确后返回用户列表信息
    @RequestMapping(value="/getUserList",method = RequestMethod.POST)
    @ApiOperation(value="获取用户列表",httpMethod = "POST")
//    这个方法的请求数据是以Body Data的Json格式
    public String getUserList(HttpServletRequest request,@RequestBody User u){
        User user=new User();
//        获取cookies
        Cookie[] cookies=request.getCookies();
//        验证cookie是否合法
        for(Cookie c:cookies){
            if(c.getName().equals("status")&&c.getValue().equals("1000")&&u.getUsername().equals("zhangsan")&&u.getPassword().equals("123456")){
                user.setName("lisi");
                user.setAge("18");
                user.setSex("男");
                return user.toString();
            }
        }
        return "参数不合法";
    }
}
