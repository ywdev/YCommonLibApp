package com.appdev.lib.utils;

import java.util.List;

public class ListUtils {

    /**
     * 判断字符串是否为空
     * @param list
     * @return
     */
    public static boolean isEmpty(List list) {
        return list == null || list.size() == 0;
    }

}
