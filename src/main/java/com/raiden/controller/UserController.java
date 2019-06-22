package com.raiden.controller;

import com.raiden.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("getUser")
    public User getUser(){
        User user = new User();
        user.setId("1");
        user.setName("zhangsan");
        user.setAge("12");
        return user;
    }
}
