package com.raiden.fastjson.annotation;

import java.lang.annotation.*;

/**
 * @创建人:Raiden
 * @Descriotion:该注解的作用是让FastJson序列化的时候,将所有熟悉的首字母大写
 * @Date:Created in 9:54 2019/6/22
 * @Modified By:
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface FirstLetterCapitalized {
}
