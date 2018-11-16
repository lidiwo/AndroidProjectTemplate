package com.lidiwo.android.base_module.base;


import android.content.Context;
import com.lidiwo.android.base_module.BuildConfig;
import com.lidiwo.android.base_module.greendao.GreenDaoManager;
import com.lidiwo.android.base_module.http.RetrofitManager;
import com.lidiwo.android.base_module.utils.SPUtil;
import com.lidiwo.android.log.AndroidLog;
import com.squareup.leakcanary.LeakCanary;
import dagger.android.support.DaggerApplication;


/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2018/10/11 15:02
 * @Company：智能程序员
 * @Description： *****************************************************
 */
public abstract class DefaultApplication extends DaggerApplication {

    private static Context mContext;


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

        //初始化网络
        initHttp();

        //初始化数据库
        initGreenDao();

        //初始化内存泄漏检测
        initLeakcanary();

        //初始化日志
        initLog();

        //初始化SharedPreferences
        initSharedPreferences();
    }

    public static Context getContext() {
        return mContext;
    }

    private void initHttp(){
        RetrofitManager.init(mContext,"https://shopapi.io.mi.com/").debug(BuildConfig.DEBUG);
    }


    private void initGreenDao() {
        GreenDaoManager.getInstance().initGreenDao("android");
    }

    private void initLeakcanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }

    private void initLog(){
        AndroidLog.init(BuildConfig.DEBUG,"lidiwo");
    }

    private void initSharedPreferences() {
        SPUtil.init(this,"Android");
    }

}
