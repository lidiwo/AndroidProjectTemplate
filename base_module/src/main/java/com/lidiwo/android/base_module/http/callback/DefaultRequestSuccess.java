package com.lidiwo.android.base_module.http.callback;

/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2018/10/24 19:20
 * @Company：智能程序员
 * @Description： *****************************************************
 */
public abstract class DefaultRequestSuccess<T> implements RequestSuccess {
    @Override
    public abstract void onSuccess(String response);

    public abstract void onSucceed(T response);
}
