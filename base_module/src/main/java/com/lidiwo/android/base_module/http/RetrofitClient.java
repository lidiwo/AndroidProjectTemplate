package com.lidiwo.android.base_module.http;

import com.lidiwo.android.base_module.http.callback.FileDownloadCallback;
import com.lidiwo.android.base_module.http.callback.RequestCallback;
import com.lidiwo.android.base_module.http.callback.RequestDownloadProgress;
import com.lidiwo.android.base_module.http.callback.RequestError;
import com.lidiwo.android.base_module.http.callback.RequestFailure;
import com.lidiwo.android.base_module.http.callback.RequestObserver;
import com.lidiwo.android.base_module.http.callback.RequestResult;
import com.lidiwo.android.base_module.http.callback.RequestSuccess;
import com.lidiwo.android.base_module.http.callback.RequestUploadProgress;
import com.lidiwo.android.base_module.http.upload.UploadRequestBody;
import java.io.File;
import java.util.HashMap;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2018/10/22 18:13
 * @Company：智能程序员
 * @Description： *****************************************************
 */
public class RetrofitClient {
    private final String URL;
    private final HashMap<String, Object> PARAMS;

    private final RequestObserver REQUEST;
    private final RequestResult RESULT;
    private final RequestSuccess SUCCESS;
    private final RequestFailure FAILURE;
    private final RequestError ERROR;
    private final RequestUploadProgress UPLOAD_PROGRESS;
    private final RequestDownloadProgress DOWNLOAD_PROGRESS;
    private final File FILE;
    private final RequestBody BODY;

    //下载目录
    private final String DOWNLOAD_DIR;

    //下载保存的文件名称
    private final String FILE_NAME;

    //下载文件扩展名
    private final String EXTENSION_NAME;


    public RetrofitClient(String url, HashMap<String, Object> params, RequestObserver request, RequestResult result, RequestSuccess success, RequestFailure failure, RequestError error, RequestUploadProgress uploadProgress,RequestDownloadProgress downloadProgress, File file,RequestBody body,String downloadDir,String fileName,String extensionName) {
        this.URL = url;
        this.PARAMS = params==null?new HashMap<String, Object>():params;
        this.REQUEST = request;
        this.RESULT = result;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.UPLOAD_PROGRESS = uploadProgress;
        this.DOWNLOAD_PROGRESS = downloadProgress;
        this.FILE = file;
        this.BODY=body;
        this.DOWNLOAD_DIR=downloadDir;
        this.FILE_NAME=fileName;
        this.EXTENSION_NAME=extensionName;
    }

    public static RetrofitClientBuilder create() {
        return new RetrofitClientBuilder();
    }

    private void request(HttpMethod method) {
        HttpService service = RetrofitManager.getService();
        if (REQUEST != null) {
            REQUEST.onStart();
        }

        if (RESULT != null) {
            RESULT.onStart();
        }

        Call<String> call = null;
        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody = RequestBody.create(MultipartBody.FORM, FILE);
                final UploadRequestBody uploadRequestBody = UploadRequestBody.create(requestBody, UPLOAD_PROGRESS);
                final MultipartBody.Part body = MultipartBody.Part.createFormData("file", FILE.getName(), uploadRequestBody);
                call = service.upload(URL, body);
                break;
            case PUT_RAW:
                call = service.postRaw(URL,BODY);
                break;
        }

        if (call != null) {
            call.enqueue(getCallback());
        } else {
            if (REQUEST != null) {
                REQUEST.onStop();
            }

            if (RESULT != null) {
                RESULT.onStop();
            }
        }
    }

    private RequestCallback getCallback() {
        return new RequestCallback(REQUEST, RESULT, SUCCESS, FAILURE, ERROR);
    }

    private FileDownloadCallback getFileDownloadCallback() {
        return new FileDownloadCallback(REQUEST, RESULT, SUCCESS, FAILURE, ERROR,DOWNLOAD_PROGRESS,DOWNLOAD_DIR,FILE_NAME,EXTENSION_NAME);
    }


    public void get() {
        request(HttpMethod.GET);
    }

    public void post() {
        request(HttpMethod.POST);
    }

    public void postRaw() {
        request(HttpMethod.PUT_RAW);
    }

    public void upload() {
        request(HttpMethod.UPLOAD);
    }

    public void download() {
        if (REQUEST != null) {
            REQUEST.onStart();
        }

        if (RESULT != null) {
            RESULT.onStart();
        }

        RetrofitManager.getDownloadService(DOWNLOAD_PROGRESS).download(URL,PARAMS).enqueue(getFileDownloadCallback());
    }
}
