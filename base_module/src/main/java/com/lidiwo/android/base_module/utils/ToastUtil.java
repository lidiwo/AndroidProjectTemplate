package com.lidiwo.android.base_module.utils;

import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;


import com.lidiwo.android.base_module.R;
import com.lidiwo.android.base_module.base.DefaultApplication;

/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2018/10/25 11:03
 * @Company：智能程序员
 * @Description： *****************************************************
 */
public class ToastUtil {
    private static final int LONG_DURATION_TIMEOUT = 3500; // 3.5 seconds
    private static final int SHORT_DURATION_TIMEOUT = 2000; // 2 seconds

    protected static Toast toast = null;
    private static long oneTime = 0;
    private static long twoTime = 0;
    private static String oldMsg;

    public static void show(String content) {
        int toast_y_offset = DefaultApplication.getContext().getResources().getDimensionPixelSize(R.dimen.toast_y_offset);
        show(content, Toast.LENGTH_SHORT, Gravity.BOTTOM, 0, toast_y_offset);
    }

    public static void show(@StringRes int resId) {
        String content = DefaultApplication.getContext().getString(resId);
        show(content);
    }

    public static void show(String content, int gravity, int xOffset, int yOffset) {
        show(content, Toast.LENGTH_SHORT, gravity, xOffset, yOffset);
    }

    public static void show(@StringRes int resId, int gravity, int xOffset, int yOffset) {
        String content = DefaultApplication.getContext().getString(resId);
        show(content, Toast.LENGTH_SHORT, gravity, xOffset, yOffset);
    }

    public static void show(String content, int duration, int gravity, int xOffset, int yOffset) {
        if (!TextUtils.isEmpty(content)) {
            int DELAY = duration == Toast.LENGTH_LONG ? LONG_DURATION_TIMEOUT : SHORT_DURATION_TIMEOUT;
            if (toast == null) {
                toast = Toast.makeText(DefaultApplication.getContext(), content, duration);
                toast.setGravity(gravity, xOffset, yOffset);

                toast.show();
                oneTime = System.currentTimeMillis();
            } else {
                twoTime = System.currentTimeMillis();
                toast.setGravity(gravity, xOffset, yOffset);
                if (content.equals(oldMsg)) {
                    if (twoTime - oneTime > DELAY) {
                        toast.show();
                        oneTime = twoTime;
                    }
                } else {
                    oldMsg = content;
                    toast.setText(content);
                    toast.show();
                    oneTime = twoTime;
                }
            }
        }
    }
}
