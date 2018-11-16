package com.lidiwo.android.base_module.http.download;

import android.os.AsyncTask;

import com.lidiwo.android.base_module.http.callback.RequestDownloadProgress;
import com.lidiwo.android.base_module.http.callback.RequestError;
import com.lidiwo.android.base_module.http.callback.RequestObserver;
import com.lidiwo.android.base_module.http.callback.RequestResult;
import com.lidiwo.android.base_module.http.callback.RequestSuccess;
import com.lidiwo.android.base_module.http.util.FileUtil;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.ResponseBody;

/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2018/10/23 14:46
 * @Company：智能程序员
 * @Description：
 * 类中参数为3种泛型类型
 * 整体作用：控制AsyncTask子类执行线程任务时各个阶段的返回类型
 * 具体说明：
 * a. Params：开始异步任务执行时传入的参数类型，对应excute（）中传递的参数
 * b. Progress：异步任务执行过程中，返回下载进度值的类型
 * c. Result：异步任务执行完成后，返回的结果类型，与doInBackground()的返回值类型保持一致
 * 注：
 * a. 使用时并不是所有类型都被使用
 * b. 若无被使用，可用java.lang.Void类型代替
 * c. 若有不同业务，需额外再写1个AsyncTask的子类
 * ****************************************************
 */
public class SaveFileAsyncTask extends AsyncTask<Object, Long, File> {
    private final RequestObserver REQUEST;
    private final RequestResult RESULT;
    private final RequestSuccess SUCCESS;
    private final RequestError ERROR;
    private final RequestDownloadProgress DOWNLOAD_PROGRESS;

    public SaveFileAsyncTask(RequestObserver REQUEST, RequestResult RESULT, RequestSuccess SUCCESS, RequestError ERROR, RequestDownloadProgress DOWNLOAD_PROGRESS) {
        this.REQUEST = REQUEST;
        this.RESULT = RESULT;
        this.SUCCESS = SUCCESS;
        this.ERROR = ERROR;
        this.DOWNLOAD_PROGRESS = DOWNLOAD_PROGRESS;
    }

    //执行后台耗时操作前被调用,通常用于进行初始化操作.
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    //异步执行后台线程要完成的任务,耗时操作将在此方法中完成.
    @Override
    protected File doInBackground(Object... objects) {
        ResponseBody body = (ResponseBody) objects[0];
        if (body != null) {
            String downloadDir = FileUtil.getDownloadDir(String.valueOf(objects[1]));
            String fileName = FileUtil.getFileName(String.valueOf(objects[2]));
            String extensionName = FileUtil.getExtensionName(String.valueOf(objects[3]));

            InputStream inputStream = body.byteStream();
            long contentLength = body.contentLength();
            File file = writeDataToDisk(inputStream, contentLength, downloadDir, fileName, extensionName);

            return file;
        } else {
            return null;
        }
    }

    //当在doInBackground方法中调用publishProgress方法更新任务执行进度后,将调用此方法.通过此方法我们可以知晓任务的完成进度.
    @Override
    protected void onProgressUpdate(Long... values) {
        super.onProgressUpdate(values);
        if (DOWNLOAD_PROGRESS != null && values != null && values.length >= 2) {

            long writeLength = values[0];
            long contentLength = values[1];

            int progress = (int) (100 * writeLength / contentLength);
            boolean saveFinish = writeLength == contentLength;
            DOWNLOAD_PROGRESS.saveProgress(progress, saveFinish);

        }
    }

    //当doInBackground方法完成后,系统将自动调用此方法,并将doInBackground方法返回的值传入此方法.通过此方法进行UI的更新
    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if (file != null && file.exists()) {
            if (SUCCESS != null) {
                SUCCESS.onSuccess(file.getAbsolutePath());
            }

            if (RESULT != null) {
                RESULT.onSuccess(file.getAbsolutePath());
            }
        } else {
            if (ERROR != null) {
                ERROR.onError(0, "文件保存失败");
            }
            if (RESULT != null) {
                RESULT.onError(0, "文件保存失败");
            }
        }

        if (REQUEST != null) {
            REQUEST.onStop();
        }

        if (RESULT != null) {
            RESULT.onStop();
        }
    }

    public File writeDataToDisk(InputStream is, long contentLength, String downloadDir, String fileName, String extensionName) {
        File file = FileUtil.createFile(downloadDir, fileName, extensionName);
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(is);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);

            byte data[] = new byte[1024 * 4];

            long writeLength = 0;
            int count;
            while ((count = bis.read(data)) != -1) {
                writeLength += count;

                bos.write(data, 0, count);
                if (DOWNLOAD_PROGRESS != null) {
                    publishProgress(writeLength, contentLength);
                }
            }

            bos.flush();
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bos != null) {
                    bos.close();
                }
                if (fos != null) {
                    fos.close();
                }
                if (bis != null) {
                    bis.close();
                }
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }
}
