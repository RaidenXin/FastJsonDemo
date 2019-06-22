package com.raiden.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.raiden.annotation.FirstLetterCapitalized;
import com.raiden.annotation.Ignore;
import com.raiden.annotation.Range;

@FirstLetterCapitalized(Range.PART)
public class User {

    @Ignore
    private String name;
    @Ignore
    private String age;
    @JSONField(name = "userId")
    private String id;
    @Ignore
    private boolean girl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
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
