package com.lidiwo.android.mvp.model;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;

import com.lidiwo.android.base_module.dagger.ActivityScope;
import com.lidiwo.android.base_module.mvp.DefaultModel;
import com.lidiwo.android.log.AndroidLog;
import com.lidiwo.android.mvp.contract.MainContract;

import javax.inject.Inject;

/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2018/11/21 17:05
 * @Company：智能程序员
 * @Description： *****************************************************
 */
@ActivityScope
public class MainModel extends DefaultModel implements MainContract.BaseModel {

    @Inject
    public MainModel() {
        AndroidLog.e("MainModel..............");
    }


    @Override
    protected void onCreate() {
        AndroidLog.e("MainModel..............onCreate");
    }

    @Override
    protected void onResume() {
        AndroidLog.e("MainModel..............onResume");
    }

    @Override
    protected void onDestroy(LifecycleOwner owner) {
        super.onDestroy(owner);
        AndroidLog.e("MainModel..............onDestroy");
    }
}
