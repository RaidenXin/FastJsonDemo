package com.raiden.fastjson.util;

/**
 * @创建人:Raiden
 * @Descriotion: 属性名称工具类
 * @Date:Created in 21:26 2019/6/23
 * @Modified By:
 */
public class FieldNameUtils {

    /**
     * 首字母大写的方法
     * @param name
     * @return
     */
    public static String firstLetterCapitalized(String name){
        char[] chars = name.toCharArray();
        StringBuilder builder = new StringBuilder();
        char c = chars[0];
        //如果是小写才替换
        if (c > 96 && c < 123){
            c -= 32;
            chars[0] = c;

        }
        builder.append(chars);
        return builder.toString();
    }

    /**
     * 首字母小写
     * @param name
     * @return
     */
    public static String firstLetterLowercase(String name){
        char[] chars = name.toCharArray();
        StringBuilder builder = new StringBuilder();
        char c = chars[0];
        //如果是小写才替换
        if (c > 64 && c < 91){
            c += 32;
            chars[0] = c;

        }
        builder.append(chars);
        return builder.toString();
    }
}
