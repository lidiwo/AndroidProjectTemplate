package com.lidiwo.android.base_module.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2018/10/25 17:18
 * @Company：智能程序员
 * @Description： *****************************************************
 */
public class SPUtil {

    private static Context mContext;

    private static String SP_FILE_NAME = "default_lidiwo";

    public static void init(Context context, String spFileName) {
        mContext = context;
        SP_FILE_NAME = "lidiwo_".concat(spFileName);
    }

    public static void putBoolean(String key, boolean value) {
        initCheck();
        SharedPreferences sp = mContext.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, value).commit();
    }

    public static boolean getBoolean(String key, boolean defValue) {
        initCheck();
        SharedPreferences sp = mContext.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defValue);
    }

    public static void putString(String key, String value) {
        initCheck();
        SharedPreferences sp = mContext.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        sp.edit().putString(key, value).commit();
    }

    public static String getString(String key, String defValue) {
        initCheck();
        SharedPreferences sp = mContext.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        return sp.getString(key, defValue);
    }

    public static void putInt(String key, int value) {
        initCheck();
        SharedPreferences sp = mContext.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        sp.edit().putInt(key, value).commit();
    }

    public static int getInt(String key, int defValue) {
        initCheck();
        SharedPreferences sp = mContext.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        return sp.getInt(key, defValue);
    }

    public static void putLong(String key, long value) {
        initCheck();
        SharedPreferences sp = mContext.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        sp.edit().putLong(key, value).commit();
    }

    public static long getLong(String key, Long defValue) {
        initCheck();
        SharedPreferences sp = mContext.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        return sp.getLong(key, defValue);
    }

    public static void putFloat(String key, float value) {
        initCheck();
        SharedPreferences sp = mContext.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        sp.edit().putFloat(key, value).commit();
    }

    public static float getFloat(String key, float defValue) {
        initCheck();
        SharedPreferences sp = mContext.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        return sp.getFloat(key, defValue);
    }

    public static void remove(String key){
        initCheck();
        SharedPreferences sp = mContext.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        if(sp.contains(key)){
            sp.edit().remove(key).commit();
        }
    }

    public static void clear() {
        initCheck();
        SharedPreferences sp = mContext.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        sp.edit().clear().commit();
    }

    private static void initCheck(){
        if(mContext==null){
            throw new NullPointerException("Please init SPUtil in Application onCreate");
        }
    }

}
