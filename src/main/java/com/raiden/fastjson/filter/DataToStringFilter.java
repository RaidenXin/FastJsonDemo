package com.raiden.fastjson.filter;

import com.alibaba.fastjson.serializer.ValueFilter;
import com.raiden.fastjson.annotation.DataToString;

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
            try {
                //如果是则获取该域
                Field field = instance.getClass().getDeclaredField(name);
                //检查该域是否有 DataToString注解
                if (field.isAnnotationPresent(DataToString.class)){
                    //获取DataToString注解
                    DataToString dataToString = field.getAnnotation(DataToString.class);
                    //获取保留小数位
                    int newScale = dataToString.newScale();
                    //获取舍入策略
                    int roundingMode = dataToString.roundingMode();
                    //返回保留值
                    return ((BigDecimal) value).setScale(newScale, roundingMode).toString();
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        return value;
    }
}
