package com.lidiwo.android.base_module.http.callback;

/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2018/10/22 18:31
 * @Company：智能程序员
 * @Description： *****************************************************
 */
public abstract class RequestResult implements RequestSuccess, RequestFailure, RequestError, RequestObserver {

    @Override
    public void onError(int code, String message) {

    }

    @Override
    public void onFailure() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public abstract void onSuccess(String response);
}
