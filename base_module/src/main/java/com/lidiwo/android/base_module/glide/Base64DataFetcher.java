package com.lidiwo.android.base_module.glide;

import android.support.annotation.NonNull;
import android.util.Base64;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.data.DataFetcher;

import java.nio.ByteBuffer;

/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2018/11/23 11:34
 * @Company：智能程序员
 * @Description： *****************************************************
 */
public class Base64DataFetcher implements DataFetcher<ByteBuffer> {

    private String model;

    public Base64DataFetcher(String model){
        this.model=model;
    }

    /**
     *
     * @param priority 如果你正在使用网络库或其他队列系统，它可以用于含有优先级的请求
     * @param callback 你需要使用你解码出来的数据来调用它，如果因为任何原因解码失败，你也可以使用错误消息来调用
     */
    @Override
    public void loadData(@NonNull Priority priority, @NonNull DataCallback<? super ByteBuffer> callback) {
        int startOfBase64Section = model.indexOf(',');
        String base64Section=model.substring(startOfBase64Section + 1);
        byte[] data = Base64.decode(base64Section, Base64.DEFAULT);
        ByteBuffer byteBuffer = ByteBuffer.wrap(data);
        callback.onDataReady(byteBuffer);
    }

    /**
     * 如果你正在加载一个 InputStream 或打开任何 I/O 类的资源，你肯定要在 cleanup() 方法中关闭并清理这些 InputStream 或资源。
     *
     * 然而，在我们这个场景下我们仅仅只是要解码一个内存中的模型到内存中的数据。因此，这里没有东西需要清理，所以我们的方法也可以留空
     */
    @Override
    public void cleanup() {

    }

    /**
     * 对于可以取消的网络连接库或长时间加载，实现 cancel() 方法是一个好主意。这将帮助加速其他队列里的加载，并节约一些 CPU ，内存或其他资源。
     * 在我们这个场景中， Base64 没有提供取消的 API ，所以我们可以把这里留空
     */
    @Override
    public void cancel() {

    }

    @NonNull
    @Override
    public Class<ByteBuffer> getDataClass() {
        return ByteBuffer.class;
    }


    /**
     * Glide 对本地图片和远程图片的默认缓存策略是不同的。Glide 假定获取本地图片是简单廉价的，因此我们默认在它们被下采样和变换之后才缓存它们。
     * 相反，Glide 假定获取远程图片是困难而且昂贵的，因此我们将默认缓存获取到的原始数据。
     * 对于 Base64 String，你的应用最好的选择可能取决于你如何获取到这些 String。如果它们是从一个本地数据库取得的，则 DataSource.LOCAL 最有意义。
     * 如果你每次通过 HTTP 取回它们，则 DataSource.REMOTE 比较合适。
     * @return
     */
    @NonNull
    @Override
    public DataSource getDataSource() {
        return DataSource.LOCAL;
    }
}
