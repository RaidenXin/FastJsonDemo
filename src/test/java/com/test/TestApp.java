package com.test;

import com.alibaba.fastjson.JSON;
import com.raiden.model.User;
import org.junit.jupiter.api.Test;

public class TestApp {

    @Test
    public void test(){
        User user = new User();
        user.setId("1");
        user.setName("zhangsan");
        user.setAge("12");
        System.out.println(JSON.toJSONString(user));
    }
}
