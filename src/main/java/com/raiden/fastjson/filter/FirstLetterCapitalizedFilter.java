package com.raiden.fastjson.filter;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.NameFilter;
import com.raiden.fastjson.util.FieldNameUtils;
import com.raiden.fastjson.annotation.FirstLetterCapitalized;
import com.raiden.fastjson.annotation.Ignore;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;

/**
 * @创建人:Raiden
 * @Descriotion:该过滤器针对属性名，首字母大写过滤器
 * @Date:Created in 9:54 2019/6/22
 * @Modified By:
 */
public class FirstLetterCapitalizedFilter implements NameFilter {
    @Override
    public String process(Object instance, String name, Object value) {
        if (null == instance || StringUtils.isEmpty(name)){
            return name;
        }
        Class<?> clazz = instance.getClass();
        //判断下是不是布尔值 如果是首字母大写并在前面加上is
        String fieldName = Boolean.class.isInstance(value)? "is" + FieldNameUtils.firstLetterCapitalized(name) : name;
        //判断类上是否有首字母大写的注解
        if (clazz.isAnnotationPresent(FirstLetterCapitalized.class)){
            try {
                //通过名称获得改域 如果使用了JSONField自定义域名会出现找不到报错的情况
                Field field = clazz.getDeclaredField(name);
                //看看域上是否有忽略的注解 如果有则不改变其属性名
                if (!field.isAnnotationPresent(Ignore.class) && !field.isAnnotationPresent(JSONField.class)){
                    //将属性名首字母大写返回
                    return FieldNameUtils.firstLetterCapitalized(fieldName);
                }
            } catch (NoSuchFieldException e) {
                //用JSONField自定义属性名称可能会找不到域 因此忽略此报错 返回自定义的名称就行
                return name;
            }
        }
        return fieldName;
    }

    private String checkBoolean(Class<?> clazz,String name,Object value){
        if (!name.toLowerCase().startsWith("is")){
            return "is" + FieldNameUtils.firstLetterCapitalized(name);
        }
        return name;
    }
}
