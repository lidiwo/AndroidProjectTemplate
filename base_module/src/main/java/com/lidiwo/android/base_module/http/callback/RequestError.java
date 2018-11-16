package com.lidiwo.android.base_module.http.callback;

/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2018/10/22 18:30
 * @Company：智能程序员
 * @Description： *****************************************************
 */
public interface RequestError {
    /**
     * 请求错误
     */
    void onError(int code,String message);
}
