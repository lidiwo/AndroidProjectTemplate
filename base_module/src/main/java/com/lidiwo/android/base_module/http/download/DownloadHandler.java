package com.lidiwo.android.base_module.http.download;


import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.lidiwo.android.base_module.http.callback.RequestDownloadProgress;
import java.lang.ref.WeakReference;

/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2018/10/24 12:00
 * @Company：智能程序员
 * @Description： *****************************************************
 */
public class DownloadHandler extends Handler {

    private final WeakReference<RequestDownloadProgress> weakReferenceProgress;

    public DownloadHandler(RequestDownloadProgress progress,Looper looper) {
        super(looper);
        weakReferenceProgress = new WeakReference<>(progress);
    }

    @Override
    public void handleMessage(Message msg) {
        Bundle data = msg.getData();
        int progress = data.getInt("progress");
        long networkSpeed = data.getLong("networkSpeed");
        boolean downloadFinish = data.getBoolean("downloadFinish");

        RequestDownloadProgress downloadProgress=  weakReferenceProgress.get();

        if(downloadProgress!=null){
            downloadProgress.downloadProgress(progress,networkSpeed,downloadFinish);
        }
        super.handleMessage(msg);
    }
}
