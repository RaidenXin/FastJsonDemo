package com.raiden.fastjson.filter;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.raiden.fastjson.util.FieldNameUtils;
import com.raiden.fastjson.annotation.DataToString;
import com.raiden.fastjson.annotation.FirstLetterCapitalized;

import java.lang.reflect.Field;
import java.math.BigDecimal;
/**
 * @创建人:Raiden
 * @Descriotion:自定义BigDecimal序列化，精度值处理过滤器
 * @Date:Created in 9:54 2019/6/22
 * @Modified By:
 */
public class DataToStringFilter implements ValueFilter {
    @Override
    public Object process(Object instance, String name, Object value) {
        //判断下实例是不是BigDecimal
        if (value instanceof BigDecimal){
            Class<?> instanceClazz = instance.getClass();
            //如果存在这个注解说明类名可能被更改
            if (instanceClazz.isAnnotationPresent(FirstLetterCapitalized.class)){
                name = FieldNameUtils.firstLetterLowercase(name);
            }
            try {
                //如果是则获取该域 如果使用了JSONField自定义域名会出现找不到报错的情况
                Field field = instance.getClass().getDeclaredField(name);
                //检查该域是否有 DataToString注解
                if (field.isAnnotationPresent(DataToString.class)){
                    return valueFormat(value, field);
                }
            } catch (NoSuchFieldException e) {
                //如果报错了说明属性名称不正确 要获取真正的属性
                Field field = getFieldName(instanceClazz, name);
                if (null != field){
                    return valueFormat(value, field);
                }
            }
        }
        return value;
    }

    /**
     * 属性格式化
     * @param value
     * @param field
     * @return
     */
    private Object valueFormat(Object value,Field field){
        //获取DataToString注解
        DataToString dataToString = field.getAnnotation(DataToString.class);
        //获取保留小数位
        int newScale = dataToString.newScale();
        //获取舍入策略
        int roundingMode = dataToString.roundingMode();
        //返回保留值
        return ((BigDecimal) value).setScale(newScale, roundingMode).toString();
    }

    /**
     * 获取真正的属性
     * @param instanceClazz
     * @param name
     * @return
     */
    private Field getFieldName(Class<?> instanceClazz,String name){
        //如果出现报错 则可能属性名称被修改了 就要遍历全部的域
        Field[] fields = instanceClazz.getDeclaredFields();
        for (Field field : fields){
            if (field.isAnnotationPresent(JSONField.class)){
                JSONField jsonField = field.getAnnotation(JSONField.class);
                if (name.equals(jsonField.name())){
                    field.setAccessible(true);
                    return field;
                }
            }
        }
        return null;
    }
}
