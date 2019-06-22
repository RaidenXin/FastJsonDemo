package com.raiden.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface FirstLetterCapitalized {

    Range value() default Range.ALL;
}
