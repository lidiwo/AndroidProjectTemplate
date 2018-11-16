package com.lidiwo.android.dagger;


import com.lidiwo.android.MainActivity;

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

    @ContributesAndroidInjector
    abstract MainActivity mainActivityInjector();

//    @ContributesAndroidInjector(modules = {BaseModule.class})
//    abstract MainActivity baseActivityInjector();
}
