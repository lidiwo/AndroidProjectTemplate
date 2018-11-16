package com.lidiwo.android.base_module.http.upload;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.lidiwo.android.base_module.http.callback.RequestUploadProgress;

import java.lang.ref.WeakReference;

/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2018/10/24 12:29
 * @Company：智能程序员
 * @Description： *****************************************************
 */
public class UploadHandler extends Handler {

    private final WeakReference<RequestUploadProgress> weakReferenceProgress;

    public UploadHandler(RequestUploadProgress progress,Looper looper) {
        super(looper);
        weakReferenceProgress = new WeakReference<>(progress);
    }

    @Override
    public void handleMessage(Message msg) {
        Bundle data = msg.getData();
        int progress = data.getInt("progress");
        long networkSpeed = data.getLong("networkSpeed");
        boolean downloadFinish = data.getBoolean("downloadFinish");

        RequestUploadProgress uploadProgress=  weakReferenceProgress.get();

        if(uploadProgress!=null){
            uploadProgress.uploadProgress(progress,networkSpeed,downloadFinish);
        }
        super.handleMessage(msg);
    }

}
