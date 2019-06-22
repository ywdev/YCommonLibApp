package com.appdev.lib.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class BitmapUtils {

    /**
     * 将base64字符串转化为Bitmap
     * @param string
     * @return
     */
    public static Bitmap base64ToBitmap(String string){
        Bitmap bitmap = null;
        byte [] bitmapArray = Base64.decode(string,Base64.DEFAULT);
        bitmap = BitmapFactory.decodeByteArray(bitmapArray,0,bitmapArray.length);
        return bitmap;
    }

}
