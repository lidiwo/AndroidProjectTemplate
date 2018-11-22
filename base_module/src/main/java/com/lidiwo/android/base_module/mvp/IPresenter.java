package com.lidiwo.android.base_module.mvp;

/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2018/11/9 14:55
 * @Company：智能程序员
 * @Description： MVP Presenter 接口标准
 * <p>
 * *****************************************************
 */
public interface IPresenter<V extends IView> {
    void takeView(V view);

    void initLifecycle();
}
