package com.lidiwo.android.base_module.base;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lidiwo.android.base_module.R;
import com.lidiwo.android.base_module.http.callback.DefaultRequestSuccess;
import com.lidiwo.android.base_module.utils.ToastUtil;

/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2018/10/25 10:21
 * @Company：智能程序员
 * @Description： *****************************************************
 */
public abstract class BaseRequestSuccess<T> extends DefaultRequestSuccess<T> {

    @Override
    public void onSuccess(String response) {
        if (TextUtils.isEmpty(response)) {
            ToastUtil.show(R.string.data_exception);
        } else {
            try {
                BaseResponseBean<T> bean = JSON.parseObject(response, new TypeReference<BaseResponseBean<T>>() {
                });
                T data = bean.getResults();
                onSucceed(data);
            } catch (Exception e) {
                e.printStackTrace();
                ToastUtil.show(R.string.data_parse_exception);
            }
        }
    }

    @Override
    public abstract void onSucceed(T response);
}
