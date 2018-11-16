package com.lidiwo.android.base_module.base;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lidiwo.android.base_module.R;
import com.lidiwo.android.base_module.http.callback.DefaultRequestResult;


/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2018/10/25 10:19
 * @Company：智能程序员
 * @Description： *****************************************************
 */
public abstract class BaseRequestResult<T> extends DefaultRequestResult<T> {
    @Override
    public void onSuccess(String response) {
        if (TextUtils.isEmpty(response)) {
            String data_exception = DefaultApplication.getContext().getString(R.string.data_exception);
            onError(0, data_exception);
        } else {
            try {
                BaseResponseBean<T> bean = JSON.parseObject(response, new TypeReference<BaseResponseBean<T>>() {
                });
                T data = bean.getResults();
                onSucceed(data);
            } catch (Exception e) {
                e.printStackTrace();
                String data_parse_exception = DefaultApplication.getContext().getString(R.string.data_parse_exception);
                onError(0, data_parse_exception);
            }
        }

    }

    @Override
    public abstract void onSucceed(T response);
}
