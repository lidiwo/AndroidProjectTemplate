package com.lidiwo.android.base_module.mvp;

/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2018/11/9 14:56
 * @Company：智能程序员
 * @Description：  MVP Model 接口标准
 *
 * *****************************************************
 */
public interface IModel {

    /**
     * 销毁时候，释放资源
     */
    void onDestroy();
}
