package com.raiden.model;

import com.raiden.fastjson.annotation.DataToString;
import com.raiden.fastjson.annotation.FirstLetterCapitalized;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @创建人:Raiden
 * @Descriotion:
 * @Date:Created in 22:48 2019/7/4
 * @Modified By:
 */
@Getter
@Setter
public abstract class Base {

    private String userId;
    @DataToString(newScale = 3,roundingMode = BigDecimal.ROUND_HALF_UP)
    private BigDecimal userAge;
}
