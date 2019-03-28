package com.course.model;

import lombok.Data;

@Data
public class UpdateUserInfoCase {
    private int id;
    private int userId;
    private String userName;
    private int age;
    private String sex;
    private int permission;
    private int isDelete;
    private String expected;
}
