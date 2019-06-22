package com.appdev.lib.utils;

import android.view.View;

public class ClickUtils {

    private static final int DELAY = 1000;

    /**
     * 绑定view与点击事件 默认禁止点击延迟1000毫秒
     * @param view
     * @param onClickListener
     */
    public static void bind(View view, View.OnClickListener onClickListener) {
        bind(view, onClickListener, DELAY);
    }

    /**
     * 绑定view与点击事件
     * @param view
     * @param onClickListener
     * @param delay 禁止点击延时
     */
    public static void bind(View view, final View.OnClickListener onClickListener, final long delay) {
        view.setOnClickListener(new View.OnClickListener() {
            long lastClick = 0;
            @Override
            public void onClick(View v) {
                long time = System.currentTimeMillis();
                if (time - lastClick > delay) {
                    lastClick = time;
                    if (onClickListener != null) onClickListener.onClick(v);
                }
            }
        });
    }
}
