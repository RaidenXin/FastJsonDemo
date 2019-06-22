package com.raiden.annotation;

import java.lang.annotation.*;

/**
 * 该注解的作用是让FastJson序列化的时候 将所有熟悉的首字母大写
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface FirstLetterCapitalized {

    //作用范围的属性 默认是全部属性值
    Range value() default Range.ALL;
}
