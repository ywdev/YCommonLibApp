package com.appdev.lib.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TimeUtilsTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void formatMillisToTimeString() {
       String time = TimeUtils.formatMillisToTimeString("yyyy/MM/dd HH:mm:dd",System.currentTimeMillis());
       System.out.print("formatMillisToTimeString=>"+time);
    }

    @Test
    public void getCurWeekday() {
        int curWeekday = TimeUtils.getCurWeekday();
        System.out.println(curWeekday);
    }

    @Test
    public void isEmpty(){
        boolean empty = StringUtils.isEmpty(" ");
        System.out.println(empty);
    }
}