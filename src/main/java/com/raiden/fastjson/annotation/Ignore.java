package com.raiden.fastjson.annotation;

import java.lang.annotation.*;

/**
 * @创建人:Raiden
 * @Descriotion:忽略该属性注解
 * @Date:Created in 9:54 2019/6/22
 * @Modified By:
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Ignore {
}
