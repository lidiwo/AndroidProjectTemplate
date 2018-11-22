package com.lidiwo.android.base_module.mvp;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.annotation.Nullable;

import com.lidiwo.android.base_module.utils.CheckUtil;


/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2018/11/9 15:55
 * @Company：智能程序员
 * @Description： *****************************************************
 */
public class DefaultPresenter<V extends IView, M extends IModel> implements IPresenter<V>, LifecycleObserver {

    @Nullable
    protected V mProjectView;
    @Nullable
    protected M mProjectModel;

    public DefaultPresenter() {

    }

    public DefaultPresenter(@Nullable M model) {
        CheckUtil.checkObject(model, IModel.class);
        this.mProjectModel = model;
    }

    @Override
    public void takeView(V view) {
        this.mProjectView = view;
    }

    @Override
    public void initLifecycle() {
        if (mProjectView instanceof LifecycleOwner) {
            ((LifecycleOwner) mProjectView).getLifecycle().addObserver(this);
            if (mProjectModel instanceof LifecycleObserver) {
                ((LifecycleOwner) mProjectView).getLifecycle().addObserver((LifecycleObserver) mProjectModel);
            }
        }
    }




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
        if (mProjectView != null) {
            mProjectView = null;
        }
    }
}
