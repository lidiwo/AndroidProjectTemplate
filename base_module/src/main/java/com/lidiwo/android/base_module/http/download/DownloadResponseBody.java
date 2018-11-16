package com.lidiwo.android.base_module.http.download;

import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import com.lidiwo.android.base_module.http.callback.RequestDownloadProgress;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2018/10/23 17:00
 * @Company：智能程序员
 * @Description：
 * https://github.com/square/okhttp/blob/master/samples/guide/src/main/java/okhttp3/recipes/Progress.java
 * *****************************************************
 */
public class DownloadResponseBody extends ResponseBody {

    private ResponseBody mBody;
    private RequestDownloadProgress mProgress;
    private BufferedSource bufferedSource;
    private long startTime;
    private DownloadHandler mDownloadHandler;

    private boolean flag=true;


    public static DownloadResponseBody create(ResponseBody body, RequestDownloadProgress progress) {
        return new DownloadResponseBody(body, progress);
    }

    private DownloadResponseBody(ResponseBody body, RequestDownloadProgress progress) {
        this.mBody = body;
        this.mProgress = progress;
        this.mDownloadHandler = new DownloadHandler(progress,Looper.getMainLooper());
    }

    @Override
    public MediaType contentType() {
        return mBody.contentType();
    }

    @Override
    public long contentLength() {
        return mBody.contentLength();
    }

    @Override
    public BufferedSource source() {
        startTime = System.currentTimeMillis();
        if (bufferedSource == null) {
            MyBufferedSource myBufferedSource = new MyBufferedSource(mBody.source());
            bufferedSource = Okio.buffer(myBufferedSource);
        }
        return bufferedSource;
    }


    private final class MyBufferedSource extends ForwardingSource {


        //当前已经下载字节数
        private long totalBytesRead = 0L;
        //总字节长度，避免多次调用contentLength()方法
        private long contentLength = 0L;

        private MyBufferedSource(Source delegate) {
            super(delegate);
        }

        @Override
        public long read(Buffer sink, long byteCount) throws IOException {
            long bytesRead = super.read(sink, byteCount);

            if (contentLength == 0) {
                //获得contentLength的值，后续不再调用
                contentLength = contentLength();
            }

            // read() returns the number of bytes read, or -1 if this source is exhausted.
            totalBytesRead += bytesRead != -1 ? bytesRead : 0;

            if (mProgress != null) {

                if (contentLength == -1) {
                    setUIMessage(-1, 0, false);
                   // mProgress.downloadProgress(-1, 0, false);
                } else {
                    //计算速度
                    long totalTime = (System.currentTimeMillis() - startTime) / 1000;
                    if (totalTime == 0) {
                        totalTime += 1;
                    }
                    long networkSpeed = totalBytesRead / totalTime;
                    int progress = (int) (100 * totalBytesRead / contentLength);
                    boolean downloadFinish = bytesRead == -1;

                    if(flag){
                        flag=!downloadFinish;
                        setUIMessage(progress, networkSpeed, downloadFinish);
                    }

                   // mProgress.downloadProgress(progress, networkSpeed, downloadFinish);
                }
            }
            return bytesRead;
        }
    }

    private void setUIMessage(int progress, long networkSpeed, boolean downloadFinish) {
        if (mDownloadHandler != null) {
            Message message = Message.obtain();

            Bundle data = new Bundle();
            data.putInt("progress", progress);
            data.putLong("networkSpeed", networkSpeed);
            data.putBoolean("downloadFinish",downloadFinish);

            // 把数据保存到Message对象中
            message.setData(data);
            // 使用Handler对象发送消息
            mDownloadHandler.sendMessage(message);
        }
    }


}
