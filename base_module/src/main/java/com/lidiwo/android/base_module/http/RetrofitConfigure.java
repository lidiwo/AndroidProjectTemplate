package com.lidiwo.android.base_module.http;

import android.content.Context;

import java.util.HashMap;

/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2018/10/22 16:28
 * @Company：智能程序员
 * @Description： *****************************************************
 */
public class RetrofitConfigure {

    private static final HashMap<String, Object> CONFIGS = new HashMap<>();

    private RetrofitConfigure() {
        //读超时长，单位：秒
        CONFIGS.put(ConfigKey.READ_TIME_OUT.name(), 30L);
        //连接时长，单位：秒
        CONFIGS.put(ConfigKey.CONNECT_TIME_OUT.name(), 30L);
        //设置网络缓存最大值 300MB
        CONFIGS.put(ConfigKey.CACHE_MAX_SIZE.name(), 1024 * 1024 * 300L);
        //设置日志级别 0 -> NONE, 1 ->  BASIC,  2 -> HEADERS, 3 -> BODY
        CONFIGS.put(ConfigKey.LOGGING_LEVEL.name(), 3);
    }

    private static final class ConfigureHolder {
        private static final RetrofitConfigure RETROFIT_CONFIGURE = new RetrofitConfigure();
    }

    public static RetrofitConfigure getInstance() {
        return ConfigureHolder.RETROFIT_CONFIGURE;
    }

    public final RetrofitConfigure withContext(Context context) {
        CONFIGS.put(ConfigKey.APPLICATION_CONTEXT.name(), context);
        return this;
    }

    public final RetrofitConfigure withBaseUrl(String url) {
        if (url == null) {
            throw new NullPointerException("base url is null");
        }
        CONFIGS.put(ConfigKey.BASE_URL.name(), url);
        return this;
    }

    public final RetrofitConfigure readTimeOut(long timeOut) {
        CONFIGS.put(ConfigKey.READ_TIME_OUT.name(), timeOut);
        return this;
    }

    public final RetrofitConfigure connectTimeOut(long timeOut) {
        CONFIGS.put(ConfigKey.CONNECT_TIME_OUT.name(), timeOut);
        return this;
    }

    public final RetrofitConfigure cacheMaxSize(long cacheMaxSize) {
        CONFIGS.put(ConfigKey.CACHE_MAX_SIZE.name(), cacheMaxSize);
        return this;
    }

    public final RetrofitConfigure debug(boolean isDebug) {
        if (!isDebug) {
            CONFIGS.put(ConfigKey.LOGGING_LEVEL.name(), 0);
        }
        return this;
    }

    public final <T> T getConfig(String key) {
        Object value = CONFIGS.get(key);
        if (value == null) {
            throw new NullPointerException(key + "is null");
        }
        return (T) value;
    }

    public Context getContext(){
        return (Context)CONFIGS.get(ConfigKey.APPLICATION_CONTEXT.name());
    }

}
