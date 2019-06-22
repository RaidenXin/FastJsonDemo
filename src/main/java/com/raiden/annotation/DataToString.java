package com.raiden.annotation;

import java.lang.annotation.*;
import java.math.BigDecimal;

/**
 * 用于解决BigDecimal序列化精度问题
 * 将BigDecimal转成String
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DataToString {
    //默认保留3位小数
    int newScale() default 3;
    //默认使用四舍五入
    int roundingMode() default BigDecimal.ROUND_HALF_UP;
}
