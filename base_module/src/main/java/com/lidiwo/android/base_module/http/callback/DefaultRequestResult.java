package com.lidiwo.android.base_module.http.callback;

/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2018/10/24 19:18
 * @Company：智能程序员
 * @Description： *****************************************************
 */
public abstract class DefaultRequestResult<T> extends RequestResult {
    @Override
    public abstract void onSuccess(String response);

    public abstract void onSucceed(T response);
}
