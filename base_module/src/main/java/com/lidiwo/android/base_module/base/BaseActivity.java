package com.lidiwo.android.base_module.base;

import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.lidiwo.android.base_module.R;

import java.util.List;

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
public abstract class BaseActivity extends DaggerAppCompatActivity implements EasyPermissions.PermissionCallbacks {

    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setContentView());
        unbinder = ButterKnife.bind(this);
    }

    protected abstract int setContentView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
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
