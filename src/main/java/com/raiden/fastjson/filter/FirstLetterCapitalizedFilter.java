package com.raiden.fastjson.filter;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.NameFilter;
import com.raiden.fastjson.util.FieldNameUtils;
import com.raiden.fastjson.annotation.FirstLetterCapitalized;
import com.raiden.fastjson.annotation.Ignore;
import com.raiden.fastjson.util.FieldUtils;
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
        //判断类上是否有首字母大写的注解
        if (clazz.isAnnotationPresent(FirstLetterCapitalized.class)){
            //是否是boolean实例
            boolean isBooleanInstance = Boolean.class.isInstance(value);
            //通过名称获得改域 如果使用了JSONField自定义域名会出现找不到的情况
            Field field = FieldUtils.getField(clazz, name);
            if (null != field){
                //看看域上是否有忽略的注解和JSONField注解 或者有 忽略字段注解 如果有则不改变其属性名
                if (field.isAnnotationPresent(Ignore.class) || field.isAnnotationPresent(JSONField.class)){
                    return name;
                }else{
                    //判断下是不是布尔值 如果是切name不是以is开头的 首字母大写并在前面加上is
                    if (isBooleanInstance && !name.toLowerCase().startsWith("is")){
                        return "Is" + FieldNameUtils.firstLetterCapitalized(name);
                    }
                    //将属性名首字母大写返回
                    return FieldNameUtils.firstLetterCapitalized(name);
                }
            }
            //用JSONField自定义属性名称可能会找不到域 因此忽略此报错 返回自定义的名称就行
            return checkBoolean(clazz, name, isBooleanInstance);
        }
        return name;
    }

    private String checkBoolean(Class<?> clazz, String name,boolean isBooleanInstance){
        if (isBooleanInstance){
            try {
                //布尔值找不到域 存在2种可能1是用了JSONField注解 2 是使用了小写的is开头 如 isShow 这里的name会是show
                String fieldName = "is" + FieldNameUtils.firstLetterCapitalized(name);
                //所以拼装好名字之后 在尝试找一次域
                clazz.getDeclaredField(fieldName);
                //如果找到了返回 带is的
                return fieldName;
            } catch (NoSuchFieldException e) {
                //如果还是获取不到证明使用的是 JSONField注解
                return name;
            }
        }
        return name;
    }
}
