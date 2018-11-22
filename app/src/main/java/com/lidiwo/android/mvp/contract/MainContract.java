package com.lidiwo.android.mvp.contract;

import com.lidiwo.android.base_module.mvp.IModel;
import com.lidiwo.android.base_module.mvp.IView;

/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2018/11/21 16:51
 * @Company：智能程序员
 * @Description： *****************************************************
 */
public interface MainContract {

    interface MainView extends IView {
        void demo();
    }

    interface BaseModel extends IModel {

    }
}
