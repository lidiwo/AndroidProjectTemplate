package com.lidiwo.android.base_module.http.callback;

/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2018/10/22 18:26
 * @Company：智能程序员
 * @Description： *****************************************************
 */
public interface RequestObserver {
    /**
     * 请求开始
     */
    void onStart();

    /**
     * 请求结束
     */
    void onStop();
}
