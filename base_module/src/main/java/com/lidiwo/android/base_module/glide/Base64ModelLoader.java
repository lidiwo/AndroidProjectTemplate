package com.lidiwo.android.base_module.glide;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.signature.ObjectKey;

import java.nio.ByteBuffer;

/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2018/11/23 11:17
 * @Company：智能程序员
 * @Description： *****************************************************
 */
public final class Base64ModelLoader implements ModelLoader<String, ByteBuffer> {

    private static final String DATA_URI_PREFIX = "data:image";
    private static final String DATA_URI_CONTAIN = "base64";

    @Nullable
    @Override
    public LoadData<ByteBuffer> buildLoadData(@NonNull String model, int width, int height, @NonNull Options options) {
        return new LoadData<>(new ObjectKey(model), new Base64DataFetcher(model));
    }

    /**
     * 检查传入的字符串是否是Base64的图片信息
     *
     * @param model
     * @return
     */
    @Override
    public boolean handles(@NonNull String model) {
        return model.startsWith(DATA_URI_PREFIX) && model.contains(DATA_URI_CONTAIN);
    }
}
