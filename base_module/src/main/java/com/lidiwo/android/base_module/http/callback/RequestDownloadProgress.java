package com.lidiwo.android.base_module.http.callback;

/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2018/10/23 17:02
 * @Company：智能程序员
 * @Description： *****************************************************
 */
public interface RequestDownloadProgress {
    /**
     * @param progress       下载进度
     * @param networkSpeed   网速
     * @param downloadFinish 是否下载完成
     */
    void downloadProgress(int progress, long networkSpeed, boolean downloadFinish);

    /**
     * @param progress   保存进度
     * @param saveFinish 是否保存完成
     */
    void saveProgress(int progress, boolean saveFinish);
}
