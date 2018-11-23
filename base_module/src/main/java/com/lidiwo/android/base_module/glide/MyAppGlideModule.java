package com.lidiwo.android.base_module.glide;

import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2018/11/22 19:01
 * @Company：智能程序员
 * @Description： *****************************************************
 */
@GlideModule(glideName = "ImageLoader")
public class MyAppGlideModule extends AppGlideModule {

//    @Override
//    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
//        registry.prepend(String.class, ByteBuffer.class, new Base64ModelLoaderFactory());
//    }
}
