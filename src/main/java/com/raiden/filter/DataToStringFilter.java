package com.raiden.filter;

import com.alibaba.fastjson.serializer.ValueFilter;
import com.raiden.annotation.DataToString;

import java.lang.reflect.Field;
import java.math.BigDecimal;

public class DataToStringFilter implements ValueFilter {
    @Override
    public Object process(Object instance, String name, Object value) {
        if (value instanceof BigDecimal){
            try {
                Field field = instance.getClass().getDeclaredField(name);
                if (field.isAnnotationPresent(DataToString.class)){
                    DataToString dataToString = field.getAnnotation(DataToString.class);
                    int newScale = dataToString.newScale();
                    int roundingMode = dataToString.roundingMode();
                    return ((BigDecimal) value).setScale(newScale, roundingMode).toString();
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        return value;
    }
}
