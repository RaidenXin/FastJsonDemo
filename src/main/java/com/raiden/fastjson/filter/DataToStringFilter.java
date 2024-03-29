package com.raiden.fastjson.filter;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.raiden.fastjson.util.FieldNameUtils;
import com.raiden.fastjson.annotation.DataToString;
import com.raiden.fastjson.annotation.FirstLetterCapitalized;
import com.raiden.fastjson.util.FieldUtils;
import org.springframework.util.StringUtils;

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
        if (null == instance || StringUtils.isEmpty(name) || null == value){
            return value;
        }
        //判断下实例是不是BigDecimal 或者是 Double
        if (value instanceof Double || value instanceof BigDecimal){
            Class<?> instanceClazz = instance.getClass();
            //如果存在这个注解说明类名可能被更改
            if (instanceClazz.isAnnotationPresent(FirstLetterCapitalized.class)){
                name = FieldNameUtils.firstLetterLowercase(name);
            }
            //如果是则获取该域 如果使用了JSONField自定义域名会出现找不到报错的情况
            Field field = FieldUtils.getField(instanceClazz, name);
            if (null == field){
                field = getField(instanceClazz, name);
            }
            //检查该域是否有 DataToString注解
            if (null != field && field.isAnnotationPresent(DataToString.class)){
                return valueFormat(value, field);
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
        if (value instanceof Double){
            return new BigDecimal((Double) value).setScale(newScale, roundingMode).toString();
        }
        //返回保留值
        return ((BigDecimal) value).setScale(newScale, roundingMode).toString();
    }

    /**
     * 获取真正的属性
     * @param instanceClazz
     * @param name
     * @return
     */
    private Field getField(Class<?> instanceClazz,String name){
        Class<?> superclass = instanceClazz.getSuperclass();
        if (null == superclass){
            //父类为空证明该类为Object 不递归了返回吧
            return null;
        }
        //遍历全部的域
        Field[] fields = instanceClazz.getDeclaredFields();
        for (Field field : fields){
            if (!field.isAnnotationPresent(JSONField.class)){
                continue;
            }
            JSONField jsonField = field.getAnnotation(JSONField.class);
            if (name.equals(jsonField.name())){
                return field;
            }
        }
        return getField(superclass, name);
    }
}
