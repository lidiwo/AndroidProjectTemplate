package com.lidiwo.android.base_module.http.interceptor;

import java.io.IOException;


import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2018/11/8 15:06
 * @Company：智能程序员
 * @Description：
 *
 * 当后台没有配置缓存策略请求头的时候，客户端需要做缓存的时候，可以使用缓存拦截器，配置缓存策略
 *
 * *****************************************************
 */
public class CacheInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Response originResponse = chain.proceed(chain.request());

        //设置缓存时间为60秒，并移除了pragma消息头，移除它的原因是因为pragma也是控制缓存的一个消息头属性
        return originResponse.newBuilder().removeHeader("pragma")
                .header("Cache-Control","max-age=60").build();
    }
}
