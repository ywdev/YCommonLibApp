package com.appdev.lib.utils;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

public class FileUtils {

    /**
     * 读取Assets文件
     * @param context
     * @param fileName
     * @return
     */
    public static String readAssetsFile(Context context, String fileName) {
        InputStream is = null;
        try {
            is = context.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            return new String(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 将文件大小格式化
     * @param size
     * @return
     */
    public static String formatFileSize(long size) {
        DecimalFormat df = new DecimalFormat("#.#");
        String fileSizeString = "";
        String wrongSize = "0B";
        if (size == 0) {
            return wrongSize;
        }
        if (size < 1024) {
            fileSizeString = df.format((double) size) + " B";
        } else if (size < 1024*1024) {
            fileSizeString = df.format((double) size / 1024) + " KB";
        } else if (size < 1024*1024*1024) {
            fileSizeString = df.format((double) size / 1048576) + " MB";
        } else {
            fileSizeString = df.format((double) size / 1073741824) + " GB";
        }
        return fileSizeString;
    }

}
