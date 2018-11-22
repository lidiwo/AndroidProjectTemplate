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

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    protected void onCreate() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    protected void onStart() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    protected void onResume() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    protected void onPause() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    protected void onStop() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    protected void onDestroy(LifecycleOwner owner) {
        owner.getLifecycle().removeObserver(this);
    }
}
