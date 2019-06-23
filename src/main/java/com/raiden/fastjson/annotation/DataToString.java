package com.raiden.fastjson.annotation;

import java.lang.annotation.*;
import java.math.BigDecimal;

/**
 * @创建人:Raiden
 * @Descriotion:用于解决BigDecimal序列化精度问题，将BigDecimal转成String
 * @Date:Created in 9:54 2019/6/22
 * @Modified By:
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DataToString {
    //默认保留3位小数
    int newScale() default 3;
    //默认使用四舍五入
    int roundingMode() default BigDecimal.ROUND_HALF_UP;
}
