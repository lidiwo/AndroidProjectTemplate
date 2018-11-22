package com.lidiwo.android.mvp.presenter;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;

import com.lidiwo.android.base_module.dagger.ActivityScope;
import com.lidiwo.android.base_module.mvp.DefaultPresenter;
import com.lidiwo.android.log.AndroidLog;
import com.lidiwo.android.mvp.contract.MainContract;

import javax.inject.Inject;

/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2018/11/21 16:13
 * @Company：智能程序员
 * @Description： *****************************************************
 */
@ActivityScope
public class MainPresenter extends DefaultPresenter<MainContract.MainView,MainContract.BaseModel> {

    @Inject
    public MainPresenter(MainContract.BaseModel model) {
        super(model);
        AndroidLog.e("MainPresenter..............");
    }

    @Override
    protected void onCreate() {
        AndroidLog.e("MainPresenter..............onCreate");
    }

    @Override
    protected void onResume() {
        AndroidLog.e("MainPresenter..............onResume");
    }

    @Override
    protected void onDestroy(LifecycleOwner owner) {
        super.onDestroy(owner);

        AndroidLog.e("MainPresenter..............onDestroy");
    }


    public void aa(){
        mProjectView.demo();
    }

}
