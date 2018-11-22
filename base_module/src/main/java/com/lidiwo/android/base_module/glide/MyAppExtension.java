package com.lidiwo.android.base_module.glide;

import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.annotation.GlideExtension;
import com.bumptech.glide.annotation.GlideOption;
import com.bumptech.glide.annotation.GlideType;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestOptions;

/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2018/11/22 19:23
 * @Company：智能程序员
 * @Description： *****************************************************
 */
@SuppressWarnings("all")
@GlideExtension
public final class MyAppExtension {

    private static final RequestOptions DECODE_TYPE_GIF = RequestOptions.decodeTypeOf(GifDrawable.class).lock();

    private MyAppExtension() {
    }

    /**
     * @param options
     * @GlideOption 注解的静态方法用于扩展 RequestOptions,要保证第一个参数为 RequestOptions,且返回值为空
     */
    @GlideOption
    public static void miniThumb(RequestOptions options) {
        options.fitCenter();
    }

    /**
     * @param requestBuilder
     * @GlideType 注解的静态方法用于扩展 RequestManager,必须使用 RequestBuilder<T> 作为其第一个参数，这里的泛型 <T> 对应 @GlideType 注解中传入的类。该方法应为静态方法，且返回值为空。方法必须定义在一个被 @GlideExtension 注解标记的类中。
     */
    @GlideType(GifDrawable.class)
    public static void gif(RequestBuilder<GifDrawable> requestBuilder) {
        requestBuilder
                .transition(new DrawableTransitionOptions())
                .apply(DECODE_TYPE_GIF);
    }

}
