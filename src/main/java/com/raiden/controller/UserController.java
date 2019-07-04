package com.raiden.controller;

import com.raiden.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class UserController {

    @GetMapping("getUser")
    public User getUser(){
        User user = new User();
        user.setId("1");
        user.setName("zhangsan");
        user.setSalary(123.00004273213214);
        user.setAge(new BigDecimal(113.880));
        user.setUserAge(new BigDecimal(12.880));
        user.setUserId("7758521");
        return user;
    }
}
