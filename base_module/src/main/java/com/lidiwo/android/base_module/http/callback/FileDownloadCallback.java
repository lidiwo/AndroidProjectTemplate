package com.lidiwo.android.base_module.http.callback;

import android.os.AsyncTask;
import com.lidiwo.android.base_module.http.download.SaveFileAsyncTask;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2018/10/22 19:55
 * @Company：智能程序员
 * @Description： *****************************************************
 */
public class FileDownloadCallback implements Callback<ResponseBody> {

    private final RequestObserver REQUEST;
    private final RequestResult RESULT;
    private final RequestSuccess SUCCESS;
    private final RequestFailure FAILURE;
    private final RequestError ERROR;

    private final RequestDownloadProgress DOWNLOAD_PROGRESS;

    //下载目录
    private final String DOWNLOAD_DIR;

    //下载保存的文件名称
    private final String FILE_NAME;

    //下载文件扩展名
    private final String EXTENSION_NAME;

    public FileDownloadCallback(RequestObserver REQUEST, RequestResult RESULT, RequestSuccess SUCCESS, RequestFailure FAILURE, RequestError ERROR, RequestDownloadProgress DOWNLOAD_PROGRESS, String DOWNLOAD_DIR, String FILE_NAME, String EXTENSION_NAME) {
        this.REQUEST = REQUEST;
        this.RESULT = RESULT;
        this.SUCCESS = SUCCESS;
        this.FAILURE = FAILURE;
        this.ERROR = ERROR;
        this.DOWNLOAD_PROGRESS = DOWNLOAD_PROGRESS;
        this.DOWNLOAD_DIR = DOWNLOAD_DIR;
        this.FILE_NAME = FILE_NAME;
        this.EXTENSION_NAME = EXTENSION_NAME;
    }

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        if (response.isSuccessful()) {
            if (call.isExecuted()) {

                //开启异步任务保存
                SaveFileAsyncTask task = new SaveFileAsyncTask(REQUEST,RESULT,SUCCESS,ERROR,DOWNLOAD_PROGRESS);
                task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,response.body(),DOWNLOAD_DIR,FILE_NAME,EXTENSION_NAME);

                //下载完成
                if (task.isCancelled()) {
                    if (REQUEST != null) {
                        REQUEST.onStop();
                    }

                    if (RESULT != null) {
                        RESULT.onStop();
                    }
                }
            }
        } else {
            if (ERROR != null) {
                ERROR.onError(response.code(), response.message());
            }

            if (RESULT != null) {
                RESULT.onError(response.code(), response.message());
            }

            if (REQUEST != null) {
                REQUEST.onStop();
            }

            if (RESULT != null) {
                RESULT.onStop();
            }
        }

    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        if (FAILURE != null) {
            FAILURE.onFailure();
        }

        if (RESULT != null) {
            RESULT.onFailure();
        }

        if (REQUEST != null) {
            REQUEST.onStop();
        }

        if (RESULT != null) {
            RESULT.onStop();
        }
    }
}
