package com.appdev.lib.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TimeUtils {

    /**
     * 返回当前时间戳
     * @return
     */
    public static long now() {
        return System.currentTimeMillis();
    }

    /**
     * 将时间戳格式化
     * @param pattern
     * @param millis
     * @return
     */
    public static String formatMillisToTimeString(String pattern,long millis){
        DateFormat format = new SimpleDateFormat(pattern, Locale.CHINA);
        return format.format(new Date(millis));
    }

    /**
     * 将日期格式化
     * @param pattern
     * @param date
     * @return
     */
    public static String formatDateToTimeString(String pattern,Date date){
        DateFormat format = new SimpleDateFormat(pattern, Locale.CHINA);
        return format.format(date);
    }

    /**
     * 将当前时间格式化
     * @param format
     * @return
     */
    public static String getCurrentTime(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINA);
        return sdf.format(new Date(now()));
    }

    /**
     * 获取当前是星期几
     * @return 0[星期天] 1[星期一] 2[星期二] 3[星期三] 4[星期四] 5[星期五] 6[星期六]
     */
    public static int getCurWeekday() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(System.currentTimeMillis()));
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        return w < 0 ? 0 : w;
    }

}
