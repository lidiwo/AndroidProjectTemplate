package com.lidiwo.android;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lidiwo.android.base_module.base.BaseActivity;

/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2018/10/11 18:19
 * @Company：智能程序员
 * @Description： *****************************************************
 */
public class OneActivity extends Activity {
    public static Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        mContext=this;
    }
}
