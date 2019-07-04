package com.raiden.fastjson.util;

import java.lang.reflect.Field;

/**
 * @创建人:Raiden
 * @Descriotion:
 * @Date:Created in 23:11 2019/7/4
 * @Modified By:
 */
public class FieldUtils {

    /**
     * 递归获取域 子类找不到找父类 直到直到或者 递归到Object为止
     * @param clazz
     * @param fieldName
     * @return
     */
    public static Field getField(Class<?> clazz, String fieldName){
        //获取父类class
        Class<?> superclass = clazz.getSuperclass();
        if (null == superclass){
            //父类为空证明该类为Object 不递归了返回吧
            return null;
        }
        Field declaredField = null;
        try {
            //忽略报错
            declaredField = clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            //此处忽略报错 递归查找
            return getField(superclass, fieldName);
        }
        //找到了返回
        return declaredField;
    }
}
