package com.lidiwo.android.base_module.base;

import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.lidiwo.android.base_module.R;
import com.lidiwo.android.base_module.mvp.IPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.DaggerAppCompatActivity;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2018/10/11 15:13
 * @Company：智能程序员
 * @Description： *****************************************************
 */
public abstract class BaseActivity<P extends IPresenter> extends DaggerAppCompatActivity implements EasyPermissions.PermissionCallbacks {

    private Unbinder unbinder;


//    @Inject
    @Nullable
    P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setContentView());
        unbinder = ButterKnife.bind(this);
    }

    protected abstract int setContentView();

    @Override
    protected void onStart() {
        super.onStart();
        if (mPresenter != null) {
            mPresenter.onStart();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mPresenter != null) {
            mPresenter.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mPresenter != null) {
            mPresenter.onPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mPresenter != null) {
            mPresenter.onStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
    }


    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        //权限请求成功
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if (disposePermissionRequest()) {
            //权限请求失败
            if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
                new AppSettingsDialog.Builder(this).setTitle(R.string.permissions_title).setRationale(R.string.permissions_request_content).build().show();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (disposePermissionRequest()) {
            //处理权限请求结果
            EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
        }
    }

    /**
     * 如果需要Fragment 处理权限请求回调，返回false
     *
     * @return
     */
    protected boolean disposePermissionRequest() {
        return true;
    }
}
