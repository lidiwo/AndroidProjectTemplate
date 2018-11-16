package com.lidiwo.android.dagger;

import android.app.Application;

import com.lidiwo.android.base_module.base.DefaultApplication;
import javax.inject.Singleton;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2018/11/9 14:58
 * @Company：智能程序员
 * @Description： *****************************************************
 */
@Singleton
@Component(modules = {BuilderModule.class, AndroidSupportInjectionModule.class})
 interface AppComponent extends AndroidInjector<DefaultApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }

}
