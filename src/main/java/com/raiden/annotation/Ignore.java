package com.raiden.annotation;

import java.lang.annotation.*;

/**
 * 忽略该属性
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Ignore {
}
