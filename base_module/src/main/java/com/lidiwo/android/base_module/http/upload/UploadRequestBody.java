package com.lidiwo.android.base_module.http.upload;

import android.os.Bundle;
import android.os.Looper;
import android.os.Message;

import com.lidiwo.android.base_module.http.callback.RequestUploadProgress;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2018/10/23 10:03
 * @Company：智能程序员
 * @Description： *****************************************************
 */
public class UploadRequestBody extends RequestBody {
    private RequestBody mBody;
    private RequestUploadProgress mProgress;
    private BufferedSink mBufferedSink;
    private long startTime;
    private UploadHandler mUploadHandler;

    public static UploadRequestBody create(RequestBody body, RequestUploadProgress progress) {
        return new UploadRequestBody(body, progress);
    }

    private UploadRequestBody(RequestBody body, RequestUploadProgress progress) {
        this.mBody = body;
        this.mProgress = progress;
        this.mUploadHandler = new UploadHandler(progress, Looper.getMainLooper());
    }


    @Override
    public MediaType contentType() {
        return mBody.contentType();
    }

    @Override
    public long contentLength() throws IOException {
        if (mBody != null) {
            return mBody.contentLength();
        }
        return super.contentLength();
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        startTime = System.currentTimeMillis();
        if (mBufferedSink == null) {
            MyBufferedSink myBufferedSink = new MyBufferedSink(sink);
            mBufferedSink = Okio.buffer(myBufferedSink);
        }

        mBody.writeTo(mBufferedSink);
        mBufferedSink.flush();
    }

    private final class MyBufferedSink extends ForwardingSink {

        //当前写入字节数
        private long bytesWritten = 0L;
        //总字节长度，避免多次调用contentLength()方法
        private long contentLength = 0L;

        private MyBufferedSink(Sink delegate) {
            super(delegate);
        }

        @Override
        public void write(Buffer source, long byteCount) throws IOException {
            super.write(source, byteCount);
            //监听上传进度
            if (contentLength == 0) {
                //获得contentLength的值，后续不再调用
                contentLength = contentLength();
            }
            //增加当前写入的字节数
            bytesWritten += byteCount;
            //回调
            if (mProgress != null) {
                //计算速度
                long totalTime = (System.currentTimeMillis() - startTime) / 1000;
                if (totalTime == 0) {
                    totalTime += 1;
                }
                long networkSpeed = bytesWritten / totalTime;
                int progress = (int) (bytesWritten * 100 / contentLength);
                boolean uploadFinish = bytesWritten == contentLength;
                // mProgress.uploadProgress(progress, networkSpeed, uploadFinish);
                setUIMessage(progress, networkSpeed, uploadFinish);
            }
        }
    }


    private void setUIMessage(int progress, long networkSpeed, boolean downloadFinish) {
        if (mUploadHandler != null) {
            Message message = Message.obtain();

            Bundle data = new Bundle();
            data.putInt("progress", progress);
            data.putLong("networkSpeed", networkSpeed);
            data.putBoolean("downloadFinish", downloadFinish);

            // 把数据保存到Message对象中
            message.setData(data);
            // 使用Handler对象发送消息
            mUploadHandler.sendMessage(message);
        }
    }

}
