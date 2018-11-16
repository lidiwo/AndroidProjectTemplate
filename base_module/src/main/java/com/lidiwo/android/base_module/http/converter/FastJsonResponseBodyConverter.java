package com.lidiwo.android.base_module.http.converter;


import com.alibaba.fastjson.JSON;
import java.io.IOException;
import java.lang.reflect.Type;
import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2018/10/15 16:32
 * @Company：智能程序员
 * @Description： *****************************************************
 */
public class FastJsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private Type type;

    public FastJsonResponseBodyConverter(Type type) {
        this.type=type;
    }

    @Override
    public T convert(ResponseBody responseBody) throws IOException {
        try{
            return JSON.parseObject(responseBody.string(),type);
        }finally {
            responseBody.close();
        }
    }
}
