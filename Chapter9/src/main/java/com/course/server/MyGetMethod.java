package com.course.server;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@Api(value="/",description = "这是我全部的get方法")
public class MyGetMethod {
    @RequestMapping( value="/getCookies",method=RequestMethod.GET)
    @ApiOperation(value="通过这个方法可以获取到cookies",httpMethod ="GET")
     String getCookies(HttpServletResponse response){
//    HttpServletResponse 装响应信息的类
//    HttpServletRequest 装请求信息的类
//        返回cookie信息的get接口开发
        Cookie cookie=new Cookie("status","100");
        response.addCookie(cookie);
        return "恭喜你获得cookies信息";
    }
/*
*要求客户端携带cookie才能访问
*这是一个需要携带cookie信息才能访问的get请求
 */
    @RequestMapping(value="/get/with/cookies",method=RequestMethod.GET)
    @ApiOperation(value="需要携带cookie信息才能访问这个方法",httpMethod = "GET")
    public String getWithCookies(HttpServletRequest request){
        Cookie[] cookies=request.getCookies();
        if(Objects.isNull(cookies)){
             return "你必须携带cookies信息来访问";
        }
        for(Cookie cookie:cookies){
           if(cookie.getName().equals("status")){
               if(!cookie.getValue().equals("100")){
                   return "status 的value错误";
               }
           }else if(cookie.getName().equals("login")){
               if(!cookie.getValue().equals("true")){
                   return "login 的value错误";
               }
           }else if(cookie.getName().equals("code")){
               if(!cookie.getValue().equals("200")){
                   return "code 的value错误";
               }
           }else{
               return "cookies信息错误";
           }
//            if(cookie.getName().equals("status")&&cookie.getValue().equals("100")){
//                return "这是一个需要携带cookies信息才能访问的get请求";
//            }
//            else{
//                return "cookie信息错误";
//            }
        }
        return "访问成功:这是一个需要携带cookies信息才能访问的get请求";
    }
    /*
    * 开发一个需要携带参数才能访问的get请求
    * 第一种实现方式，url：/get/with/param?key=value&&key=value
    * eg:localhost:8888/get/with/param?start=1&&end=9
    * 我们来模拟获取商品列表*/
    @RequestMapping(value="get/with/param",method=RequestMethod.GET)
    @ApiOperation(value="携带参数才能访问的方法1",httpMethod = "GET")
    public Map<String,Integer> getList(@RequestParam Integer start, @RequestParam Integer end){
        Map<String,Integer> myList=new HashMap<>();
        myList.put("鞋子",100);
        myList.put("干脆面",50);
        myList.put("衬衫",900);
        return myList;
    }
    /*
    * 第二种需要携带参数访问的get请求
    * url：ip:port/get/with/param/{start}/{end}
    * eg:localhost:8888/get/with/param/2/900
    * */
    @RequestMapping(value="/get/with/param/{start}/{end}")
    @ApiOperation(value="携带参数才能访问的方法2",httpMethod ="GET")
    public Map myGetList(@PathVariable Integer start,@PathVariable Integer end){
        Map<String,Integer> myList=new HashMap<>();
        myList.put("鞋子",300);
        myList.put("干脆面",150);
        myList.put("衬衫",900);
        return myList;
    }

}
