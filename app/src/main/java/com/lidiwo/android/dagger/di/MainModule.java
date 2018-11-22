package com.lidiwo.android.dagger.di;

import com.lidiwo.android.base_module.dagger.ActivityScope;
import com.lidiwo.android.mvp.contract.MainContract;
import com.lidiwo.android.mvp.model.MainModel;
import dagger.Binds;
import dagger.Module;


/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2018/11/21 16:11
 * @Company：智能程序员
 * @Description： *****************************************************
 */
@Module
public abstract class MainModule {

    @ActivityScope
    @Binds
    public abstract MainContract.BaseModel provideModel(MainModel model);

}
