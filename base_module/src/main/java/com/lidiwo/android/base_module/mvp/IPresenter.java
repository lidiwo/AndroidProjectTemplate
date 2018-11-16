package com.lidiwo.android.base_module.mvp;

/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2018/11/9 14:55
 * @Company：智能程序员
 * @Description：  MVP Presenter 接口标准
 *
 * *****************************************************
 */
public interface IPresenter {

    /**
     * 初始化操作
     */
    void onStart();

    /**
     * 销毁时候，释放资源
     */
    void onDestroy();
}
