package com.course.controller;

import com.course.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@Api(value="v1",description ="这是我的第一个版本的demo" )
@RequestMapping(value="v1")
public class Demo {
//    首先获取一个执行sql语句的对象
    @Autowired
    private SqlSessionTemplate template;
    @ApiOperation(value="可以获取到用户数",httpMethod = "GET")
    @RequestMapping(value="/getUserCount",method= RequestMethod.GET)
    public int getUserCount(){
        return  template.selectOne("getUserCount");
    }
    @RequestMapping(value="/addUser",method = RequestMethod.POST)
    @ApiOperation(value="添加用户",httpMethod = "POST")
    public int addUser(@RequestBody User user){
      return template.insert("addUser",user);
    }

    @RequestMapping(value="/updateUser",method=RequestMethod.POST)
    @ApiOperation(value="更新用户",httpMethod = "POST")
    public int updateUser(@RequestBody User user){
        return template.update("updateUser",user);
    }

    @RequestMapping(value="/delUser",method=RequestMethod.DELETE)
    @ApiOperation(value="删除用户",httpMethod = "DELETE")
    public int delUser(@RequestParam int id){
        return template.delete("delUser",id);
    }
}
