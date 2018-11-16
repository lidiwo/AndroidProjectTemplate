package com.lidiwo.android.base;

import com.lidiwo.android.base_module.base.DefaultApplication;
import com.lidiwo.android.dagger.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2018/11/15 19:12
 * @Company：智能程序员
 * @Description： *****************************************************
 */
public class BaseApplication extends DefaultApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }
}
