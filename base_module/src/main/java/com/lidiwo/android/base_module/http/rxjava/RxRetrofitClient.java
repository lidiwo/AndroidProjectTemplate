package com.lidiwo.android.base_module.http.rxjava;

import android.os.AsyncTask;

import com.lidiwo.android.base_module.http.HttpMethod;
import com.lidiwo.android.base_module.http.RetrofitManager;
import com.lidiwo.android.base_module.http.callback.RequestDownloadProgress;
import com.lidiwo.android.base_module.http.callback.RequestResult;
import com.lidiwo.android.base_module.http.callback.RequestUploadProgress;
import com.lidiwo.android.base_module.http.download.SaveFileAsyncTask;
import com.lidiwo.android.base_module.http.upload.UploadRequestBody;

import java.io.File;
import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;


/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2018/10/22 18:13
 * @Company：智能程序员
 * @Description： *****************************************************
 */
public class RxRetrofitClient {
    private final String URL;
    private final HashMap<String, Object> PARAMS;

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


    public RxRetrofitClient(String url, HashMap<String, Object> params, RequestUploadProgress uploadProgress, RequestDownloadProgress downloadProgress, File file, RequestBody body, String downloadDir, String fileName, String extensionName) {
        this.URL = url;
        this.PARAMS = params == null ? new HashMap<String, Object>() : params;
        this.UPLOAD_PROGRESS = uploadProgress;
        this.DOWNLOAD_PROGRESS = downloadProgress;
        this.FILE = file;
        this.BODY = body;
        this.DOWNLOAD_DIR = downloadDir;
        this.FILE_NAME = fileName;
        this.EXTENSION_NAME = extensionName;
    }

    public static RxRetrofitClientBuilder create() {
        return new RxRetrofitClientBuilder();
    }

    private Observable<String> request(HttpMethod method) {
        RxHttpService service = RetrofitManager.getRxService();
        Observable<String> call = null;
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
                call = service.postRaw(URL, BODY);
                break;
        }

        return call;
    }

    public Observable<String> get() {
        return request(HttpMethod.GET);
    }

    public Observable<String> post() {
        return request(HttpMethod.POST);
    }

    public Observable<String> postRaw() {
        return request(HttpMethod.PUT_RAW);
    }

    public Observable<String> upload() {
        return request(HttpMethod.UPLOAD);
    }

    public void download(final RequestResult result) {
       RetrofitManager.getRxDownloadService(DOWNLOAD_PROGRESS).download(URL, PARAMS).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ResponseBody>() {
           @Override
           public void onSubscribe(Disposable d) {
               if(result!=null){
                   result.onStart();
               }

           }

           @Override
           public void onNext(ResponseBody responseBody) {

               //开启异步任务保存
               SaveFileAsyncTask task = new SaveFileAsyncTask(null,result,null,null,DOWNLOAD_PROGRESS);
               task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,responseBody,DOWNLOAD_DIR,FILE_NAME,EXTENSION_NAME);

               //下载完成
               if (task.isCancelled()) {
                   if (result != null) {
                       result.onStop();
                   }
               }

           }

           @Override
           public void onError(Throwable e) {
               if(result!=null){
                   result.onFailure();
               }
           }

           @Override
           public void onComplete() {
               if(result!=null){
                   result.onStop();
               }
           }
       });
    }
}
