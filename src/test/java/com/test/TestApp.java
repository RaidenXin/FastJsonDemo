package com.test;

import com.alibaba.fastjson.JSON;
import com.raiden.model.User;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class TestApp {

    @Test
    public void test(){
        Double d = 0.0;
        print(d);
        boolean b1 = false;
        Boolean b2 = true;
        System.out.println("b1:" + Boolean.class.isInstance(b1));
        System.out.println("b2:" + Boolean.class.isInstance(b2));
    }

    private void print(Object o){
        if (o instanceof Double){
            System.out.println(o.toString());
        }
    }
}
