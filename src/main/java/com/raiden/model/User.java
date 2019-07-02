package com.raiden.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.raiden.fastjson.annotation.DataToString;
import com.raiden.fastjson.annotation.FirstLetterCapitalized;
import com.raiden.fastjson.annotation.Ignore;
import lombok.Data;

import java.math.BigDecimal;

@Data
@FirstLetterCapitalized
public class User {

    @Ignore
    private String name;
    @DataToString(newScale = 3,roundingMode = BigDecimal.ROUND_HALF_UP)
    private BigDecimal age;
    @JSONField(name = "userId")
    private String id;

    private boolean girl;

    @DataToString(newScale = 3,roundingMode = BigDecimal.ROUND_HALF_UP)
    private double salary;
}
