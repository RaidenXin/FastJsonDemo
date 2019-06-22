package com.raiden.filter;

import com.alibaba.fastjson.serializer.NameFilter;
import com.raiden.annotation.FirstLetterCapitalized;
import com.raiden.annotation.Ignore;
import com.raiden.annotation.Range;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;

/**
 *该过滤器针对属性名
 * 首字母大写过滤器
 */
public class FirstLetterCapitalizedFilter implements NameFilter {
    @Override
    public String process(Object o, String name, Object o1) {
        Class<?> clazz = o.getClass();
        //判断下是不是布尔值 如果是首字母大写并在前面加上is
        String fieldName = Boolean.class.isInstance(o1)? "is" + firstLetterCapitalized(name) : name;
        //判断类上是否有首字母大写的注解
        if (clazz.isAnnotationPresent(FirstLetterCapitalized.class)){
            //获取注解
            FirstLetterCapitalized firstLetterCapitalized = clazz.getAnnotation(FirstLetterCapitalized.class);
            //获取注解的属性 Range.ALL指的是全部
            if (firstLetterCapitalized.value() == Range.ALL){
                return firstLetterCapitalized(fieldName);
            }else {
                //如果只是部分属性
                try {
                    //通过名称获得改域
                    Field field = o.getClass().getDeclaredField(name);
                    //看看域上是否有忽略的注解 如果有则不改变其属性名
                    if (!field.isAnnotationPresent(Ignore.class)){
                        //将属性名首字母大写返回
                        return firstLetterCapitalized(fieldName);
                    }
                } catch (NoSuchFieldException e) {
                    //用JSONField自定义属性名称可能会找不到域 因此忽略此报错
                }
            }
        }
        return fieldName;
    }

    /**
     * 首字母大写的方法
     * @param name
     * @return
     */
    private String firstLetterCapitalized(String name){
        if (StringUtils.isEmpty(name)){
            return null;
        }
        char[] chars = name.toCharArray();
        StringBuilder builder = new StringBuilder();
        char c = chars[0];
        c -= 32;
        chars[0] = c;
        builder.append(chars);
        return builder.toString();
    }

}
