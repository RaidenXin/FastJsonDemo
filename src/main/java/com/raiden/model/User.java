package com.raiden.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.raiden.fastjson.annotation.DataToString;
import com.raiden.fastjson.annotation.FirstLetterCapitalized;
import com.raiden.fastjson.annotation.Ignore;

import java.math.BigDecimal;

@FirstLetterCapitalized(Range.PART)
public class User {

    @Ignore
    private String name;
    @DataToString(newScale = 3,roundingMode = BigDecimal.ROUND_HALF_UP)
    private BigDecimal age;
    @JSONField(name = "userId")
    private String id;
    private boolean girl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getAge() {
        return age;
    }

    public void setAge(BigDecimal age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGirl() {
        return girl;
    }

    public void setGirl(boolean girl) {
        this.girl = girl;
    }
}
