package com.lidiwo.android.base_module.http;

import com.lidiwo.android.base_module.http.callback.RequestDownloadProgress;
import com.lidiwo.android.base_module.http.callback.RequestError;
import com.lidiwo.android.base_module.http.callback.RequestFailure;
import com.lidiwo.android.base_module.http.callback.RequestObserver;
import com.lidiwo.android.base_module.http.callback.RequestResult;
import com.lidiwo.android.base_module.http.callback.RequestSuccess;
import com.lidiwo.android.base_module.http.callback.RequestUploadProgress;

import java.io.File;
import java.util.HashMap;

import okhttp3.RequestBody;

/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2018/10/22 18:14
 * @Company：智能程序员
 * @Description： *****************************************************
 */
public class RetrofitClientBuilder {
    private String mUrl;
    private HashMap<String, Object> mParams;

    private RequestObserver mRequest;
    private RequestResult mResult;
    private RequestSuccess mSuccess;
    private RequestFailure mFailure;
    private RequestError mError;
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


    public RetrofitClientBuilder setUrl(String url) {
        this.mUrl = url;
        return this;
    }

    public RetrofitClientBuilder setParams(HashMap<String, Object> params) {
        this.mParams = params;
        return this;
    }

    public RetrofitClientBuilder setRequestObserver(RequestObserver request) {
        this.mRequest = request;
        return this;
    }

    public RetrofitClientBuilder setRequestSuccess(RequestSuccess success) {
        this.mSuccess = success;
        return this;
    }

    public RetrofitClientBuilder setRequestFailure(RequestFailure failure) {
        this.mFailure = failure;
        return this;
    }

    public RetrofitClientBuilder setRequestError(RequestError error) {
        this.mError = error;
        return this;
    }

    public RetrofitClientBuilder setRequestUploadProgress(RequestUploadProgress progress) {
        this.mUploadProgress = progress;
        return this;
    }

    public RetrofitClientBuilder setRequestDownloadProgress(RequestDownloadProgress progress) {
        this.mDownloadProgress = progress;
        return this;
    }

    public RetrofitClientBuilder setUploadFile(File uploadFile) {
        this.mFile = uploadFile;
        return this;
    }

    public RetrofitClientBuilder setUploadFile(String uploadFile) {
        this.mFile = new File(uploadFile);
        return this;
    }

    public RetrofitClientBuilder setRequestBody(RequestBody body) {
        this.mBody = body;
        return this;
    }


    public RetrofitClientBuilder setDownloadDir(String downloadDir) {
        this.mDownloadDir = downloadDir;
        return this;
    }

    public RetrofitClientBuilder setFileName(String fileName) {
        this.mFileName = fileName;
        return this;
    }

    public RetrofitClientBuilder setExtensionName(String extensionName) {
        this.mExtensionName = extensionName;
        return this;
    }


    public RetrofitClient build() {
        return new RetrofitClient(mUrl, mParams, mRequest, mResult, mSuccess, mFailure, mError, mUploadProgress, mDownloadProgress, mFile, mBody,mDownloadDir,mFileName,mExtensionName);
    }

    public void get(String url, HashMap<String, Object> params, RequestResult result) {
        this.mUrl = url;
        this.mParams = params;
        this.mResult = result;
        RetrofitClient client = build();
        client.get();
    }

    public void post(String url, HashMap<String, Object> params, RequestResult result) {
        this.mUrl = url;
        this.mParams = params;
        this.mResult = result;
        RetrofitClient client = build();
        client.post();
    }

    public void postRaw(String url, RequestBody body, RequestResult result) {
        this.mUrl = url;
        this.mBody = body;
        this.mResult = result;
        RetrofitClient client = build();
        client.postRaw();
    }

    public void upload(String url, File uploadFile, RequestUploadProgress progress, RequestResult result) {
        this.mUrl = url;
        this.mFile = uploadFile;
        this.mUploadProgress = progress;
        this.mResult = result;
        RetrofitClient client = build();
        client.upload();
    }

    public void download(String url, HashMap<String, Object> params, String downloadDir,String fileName,String extensionName, RequestDownloadProgress progress, RequestResult result) {
        this.mUrl = url;
        this.mParams = params;
        this.mDownloadDir=downloadDir;
        this.mFileName=fileName;
        this.mExtensionName=extensionName;
        this.mDownloadProgress = progress;
        this.mResult = result;
        RetrofitClient client = build();
        client.download();
    }

}
