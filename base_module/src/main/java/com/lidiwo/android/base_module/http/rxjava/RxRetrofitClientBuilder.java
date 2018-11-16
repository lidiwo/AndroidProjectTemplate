package com.lidiwo.android.base_module.http.rxjava;

import com.lidiwo.android.base_module.http.callback.RequestDownloadProgress;
import com.lidiwo.android.base_module.http.callback.RequestResult;
import com.lidiwo.android.base_module.http.callback.RequestUploadProgress;
import java.io.File;
import java.util.HashMap;
import io.reactivex.Observable;
import okhttp3.RequestBody;


/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2018/10/22 18:14
 * @Company：智能程序员
 * @Description： *****************************************************
 */
public class RxRetrofitClientBuilder {
    private String mUrl;
    private HashMap<String, Object> mParams;

    private RequestUploadProgress mUploadProgress;
    private RequestDownloadProgress mDownloadProgress;
    private RequestBody mBody;

    //上传的文件
    private File mFile;

    //下载目录
    private String mDownloadDir;

    //下载保存的文件名称
    private String mFileName;

    //下载文件扩展名
    private String mExtensionName;

    public RxRetrofitClientBuilder setUrl(String url) {
        this.mUrl = url;
        return this;
    }

    public RxRetrofitClientBuilder setParams(HashMap<String, Object> params) {
        this.mParams = params;
        return this;
    }

    public RxRetrofitClientBuilder setRequestUploadProgress(RequestUploadProgress progress) {
        this.mUploadProgress = progress;
        return this;
    }

    public RxRetrofitClientBuilder setRequestDownloadProgress(RequestDownloadProgress progress) {
        this.mDownloadProgress = progress;
        return this;
    }

    public RxRetrofitClientBuilder setUploadFile(File uploadFile) {
        this.mFile = uploadFile;
        return this;
    }

    public RxRetrofitClientBuilder setUploadFile(String uploadFile) {
        this.mFile = new File(uploadFile);
        return this;
    }

    public RxRetrofitClientBuilder setRequestBody(RequestBody body) {
        this.mBody = body;
        return this;
    }

    public RxRetrofitClientBuilder setDownloadDir(String downloadDir) {
        this.mDownloadDir = downloadDir;
        return this;
    }

    public RxRetrofitClientBuilder setFileName(String fileName) {
        this.mFileName = fileName;
        return this;
    }

    public RxRetrofitClientBuilder setExtensionName(String extensionName) {
        this.mExtensionName = extensionName;
        return this;
    }

    public RxRetrofitClient build() {
        return new RxRetrofitClient(mUrl, mParams, mUploadProgress, mDownloadProgress, mFile, mBody, mDownloadDir, mFileName, mExtensionName);
    }

    public Observable<String> get(String url, HashMap<String, Object> params) {
        this.mUrl = url;
        this.mParams = params;
        RxRetrofitClient client = build();
        return client.get();
    }

    public Observable<String> post(String url, HashMap<String, Object> params) {
        this.mUrl = url;
        this.mParams = params;
        RxRetrofitClient client = build();
        return client.post();
    }

    public Observable<String> postRaw(String url, RequestBody body) {
        this.mUrl = url;
        this.mBody = body;
        RxRetrofitClient client = build();
        return client.postRaw();
    }

    public Observable<String> upload(String url, File uploadFile, RequestUploadProgress progress) {
        this.mUrl = url;
        this.mFile = uploadFile;
        this.mUploadProgress = progress;

        RxRetrofitClient client = build();
        return client.upload();
    }

    public void download(String url, HashMap<String, Object> params, String downloadDir, String fileName, String extensionName, RequestDownloadProgress progress,RequestResult result) {
        this.mUrl = url;
        this.mParams = params;
        this.mDownloadDir = downloadDir;
        this.mFileName = fileName;
        this.mExtensionName = extensionName;
        this.mDownloadProgress = progress;

        RxRetrofitClient client = build();
        client.download(result);
    }
}
