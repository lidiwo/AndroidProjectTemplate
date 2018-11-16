package com.lidiwo.android.base_module.http.interceptor;

import com.lidiwo.android.base_module.http.callback.RequestDownloadProgress;
import com.lidiwo.android.base_module.http.download.DownloadResponseBody;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2018/10/23 18:09
 * @Company：智能程序员
 * @Description： 下载请求的时候，添加下载进度监听
 * *****************************************************
 */
public class DownloadInterceptor implements Interceptor {

    private RequestDownloadProgress mProgress;

    public DownloadInterceptor(RequestDownloadProgress progress) {
        this.mProgress = progress;
    }


    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        return originalResponse.newBuilder()
                .body(DownloadResponseBody.create(originalResponse.body(), mProgress))
                .build();
    }
}
