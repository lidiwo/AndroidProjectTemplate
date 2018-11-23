package com.lidiwo.android.base_module.glide;

import android.support.annotation.NonNull;

import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.MultiModelLoaderFactory;

import java.nio.ByteBuffer;

/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2018/11/23 13:49
 * @Company：智能程序员
 * @Description： *****************************************************
 */
public class Base64ModelLoaderFactory implements ModelLoaderFactory<String,ByteBuffer>{
    @NonNull
    @Override
    public ModelLoader<String, ByteBuffer> build(@NonNull MultiModelLoaderFactory multiFactory) {
        return new Base64ModelLoader();
    }

    @Override
    public void teardown() {

    }
}
