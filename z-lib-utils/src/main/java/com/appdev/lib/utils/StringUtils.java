package com.appdev.lib.utils;

public class StringUtils {

    /**
     * 判断一个字符串是否为空(包含空格)
     * @param string
     * @return
     */
    public static boolean isEmpty(String string) {
        return string == null || "".equals(string.trim());
    }
}
