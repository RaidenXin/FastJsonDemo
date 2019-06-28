package com.test;

import com.alibaba.fastjson.JSON;
import com.raiden.model.User;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestApp {

    @Test
    public void test(){
        String s = "\n\n\n\r\n12\r\n34\r\n1";
        Character[] pattem = {'\r','\n'};
        String s2 = StringUtils.trimStart(s, pattem);
        System.out.println(s2);
    }

    public static int compareVersion(String currentVersion, String targetVersion) {
        String[] versionArray1 = currentVersion.split("\\.");
        String[] versionArray2 = targetVersion.split("\\.");
        int idx = 0;
        int minLength = Math.min(versionArray1.length, versionArray2.length);
        int diff = 0;
        while (idx < minLength
                && (diff = versionArray1[idx].length() - versionArray2[idx].length()) == 0
                && (diff = versionArray1[idx].compareTo(versionArray2[idx])) == 0) {
            ++idx;
        }
        //如果已经分出大小，则直接返回，如果未分出大小，则再比较位数，有子版本的为大；
        diff = (diff != 0) ? diff : versionArray1.length - versionArray2.length;
        return diff;
    }
}
