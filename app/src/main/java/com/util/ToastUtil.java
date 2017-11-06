package com.util;

import android.content.Context;
import android.widget.Toast;

/**
 * 作者 ： Created by zjr on 2017/11/6 10:00.
 */

public class ToastUtil {

    /**
     * 显示toast
     *
     * @param context
     * @param title
     * @param isLong  是否设置long 显示时间
     */
    public static void showToast(Context context, String title, boolean isLong) {
        // 显示时间 long
        if (isLong) {
            Toast.makeText(context, title, Toast.LENGTH_LONG).show();
        }
        // 显示时间 short
        else {
            Toast.makeText(context, title, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 显示toast
     *
     * @param context
     * @param title
     */
    public static void showToast(Context context, String title) {
        Toast.makeText(context, title, Toast.LENGTH_SHORT).show();

    }
}
