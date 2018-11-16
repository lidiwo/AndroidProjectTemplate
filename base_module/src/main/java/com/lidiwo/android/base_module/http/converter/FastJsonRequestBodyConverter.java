package com.lidiwo.android.base_module.http.converter;

import com.alibaba.fastjson.JSON;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2018/10/15 16:32
 * @Company：智能程序员
 * @Description： *****************************************************
 */
public class FastJsonRequestBodyConverter<T> implements Converter<T, RequestBody> {
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json;charset=UTF-8");

    @Override
    public RequestBody convert(T value) {
        return RequestBody.create(MEDIA_TYPE,JSON.toJSONString(value).getBytes());
    }
}
