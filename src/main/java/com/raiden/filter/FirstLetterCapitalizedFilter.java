package com.raiden.filter;

import com.alibaba.fastjson.serializer.NameFilter;
import com.raiden.annotation.FirstLetterCapitalized;
import com.raiden.annotation.Ignore;
import com.raiden.annotation.Range;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;

public class FirstLetterCapitalizedFilter implements NameFilter {
    @Override
    public String process(Object o, String name, Object o1) {
        Class<?> clazz = o.getClass();
        String fieldName = Boolean.class.isInstance(o1)? "is" + firstLetterCapitalized(name) : name;
        if (clazz.isAnnotationPresent(FirstLetterCapitalized.class)){
            FirstLetterCapitalized firstLetterCapitalized = clazz.getAnnotation(FirstLetterCapitalized.class);
            if (firstLetterCapitalized.value() == Range.ALL){
                return firstLetterCapitalized(fieldName);
            }else {
                try {
                    Field field = o.getClass().getDeclaredField(name);
                    if (!field.isAnnotationPresent(Ignore.class)){
                        return firstLetterCapitalized(fieldName);
                    }
                } catch (NoSuchFieldException e) {
                    //如果找不到就是自定义名称 忽略此报错
                }
            }
        }
        return fieldName;
    }


    private String firstLetterCapitalized(String name){
        if (StringUtils.isEmpty(name)){
            return null;
        }
        char[] chars = name.toCharArray();
        StringBuilder builder = new StringBuilder();
        boolean sign = true;
        for (char c : chars){
            if (96< c && c < 123 && sign){
                c -=32;
                builder.append(c);
                sign = false;
            }else {
                builder.append(c);
            }
        }
        return builder.toString();
    }
}
