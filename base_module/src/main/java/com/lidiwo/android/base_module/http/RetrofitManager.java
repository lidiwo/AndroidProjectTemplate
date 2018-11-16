package com.lidiwo.android.base_module.http;

import android.content.Context;

import com.lidiwo.android.base_module.http.callback.RequestDownloadProgress;
import com.lidiwo.android.base_module.http.converter.FastJsonConverterFactory;
import com.lidiwo.android.base_module.http.interceptor.CacheInterceptor;
import com.lidiwo.android.base_module.http.interceptor.DownloadInterceptor;
import com.lidiwo.android.base_module.http.rxjava.RxHttpService;
import com.lidiwo.android.log.AndroidLog;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2018/10/15 15:33
 * @Company：智能程序员
 * @Description： *****************************************************
 */
public class RetrofitManager {

    public static RetrofitConfigure init(Context context, String baseUrl) {
        return RetrofitConfigure.getInstance().withContext(context).withBaseUrl(baseUrl);
    }

    private static final class OKHttpHolder {
        //读超时长，单位：秒
        private static final long READ_TIME_OUT = RetrofitConfigure.getInstance().getConfig(ConfigKey.READ_TIME_OUT.name());
        //连接时长，单位：秒
        private static final long CONNECT_TIME_OUT = RetrofitConfigure.getInstance().getConfig(ConfigKey.CONNECT_TIME_OUT.name());
        //设置网络缓存最大值
        private static final long CACHE_MAX_SIZE = RetrofitConfigure.getInstance().getConfig(ConfigKey.CACHE_MAX_SIZE.name());

        //初始化日志拦截器
        private static final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                AndroidLog.i(message);
            }
        }).setLevel(getLoggingLevel());

        //初始化Gzip 压缩拦截器
        // private static final GzipRequestInterceptor gzipRequestInterceptor=new GzipRequestInterceptor();

        //缓存拦截器
        //private static final CacheInterceptor cacheInterceptor = new CacheInterceptor();


        //设置缓存
        private static final File cacheFile = new File(RetrofitConfigure.getInstance().getContext().getCacheDir(), "httpCache");
        private static final Cache cache = new Cache(cacheFile, CACHE_MAX_SIZE);

        //初始化OkHttpClient
        private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                // .addNetworkInterceptor(cacheInterceptor)
                // .addInterceptor(gzipRequestInterceptor)
                .cache(cache)
                .build();
    }

    private static final class RetrofitHolder {
        private static final String BASE_URL = RetrofitConfigure.getInstance().getConfig(ConfigKey.BASE_URL.name());

        private static final Retrofit RETROFIT = new Retrofit.Builder()
                .client(OKHttpHolder.OK_HTTP_CLIENT)
                .baseUrl(BASE_URL)
                .addConverterFactory(FastJsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private static final class ServiceHolder {
        private static final HttpService SERVICE = RetrofitHolder.RETROFIT.create(HttpService.class);
    }

    private static final class RxServiceHolder {
        private static final RxHttpService SERVICE = RetrofitHolder.RETROFIT.create(RxHttpService.class);
    }

    private static Level getLoggingLevel() {
        //  0 -> NONE, 1 ->  BASIC,  2 -> HEADERS, 3 -> BODY
        int loggingLevel = RetrofitConfigure.getInstance().getConfig(ConfigKey.LOGGING_LEVEL.name());

        if (loggingLevel == 0) {
            return Level.NONE;
        } else if (loggingLevel == 1) {
            return Level.BASIC;
        } else if (loggingLevel == 2) {
            return Level.HEADERS;
        } else if (loggingLevel == 3) {
            return Level.BODY;
        } else {
            return Level.NONE;
        }
    }

    public static HttpService getService() {
        return ServiceHolder.SERVICE;
    }

    public static RxHttpService getRxService() {
        return RxServiceHolder.SERVICE;
    }

    public static HttpService getDownloadService(RequestDownloadProgress progress) {
        OkHttpClient client = OKHttpHolder.OK_HTTP_CLIENT.newBuilder().addInterceptor(new DownloadInterceptor(progress)).build();
        Retrofit retrofit = RetrofitHolder.RETROFIT.newBuilder().client(client).build();
        HttpService service = retrofit.create(HttpService.class);
        return service;
    }

    public static RxHttpService getRxDownloadService(RequestDownloadProgress progress) {
        OkHttpClient client = OKHttpHolder.OK_HTTP_CLIENT.newBuilder().addInterceptor(new DownloadInterceptor(progress)).build();
        Retrofit retrofit = RetrofitHolder.RETROFIT.newBuilder().client(client).build();
        RxHttpService service = retrofit.create(RxHttpService.class);
        return service;
    }

}
