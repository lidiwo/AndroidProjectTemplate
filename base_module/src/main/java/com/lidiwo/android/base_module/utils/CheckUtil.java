package com.lidiwo.android.base_module.utils;

import android.support.annotation.NonNull;

/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2018/11/16 18:33
 * @Company：校验工具类
 * @Description： *****************************************************
 */
public class CheckUtil {

    public static <T> T checkObject(T obj) {
        if (obj == null) {
            throw new NullPointerException();
        }
        return obj;
    }

    public static <T> T checkObject(T obj, @NonNull Class<?> clazz) {
        if (obj == null) {
            throw new NullPointerException(clazz.getClass().getSimpleName().concat("cannot be null"));
        }
        return obj;
    }
}
