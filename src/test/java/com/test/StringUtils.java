package com.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringUtils {

    public static String trimStart(String value,Character[] characters){
        if (null != value){
            List<Character> characterList = Arrays.asList(characters);
            char[] chars = value.toCharArray();
            boolean sign = false;
            int index = 0;
            for (char c : chars) {
                if (characterList.contains(c)){
                    index++;
                }else {
                    break;
                }
            }
            char[] result = new char[chars.length - index];
            System.arraycopy(chars,index, result,0, result.length);
            return new String(result);
        }
        return null;
    }
}
