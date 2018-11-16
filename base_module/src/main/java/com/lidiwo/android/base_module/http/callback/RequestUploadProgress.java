package com.lidiwo.android.base_module.http.callback;

/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2018/10/23 10:12
 * @Company：智能程序员
 * @Description： *****************************************************
 */
public interface RequestUploadProgress {
    /**
     * 上传进度
     * @param progress 当前进度
     * @param networkSpeed 网速
     * @param uploadFinish 是否上传完成
     */
    void uploadProgress(int progress, long networkSpeed, boolean uploadFinish);
}
