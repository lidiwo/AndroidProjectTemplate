package com.lidiwo.android.base_module.mvp;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;

import com.lidiwo.android.base_module.utils.CheckUtil;

import io.reactivex.functions.Action;

/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2018/11/9 15:55
 * @Company：智能程序员
 * @Description： *****************************************************
 */
public class DefaultPresenter<V extends IView, M extends IModel> implements IPresenter, LifecycleObserver {
    private V mProjectView;

    private M mProjectModel;


    public DefaultPresenter() {

    }

    public DefaultPresenter(V view) {
        CheckUtil.checkObject(view, IView.class);
        this.mProjectView = view;
    }

    public DefaultPresenter(V view, M model) {
        CheckUtil.checkObject(view, IView.class);
        CheckUtil.checkObject(model, IModel.class);
        this.mProjectView = view;
        this.mProjectModel = model;
    }


    @Override
    public void onStart() {
        if (mProjectView != null && mProjectView instanceof LifecycleOwner) {
            ((LifecycleOwner) mProjectView).getLifecycle().addObserver(this);
            if (mProjectModel != null && mProjectModel instanceof LifecycleObserver) {
                ((LifecycleOwner) mProjectView).getLifecycle().addObserver((LifecycleObserver) mProjectModel);
            }
        }
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {
        if (mProjectModel != null) {
            mProjectModel.onDestroy();
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy(LifecycleOwner owner) {
        owner.getLifecycle().removeObserver(this);
    }


}
