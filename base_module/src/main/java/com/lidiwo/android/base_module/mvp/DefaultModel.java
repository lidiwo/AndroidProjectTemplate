package com.lidiwo.android.base_module.mvp;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;

/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2018/11/12 10:16
 * @Company：智能程序员
 * @Description： *****************************************************
 */
public class DefaultModel implements IModel, LifecycleObserver {

    @Override
    public void onDestroy() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy(LifecycleOwner owner) {
        owner.getLifecycle().removeObserver(this);
    }
}
