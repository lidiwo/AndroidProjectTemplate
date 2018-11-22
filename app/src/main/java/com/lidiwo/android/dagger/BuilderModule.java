package com.lidiwo.android.dagger;


import com.lidiwo.android.activity.MainActivity;
import com.lidiwo.android.base_module.dagger.ActivityScope;
import com.lidiwo.android.dagger.di.MainModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2018/11/12 17:01
 * @Company：智能程序员
 * @Description： *****************************************************
 */
@Module
public abstract class BuilderModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = {MainModule.class})
    abstract MainActivity mainActivityInjector();

}
